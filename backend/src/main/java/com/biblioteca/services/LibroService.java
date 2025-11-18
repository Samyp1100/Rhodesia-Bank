package com.biblioteca.services;

import com.biblioteca.dto.AutorDTO;
import com.biblioteca.dto.LibroCreateDTO;
import com.biblioteca.dto.LibroDTO;
import com.biblioteca.entities.Autor;
import com.biblioteca.entities.Libro;
import com.biblioteca.repositories.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorService autorService;

    public List<LibroDTO> listarTodos() {
        List<Libro> libros = libroRepository.findByDeletedFalse();
        return libros.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public LibroDTO buscarPorId(Long id) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con ID: " + id));

        if (libro.getDeleted()) {
            throw new RuntimeException("El libro ha sido eliminado");
        }

        return convertirADTO(libro);
    }

    public LibroDTO crear(LibroCreateDTO createDTO) {
        // Validar que el ISBN no exista
        if (libroRepository.existsByIsbnAndDeletedFalse(createDTO.getIsbn())) {
            throw new RuntimeException("Ya existe un libro con el ISBN: " + createDTO.getIsbn());
        }

        // Crear el libro
        Libro libro = Libro.builder()
                .titulo(createDTO.getTitulo())
                .isbn(createDTO.getIsbn())
                .anio(createDTO.getAnio())
                .deleted(false)
                .autores(new ArrayList<>())
                .build();

        // Agregar autores si se proporcionaron
        if (createDTO.getAutoresIds() != null && !createDTO.getAutoresIds().isEmpty()) {
            for (Long autorId : createDTO.getAutoresIds()) {
                Autor autor = autorService.obtenerAutorPorId(autorId);
                libro.agregarAutor(autor);
            }
        }

        Libro guardado = libroRepository.save(libro);
        return convertirADTO(guardado);
    }

    public LibroDTO actualizar(Long id, LibroCreateDTO updateDTO) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con ID: " + id));

        if (libro.getDeleted()) {
            throw new RuntimeException("El libro ha sido eliminado");
        }

        // Validar ISBN si cambió
        if (!libro.getIsbn().equals(updateDTO.getIsbn()) &&
            libroRepository.existsByIsbnAndDeletedFalse(updateDTO.getIsbn())) {
            throw new RuntimeException("Ya existe un libro con el ISBN: " + updateDTO.getIsbn());
        }

        libro.setTitulo(updateDTO.getTitulo());
        libro.setIsbn(updateDTO.getIsbn());
        libro.setAnio(updateDTO.getAnio());

        // Actualizar autores
        libro.getAutores().clear();
        if (updateDTO.getAutoresIds() != null && !updateDTO.getAutoresIds().isEmpty()) {
            for (Long autorId : updateDTO.getAutoresIds()) {
                Autor autor = autorService.obtenerAutorPorId(autorId);
                libro.agregarAutor(autor);
            }
        }

        Libro actualizado = libroRepository.save(libro);
        return convertirADTO(actualizado);
    }

    public void eliminar(Long id) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con ID: " + id));
        libro.setDeleted(true);
        libroRepository.save(libro);
    }

    public List<LibroDTO> buscarPorTitulo(String titulo) {
        List<Libro> libros = libroRepository.findByTituloContainingIgnoreCaseAndDeletedFalse(titulo);
        return libros.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public List<LibroDTO> buscarPorAnio(Integer anio) {
        List<Libro> libros = libroRepository.findByAnioAndDeletedFalse(anio);
        return libros.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public List<LibroDTO> buscarPorAutor(Long autorId) {
        List<Libro> libros = libroRepository.findByAutorId(autorId);
        return libros.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    // Método auxiliar para convertir entidad a DTO
    private LibroDTO convertirADTO(Libro libro) {
        List<AutorDTO> autoresDTO = libro.getAutores().stream()
                .filter(autor -> !autor.getDeleted())
                .map(autor -> AutorDTO.builder()
                        .id(autor.getId())
                        .nombre(autor.getNombre())
                        .nacionalidad(autor.getNacionalidad())
                        .build())
                .collect(Collectors.toList());

        return LibroDTO.builder()
                .id(libro.getId())
                .titulo(libro.getTitulo())
                .isbn(libro.getIsbn())
                .anio(libro.getAnio())
                .autores(autoresDTO)
                .build();
    }
}
