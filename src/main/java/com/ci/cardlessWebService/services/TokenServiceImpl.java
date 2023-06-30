package com.ci.cardlessWebService.services;

import com.ci.cardlessWebService.dtos.TokenResponse;
import com.ci.cardlessWebService.models.Token;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
        String username = "MEEKFIUTEST";
        String password = "1234@MeekFi##";

        // Set request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Basic " + getBasicAuth(username, password));

        // Set request body
        HttpEntity<Token> requestEntity = new HttpEntity<>(tokenRequest, headers);

        // Send the request
        ResponseEntity<TokenResponse> responseEntity = restTemplate.exchange(
                apiUrl, HttpMethod.POST, requestEntity, TokenResponse.class);

        // Return the token response
        return responseEntity.getBody();
    }

    private String getBasicAuth(String username, String password) {
        String credentials = username + ":" + password;
        byte[] credentialsBytes = credentials.getBytes();
        String encodedCredentials = Base64.getEncoder().encodeToString(credentialsBytes);
        return encodedCredentials;
    }
}
