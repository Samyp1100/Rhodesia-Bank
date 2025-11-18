# 📚 Sistema de Biblioteca - Frontend

Frontend del sistema de biblioteca desarrollado con Angular 17 (standalone components).

## 🚀 Tecnologías

- **Angular 17** (Standalone Components)
- **TypeScript**
- **Bootstrap 5**
- **Bootstrap Icons**
- **RxJS**

## 📋 Requisitos

- Node.js 18 o superior
- npm 9+

## ⚙️ Instalación y Ejecución

### 1. Instalar dependencias
```bash
cd frontend
npm install
```

### 2. Ejecutar en desarrollo
```bash
npm start
```

La aplicación estará disponible en: `http://localhost:4200`

### 3. Compilar para producción
```bash
npm run build
```

Los archivos compilados estarán en `dist/biblioteca-frontend/`

## 📱 Características

### Gestión de Libros
- ✅ Listar todos los libros
- ✅ Crear nuevo libro
- ✅ Editar libro existente
- ✅ Eliminar libro (soft delete)
- ✅ Asignar múltiples autores a un libro
- ✅ Visualizar autores de cada libro

### Gestión de Autores
- ✅ Listar todos los autores
- ✅ Crear nuevo autor
- ✅ Editar autor existente
- ✅ Eliminar autor (soft delete)
- ✅ Buscar autores por nombre o nacionalidad

### Interfaz de Usuario
- ✅ Diseño moderno con Bootstrap 5
- ✅ Iconos de Bootstrap Icons
- ✅ Navegación intuitiva
- ✅ Formularios inline para crear/editar
- ✅ Confirmaciones antes de eliminar
- ✅ Mensajes de éxito/error

## 🏗️ Estructura del Proyecto

```
frontend/
├── src/
│   ├── app/
│   │   ├── components/
│   │   │   ├── home/              # Página de inicio
│   │   │   ├── libros/            # Gestión de libros
│   │   │   └── autores/           # Gestión de autores
│   │   ├── models/                # Interfaces TypeScript
│   │   ├── services/              # Servicios HTTP
│   │   ├── app.component.*        # Componente principal
│   │   ├── app.config.ts          # Configuración de la app
│   │   └── app.routes.ts          # Rutas de la aplicación
│   ├── index.html                 # HTML principal
│   ├── main.ts                    # Punto de entrada
│   └── styles.css                 # Estilos globales
├── angular.json                   # Configuración de Angular
├── package.json                   # Dependencias
└── tsconfig.json                  # Configuración de TypeScript
```

## 🎨 Componentes

### HomeComponent
Página de inicio con diseño hero y tarjetas de características.

### LibrosListaComponent
- Tabla de libros con acciones (editar/eliminar)
- Formulario inline para crear/editar
- Selección múltiple de autores con checkboxes
- Búsqueda y filtrado (próximamente)

### AutoresListaComponent
- Tabla de autores con acciones
- Formulario inline para crear/editar
- Validaciones de campos requeridos

## 🔌 Servicios

### LibroService
Maneja todas las operaciones CRUD de libros:
- `listar()`: GET /api/libros
- `buscarPorId(id)`: GET /api/libros/{id}
- `crear(libro)`: POST /api/libros
- `actualizar(id, libro)`: PUT /api/libros/{id}
- `eliminar(id)`: DELETE /api/libros/{id}

### AutorService
Maneja todas las operaciones CRUD de autores:
- `listar()`: GET /api/autores
- `buscarPorId(id)`: GET /api/autores/{id}
- `crear(autor)`: POST /api/autores
- `actualizar(id, autor)`: PUT /api/autores/{id}
- `eliminar(id)`: DELETE /api/autores/{id}

## 🌐 Configuración de API

La URL base del backend está configurada en cada servicio:
```typescript
private apiUrl = 'http://localhost:8080/api/...';
```

Para cambiar la URL del backend, edita los archivos:
- `src/app/services/libro.service.ts`
- `src/app/services/autor.service.ts`

## 🎯 Rutas

- `/` - Página de inicio
- `/libros` - Gestión de libros
- `/autores` - Gestión de autores

## 🚦 Asegúrate de que el backend esté corriendo

Antes de ejecutar el frontend, asegúrate de que el backend Spring Boot esté corriendo en `http://localhost:8080`
