data "aws_secretsmanager_secret" "rds_password" {
  name = "customer-file-delivery/dev/rds-v2"
}