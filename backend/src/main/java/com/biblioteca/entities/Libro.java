package com.biblioteca.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "libros")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(name = "anio")
    private Integer anio;

    @Column(name = "deleted")
    private Boolean deleted = false;

    // ManyToMany con Autor
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "libro_autor",
        joinColumns = @JoinColumn(name = "libro_id"),
        inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    @Builder.Default
    private List<Autor> autores = new ArrayList<>();

    // Método helper para agregar autores
    public void agregarAutor(Autor autor) {
        this.autores.add(autor);
        autor.getLibros().add(this);
    }

    // Método helper para remover autores
    public void removerAutor(Autor autor) {
        this.autores.remove(autor);
        autor.getLibros().remove(this);
    }
}
