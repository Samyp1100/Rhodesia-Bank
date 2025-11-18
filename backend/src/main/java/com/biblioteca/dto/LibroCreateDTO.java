package com.biblioteca.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibroCreateDTO {

    private String titulo;
    private String isbn;
    private Integer anio;
    private List<Long> autoresIds; // IDs de los autores existentes
}
