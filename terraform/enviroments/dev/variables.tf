variable "aws_region" {
  description = "AWS region for the development environment"
  type        = string
  default     = "us-east-1"
}

variable "bucket_name" {
  description = "S3 bucket name for customer files"
  type        = string
  default     = "customer-file-delivery-dev"
}