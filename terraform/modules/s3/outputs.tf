output "bucket_name" {
  description = "Name of the customer files S3 bucket"
  value       = aws_s3_bucket.this.bucket
}