package ar.edu.utn.frba.ddsi.common.models.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

@Slf4j
@Getter
@Setter
public class RegistroClima {
    private Long id;
    private String ubicacion;
    private LocalDate fechaHora;
    private Double temperatura;
    private Double humedad;

    public Boolean esAlerta(Double temperaturaLimite, Double humedadLimite) {
        if(temperaturaLimite == null || humedadLimite == null){
            log.info("Los valores de temperaturaLimite y humedadLimite no están configurados. No se puede determinar si es una alerta." +
                    "temperaturaLimite = " + temperaturaLimite +
                    ", humedadLimite = " + humedadLimite);
            return false;
        }
        if (this.temperatura < temperaturaLimite && this.humedad > humedadLimite) {
            return true;
        }
        return false;
    }
}
