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

    public Boolean esAlerta(){
        return this.temperatura > 30.0 && this.humedad > 60.0;
    }
}
