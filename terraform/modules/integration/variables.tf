// *************************************** //
// ******** VPC & Subnet Variables ******* //
// *************************************** //

//AWS VPC ID
variable "vpc_id" {
  description = "VPC ID"
  type        = string
}

//AWS Subnet IDs
variable "subnet_ids" {
  description = "Subnet IDs"
  type        = list(string)
}

// *************************************** //
// ******* Load Balancer Variables ******* //
// *************************************** //

variable "load_balancer_name" {
  description = "Id of the load balancer"
  type        = string
}