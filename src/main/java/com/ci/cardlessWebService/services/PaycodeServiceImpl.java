package com.ci.cardlessWebService.services;

import com.ci.cardlessWebService.dtos.CancelPaycodeRequest;
import com.ci.cardlessWebService.dtos.CancelPaycodeResponse;
import com.ci.cardlessWebService.dtos.PaycodeResponse;
import com.ci.cardlessWebService.dtos.TokenResponse;
import com.ci.cardlessWebService.models.Paycode;
import com.ci.cardlessWebService.models.Token;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class PaycodeServiceImpl implements PaycodeService {


    private static final String API_URL = "https://sandbox.meekfi.com:7000/atm/v1/";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String ACCESS_TOKEN_HEADER = "access_token";
    private static final String BASIC_AUTH_PREFIX = "Basic ";
    private final String username = "VAPTUSER";
    private final String password = "VAPT@Password2023@#";


    private final RestTemplate restTemplate;
    private final TokenService tokenService;

    public PaycodeServiceImpl(RestTemplate restTemplate, TokenService tokenService) {
        this.restTemplate = restTemplate;
        this.tokenService = tokenService;
    }


    @Override
    public PaycodeResponse generatePaycode(Paycode request,String countryCode) {
        TokenResponse tokenResponse=tokenService.generateToken(new Token(countryCode));
        System.out.println("accesstoken: " + tokenResponse.getAccessToken());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(AUTHORIZATION_HEADER, getAuthorizationHeader());
        headers.add(ACCESS_TOKEN_HEADER, tokenResponse.getAccessToken());

        HttpEntity<Paycode> entity = new HttpEntity<>(request, headers);

        try {
            ResponseEntity<PaycodeResponse> response = restTemplate.exchange(
                    API_URL + "generate_paycode",
                    HttpMethod.POST,
                    entity,
                    PaycodeResponse.class
            );
                return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred during generatePaycode API call", e);
        }
    }

    @Override
    public CancelPaycodeResponse cancelPaycode(CancelPaycodeRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", getBasicAuth(username, password));
        headers.remove(ACCESS_TOKEN_HEADER);

        HttpEntity<CancelPaycodeRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<CancelPaycodeResponse> response = restTemplate.exchange(
                API_URL + "cancel_paycode",
                HttpMethod.POST,
                entity,
                CancelPaycodeResponse.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("Error occurred during cancelPaycode API call. Status code: " + response.getStatusCode());
        }
    }

    private String getAuthorizationHeader() {
        String credentials = username + ":" + password;
        String base64Credentials = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
        return BASIC_AUTH_PREFIX + base64Credentials;
    }

    private String getBasicAuth(String username, String password) {
        String credentials = username + ":" + password;
        byte[] credentialsBytes = credentials.getBytes(StandardCharsets.UTF_8);
        String encodedCredentials = Base64.getEncoder().encodeToString(credentialsBytes);
        return encodedCredentials;
    }
//    private String  getAccess_token(){
//        TokenResponse tokenResponse = tokenService.generateToken(new Token());
//
//        String accessToken = tokenResponse.getAccessToken();
//        return accessToken;
//    }
}

