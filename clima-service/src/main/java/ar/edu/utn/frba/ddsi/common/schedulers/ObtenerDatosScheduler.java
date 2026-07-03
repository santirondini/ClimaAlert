package ar.edu.utn.frba.ddsi.common.schedulers;

import ar.edu.utn.frba.ddsi.common.services.impl.ClimaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ObtenerDatosScheduler {

    private final ClimaService climaService;

    @Scheduled(fixedRate = 5000)
    public void obtenerDatosClimaticos() {
        try {
            log.info("Obteniendo datos climaticos");
            this.climaService.obtenerDatosClimaticos();
        } catch (Exception e) {
            log.error("Error al obtener datos climaticos", e);
        }
    }
}

