package ar.edu.utn.frba.ddsi.common.models.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RegistroClima {
    private Long id;
    private String ubicacion;
    private LocalDate fechaHora;
    private Double temperatura;
    private Double humedad;

    // > 30 y > 60
    public Boolean esAlerta(){
        return this.temperatura < 3.0 && this.humedad > 60.0;
    }
}
