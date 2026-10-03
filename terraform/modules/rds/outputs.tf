output "db_instance_endpoint" {
  description = "RDS PostgresSQL endpoint"
  value       = aws_db_instance.this.address
}

output "db_instance_port" {
  description = "RDS PostgresSQL port"
  value       = aws_db_instance.this.port
}

output "db_name" {
  description = "Database name"
  value       = aws_db_instance.this.db_name
}

output "db_username" {
  description = "Database username"
  value       = aws_db_instance.this.username
}

output "security_group_id" {
  description = "RDS security group ID"
  value       = aws_security_group.rds.id
}