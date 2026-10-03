module "customer_files_bucket" {
  source = "../../modules/s3"

  bucket_name = var.bucket_name
}

module "customer_files_bucket" {
  source = "../../modules/s3"

  bucket_name = var.bucket_name
}

module "customer_database" {
  source = "../../modules/rds"

  identifier = "customer-file-delivery-db"

  db_name     = var.db_name
  db_username = var.db_username
  db_password = var.db_password

  engine_version = "17"
  instance_class = "db.t3.micro"
}