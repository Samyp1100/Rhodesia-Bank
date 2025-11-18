package com.biblioteca.repositories;

import com.biblioteca.entities.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    // Buscar autores no eliminados
    List<Autor> findByDeletedFalse();

    // Buscar por nombre (no eliminados)
    List<Autor> findByNombreContainingIgnoreCaseAndDeletedFalse(String nombre);

    // Buscar por nacionalidad (no eliminados)
    List<Autor> findByNacionalidadIgnoreCaseAndDeletedFalse(String nacionalidad);

    // Buscar por ID (no eliminado)
    Optional<Autor> findByIdAndDeletedFalse(Long id);
}
