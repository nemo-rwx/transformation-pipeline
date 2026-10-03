data "aws_iam_role" "ecs_task_execution" {
  name = "ecsTaskExecutionRole"
}

resource "aws_iam_role_policy" "ecs_ssm_parameter_access" {
  name = "customer-file-delivery-ssm-access"
  role = data.aws_iam_role.ecs_task_execution.name

  policy = jsonencode({
    Version = "2012-10-17"

    Statement = [
      {
        Sid    = "ReadDatabasePassword"
        Effect = "Allow"

        Action = [
          "ssm:GetParameter",
          "ssm:GetParameters"
        ]

        Resource = module.database_password_parameter.parameter_arn
      }
    ]
  })
}