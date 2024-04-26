data "aws_lb" "load_balancer" {
  name = var.load_balancer_name
}

data "aws_lb_listener" "load_balancer_listener" {
  load_balancer_arn = data.aws_lb.load_balancer.arn
  port              = 9000
}

resource "aws_security_group" "vpc_link" {
  name   = "vpc-link"
  vpc_id = var.vpc_id

  egress {
    from_port        = 0
    to_port          = 0
    protocol         = "-1"
    cidr_blocks      = ["0.0.0.0/0"]
  }
}

resource "aws_apigatewayv2_vpc_link" "eks" {
  name               = "eks"
  security_group_ids = [aws_security_group.vpc_link.id]
  subnet_ids = var.subnet_ids
}

resource "aws_apigatewayv2_integration" "eks" {
  depends_on          = [aws_apigatewayv2_api.main, aws_apigatewayv2_vpc_link.eks]
  api_id              = aws_apigatewayv2_api.main.id
  integration_uri     = data.aws_lb_listener.load_balancer_listener.arn
  integration_type    = "HTTP_PROXY"
  integration_method  = "ANY"
  connection_type     = "VPC_LINK"
  connection_id       = aws_apigatewayv2_vpc_link.eks.id
}