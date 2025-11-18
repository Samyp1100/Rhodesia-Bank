# 📚 Sistema de Biblioteca - Backend

Backend del sistema de biblioteca desarrollado con Spring Boot, JPA y Lombok.

## 🚀 Tecnologías

- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **Lombok**
- **H2 Database** (desarrollo)
- **MySQL** (producción - opcional)

## 📋 Requisitos

- Java 17 o superior
- Maven 3.6+

## ⚙️ Instalación y Ejecución

### 1. Clonar el repositorio
```bash
cd backend
```

### 2. Compilar el proyecto
```bash
mvn clean install
```

### 3. Ejecutar la aplicación
```bash
mvn spring-boot:run
```

El servidor estará disponible en: `http://localhost:8080`

## 🗄️ Base de Datos

### Consola H2
Durante el desarrollo, puedes acceder a la consola H2 en:
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:biblioteca_db`
- Usuario: `sa`
- Contraseña: (dejar en blanco)

## 📡 API Endpoints

### Libros
- `GET /api/libros` - Listar todos los libros
- `GET /api/libros/{id}` - Obtener un libro por ID
- `POST /api/libros` - Crear un nuevo libro
- `PUT /api/libros/{id}` - Actualizar un libro
- `DELETE /api/libros/{id}` - Eliminar un libro (soft delete)
- `GET /api/libros/buscar/titulo?titulo={titulo}` - Buscar por título
- `GET /api/libros/buscar/anio?anio={anio}` - Buscar por año
- `GET /api/libros/buscar/autor/{autorId}` - Buscar libros de un autor

### Autores
- `GET /api/autores` - Listar todos los autores
- `GET /api/autores/{id}` - Obtener un autor por ID
- `POST /api/autores` - Crear un nuevo autor
- `PUT /api/autores/{id}` - Actualizar un autor
- `DELETE /api/autores/{id}` - Eliminar un autor (soft delete)
- `GET /api/autores/buscar/nombre?nombre={nombre}` - Buscar por nombre
- `GET /api/autores/buscar/nacionalidad?nacionalidad={nac}` - Buscar por nacionalidad

## 📝 Ejemplos de uso

### Crear un autor
```json
POST /api/autores
{
  "nombre": "Gabriel García Márquez",
  "nacionalidad": "Colombiano"
}
```

### Crear un libro con autores
```json
POST /api/libros
{
  "titulo": "Cien años de soledad",
  "isbn": "978-0307474728",
  "anio": 1967,
  "autoresIds": [1]
}
```

## 🏗️ Estructura del Proyecto

```
backend/
├── src/main/java/com/biblioteca/
│   ├── entities/         # Entidades JPA
│   ├── repositories/     # Repositorios Spring Data
│   ├── dto/             # Data Transfer Objects
│   ├── services/        # Lógica de negocio
│   ├── controllers/     # Controladores REST
│   └── BibliotecaApplication.java
├── src/main/resources/
│   └── application.properties
└── pom.xml
```

## 🔧 Configuración para Producción

Para usar MySQL en producción, actualiza `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/biblioteca_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
```
