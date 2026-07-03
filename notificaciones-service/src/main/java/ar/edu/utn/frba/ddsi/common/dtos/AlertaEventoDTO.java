package ar.edu.utn.frba.ddsi.common.dtos;

import lombok.Data;

@Data
public class AlertaEventoDTO {
    private String ubicacion;
    private Double temperatura;
    private Double humedad;
    private String mensaje;

    public String toString(){
        return "AlertaEventoDTO{" +
                "ubicacion='" + ubicacion + '\'' +
                ", temperatura=" + temperatura +
                ", humedad=" + humedad +
                ", mensaje='" + mensaje + '\'' +
                '}';
    }
}
