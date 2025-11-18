# 📚 Sistema de Biblioteca - Full Stack

Sistema completo de gestión de biblioteca desarrollado con Spring Boot (Backend) y Angular (Frontend).

## 🎯 Descripción del Proyecto

Sistema web que permite gestionar libros y autores en una biblioteca. Implementa una relación **ManyToMany** donde:
- Un libro puede tener varios autores
- Un autor puede escribir varios libros

## 🛠️ Tecnologías Utilizadas

### Backend
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **Lombok**
- **H2 Database** (desarrollo)
- **MySQL** (producción - opcional)
- **Maven**

### Frontend
- **Angular 17** (Standalone Components)
- **TypeScript**
- **Bootstrap 5**
- **Bootstrap Icons**
- **RxJS**

## 📋 Requisitos Previos

### Backend
- Java 17 o superior
- Maven 3.6+

### Frontend
- Node.js 18 o superior
- npm 9+

## 🚀 Instalación y Ejecución

### 1️⃣ Clonar el repositorio
```bash
git clone <url-del-repositorio>
cd Rhodesia-Bank
```

### 2️⃣ Ejecutar el Backend

```bash
# Ir a la carpeta backend
cd backend

# Compilar el proyecto
mvn clean install

# Ejecutar la aplicación
mvn spring-boot:run
```

El backend estará disponible en: `http://localhost:8080`

#### Consola H2 (Base de datos)
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:biblioteca_db`
- Usuario: `sa`
- Contraseña: (dejar en blanco)

### 3️⃣ Ejecutar el Frontend

```bash
# Abrir una nueva terminal e ir a la carpeta frontend
cd frontend

# Instalar dependencias
npm install

# Ejecutar la aplicación
npm start
```

El frontend estará disponible en: `http://localhost:4200`

## 📊 Modelo de Datos

### Entidad: Libro
- `id` (Long) - Identificador único
- `titulo` (String) - Título del libro
- `isbn` (String) - ISBN único
- `anio` (Integer) - Año de publicación
- `deleted` (Boolean) - Borrado lógico
- `autores` (List<Autor>) - Relación ManyToMany

### Entidad: Autor
- `id` (Long) - Identificador único
- `nombre` (String) - Nombre del autor
- `nacionalidad` (String) - Nacionalidad
- `deleted` (Boolean) - Borrado lógico
- `libros` (List<Libro>) - Relación ManyToMany

## 🌐 API Endpoints

### Libros
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/libros` | Listar todos los libros |
| GET | `/api/libros/{id}` | Obtener un libro por ID |
| POST | `/api/libros` | Crear un nuevo libro |
| PUT | `/api/libros/{id}` | Actualizar un libro |
| DELETE | `/api/libros/{id}` | Eliminar un libro (soft delete) |
| GET | `/api/libros/buscar/titulo?titulo={titulo}` | Buscar por título |
| GET | `/api/libros/buscar/anio?anio={anio}` | Buscar por año |
| GET | `/api/libros/buscar/autor/{autorId}` | Buscar libros de un autor |

### Autores
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/autores` | Listar todos los autores |
| GET | `/api/autores/{id}` | Obtener un autor por ID |
| POST | `/api/autores` | Crear un nuevo autor |
| PUT | `/api/autores/{id}` | Actualizar un autor |
| DELETE | `/api/autores/{id}` | Eliminar un autor (soft delete) |
| GET | `/api/autores/buscar/nombre?nombre={nombre}` | Buscar por nombre |
| GET | `/api/autores/buscar/nacionalidad?nacionalidad={nac}` | Buscar por nacionalidad |

## 📝 Ejemplos de Uso

### Crear un Autor
```bash
POST http://localhost:8080/api/autores
Content-Type: application/json

{
  "nombre": "Gabriel García Márquez",
  "nacionalidad": "Colombiano"
}
```

### Crear un Libro con Autores
```bash
POST http://localhost:8080/api/libros
Content-Type: application/json

{
  "titulo": "Cien años de soledad",
  "isbn": "978-0307474728",
  "anio": 1967,
  "autoresIds": [1]
}
```

## 🎨 Capturas de Pantalla

### Página de Inicio
- Hero section con diseño moderno
- Tarjetas de características
- Navegación intuitiva

### Gestión de Libros
- Tabla con listado de todos los libros
- Formulario inline para crear/editar
- Selección múltiple de autores
- Botones de edición y eliminación

### Gestión de Autores
- Tabla con listado de autores
- Formulario inline para crear/editar
- Información de nacionalidad
- Acciones de CRUD completas

## 🏗️ Estructura del Proyecto

```
Rhodesia-Bank/
├── backend/                      # Backend Spring Boot
│   ├── src/main/java/com/biblioteca/
│   │   ├── entities/            # Entidades JPA (Libro, Autor)
│   │   ├── repositories/        # Repositorios Spring Data
│   │   ├── dto/                 # Data Transfer Objects
│   │   ├── services/            # Lógica de negocio
│   │   ├── controllers/         # Controladores REST
│   │   └── BibliotecaApplication.java
│   ├── src/main/resources/
│   │   └── application.properties
│   └── pom.xml
│
└── frontend/                    # Frontend Angular
    ├── src/app/
    │   ├── components/          # Componentes Angular
    │   │   ├── home/           # Página de inicio
    │   │   ├── libros/         # Gestión de libros
    │   │   └── autores/        # Gestión de autores
    │   ├── models/             # Interfaces TypeScript
    │   ├── services/           # Servicios HTTP
    │   ├── app.component.*     # Componente principal
    │   ├── app.config.ts       # Configuración
    │   └── app.routes.ts       # Rutas
    ├── angular.json
    └── package.json
```

## ✨ Características Principales

### Backend
- ✅ API RESTful completa
- ✅ Relación ManyToMany entre Libro y Autor
- ✅ Soft Delete (borrado lógico)
- ✅ DTOs para transferencia de datos
- ✅ Validaciones
- ✅ Búsquedas personalizadas
- ✅ Base de datos H2 en memoria

### Frontend
- ✅ Standalone Components (Angular 17)
- ✅ Diseño responsive con Bootstrap 5
- ✅ Iconos de Bootstrap Icons
- ✅ Formularios reactivos
- ✅ Validaciones en el cliente
- ✅ Confirmaciones antes de eliminar
- ✅ Mensajes de éxito/error
- ✅ Animaciones y transiciones

## 🔧 Configuración para Producción

### Backend - MySQL
Edita `backend/src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/biblioteca_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
```

### Frontend - URL del Backend
Edita las URLs en los servicios:
- `frontend/src/app/services/libro.service.ts`
- `frontend/src/app/services/autor.service.ts`

```typescript
private apiUrl = 'https://tu-dominio.com/api/...';
```

## 📚 Documentación Adicional

- [README Backend](backend/README.md)
- [README Frontend](frontend/README.md)
- [Guía de Uso para Parciales](GUIA_USO.md)

## 🤝 Contribuir

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto es de código abierto y está disponible bajo la licencia MIT.

## 👨‍💻 Autor

Desarrollado con ❤️ para aprender Spring Boot, JPA, Lombok y Angular

---

**¡Gracias por usar este sistema!** Si tienes alguna pregunta o sugerencia, no dudes en contactarme.
