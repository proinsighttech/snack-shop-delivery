# Snack Shop API


## 📝 Descrição

O Snack Shop API é um sistema de gerenciamento para lanchonetes que oferece uma série de vantagens. Ele permite a realização de pedidos de forma eficiente e intuitiva, melhorando a experiência do cliente e otimizando o tempo dos funcionários.

A aplicação "Snack Shop API" é um sistema de gerenciamento para lanchonetes. Ela foi projetada para melhorar a eficiência operacional e proporcionar uma experiência superior aos clientes. Aqui estão os principais requisitos de negócios que a aplicação atende:  

1. Gestão de Pedidos: A aplicação permite a realização de pedidos de forma eficiente e intuitiva. Os usuários podem listar todos os pedidos, listar um pedido por ID, adicionar um pedido e atualizar o status de um pedido.  

2. Gestão de Usuários: A aplicação permite o gerenciamento de usuários. Os usuários podem ser listados, adicionados, atualizados e removidos. Além disso, os usuários podem ser adicionados a grupos e ter suas senhas alteradas.  

3. Gestão de Grupos: A aplicação permite o gerenciamento de grupos de usuários. Os grupos podem ser listados, adicionados, atualizados e removidos. Além disso, as permissões podem ser listadas, adicionadas e removidas de um grupo.  

4. Gestão de Produtos: A aplicação permite o gerenciamento de produtos. Os produtos podem ser listados, adicionados, atualizados e removidos.  

5. Gestão de Ingredientes: A aplicação permite o gerenciamento de ingredientes. Os ingredientes podem ser listados, adicionados, atualizados e removidos.  

6. Gestão de Métodos de Pagamento: A aplicação permite o gerenciamento de métodos de pagamento. Os métodos de pagamento podem ser listados, adicionados, atualizados e removidos.  

7. Gestão de Lanchonetes (Snack Bars): A aplicação permite o gerenciamento de lanchonetes. As lanchonetes podem ser listadas, adicionadas, atualizadas e ativadas/desativadas. Além disso, os produtos, formas de pagamento e administradores de uma lanchonete podem ser gerenciados.

Em resumo, a Snack Shop API é uma solução completa para lanchonetes que buscam melhorar sua eficiência operacional e proporcionar uma experiência superior aos seus clientes.


# FASE 3

## 📝 Arquitetura AWS

![Arquitetura_AWS](src/main/resources/documentation/images/aws/00-arquitetura-aws.png)

## Descrição da Arquitetura do Projeto na AWS

### 1. Usuário e API Gateway
**Usuário:** Os usuários interagem com seu sistema enviando requisições HTTP.

**API Gateway:** Serve como ponto de entrada para todas as requisições dos usuários. Ele gerencia e roteia essas requisições para os serviços apropriados no backend.

### 2. Lambda Authorizer
**Lambda Authorizer:** Antes de permitir que as requisições atinjam o backend, o API Gateway utiliza um Lambda Authorizer para autenticar e autorizar as requisições. Este Lambda verifica se a solicitação tem permissão para prosseguir com base em tokens ou outros critérios de segurança.
**Nesse caso o usuário seá autenticado por CPF.**

### 3. Load Balancer
**Load Balancer:** Distribui o tráfego recebido do API Gateway entre as instâncias de serviços backend localizadas nas diferentes zonas de disponibilidade. Isso ajuda a distribuir a carga de maneira uniforme e a garantir alta disponibilidade e resiliência do sistema.

### 4. VPC (Virtual Private Cloud)
**VPC:** Toda a infraestrutura está dentro de uma VPC, garantindo que os recursos estejam isolados e seguros. A VPC inclui várias sub-redes para segmentação lógica e controle de tráfego.

### 5. Sub-redes Privadas e Zonas de Disponibilidade
**Sub-redes Privadas:** Os recursos como instâncias EC2 e serviços ECS (Elastic Container Service) são implantados em sub-redes privadas, garantindo que não sejam acessíveis diretamente da internet.
   
