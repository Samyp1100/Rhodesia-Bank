package com.biblioteca.services;

import com.biblioteca.dto.AutorDTO;
import com.biblioteca.entities.Autor;
import com.biblioteca.repositories.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public List<AutorDTO> listarTodos() {
        List<Autor> autores = autorRepository.findByDeletedFalse();
        return autores.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public AutorDTO buscarPorId(Long id) {
        Autor autor = autorRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Autor no encontrado con ID: " + id));
        return convertirADTO(autor);
    }

    public AutorDTO crear(AutorDTO dto) {
        Autor autor = Autor.builder()
                .nombre(dto.getNombre())
                .nacionalidad(dto.getNacionalidad())
                .deleted(false)
                .build();

        Autor guardado = autorRepository.save(autor);
        return convertirADTO(guardado);
    }

    public AutorDTO actualizar(Long id, AutorDTO dto) {
        Autor autor = autorRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Autor no encontrado con ID: " + id));

        autor.setNombre(dto.getNombre());
        autor.setNacionalidad(dto.getNacionalidad());

        Autor actualizado = autorRepository.save(autor);
        return convertirADTO(actualizado);
    }

    public void eliminar(Long id) {
        Autor autor = autorRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Autor no encontrado con ID: " + id));
        autor.setDeleted(true);
        autorRepository.save(autor);
    }

    public List<AutorDTO> buscarPorNombre(String nombre) {
        List<Autor> autores = autorRepository.findByNombreContainingIgnoreCaseAndDeletedFalse(nombre);
        return autores.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public List<AutorDTO> buscarPorNacionalidad(String nacionalidad) {
        List<Autor> autores = autorRepository.findByNacionalidadIgnoreCaseAndDeletedFalse(nacionalidad);
        return autores.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    // Método auxiliar para convertir entidad a DTO
    private AutorDTO convertirADTO(Autor autor) {
        return AutorDTO.builder()
                .id(autor.getId())
                .nombre(autor.getNombre())
                .nacionalidad(autor.getNacionalidad())
                .build();
    }

    // Método público para usar en otros servicios
    public Autor obtenerAutorPorId(Long id) {
        return autorRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Autor no encontrado con ID: " + id));
    }
}
