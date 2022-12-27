package com.reto.cliente.mapper;

import com.reto.cliente.dto.ClienteDto;
import com.reto.cliente.entity.Cliente;
import org.springframework.stereotype.Component;

@Component
public class MapperCliente {

    public ClienteDto entityToDto(Cliente entity){
        ClienteDto dto = ClienteDto.builder()
                .codigoUnico(entity.getCodigoUnico())
                .nombre(entity.getNombre())
                .apellidos(entity.getApellidos())
                .tipoDocumento(entity.getTipoDocumento())
                .numeroDocumento(entity.getNumeroDocumento())
                .build();

        return dto;
    }

}
