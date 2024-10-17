echo "Iniciando a construção e execução da aplicação Quarkus..."
chmod +x ./mvnw

#!/bin/bash


# Verifica se o argumento -d foi passado
if [ "$1" = "-d" ]; then
    echo "Modo de depuração ativado."
    docker-compose -f ./composer/banco-compose.yml up --build -d
    ./mvnw quarkus:dev -DskipTests
    exit 0
fi


echo "Subindo o docker"
docker-compose -f ./composer/docker-compose.yml up --build

# Verifica se o arquivo .env existe


# Inicia a aplicação Quarkus em modo de desenvolvimento

