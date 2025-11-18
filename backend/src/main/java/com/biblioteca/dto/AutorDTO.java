package com.biblioteca.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AutorDTO {

    private Long id;
    private String nombre;
    private String nacionalidad;
}
