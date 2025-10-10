# Use uma imagem do Java (ajuste a versão conforme seu projeto)
FROM eclipse-temurin:21-jre

# Crie um diretório para a aplicação
WORKDIR /app

# Copie o jar gerado pelo Maven para dentro do container
COPY target/WishlistRms-1.0-SNAPSHOT.jar app.jar

# Exponha a porta que sua aplicação usa (ajuste se necessário)
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]