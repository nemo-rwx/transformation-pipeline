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

variable "db_name" {
  description = "PostgreSQL database name"
  type        = string
  default     = "sftpupload"
}

variable "db_username" {
  description = "PostgreSQL application username"
  type        = string
  default     = "appuser"
}

variable "db_password" {
  description = "PostgreSQL application password"
  type        = string
  sensitive   = true
}