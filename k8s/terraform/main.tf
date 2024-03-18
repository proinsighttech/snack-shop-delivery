variable "region" {
  default     = "us-west-2"
  description = "US West (Oregon)"
}

provider "aws" {
  region = var.region
  profile = "default"
}