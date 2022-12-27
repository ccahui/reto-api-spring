package com.reto.cliente.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActualizarClienteDto {
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellidos;
}
