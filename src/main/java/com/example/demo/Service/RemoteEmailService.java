package com.example.demo.Service;

import com.example.demo.DTO.TopGlobalEmailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RemoteEmailService {

    private final RestTemplate restTemplate;

    public String sendTopGlobalEmail(List<TopGlobalEmailDTO> request) {
        String url = "http://192.168.1.50:8081/email/send"; // Cambiar la IP
        return restTemplate.postForObject(url, request, String.class);
    }
}