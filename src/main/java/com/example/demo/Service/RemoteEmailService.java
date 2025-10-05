package com.example.demo.Service;

import com.example.demo.DTO.TopGlobalEmailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RemoteEmailService {

    private final RestTemplate restTemplate;

    @Value("${ACCOUNTS_SERVICE_URL}")
    private String accountsServiceUrl;


    public String getUserRole(Long id, String jwtToken) {
        String url = accountsServiceUrl + "/api/v1/auth/profile/" + id;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwtToken);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                Map.class
        );

        return (String) response.getBody().get("role");
    }
}
