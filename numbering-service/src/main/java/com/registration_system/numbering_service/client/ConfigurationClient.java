package com.registration_system.numbering_service.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.registration_system.numbering_service.exception.ConfigurationClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ConfigurationClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final String configurationServiceUrl;
    private final String defaultConfigServiceUrl;

    @Autowired
    public ConfigurationClient(@Value("${config.service.url}") String configurationServiceUrl,
                               @Value("${config.default.url}") String defaultConfigServiceUrl,
                               RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
        this.objectMapper = new ObjectMapper();
        this.configurationServiceUrl = configurationServiceUrl;
        this.defaultConfigServiceUrl = defaultConfigServiceUrl;
    }

    /**
     * Récupère une configuration par ID ou retourne la configuration par défaut si configId est null.
     *
     * @param configId l'ID de la configuration (peut être null).
     * @return JsonNode représentant la configuration.
     */
    public JsonNode getConfigurationById(Long configId) {
        String url = (configId != null)
                ? configurationServiceUrl.replace("{configId}", configId.toString())
                : defaultConfigServiceUrl;

        try {
            String jsonResponse = restTemplate.getForObject(url, String.class);
            return parseJsonResponse(jsonResponse);
        } catch (Exception e) {
            throw new ConfigurationClientException("Échec de la récupération de la configuration depuis l'URL : " + url, e);
        }
    }

    /**
     * Analyse la réponse JSON.
     *
     * @param jsonResponse réponse JSON sous forme de chaîne.
     * @return JsonNode représentant la réponse analysée.
     */
    private JsonNode parseJsonResponse(String jsonResponse) {
        try {
            return objectMapper.readTree(jsonResponse);
        } catch (Exception e) {
            throw new ConfigurationClientException("Échec du parsing de la réponse JSON.", e);
        }
    }
}
