package com.biblioteca.controllers;

import com.biblioteca.dto.LibroCreateDTO;
import com.biblioteca.dto.LibroDTO;
import com.biblioteca.services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
@CrossOrigin(origins = "http://localhost:4200")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping
    public ResponseEntity<List<LibroDTO>> listar() {
        List<LibroDTO> libros = libroService.listarTodos();
        return ResponseEntity.ok(libros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibroDTO> buscarPorId(@PathVariable Long id) {
        try {
            LibroDTO libro = libroService.buscarPorId(id);
            return ResponseEntity.ok(libro);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<LibroDTO> crear(@RequestBody LibroCreateDTO libroCreateDTO) {
        try {
            LibroDTO nuevoLibro = libroService.crear(libroCreateDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoLibro);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibroDTO> actualizar(@PathVariable Long id, @RequestBody LibroCreateDTO libroCreateDTO) {
        try {
            LibroDTO libroActualizado = libroService.actualizar(id, libroCreateDTO);
            return ResponseEntity.ok(libroActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            libroService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar/titulo")
    public ResponseEntity<List<LibroDTO>> buscarPorTitulo(@RequestParam String titulo) {
        List<LibroDTO> libros = libroService.buscarPorTitulo(titulo);
        return ResponseEntity.ok(libros);
    }

    @GetMapping("/buscar/anio")
    public ResponseEntity<List<LibroDTO>> buscarPorAnio(@RequestParam Integer anio) {
        List<LibroDTO> libros = libroService.buscarPorAnio(anio);
        return ResponseEntity.ok(libros);
    }

    @GetMapping("/buscar/autor/{autorId}")
    public ResponseEntity<List<LibroDTO>> buscarPorAutor(@PathVariable Long autorId) {
        List<LibroDTO> libros = libroService.buscarPorAutor(autorId);
        return ResponseEntity.ok(libros);
    }
}
