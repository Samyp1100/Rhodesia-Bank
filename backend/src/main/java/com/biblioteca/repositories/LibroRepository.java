package com.biblioteca.repositories;

import com.biblioteca.entities.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    // Buscar libros no eliminados
    List<Libro> findByDeletedFalse();

    // Buscar por título (no eliminados)
    List<Libro> findByTituloContainingIgnoreCaseAndDeletedFalse(String titulo);

    // Buscar por ISBN (no eliminado)
    Optional<Libro> findByIsbnAndDeletedFalse(String isbn);

    // Buscar por año (no eliminados)
    List<Libro> findByAnioAndDeletedFalse(Integer anio);

    // Buscar libros de un autor específico
    @Query("SELECT l FROM Libro l JOIN l.autores a WHERE a.id = :autorId AND l.deleted = false")
    List<Libro> findByAutorId(@Param("autorId") Long autorId);

    // Verificar si existe un ISBN
    boolean existsByIsbnAndDeletedFalse(String isbn);
}