**Zonas de Disponibilidade:** A arquitetura é configurada em múltiplas zonas de disponibilidade para garantir alta disponibilidade e tolerância a falhas.

### 6. Elastic Container Service (ECS) e EC2 Instances
**ECS:** O ECS gerencia os contêineres Docker que hospedam suas aplicações. Ele facilita a execução, escalonamento e manutenção dos contêineres.

**EC2 Instances:** As instâncias EC2 dentro do ECS executam tarefas e serviços definidos nos contêineres. Eles estão espalhados por várias sub-redes privadas para maior resiliência.

### 7. Serviços e Tarefas
**Serviços:** Representam aplicações de longa duração gerenciadas pelo ECS, garantindo que um número especificado de tarefas esteja sempre em execução.
   
**Tarefas:** Unidades básicas de trabalho no ECS, definidas pelas imagens de contêineres e suas configurações.

### 8. RDS (Relational Database Service)
**RDS:** Um banco de dados gerenciado usado para armazenar dados de aplicação. Ele está localizado dentro da VPC, garantindo acesso seguro e performance otimizada. O RDS pode ser configurado para alta disponibilidade e recuperação de desastres com réplicas em múltiplas zonas de disponibilidade.


# Executando a aplicação na AWS com EKS, API Gateway e Lambda Authorizer
Este é um exemplo de como executar a aplicação na AWS usando o EKS, API Gateway e Lambda Authorizer. Foram utilizados os seguintes repositorios:

### Repositórios
* Banco de Dados: RDS com MySQL
  https://github.com/proinsighttech/lanchonete-infra-banco-de-dados


* Infraestrutura: Terraform para criação do ambiente
  https://github.com/proinsighttech/lanchonete-infra-kubernetes


* Lambda Authorizer: Lambda que realiza autenticação por CPF
  https://github.com/proinsighttech/lanchonete-lambda

### Implantando a aplicação
Para implantar a implantação da aplicação temos pipelines de CI/CD que realizam a implantação da aplicação no EKS, API Gateway e Lambda Authorizer.  
Para implantar a aplicação, siga as etapas abaixo:

1) Execute a esteira do banco RDS para criar o base de dados no MySQL.
2) Execute a esteira de infraestrutura para criar o ambiente no EKS. essa esteira criará toda a infraestrutura necessária para a aplicação. (VPC, Subnets, Security Groups, EKS, RDS, etc.)
3) A esteira da infraestrutura também fará o deploy da aplicação e enviará uma imagem para o ECR a fim de ser utilizada no EKS.
4) Além disso, a esteira da infraestrutura também implantará o Lambda Authorizer no AWS Lambda.

### Testando a aplicação
A seguir, vamos testar a aplicação na AWS usando o EKS, API Gateway e Lambda Authorizer.

![Running](src/main/resources/documentation/video/snack-shop-aws.gif)

### OBS: Para testar a aplicação  importar a collection do Postman no diretório src/main/resources/collections.

## Procedimento Realizado

**1. Deploy da Aplicação**

Deploy é realizado no GitHub usando a esteira de CI/CD do GitHub Actions.

![Deploy](src/main/resources/documentation/images/aws/01-deploy-github.png)

A esteira de CI/CD do GitHub Actions é acionada quando um novo commit é enviado para o repositório. 
Ela executa os testes de unidade e integração e, em seguida, implanta a aplicação no EKS, API Gateway e Lambda Authorizer.



**2. API Gateway e Lambda Authorizer**

O API Gateway e o Lambda Authorizer são implantados na AWS. O API Gateway serve como ponto de entrada para todas as requisições dos usuários, enquanto o Lambda Authorizer é usado para autenticar e autorizar as requisições.

![API Gateway](src/main/resources/documentation/images/aws/02-api-gateway.png)



**3. Enderenço de Acesso**

