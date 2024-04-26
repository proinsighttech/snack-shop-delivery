resource "aws_apigatewayv2_api" "main" {
  name          = "main"
  protocol_type = "HTTP"

  cors_configuration  {
    allow_credentials           = false
    allow_headers               = ["*"]
    allow_methods               = ["GET", "POST", "PUT", "PATCH", "DELETE"]
    allow_origins               = ["*"]
    expose_headers              = ["*"]
    max_age                     = 60
  }
}

resource "aws_apigatewayv2_stage" "api" {
  api_id = aws_apigatewayv2_api.main.id

  name        = "api"
  auto_deploy = true
}

output "api_gateway_arn" {
  description = "The ARN of the API Gateway"
  value       = aws_apigatewayv2_api.main.arn
}