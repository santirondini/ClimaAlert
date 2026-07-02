package ar.edu.utn.frba.ddsi.common.dtos;

import lombok.Data;

@Data
public class AlertaEventoDTO {
    private String ubicacion;
    private Double temperatura;
    private Double humedad;
    private String mensaje;
}
