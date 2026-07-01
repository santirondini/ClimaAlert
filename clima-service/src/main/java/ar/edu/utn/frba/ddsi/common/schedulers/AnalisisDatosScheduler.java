package ar.edu.utn.frba.ddsi.common.schedulers;

import ar.edu.utn.frba.ddsi.common.services.impl.ClimaService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnalisisDatosScheduler {

    private ClimaService climaService;

    @Scheduled(fixedRate = 300000) // cada 5 minutos
    public void obtenerDatosClimaticos() {
        climaService.obtenerDatosClimaticos();
    }

}
