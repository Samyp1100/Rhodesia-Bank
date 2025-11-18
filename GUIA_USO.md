# 📖 GUÍA DE USO - Sistema de Biblioteca

## 🎓 ¡PERFECTA PARA EL PARCIAL!

Esta guía te ayudará a entender cómo usar el template para tu examen.

---

## 📋 PASO 1: Entender el Enunciado

Ejemplo de enunciado:
> *"Crear un sistema de biblioteca donde se registren **Libros** y **Autores**. Un libro tiene título, ISBN, año. Un autor tiene nombre y nacionalidad. Un libro puede tener varios autores y un autor puede escribir varios libros."*

### Identificar:
1. **Entidades**: Libro, Autor
2. **Atributos**:
   - Libro: titulo, isbn, anio
   - Autor: nombre, nacionalidad
3. **Relación**: ManyToMany

---

## 🔧 PASO 2: Backend - Estructura

### 1. Entidades (`entities/`)
```java
@Entity
@Table(name = "libros")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String isbn;
    private Integer anio;
    private Boolean deleted = false;

    @ManyToMany
    @JoinTable(...)
    private List<Autor> autores;
}
```

### 2. Repositories (`repositories/`)
```java
@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByDeletedFalse();
    List<Libro> findByTituloContainingIgnoreCaseAndDeletedFalse(String titulo);
}
```

### 3. DTOs (`dto/`)
```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibroDTO {
    private Long id;
    private String titulo;
    private String isbn;
    private Integer anio;
    private List<AutorDTO> autores;
}
```

### 4. Services (`services/`)
```java
@Service
@Transactional
public class LibroService {
    @Autowired
    private LibroRepository repository;

    public List<LibroDTO> listarTodos() { ... }
    public LibroDTO crear(LibroCreateDTO dto) { ... }
    public void eliminar(Long id) { ... }
}
```

### 5. Controllers (`controllers/`)
```java
@RestController
@RequestMapping("/api/libros")
@CrossOrigin(origins = "http://localhost:4200")
public class LibroController {
    @Autowired
    private LibroService service;

    @GetMapping
    public ResponseEntity<List<LibroDTO>> listar() { ... }

    @PostMapping
    public ResponseEntity<LibroDTO> crear(@RequestBody LibroCreateDTO dto) { ... }
}
```

---

## 🎨 PASO 3: Frontend - Estructura

### 1. Modelos (`models/`)
```typescript
export interface Libro {
  id?: number;
  titulo: string;
  isbn: string;
  anio: number;
  autores?: Autor[];
}
```

### 2. Servicios (`services/`)
```typescript
@Injectable({ providedIn: 'root' })
export class LibroService {
  private apiUrl = 'http://localhost:8080/api/libros';

  constructor(private http: HttpClient) { }

  listar(): Observable<Libro[]> {
    return this.http.get<Libro[]>(this.apiUrl);
  }
}
```

### 3. Componentes (`components/`)
```typescript
@Component({
  selector: 'app-libros-lista',
  standalone: true,
  imports: [CommonModule, RouterLink, FormsModule],
  templateUrl: './libros-lista.component.html'
})
export class LibrosListaComponent implements OnInit {
  libros: Libro[] = [];

  ngOnInit(): void {
    this.cargarLibros();
  }

  cargarLibros(): void {
    this.libroService.listar().subscribe({
      next: (data) => this.libros = data
    });
  }
}
```

---

## ✅ CHECKLIST PARA EL PARCIAL

### Backend ✔️
- [ ] Crear entidad con `@Entity`, `@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`
- [ ] Agregar `@Id` y `@GeneratedValue` al campo id
- [ ] Agregar campo `deleted` para soft delete
- [ ] Definir relaciones (`@ManyToMany`, `@OneToMany`, etc.)
- [ ] Crear Repository que extienda `JpaRepository`
- [ ] Crear DTO con los campos necesarios
- [ ] Crear Service con `@Service` y `@Transactional`
- [ ] Implementar métodos CRUD en el Service
- [ ] Crear Controller con `@RestController` y `@CrossOrigin`
- [ ] Configurar `application.properties`
- [ ] Configurar `pom.xml` con todas las dependencias

