package ar.edu.utn.frba.ddsi.common.adapters.impl;

import ar.edu.utn.frba.ddsi.common.adapters.IProvedorClimaAdapter;
import ar.edu.utn.frba.ddsi.common.dtos.ClimaResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


public class WeatherApiAdapter implements IProvedorClimaAdapter {

    private final RestTemplate restTemplate;

    @Value("${weather.api.url}")
    private String apiUrl;

    @Value("${weather.api.key}")
    private String apiKey;

    public WeatherApiAdapter(RestTemplate restTemplate, String apiUrl, String apiKey) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
        this.apiKey = apiKey;
    }

    @Override
    public ClimaResponseDTO obtenerClimaActual(String ubicacion) {
        String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("key", apiKey)
                .queryParam("q", ubicacion)
                .toUriString();
        return restTemplate.getForObject(url, ClimaResponseDTO.class);
    }
}
