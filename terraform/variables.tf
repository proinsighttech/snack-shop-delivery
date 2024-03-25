// *************************************** //
// ******** Environment Variables ******** // 
// *************************************** //

//Region
variable "region" {
  description = "AWS Region"
  type        = string
  default     = "us-west-2"
}

//Environment
variable "environment" {
  description = "Environment"
  type = string
  default = "dev"
}

//Author
variable "author" {
  description = "Author"
  type        = string
  default     = "ProInsightTech"
}

// *************************************** //
// ********** Account Variables ********** //
// *************************************** //

//AWS Profile
variable "profile" {
  description = "AWS Profile"
  type = string
  default     = "default"
}

//AWS Account ID
variable "account_id" {
  description = "AWS Account ID"
  type        = string
}

//AWS User Name
variable "user_name" {
  description = "AWS User Name"
  type        = string
}

variable "role_name" {
  description = "AWS Role Name"
  type        = string
}


// *************************************** //
// ******** EKS Cluster Variables ******** // 
// *************************************** //

//AWS EKS Cluster Name
variable "cluster_name" {
  description = "EKS Cluster Name"
  type        = string
}

//AWS EKS Cluster Version
variable "cluster_version" {
  description = "EKS Cluster Version"
  type        = string
  default     = "1.27"
}

//AWS EKS Cluster Endpoint Public Access
variable "cluster_endpoint_public_access" {
  description = "EKS Cluster Endpoint Public Access"
  type        = bool
  default     = true
}

//AWS EKS Node Group Instance Type
variable "instance_type_1" {
  description = "EKS Node Group Instance Type 1"
  type        = string
  default     = "t3a.medium"
}

//AWS EKS Node Group Instance Type
variable "instance_type_2" {
  description = "EKS Node Group Instance Type 2"
  type        = string
  default     = "t3.medium"
}

//AWS EKS Node Group Instance Type
variable "instance_type_3" {
  description = "EKS Node Group Instance Type 3"
  type        = string
  default     = "t3a.large"
}


variable "instance_capacity_type" {
  description = "EKS Node Group Instance Capacity Type"
  type        = string
  default     = "SPOT"
}

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

//AWS ECR Repository Name
variable "repository_name" {
  description = "ECR Repository Name"
  type        = string
}

//AWS ECR Image Name
variable "image_name" {
  description = "ECR Image Name"
  type        = string
}