### Frontend ✔️
- [ ] Crear interface en `models/`
- [ ] Crear service con `@Injectable({ providedIn: 'root' })`
- [ ] Implementar métodos HTTP (GET, POST, PUT, DELETE)
- [ ] Crear componente con `@Component` y `standalone: true`
- [ ] Importar `CommonModule`, `RouterLink`, `FormsModule`
- [ ] Crear template HTML con Bootstrap
- [ ] Implementar métodos ngOnInit, cargar datos, crear, editar, eliminar
- [ ] Configurar rutas en `app.routes.ts`
- [ ] Actualizar navegación en `app.component.html`

---

## 🚀 CÓMO EJECUTAR EL PROYECTO

### 1. Backend
```bash
cd backend
mvn clean install
mvn spring-boot:run
```

### 2. Frontend
```bash
cd frontend
npm install
npm start
```

### 3. Acceder
- Frontend: `http://localhost:4200`
- Backend API: `http://localhost:8080/api`
- H2 Console: `http://localhost:8080/h2-console`

---

## 💡 TIPS PARA EL PARCIAL

### 1. **Gestión del Tiempo**
- 10 min: Leer y entender el enunciado
- 40 min: Implementar backend (entidades → repos → services → controllers)
- 40 min: Implementar frontend (modelos → servicios → componentes)
- 10 min: Probar y corregir errores

### 2. **Errores Comunes**
- ❌ Olvidar `@CrossOrigin` en los controllers
- ❌ No agregar `standalone: true` en componentes Angular
- ❌ Olvidar importar `FormsModule` para `[(ngModel)]`
- ❌ No configurar `provideHttpClient()` en `app.config.ts`
- ❌ Olvidar el campo `deleted` para soft delete

### 3. **Atajos Útiles**
- Usa Lombok para evitar getters/setters
- Copia y adapta los componentes existentes
- Usa los mismos estilos CSS (ya están listos)
- Bootstrap ya está configurado, úsalo sin instalaciones

### 4. **Validaciones Importantes**
```java
// Backend
@Column(nullable = false)
private String titulo;

@Column(nullable = false, unique = true)
private String isbn;
```

```typescript
// Frontend
if (!this.nuevoLibro.titulo.trim()) {
  alert('El título es obligatorio');
  return;
}
```

---

## 🎯 RELACIONES MÁS COMUNES

### ManyToMany (Ejemplo: Libro ↔ Autor)
```java
// Libro.java
@ManyToMany
@JoinTable(
  name = "libro_autor",
  joinColumns = @JoinColumn(name = "libro_id"),
  inverseJoinColumns = @JoinColumn(name = "autor_id")
)
private List<Autor> autores;

// Autor.java
@ManyToMany(mappedBy = "autores")
private List<Libro> libros;
```

### OneToMany (Ejemplo: Autor → Libros)
```java
// Autor.java
@OneToMany(mappedBy = "autor")
private List<Libro> libros;

// Libro.java
@ManyToOne
@JoinColumn(name = "autor_id")
private Autor autor;
```

### ManyToOne (Ejemplo: Libro → Editorial)
```java
// Libro.java
@ManyToOne
@JoinColumn(name = "editorial_id")
private Editorial editorial;

// Editorial.java
@OneToMany(mappedBy = "editorial")
private List<Libro> libros;
```

---

## 📚 RECURSOS ADICIONALES

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Angular Documentation](https://angular.io/docs)
- [Bootstrap 5 Components](https://getbootstrap.com/docs/5.3/components/)
- [Lombok Features](https://projectlombok.org/features/)

---

¡ÉXITO EN TU PARCIAL! 🎓✨
