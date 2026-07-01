package ar.edu.utn.frba.ddsi.common.services.impl;

import ar.edu.utn.frba.ddsi.common.adapters.IProvedorClimaAdapter;
import ar.edu.utn.frba.ddsi.common.dtos.ClimaResponseDTO;
import ar.edu.utn.frba.ddsi.common.dtos.LocationDTO;
import ar.edu.utn.frba.ddsi.common.models.entities.RegistroClima;
import ar.edu.utn.frba.ddsi.common.models.repositories.IClimaRepository;
import ar.edu.utn.frba.ddsi.common.services.IClimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class ClimaService implements IClimaService {

    @Autowired
    private IClimaRepository climaRepository;
    @Autowired
    private IProvedorClimaAdapter climaAdapter;

    private final String ciudad = "Buenos Aires";

    private RegistroClima dtoToRegistroClima(ClimaResponseDTO climaResponseDTO){
        RegistroClima registro = new RegistroClima();

        // Extraemos los datos necesarios desde la estructura anidada del DTO
        registro.setUbicacion(climaResponseDTO.getLocation().getName());
        registro.setTemperatura(climaResponseDTO.getDatosClimaticos().getTemp_c());
        registro.setHumedad(climaResponseDTO.getDatosClimaticos().getHumidity());

        // Mapeo/conversión de tipo de datos (String de la API a LocalDateTime)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        registro.setFechaHora(LocalDate.parse(climaResponseDTO.getDatosClimaticos().getLast_updated(), formatter));

        return registro;
    }

    @Override
    public void obtenerDatosClimaticos(){
        ClimaResponseDTO dto = climaAdapter.obtenerClimaActual(this.ciudad);
        RegistroClima registro = this.dtoToRegistroClima(dto);
        climaRepository.guardarRegistroClimatico(registro);
    }
}




