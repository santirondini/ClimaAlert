package ar.edu.utn.frba.ddsi.common.adapters;


import ar.edu.utn.frba.ddsi.common.dtos.api.ClimaResponseDTO;

public interface IProvedorClimaAdapter {
    ClimaResponseDTO obtenerClimaActual(String ubicacion);
}
