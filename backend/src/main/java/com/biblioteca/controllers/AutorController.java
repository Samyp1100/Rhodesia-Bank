package com.biblioteca.controllers;

import com.biblioteca.dto.AutorDTO;
import com.biblioteca.services.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autores")
@CrossOrigin(origins = "http://localhost:4200")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping
    public ResponseEntity<List<AutorDTO>> listar() {
        List<AutorDTO> autores = autorService.listarTodos();
        return ResponseEntity.ok(autores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> buscarPorId(@PathVariable Long id) {
        try {
            AutorDTO autor = autorService.buscarPorId(id);
            return ResponseEntity.ok(autor);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<AutorDTO> crear(@RequestBody AutorDTO autorDTO) {
        try {
            AutorDTO nuevoAutor = autorService.crear(autorDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoAutor);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorDTO> actualizar(@PathVariable Long id, @RequestBody AutorDTO autorDTO) {
        try {
            AutorDTO autorActualizado = autorService.actualizar(id, autorDTO);
            return ResponseEntity.ok(autorActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            autorService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar/nombre")
    public ResponseEntity<List<AutorDTO>> buscarPorNombre(@RequestParam String nombre) {
        List<AutorDTO> autores = autorService.buscarPorNombre(nombre);
        return ResponseEntity.ok(autores);
    }

    @GetMapping("/buscar/nacionalidad")
    public ResponseEntity<List<AutorDTO>> buscarPorNacionalidad(@RequestParam String nacionalidad) {
        List<AutorDTO> autores = autorService.buscarPorNacionalidad(nacionalidad);
        return ResponseEntity.ok(autores);
    }
}
