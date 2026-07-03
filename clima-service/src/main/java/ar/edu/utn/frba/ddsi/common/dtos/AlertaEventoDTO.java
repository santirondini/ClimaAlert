package ar.edu.utn.frba.ddsi.common.dtos;

import lombok.Data;

@Data
public class AlertaEventoDTO {
    public String ubicacion;
    public Double temperatura;
    public Double humedad;
    public String mensaje;
}
