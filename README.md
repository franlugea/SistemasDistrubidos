# Sistema de Gestión de Clases – Backend

Backend del sistema de gestión de clases e inscripciones para un gimnasio, desarrollado con Spring Boot, utilizando Auth0 para autenticación y autorización segura mediante JWT

## Integrantes del equipo
Francisco Lugea


##  Requisitos para su ejecución
* Java 21+
* Maven 3.9+
* Base de datos MySQL 
* Cuenta de Auth0 con una API configurada

## ⚙️ Configuración del archivo application.properties
El proyecto utiliza variables de entorno para proteger credenciales.
Crear un archivo:

```bash
src/main/resources/application.properties
```
y colocar 

```
spring.application.name=SistemasDistribuidos
server.port=${PORT:8080}

-- Base de datos --

spring.datasource.url=${BD.URL}
spring.datasource.username=${BD.USERNAME}
spring.datasource.password=${BD.PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.show-sql=true

-- Auth0 --

auth0.domain=${AUTH0_DOMAIN}
auth0.clientId=${AUTH0_CLIENT}
auth0.clientSecret=${AUTH0_SECRET}
auth0.audience=${AUTH0_AUDIENCE}

-- Seguridad JWT --

spring.security.oauth2.resourceserver.jwt.issuer-uri=https://dev-j4oh22azd4aciul5.us.auth0.com/
spring.security.oauth2.resourceserver.jwt.jwk-set-uri=https://dev-j4oh22azd4aciul5.us.auth0.com/.well-known/jwks.json
```

### Varaibles de entorno necesarias

* `BD.URL=` URL de conexión a la base de datos

* `BD.USERNAME=` Usuario de la base de datos 

* `BD.PASSWORD=` Contraseña de la Base de Datos

* `AUTH0_DOMAIN=` Dominio de tu tenant de Auth0
* `AUTH0_CLIENT=` Client ID de la aplicación configurada en Auth0
* `AUTH0_SECRET=` Client Secret de Auth0 (usado para validación interna)
* `AUTH0_AUDIENCE=` Identificador de la API configurada en Auth0


## Instrucciones de instalación y ejecución

### 1.Clonar repositorio
```bash
git clone https://github.com/franlugea/SistemasDistrubidos.git
```
### 2.Configurar las variables de entorno
⚠️ Nunca subas tus credenciales directamente al repositorio.

### 3.Ejecutar proyecto
```bash
mvn spring-boot:run
```
## Ejecutar como JAR

Compilar: `mvn clean package`

Ejecutar: `java -jar SistemasDistribuidos-0.0.1-SNAPSHOT.jar`

## 🛠 Tecnologías utilizadas
* Spring Web
* Spring security
* OAuth2 Resource server
* Spring Data JPA 
* Loombok
* Validation
* MapStruct 
* Maven
* MySQL
