provider "aws" {
 region = var.region
}

module "infra" {
    source                           = "./modules/infra"
    availability_zones               = var.availability_zones
    cluster_name                     = var.cluster_name
    cluster_version                  = var.cluster_version
    desired_size                     = var.desired_size
    max_size                         = var.max_size
    min_size                         = var.min_size
}

# module "integration" {
#     source                          = "./modules/integration"
#     vpc_id                          = var.vpc_id
#     subnet_ids                      = var.subnet_ids
#     load_balancer_name              = var.load_balancer_name
# }