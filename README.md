<h1 align="center">Fluxo de Reprocessamento de Mensagens</h1>


## Descrição do Projeto
<p align="left">Este projeto tem com objetivo mostrar uma forma de criar um fluxo de reprocessamento de mensagens e retentativas, quando ocorre um problema de transação com o banco de dados.</p>

## Tecnologias utilizadas
<!--ts-->
   * [Spring](https://spring.io/)
   * [RabbitMQ](https://www.rabbitmq.com/)
   * [Docker](https://www.docker.com/)
   * [MySQL](https://www.mysql.com/)
<!--te-->

## Como utilizar?
Antes de tudo, certifique-se de que você instalou o Docker e o Docker-Compose em sua máquina. Uma vez instalado,
execute os seguintes comandos:
```
cd dir_do_projeto
docker build -t rabbitmq-custom .
docker-compose up -d
``` 
Para acessar o administrador do RabbitMQ, acesse: http://localhost:15672/ </br>
Usuário e senha: guest
