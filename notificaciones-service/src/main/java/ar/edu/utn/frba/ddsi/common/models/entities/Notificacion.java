package ar.edu.utn.frba.ddsi.common.models.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Notificacion {
    private String mensaje;
    private LocalDate fecha;

}
