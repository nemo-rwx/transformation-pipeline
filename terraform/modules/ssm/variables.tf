variable "parameter_name" {
  description = "SSM parameter name"
  type        = string
}

variable "parameter_value" {
  description = "Secret value"
  type        = string
  sensitive   = true
}

variable "parameter_version" {
  description = "Version used to trigger secret updates"
  type        = number
  default     = 1
}