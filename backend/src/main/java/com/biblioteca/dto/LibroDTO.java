package com.biblioteca.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibroDTO {

    private Long id;
    private String titulo;
    private String isbn;
    private Integer anio;
    private List<AutorDTO> autores;
}
