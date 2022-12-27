package com.reto.cliente.dto;

import com.reto.cliente.utils.TipoDocumento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {
    private String codigoUnico;
    private String nombre;
    private String apellidos;
    private TipoDocumento tipoDocumento;
    private String numeroDocumento;
}
