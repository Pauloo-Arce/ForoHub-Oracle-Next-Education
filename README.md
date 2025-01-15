# Foro Hub API

Foro Hub es una API RESTful desarrollada en Java utilizando Spring Boot. Permite gestionar un foro de discusión, implementando funcionalidades básicas de CRUD para tópicos y seguridad mediante JWT.

## Características

- **CRUD de Tópicos:** Crear, leer, actualizar y eliminar tópicos.
- **Validaciones:** Reglas de negocio para garantizar datos consistentes.
- **Seguridad:** Autenticación y autorización con Spring Security y JWT.
- **Persistencia:** Base de datos MySQL para almacenar la información del foro.

## Tecnologías Utilizadas

- Java
- Spring Boot
- Maven

## Instalación y Ejecución

1. Clona el repositorio:
   ```bash
   git clone <URL-del-repositorio>
   ```

2. Accede al directorio del proyecto:
   ```bash
   cd foro-hub
   ```

3. Configura la base de datos y propiedades en `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/foro_hub
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   spring.jpa.hibernate.ddl-auto=update
   ```

4. Ejecuta la aplicación:
   ```bash
   mvn spring-boot:run
   ```

## Desarrollado por

Este proyecto fue desarrollado por Paulo Arce.
