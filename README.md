# Lanchonete PosFiap

![JAVA](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Banco](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![](https://img.shields.io/badge/Amazon_AWS-FF9900?style=for-the-badge&logo=amazonaws&logoColor=white)
![Quarkus](https://img.shields.io/badge/QUARKUS-009CAB?style=for-the-badge&logo=quarkus&logoColor=white)

Este projeto usa Quarkus, o Supersonic Subatomic Java Framework.

Se você quiser saber mais sobre o Quarkus, visite seu site: <https://quarkus.io/>

## Rodando a Aplicação

Crie um .env com as seguintes informações:

```
POSTGRES_DB=
POSTGRES_USER=
POSTGRES_PASSWORD=
POSTGRES_URL=
DB_KIND=postgresql
```

Para roda a aplicação localmente:

```shell script
chmod +x ./run.sh -d
./run.sh
```

Para rodar a aplicação dentro de um container

```shell script
chmod +x ./run.sh 
./run.sh
```

> **_NOTE:_**  O Quarkus agora vem com uma Dev UI, que está disponível no modo dev apenas
> em <http://localhost:8080/q/dev/>.

## Swagger

Para acessar a documentação do REST acessar <http://localhost:8080/swagger-ui/>



