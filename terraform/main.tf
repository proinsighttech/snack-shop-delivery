provider "aws" {
  region = var.region
}

data "aws_eks_cluster_auth" "cluster" {
  name = module.eks.cluster_id
}

provider "kubernetes" {
  host                   = module.eks.cluster_endpoint
  cluster_ca_certificate = base64decode(module.eks.cluster_certificate_authority_data)
  token                  = data.aws_eks_cluster_auth.cluster.token
}

module "eks" {
  source                            = "terraform-aws-modules/eks/aws"
  version                           = "~> 18.0"
  cluster_name                      = var.cluster_name
  cluster_version                   = var.cluster_version
  cluster_endpoint_public_access    = var.cluster_endpoint_public_access

  cluster_addons = {
    coredns = {
      most_recent                 = true
    }
    kube-proxy = {
      most_recent                 = true
    }
    vpc-cni = {
      most_recent                 = true
    }
  }

  vpc_id                   = var.vpc_id
  subnet_ids               = var.subnet_ids
  control_plane_subnet_ids = var.subnet_ids

  # Self Managed Node Group(s)
  self_managed_node_group_defaults = {
    instance_type                          = var.instance_type_1
    update_launch_template_default_version = true
    iam_role_additional_policies = [
      "arn:aws:iam::aws:policy/AmazonSSMManagedInstanceCore"
    ]
  }

  self_managed_node_groups = {
    one = {
      name         = "mixed-1"
      max_size     = 3
      desired_size = 1

      use_mixed_instances_policy = true
      mixed_instances_policy = {
        instances_distribution = {
          on_demand_base_capacity                  = 0
          on_demand_percentage_above_base_capacity = 10
          spot_allocation_strategy                 = "capacity-optimized"
        }

        override = [
          {
            instance_type     = var.instance_type_1
            weighted_capacity = "1"
          },
          {
            instance_type     = var.instance_type_2
            weighted_capacity = "2"
          },
        ]
      }
    }
  }

  # EKS Managed Node Group(s)
  eks_managed_node_group_defaults = {
    instance_types = [var.instance_type_1, var.instance_type_3]
  }

  eks_managed_node_groups = {
    blue = {}
    green = {
      min_size     = 1
      max_size     = 10
      desired_size = 1

      instance_types = [var.instance_type_3]
      capacity_type  = var.instance_capacity_type
    }
  }

  # aws-auth configmap
  manage_aws_auth_configmap = true

  aws_auth_roles = [
    {
      rolearn  = "arn:aws:iam::${var.account_id}:role/${var.role_name}"
      username = var.role_name
      groups   = ["system:masters"]
    },
  ]

  aws_auth_users = [
    {
      userarn  = "arn:aws:iam::${var.account_id}:user/${var.user_name}"
      username = var.user_name
      groups   = ["system:masters"]
    },
  ]

  aws_auth_accounts = [
    var.account_id,
  ]

  tags = {
    Environment = var.environment
    Terraform   = "true"
    Autor       = var.author
  }
}