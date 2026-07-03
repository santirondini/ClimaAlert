package ar.edu.utn.frba.ddsi.common.services.impl;

import ar.edu.utn.frba.ddsi.common.adapters.IProvedorClimaAdapter;
import ar.edu.utn.frba.ddsi.common.dtos.api.ClimaResponseDTO;
import ar.edu.utn.frba.ddsi.common.events.AlertaEventoPublisher;
import ar.edu.utn.frba.ddsi.common.models.entities.RegistroClima;
import ar.edu.utn.frba.ddsi.common.models.repositories.IClimaRepository;
import ar.edu.utn.frba.ddsi.common.services.IClimaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClimaService implements IClimaService {

    @Autowired
    private IClimaRepository climaRepository;

    private final IProvedorClimaAdapter climaAdapter;
    private final AlertaEventoPublisher alertaEventoPublisher;
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
        log.info("Obteniendo datos climaticos: "
                + registro.getUbicacion() + ", "
                + registro.getTemperatura() + "°C, "
                + registro.getHumedad() + "%, "
                + registro.getFechaHora());
        climaRepository.guardarRegistroClimatico(registro);
    }

    @Override
    public String analizarDatos() {
        RegistroClima ultimoRegistro = climaRepository.obtenerUltimoRegistro();
        if (ultimoRegistro == null) {
            log.info("No hay registros climaticos registrados en el sistema");
            return "No hay registros climaticos registrados en el sistema";
        }
        if(ultimoRegistro.esAlerta()) {
            log.info("Se ha detectado una alerta climática: "
                + ultimoRegistro.getUbicacion() + ", "
                + ultimoRegistro.getTemperatura() + "°C, "
                + ultimoRegistro.getHumedad() + "%, "
                + ultimoRegistro.getFechaHora())
            ;
            this.alertaEventoPublisher.publicarAlerta(ultimoRegistro);
            return "Se ha detectado una alerta";
        }
        return "No se ha detectado ninguna alerta climática.";
    }
}





