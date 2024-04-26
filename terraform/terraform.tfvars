region                              = "us-west-2"
environment                         = "dev"
author                              = "ProInsightTech"
profile                             = "default"

//IAM
account_id                          = "279044386247"
user_name                           = "lanchonete-api"
role_name                           = "lanchonete-api-role"

// EKS
cluster_name                        = "snackshop-cluster"
cluster_version                     = "1.28"
cluster_endpoint_public_access      = true
# eks_cluster_id                      = "snackshop-cluster"

// VPC
vpc_id                              = "vpc-03ff6e3c383770fe7"
subnet_ids                          = ["subnet-03a5221550ebcaa7a", "subnet-093d549666b80aeb6", "subnet-0ddb10d62fe5cc33a"]

// Node Group EC2
instance_type_1                     = "t3a.medium"
instance_type_2                     = "t3.medium"
instance_type_3                     = "t3a.large"
instance_capacity_type              = "SPOT"


// ECR Repository Name
repository_name                 = "snackshop-repository"
image_name                      = "snack-shop-delivery-internal"

// API Gateway
backend_url                     = "http://localhost:9000"

// Load Balancer
load_balancer_name              = "a2056a2348fef49b892f388af1bfe9da"

//AutoScaling
min_size                        = 0
max_size                        = 5
desired_size                    = 1