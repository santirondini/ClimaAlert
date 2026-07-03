package ar.edu.utn.frba.ddsi.common.dtos.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DatosClimaticosDTO {
    @JsonProperty("temp_c")
    public Double temp_c;
    @JsonProperty("humidity")
    public Double humidity;
    @JsonProperty("last_updated")
    public String last_updated;
}
