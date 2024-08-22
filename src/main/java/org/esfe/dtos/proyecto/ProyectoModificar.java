package org.esfe.dtos.proyecto;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
public class ProyectoModificar implements Serializable {
    private Integer id;
    private String titulo;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Integer categoriaId;
}
