package ar.edu.utn.frba.ddsi.common.schedulers;

import ar.edu.utn.frba.ddsi.common.services.impl.ClimaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Slf4j
@Component
@RequiredArgsConstructor
public class AnalisisDatoScheduler {

    private final ClimaService climaService;

    @Scheduled(fixedRate = 50000)
    public void analizarDatos() {
        try {
        log.info("Analizando datos");
        this.climaService.analizarDatos();
    } catch (Exception e) {
        log.error("Error al analizar datos", e);
    }
    }
}