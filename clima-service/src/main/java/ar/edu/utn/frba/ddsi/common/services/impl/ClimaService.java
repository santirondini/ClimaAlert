package ar.edu.utn.frba.ddsi.common.services.impl;

import ar.edu.utn.frba.ddsi.common.adapters.IProvedorClimaAdapter;
import ar.edu.utn.frba.ddsi.common.dtos.api.ClimaResponseDTO;
import ar.edu.utn.frba.ddsi.common.events.AlertaEventoPublisher;
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
    private IProvedorClimaAdapter climaAdapter;
    private AlertaEventoPublisher alertaEventoPublisher;
    private final String ciudad = "Buenos Aires";

    private RegistroClima dtoToRegistroClima(ClimaResponseDTO climaResponseDTO) {
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
    public void obtenerDatosClimaticos() {
        ClimaResponseDTO dto = this.climaAdapter.obtenerClimaActual(this.ciudad);
        RegistroClima registro = this.dtoToRegistroClima(dto);
        climaRepository.guardarRegistroClimatico(registro);
    }

    @Override
    public String analizarDatos() {
        RegistroClima ultimoRegistro = climaRepository.obtenerUltimoRegistro();
        if (ultimoRegistro == null) {
            return "No hay registros climaticos registrados en el sistma";
        }
        if(ultimoRegistro.esAlerta()) {
            this.alertaEventoPublisher.publicarAlerta(ultimoRegistro);
            return "Se ha detectado una alerta";
        }
        return "No se ha detectado ninguna alerta climática.";
    }
}





