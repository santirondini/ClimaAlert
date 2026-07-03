package ar.edu.utn.frba.ddsi.common.dtos.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ClimaResponseDTO {
    public LocationDTO location;
    @JsonProperty("current")
    public DatosClimaticosDTO datosClimaticos;
}