O Endereço de Acesso é fornecido pelo API Gateway. Ele é usado para acessar a aplicação na AWS.

![Endereço](src/main/resources/documentation/images/aws/03-endereco-api.png)



**4. Testando a Aplicação**

A aplicação é testada usando o Postman. As requisições são enviadas para o Endereço de Acesso fornecido pelo API Gateway.

![Postman](src/main/resources/documentation/images/aws/04-chamando-api.png)




**5. Autenticação por CPF**

Podemos observar que a autenticação por CPF foi bem-sucedida. O Lambda Authorizer permitiu que a requisição fosse encaminhada para a aplicação.

![CPF](src/main/resources/documentation/images/aws/05-autenticacao-lambda.png)

   



___
___
___

## 📄 Diagramas de negócio
- [Processos](https://miro.com/app/board/uXjVNzyqNFE=/)
- Diagrama de Objetos
  ![Diagrama](src/main/resources/documentation/images/snack-shop-delivery.drawio.png)

## 💻 Tecnologias

As seguintes ferramentas foram usadas na construção do projeto:

- [Java](https://www.java.com)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Maven](https://maven.apache.org/)
- [SQL](https://www.mysql.com/)
## ✅ Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
- [Docker](https://www.docker.com/products/docker-desktop)
- [Docker Compose](https://docs.docker.com/compose/install/)

## Arquitetura
![Arquitetura_local](src/main/resources/documentation/images/arquitetura-local.png)

## 🚀 Como executar o projeto

### ⚙️ Rodando o Backend (servidor)

```bash
# Clone este repositório
$ git clone https://github.com/proinsighttech/snack-shop-delivery.git

# Acesse a pasta do projeto no terminal/cmd
$ cd snack-shop-delivery

# Use o Docker Compose para subir a aplicação e o banco de dados
$ docker-compose up
```

Ao executar o comando docker-compose up, o Docker Compose inicia a aplicação e o banco de dados em contêineres separados, mas na mesma rede Docker. Isso permite que a aplicação e o banco de dados se comuniquem entre si.  O banco de dados é iniciado na porta padrão 3306. 

A aplicação Spring Boot, por sua vez, se conecta a este banco de dados através da rede Docker.  Quando a aplicação Spring Boot é iniciada, o Flyway é executado. O Flyway é uma ferramenta de migração de banco de dados que ajuda a gerenciar e aplicar mudanças no esquema do banco de dados. Ele cria as tabelas necessárias para a interação com a aplicação, como as tabelas de pedidos, usuários, produtos e ingredientes.  

Após a criação das tabelas, um script chamado afterMigrate.sql é executado. Este script preenche as tabelas com dados fictícios, permitindo que você teste a aplicação.  Finalmente, a aplicação é exposta na porta 9000. Você pode interagir com a aplicação através de um cliente HTTP como o Postman, chamando os respectivos endpoints da API.


## 📝 Documentação da API

Para testar a aplicação, fornecemos uma coleção do Postman que contém várias solicitações pré-configuradas. Esta coleção está localizada no diretório src/main/resources/collections do projeto.  Para importar a coleção no Postman, siga os passos abaixo:  

1. Abra o Postman.
2. Clique no botão "Import" no canto superior esquerdo.
3. Na janela que se abre, selecione a opção "File".
4. Navegue até o diretório src/main/resources/collections do projeto e selecione o arquivo da coleção.
5. Clique em "Open" para importar a coleção.

Após importar a coleção, você verá uma lista de solicitações no painel esquerdo do Postman. Você pode clicar em qualquer uma dessas solicitações para abri-la e, em seguida, clicar no botão "Send" para enviar a solicitação para a aplicação.  Lembre-se de que a aplicação deve estar rodando na porta 9000 para que você possa interagir com ela através do Postman.

### 👥 Grupos

Para listar os grupos cadastrados na aplicação definimos uma pasta dentro da Collection chamada de Groups, nela você encontrará as seguintes requisições:

- Listar todos os grupos
- Listar grupo por ID
- Adicionar Grupo
- Atualizar Grupo
- Remover Grupo
- Listar Permissões do Grupo
- Adicionar Permissão ao Grupo
- Remover Permissão do Grupo


### Exemplo de Requisição
- Buscando Grupo por ID
![Group by ID](src/main/resources/documentation/images/group-by-id.png)

### 👤 Usuários

Para listar os usuários cadastrados na aplicação definimos uma pasta dentro da Collection chamada de Users, nela você encontrará as seguintes requisições:

- Listar todos os usuários
- Listar usuário por ID
- Listar Grupo de Usuários
- Adicionar Usuário ao Grupo
- Remover Usuário do Grupo
- Adicionar Usuário
- Atualizar Usuário
- Remover Usuário
- Alterar Senha do Usuário

### Exemplo de Requisição
- Adicionando Usuário
![User by ID](src/main/resources/documentation/images/add-user.png)


### 🛒 Pedidos

Para listar os pedidos cadastrados na aplicação definimos uma pasta dentro da Collection chamada de Orders, nela você encontrará as seguintes requisições:

- Listar todos os pedidos
- Listar pedido por ID
- Adicionar Pedido
- Atualizar Status do Pedidos

### Exemplo de Requisição
- Listando Pedidos
Ao listar os pedidos podemos filtar por status, intervalo de data de criação e usuário.
![Orders](src/main/resources/documentation/images/list-orders.png)


### 💳 Métodos de Pagamento

Para listar os metodos de pagamento cadastrados na aplicação definimos uma pasta dentro da Collection chamada de Payment Methods, nela você encontrará as seguintes requisições:

- Listar todos os metodos de pagamento
- Listar metodo de pagamento por ID
- Adicionar Metodo de Pagamento
- Atualizar Metodo de Pagamento
- Remover Metodo de Pagamento

### Exemplo de Requisição
- Listando Metodos de Pagamento
![Payment Methods](src/main/resources/documentation/images/list-payment-methods.png)

### 🍔 Lanchonetes

Para listar as lanchonetes cadastradas na aplicação definimos uma pasta dentro da Collection chamada de Snack Bars, nela você encontrará as seguintes requisições:

- Listar todas as lanchonetes
- Listar lanchonete por ID
- Adicionar Lanchonete
- Atualizar Lanchonete
- Ativar/Desativar Lanchonete
- Lista de Produtos da Lanchonete
- Lista Formas de Pagamento da Lanchonete
- Adicionar Forma de Pagamento a Lanchonete
- Remover Forma de Pagamento da Lanchonete
- Lista Administradores da Lanchonete
- Adicionar Administrador a Lanchonete
- Remover Administrador da Lanchonete

### Exemplo de Requisição
- Listando Lanchonetes
![Snack Bars](src/main/resources/documentation/images/find-snack-shop-by-id.png)


### 📦 Produtos

Para listar os produtos cadastrados na aplicação definimos uma pasta dentro da Collection chamada de Products, nela você encontrará as seguintes requisições:

- Listar todos os produtos
- Listar produto por ID
- Adicionar Produto
- Atualizar Produto
- Remover Produto

### Exemplo de Requisição
- Adicionando Produto
![Products](src/main/resources/documentation/images/add-product.png)



### 🥕 Ingredientes

Para listar os ingredientes cadastrados na aplicação definimos uma pasta dentro da Collection chamada de Ingredients, nela você encontrará as seguintes requisições:

- Listar todos os ingredientes
- Listar ingrediente por ID
- Adicionar Ingrediente
- Atualizar Ingrediente
- Remover Ingrediente
- Lista Ingredientes em Estoque


### Exemplo de Requisição
- Listando Ingrediente em Estoque
![Ingredients](src/main/resources/documentation/images/find-ingredient-by-id.png)

---------------------------------------

# Implementando Microsserviços em Kubernetes Local com Minkube

### Pré-Requisitos
* Docker (Instalação: https://www.docker.com/get-started/)
* Chocolatey (Instalação: https://chocolatey.org/install)
* Minikube (Instalação: https://minikube.sigs.k8s.io/docs/start/)
* Istio (Instalação: https://istio.io/latest/docs/setup/getting-started/)


### Arquitetura com Kubernetes Local
![Arquitetura_local](src/main/resources/documentation/images/arquitetura-kubernetes.png)

### Instalando Minikube

Você precisa ter o Minikube instalado para criar um cluster Kubernetes localmente. Consulte a documentação oficial do Minikube para obter instruções de instalação.
Podemos instalar o Minikube usando o Chocolatey:

    choco install minikube

###  Acessando o Projeto
Navegue até o diretório do projeto:

    cd snack-shop-delivery-internal

## Executando Minikube
### Inicie o Minikube:

    minikube start

![Minikube](src/main/resources/documentation/images/01-minikube-start.png)

## Deploy do Cluster
 A seguir, vamos implantar a aplicação no cluster Kubernetes local usando o Minikube.

# Configurar o ambiente Docker para usar o Minikube
Entrando dentro do terminal do Minikube:
### UNIX
    eval $(minikube -p minikube docker-env)
### WINDOWS
    minikube -p minikube docker-env | Invoke-Expression

### Verificar as imagens Docker
Verifique se as imagens Docker estão disponíveis para o Minikube:

    docker images

### Build da aplicação
Faça o build da aplicação usando o Docker Compose:

    docker-compose build

![Docker](src/main/resources/documentation/images/02-configurando-minikube-docker.png)

### Verificar o kubectl
    minikube kubectl -- version

### Preparando os arquivos da aplicação
Aplicando os arquivos de deployment e service da aplicação:

    minikube kubectl -- apply -f ./k8s/00-snack-shop-mysql.yml

### Verifique se o servico do mySQL está em execução antes de subir a aplicação
    minikube kubectl -- get pods

![Arquivos](src/main/resources/documentation/images/05a-aplicando-arquivos.png)


### Aplicando os arquivos da aplicação
Com o MySQL em execução, aplique os arquivos da aplicação:

    minikube kubectl -- apply -f ./k8s/01-snack-shop-api-local.yml
    

![Arquivos](src/main/resources/documentation/images/05-aplicando-arquivos.png)


### Verificar os pods
Verifique se os pods estão em execução:

    minikube kubectl -- get pods

![Pods](src/main/resources/documentation/images/06-pods.png)


### Ativar o serviço de Load Balance
Abra um novo terminal e execute o comando abaixo para ativar o serviço de Load Balance e expor o IP da aplicação:

    minikube tunnel

![Tunel](src/main/resources/documentation/images/07-tunel.png)

### Verificar os serviços em execução
Após a ativação do serviço de Load Balance, verifique os serviços em execução:

    minikube kubectl -- get services

![Servicos](src/main/resources/documentation/images/08-servicos.png)

### Testar no Postman
Após a implantação, teste os serviços usando o Postman com o IP fornecido pelo comando ' minikube kubectl -- get services' na porta 9000.

![Postan](src/main/resources/documentation/images/11-postman.png)

### Instalar Istio
Você pode instalar o Istio como um Service Mesh ou Sidecar Proxy. Consulte a documentação oficial do Istio para obter mais informações.
Prodemos instalar o Istio usando o Chocolatey:

    choco install istioctl

### Configurar Istio
Após a instalação, configure o Istio:

    istioctl install

![Istio](src/main/resources/documentation/images/09-istio.png)

### Habilitando o Istio para o namespace padrão
    kubectl label namespace default istio-injection=enabled

### Aplicando Arquivos do API Gateway
Aplique os arquivos do API Gateway:

    minikube kubectl -- apply -f ./k8s/istio/gateway.yml

![GAteway](src/main/resources/documentation/images/10-gateway-istio.png)

### Verificar o serviço
    minikube kubectl -- -n istio-system get services

## Configurando AutoScalling
Para configurar o AutoScaling, siga as etapas abaixo:

### Habilitar métricas
    minikube addons enable metrics-server

### Aplicar o serviço
    minikube kubectl -- apply -f ./k8s/02-hpa.yml

### Verificar o AutoScaling
    minikube kubectl -- get hpa

Este Readme fornece instruções detalhadas para implantar e testar microsserviços usando Kubernetes.
Certifique-se de seguir cada passo cuidadosamente para uma implementação bem-sucedida.

---------------------------------------

# Implementando Microsserviços em Cloud AWS com EKS e Terraform

### Pré-Requisitos
* Docker (Instalação: https://www.docker.com/get-started/)
* Chocolatey (Instalação: https://chocolatey.org/install)
* Terraform (Instalação: https://developer.hashicorp.com/terraform/tutorials/aws-get-started/install-cli)


### Arquitetura com Kubernetes Cloud
![Arquitetura_cloud](src/main/resources/documentation/images/arquitetura-cloud.png)


### Instalando Terraform
Podemos instalar o Terraform usando o Chocolatey:
    choco install terraform

###  Acessando os arquivos do Terraform
Navegue até o diretório do projeto:

    cd snack-shop-delivery-internal/terraform

### Crianção do Cluster EKS e Ambiente AWS
    terraform apply --auto-approve

### Conectar com Cluster
    aws eks --region us-west-2 update-kubeconfig --name snackshop-cluster

### Criar Imagem Docker
    docker-compose build

### Verificar as imagens Docker
    docker images

### Subir imagem no ECR usando comando da AWS
    aws ecr get-login-password --region us-west-2 | docker login --username AWS --password-stdin ************.dkr.ecr.us-west-2.amazonaws.com
    docker build -t snack-shop-delivery-internal-snack-shop-api .    
    docker tag snack-shop-delivery-internal-snack-shop-api:latest ************.dkr.ecr.us-west-2.amazonaws.com/snack-shop-delivery-internal-snack-shop-api:latest
    docker push ************.dkr.ecr.us-west-2.amazonaws.com/snack-shop-delivery-internal-snack-shop-api:latest

### Verificar o kubectl
    kubectl version

### Verificar nós criados
    kubectl get nodes

![Noes](src/main/resources/documentation/images/12-nodes-aws.png)

### Preparando os arquivos da aplicação
Aplicando os arquivos de deployment e service da aplicação:

    kubectl apply -f ..\k8s\00-snack-shop-mysql.yml

### Verifique se o servico do mySQL está em execução antes de subir a aplicação
    kubectl get pods

![Arquivos](src/main/resources/documentation/images/05a-aplicando-arquivos.png)


### Aplicando os arquivos da aplicação
Com o MySQL em execução, aplique os arquivos da aplicação:

    kubectl apply -f ..\k8s\01-snack-shop-api.yml


![Arquivos](src/main/resources/documentation/images/05-aplicando-arquivos.png)

### Aplique os arquivos do API Gateway
    
    kubectl apply -f ..\k8s\02-hpa.yml

### Verificar status da implementação
    kubectl get deployments

### Verificar pods em execução
    kubectl get pods

### Verificando Services
    kubectl get services

![Services_AWS](src/main/resources/documentation/images/13-services-aws.png)


### Verificando Logs
    kubectl logs -f <pod-name>

### Testar no Postman
Após a implantação, teste os serviços usando o Postman com o DNS da AWS fornecido pelo comando 'kubectl get services' na porta 9000.

![Postman_AWS](src/main/resources/documentation/images/14-postman-aws.png)


# Executando a aplicação na AWS com EKS
Este é um exemplo de como executar a aplicação na AWS usando o EKS.  Para executar a aplicação na AWS, siga as etapas abaixo:

![Running](src/main/resources/documentation/video/executando-aplicacao.gif)
