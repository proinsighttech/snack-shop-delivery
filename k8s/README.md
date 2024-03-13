# Implementando Microsserviços com Kubernetes

### Instalando Minikube

Você precisa ter o Minikube instalado para criar um cluster Kubernetes localmente. Consulte a documentação oficial do Minikube para obter instruções de instalação: 

### Minikube Documentation

No Windows, você pode instalar o Minikube usando o Chocolatey:

    choco install minikube

###  Acessando o Projeto
Navegue até o diretório do projeto:

    cd snack-shop-delivery-internal


## Executando Minikube
### Inicie o Minikube:


    minikube start

## Deploy do Cluster
Dentro do ambiente Minikube, execute os seguintes comandos:

# Configurar o ambiente Docker para usar o Minikube
### UNIX
    eval $(minikube -p minikube docker-env)
### WINDOWS
    minikube -p minikube docker-env | Invoke-Expression

### Verificar as imagens Docker
    docker images

### Build da aplicação
    docker-compose build

### Verificar o kubectl
    minikube kubectl -- version

### Aplicar os arquivos da aplicação
    minikube kubectl -- apply -f ./k8s/00-snack-shop-mysql.yml
    minikube kubectl -- apply -f ./k8s/01-snack-shop-api.yml

### Verificar os pods
    minikube kubectl -- get pods

### Ativar o serviço de Load Balance
    minikube tunnel

### Verificar os serviços em execução
    minikube kubectl -- get services

### Testar no Postman
Após a implantação, teste os serviços usando o Postman.

### Instalar Istio
Você pode instalar o Istio como um Service Mesh ou Sidecar Proxy. Consulte a documentação oficial do Istio para obter mais informações: Istio Documentation

No Windows, você pode instalar o Istio usando o Chocolatey:

    choco install istioctl

### Configurar Istio
Após a instalação, configure o Istio:

    istioctl install

### Habilitando o Istio para o namespace padrão
    kubectl label namespace default istio-injection=enabled

### Aplicando Arquivos do API Gateway
Aplique os arquivos do API Gateway:

    minikube kubectl -- apply -f ./k8s/istio/gateway.yml

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

Este Readme fornece instruções detalhadas para implantar e testar microsserviços usando Kubernetes. Certifique-se de seguir cada passo cuidadosamente para uma implementação bem-sucedida.