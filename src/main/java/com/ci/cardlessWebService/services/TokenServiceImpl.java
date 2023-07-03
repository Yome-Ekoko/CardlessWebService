package com.ci.cardlessWebService.services;

import com.ci.cardlessWebService.dtos.TokenResponse;
import com.ci.cardlessWebService.models.Token;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class TokenServiceImpl implements TokenService {
    private final RestTemplate restTemplate;

    public TokenServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public TokenResponse generateToken(Token tokenRequest) {
        String apiUrl = "https://sandbox.meekfi.com:7000/atm/v1/generate_token";
        String username = "VAPTUSER";
        String password = "VAPT@Password2023@#";

        // Set request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Basic " + getBasicAuth(username, password));

        HttpEntity<Token> requestEntity = new HttpEntity<>(tokenRequest, headers);

        ResponseEntity<TokenResponse> responseEntity = restTemplate.exchange(
                apiUrl, HttpMethod.POST, requestEntity, TokenResponse.class);

        return responseEntity.getBody();
    }

    private String getBasicAuth(String username, String password) {
        String credentials = username + ":" + password;
        byte[] credentialsBytes = credentials.getBytes(StandardCharsets.UTF_8);
        String encodedCredentials = Base64.getEncoder().encodeToString(credentialsBytes);
        return encodedCredentials;
    }
}