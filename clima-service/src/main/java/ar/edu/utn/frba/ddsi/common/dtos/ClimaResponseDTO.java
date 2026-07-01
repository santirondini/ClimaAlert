package ar.edu.utn.frba.ddsi.common.dtos;

import lombok.Data;

@Data
public class ClimaResponseDTO {
    public LocationDTO location;
    public DatosClimaticosDTO datosClimaticos;
}
