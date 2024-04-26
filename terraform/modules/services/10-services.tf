resource "null_resource" "update_kubeconfig" {
  provisioner "local-exec" {
    command = "aws eks --region us-west-2 update-kubeconfig --name snackshop-cluster"
  }
}

provider "kubernetes" {
  config_path = "~/.kube/config"
}

resource "kubernetes_service" "snack_shop_mysql" {
  metadata {
    name = "snack-shop-mysql"
  }
  spec {
    selector = {
      app = "snack-shop-mysql"
    }
    port {
      port        = 3306
      target_port = 3306
    }
  }
}

resource "kubernetes_deployment" "snack_shop_mysql" {
  metadata {
    name = "snack-shop-mysql"
  }
  spec {
    replicas = 1
    selector {
      match_labels = {
        app = "snack-shop-mysql"
      }
    }
    template {
      metadata {
        labels = {
          app = "snack-shop-mysql"
        }
      }
      spec {
        container {
          image = "mysql:8.0"
          name  = "snack-shop-mysql"
          env {
            name  = "MYSQL_ROOT_PASSWORD"
            value = "12345678"
          }
          port {
            container_port = 3306
          }
        }
      }
    }
  }
}

resource "kubernetes_service" "snack_shop_api" {
  depends_on = [kubernetes_service.snack_shop_mysql]
  metadata {
    name = "snack-shop-api"
    annotations = {
      "service.beta.kubernetes.io/aws-load-balancer-type" = "nlb"
      "service.beta.kubernetes.io/aws-load-balancer-internal" = "true"
    }
  }
  spec {
    selector = {
      app = "snack-shop-api"
    }
    port {
      port        = 9000
      target_port = 9000
    }
    type = "LoadBalancer"
  }
}

resource "kubernetes_deployment" "snack_shop_api" {
  depends_on = [kubernetes_service.snack_shop_mysql]
  metadata {
    name = "snack-shop-api"
  }
  spec {
    replicas = 1
    selector {
      match_labels = {
        app = "snack-shop-api"
      }
    }
    template {
      metadata {
        labels = {
          app = "snack-shop-api"
        }
      }
      spec {
        container {
          image = "279044386247.dkr.ecr.us-west-2.amazonaws.com/snack-shop-delivery-internal-snack-shop-api:latest"
          name  = "snack-shop-api"
          port {
            container_port = 9000
          }
          env {
            name  = "SPRING_DATASOURCE_URL"
            value = "jdbc:mysql://snack-shop-mysql:3306/snackshop?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false"
          }
          env {
            name  = "SPRING_DATASOURCE_USERNAME"
            value = "root"
          }
          env {
            name  = "SPRING_DATASOURCE_PASSWORD"
            value = "12345678"
          }
          env {
            name  = "SERVER_SERVLET_CONTEXT_PATH"
            value = "/api"
          }
        }
      }
    }
  }
}