package com.reto.cliente.entity;

import com.reto.cliente.utils.TipoDocumento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String codigoUnico;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(length = 100, nullable = false)
    private String apellidos;

    @Column(nullable = false, length = 40 )
    @Enumerated(value = EnumType.STRING)
    private TipoDocumento tipoDocumento;

    @Column(length = 50, nullable = false)
    private String numeroDocumento;

}