package com.emse.spring.automacorp.gouv_api;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class AdressSearchService {

    private final RestTemplate restTemplate;

    //Constructeur qui initialise restTemplate
    public AdressSearchService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.rootUri("https://example.com").build();
    }

    // Méthode searchAdress qui s'adresse à l'API
    public List<ApiGouvAdress> searchAdress(List<String> parameters) {
        String params = String.join("+", parameters);
        String uri = UriComponentsBuilder.fromUriString("/search")//Construction de Uri
                .queryParam("q", params)
                .queryParam("limit", 15)
                .build()
                .toUriString();



        ApiGouvResponse apiGouvResponse = restTemplate.getForObject(uri, ApiGouvResponse.class);

        if (apiGouvResponse != null && apiGouvResponse.features() != null) {
            return apiGouvResponse.features().stream()
                    .map(ApiGouvFeature::properties)
                    .toList();
        } else {
            return List.of();
        }
    }


}
