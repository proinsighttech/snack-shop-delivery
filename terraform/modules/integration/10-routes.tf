resource "aws_apigatewayv2_route" "get_users" {
  api_id = aws_apigatewayv2_api.main.id
  route_key = "GET /users"
  target    = "integrations/${aws_apigatewayv2_integration.eks.id}"
}

resource "aws_apigatewayv2_route" "get_user_by_id" {
  api_id = aws_apigatewayv2_api.main.id
  route_key = "GET /users/{userId}"
  target    = "integrations/${aws_apigatewayv2_integration.eks.id}"
}

resource "aws_apigatewayv2_route" "get_user_groups" {
  api_id = aws_apigatewayv2_api.main.id
  route_key = "GET /users/{userId}/groups"
  target    = "integrations/${aws_apigatewayv2_integration.eks.id}"
}

resource "aws_apigatewayv2_route" "put_user_group" {
  api_id = aws_apigatewayv2_api.main.id
  route_key = "PUT /users/{userId}/groups/{groupId}"
  target    = "integrations/${aws_apigatewayv2_integration.eks.id}"
}

resource "aws_apigatewayv2_route" "delete_user_group" {
  api_id = aws_apigatewayv2_api.main.id
  route_key = "DELETE /users/{userId}/groups/{groupId}"
  target    = "integrations/${aws_apigatewayv2_integration.eks.id}"
}

resource "aws_apigatewayv2_route" "post_users" {
  api_id = aws_apigatewayv2_api.main.id
  route_key = "POST /users"
  target    = "integrations/${aws_apigatewayv2_integration.eks.id}"
}

output "orders_base_url" {
  value = "${aws_apigatewayv2_stage.api.invoke_url}/users"
}


