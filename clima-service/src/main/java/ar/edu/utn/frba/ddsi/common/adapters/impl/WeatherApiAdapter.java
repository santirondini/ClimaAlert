package ar.edu.utn.frba.ddsi.common.adapters.impl;

import ar.edu.utn.frba.ddsi.common.adapters.IProvedorClimaAdapter;
import ar.edu.utn.frba.ddsi.common.dtos.api.ClimaResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class WeatherApiAdapter implements IProvedorClimaAdapter {

    private final RestTemplate restTemplate;
    private final String apiUrl;
    private final String apiKey;

    // Fíjate cómo @Value va directo en el parámetro del constructor:
    public WeatherApiAdapter(
            RestTemplate restTemplate,
            @Value("${weather.api.url}") String apiUrl,
            @Value("${weather.api.key}") String apiKey) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
        this.apiKey = apiKey;
    }

    @Override
    public ClimaResponseDTO obtenerClimaActual(String ubicacion) {
        String url = UriComponentsBuilder.fromUriString(apiUrl)
                .queryParam("key", apiKey)
                .queryParam("q", ubicacion)
                .toUriString();
        return restTemplate.getForObject(url, ClimaResponseDTO.class);
    }
}