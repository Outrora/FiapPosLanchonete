# Definindo cores
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # Sem cor


echo  "${GREEN}Iniciando a construção e execução da aplicação Quarkus..."
chmod +x ./mvnw

# Verifica se o argumento -d foi passado
if [ "$1" = "-d" ]; then

    if [ -f .env ]; then
        echo "${GREEN}Carregando variáveis de ambiente do arquivo .env no diretório raiz${NC}"
        export $(grep -v '^#' .env | xargs)
    else
        echo "${RED}Arquivo .env não encontrado. Criar um arquivo .env no diretório raiz.${NC}"
        exit 1
    fi

    echo "${GREEN}Modo de depuração ativado."
    docker-compose -f ./composer/banco-compose.yml up --build -d
    ./mvnw quarkus:dev -DskipTests
    exit 0
fi


echo  "${GREEN}Subindo o docker"
if [ -f ./composer/.env ]; then
    echo  "${GREEN}Carregando variáveis de ambiente do arquivo .env no diretório composer${NC}"
    export $(grep -v '^#' ./composer/.env | xargs)
else
    echo  "${RED}Arquivo .env não encontrado. Criar um arquivo .env  no diretório composer.${NC}"
    exit 1
fi
docker-compose -f ./composer/docker-compose.yml up --build

# Verifica se o arquivo .env existe


# Inicia a aplicação Quarkus em modo de desenvolvimento

