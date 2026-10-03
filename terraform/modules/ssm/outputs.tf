output "parameter_arn" {
  description = "SSM parameter ARN"
  value       = aws_ssm_parameter.this.arn
}

output "parameter_name" {
  description = "SSM parameter name"
  value       = aws_ssm_parameter.this.name
}