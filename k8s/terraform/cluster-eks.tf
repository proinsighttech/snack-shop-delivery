module "eks" {
  source  = "terraform-aws-modules/eks/aws"
  version = "~> 19.0"

  cluster_name    = "snackshop-cluster"
  cluster_version = "1.27"

  cluster_endpoint_public_access  = true

  cluster_addons = {
    coredns = {
      most_recent = true
    }
    kube-proxy = {
      most_recent = true
    }
    vpc-cni = {
      most_recent = true
    }
  }

  vpc_id                   = "vpc-04eb7105aacef31e6"
  subnet_ids               = ["subnet-0bf0d49f478314f7e", "subnet-053ae7ad3b57d4284", "subnet-0147275f46d88b06b"]
  control_plane_subnet_ids = ["subnet-0bf0d49f478314f7e", "subnet-053ae7ad3b57d4284", "subnet-0147275f46d88b06b"]

  # Self Managed Node Group(s)
  self_managed_node_group_defaults = {
    instance_type                          = "t2.small"
    update_launch_template_default_version = true
    iam_role_additional_policies = {
      AmazonSSMManagedInstanceCore = "arn:aws:iam::aws:policy/AmazonSSMManagedInstanceCore"
    }
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
            instance_type     = "t2.small"
            weighted_capacity = "1"
          },
          {
            instance_type     = "t2.small"
            weighted_capacity = "2"
          },
        ]
      }
    }
  }

  # EKS Managed Node Group(s)
  eks_managed_node_group_defaults = {
    instance_types = ["t2.small"]
  }

  eks_managed_node_groups = {
    blue = {}
    green = {
      min_size     = 1
      max_size     = 10
      desired_size = 1

      instance_types = ["t2.small"]
      capacity_type  = "SPOT"
    }
  }

  # aws-auth configmap
  manage_aws_auth_configmap = true

  aws_auth_roles = [
    {
      rolearn  = "arn:aws:iam::279044386247:role/role1"
      username = "role1"
      groups   = ["system:masters"]
    },
  ]

  aws_auth_users = [
    {
      userarn  = "arn:aws:iam::279044386247:user/lanchonete-api"
      username = "lanchonete-api"
      groups   = ["system:masters"]
    },
  ]

  aws_auth_accounts = [
    "279044386247",
  ]

  tags = {
    Environment = "dev"
    Terraform   = "true"
    Autor       = "ProInsightTech"
  }
}