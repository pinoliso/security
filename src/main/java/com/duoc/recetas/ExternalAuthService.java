package com.duoc.recetas;

import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;

@Service
public class ExternalAuthService {

    private String token;

    public String login(String username, String password) {
        RestTemplate restTemplate = new RestTemplate();
        String loginUrl = "http://20.83.144.250:8081/login";

        // Crear cuerpo y cabeceras de la petición
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Crear el cuerpo de la solicitud con los datos de usuario y contraseña
        MultiValueMap<String, String> formParams = new LinkedMultiValueMap<>();
        formParams.add("user", username);
        formParams.add("encryptedPass", password);

        // Crear la entidad de la solicitud con cabeceras y datos
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formParams, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(loginUrl, HttpMethod.POST, requestEntity, String.class);


            System.out.println("LLAMADA");
            if (response.getStatusCode().is2xxSuccessful()) {
                this.token = response.getBody();
                System.out.println("TOKEN: " + this.token);
                return this.token;
            }
            return null;
        } catch (Exception e) {
            System.out.println("LLAMADA nula, error:" + e.getMessage());
            return null;
        }
    }

    public String getToken() {
        return this.token;
    }

    public ResponseEntity<?> callExternalApi(String endpoint) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://api.external.com/" + endpoint;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + this.token);
        HttpEntity<String> request = new HttpEntity<>(headers);

        return restTemplate.exchange(apiUrl, HttpMethod.GET, request, Object.class);
    }
}

