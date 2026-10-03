resource "aws_ssm_parameter" "this" {
  name = var.parameter_name
  type = "SecureString"

  value_wo         = var.parameter_value
  value_wo_version = var.parameter_version

  tags = {
    Environment = "dev"
    Project     = "customer-file-delivery"
  }
}