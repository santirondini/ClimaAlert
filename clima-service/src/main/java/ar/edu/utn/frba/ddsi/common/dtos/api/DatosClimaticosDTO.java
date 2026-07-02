package ar.edu.utn.frba.ddsi.common.dtos.api;

import lombok.Data;

@Data
public class DatosClimaticosDTO {
    private Double temp_c;
    private Double humidity;
    private String last_updated;
}
