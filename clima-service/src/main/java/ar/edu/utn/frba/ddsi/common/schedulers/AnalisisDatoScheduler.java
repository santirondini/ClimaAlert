package ar.edu.utn.frba.ddsi.common.schedulers;

import ar.edu.utn.frba.ddsi.common.services.impl.ClimaService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnalisisDatoScheduler {

    private final ClimaService climaService;

    @Scheduled(fixedRate = 6000)
    public void analizarDatos() {
        // Lógica para obtener datos climáticos
    }
}