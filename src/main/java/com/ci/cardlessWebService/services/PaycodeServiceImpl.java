package com.ci.cardlessWebService.services;

import com.ci.cardlessWebService.dtos.CancelPaycodeRequest;
import com.ci.cardlessWebService.dtos.CancelPaycodeResponse;
import com.ci.cardlessWebService.dtos.PaycodeResponse;
import com.ci.cardlessWebService.models.Paycode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@Service
public class PaycodeServiceImpl implements PaycodeService {
    private static final String API_URL = "https://sandbox.meekfi.com:7000/atm/v1/";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String ACCESS_TOKEN_HEADER = "access_token";
    private static final String BASIC_AUTH_PREFIX = "Basic ";
    String username = "MEEKFIUTEST";
    String password = "1234@MeekFi##";

    private final RestTemplate restTemplate;

    public PaycodeServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public PaycodeResponse generatePaycode(Paycode request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(AUTHORIZATION_HEADER, getAuthorizationHeader());
        headers.add(ACCESS_TOKEN_HEADER, "");


        HttpEntity<Paycode> entity = new HttpEntity<>(request, headers);

        try {
            ResponseEntity<PaycodeResponse> response = restTemplate.exchange(
                    API_URL + "generate_paycode",
                    HttpMethod.POST,
                    entity,
                    PaycodeResponse.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                log.error("Error occurred during generatePaycode API call. Status code: {}", response.getStatusCode());
            }
        } catch (Exception e) {
            log.error("Exception occurred during generatePaycode API call", e);
        }

        return null;
    }

    @Override
    public CancelPaycodeResponse cancelPaycode(CancelPaycodeRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(getBasicAuth(username,password));
        headers.add(ACCESS_TOKEN_HEADER, "");

        HttpEntity<CancelPaycodeRequest> entity = new HttpEntity<>(request, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CancelPaycodeResponse> response = restTemplate.exchange(
                API_URL + "cancle_paycode",
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
            String username = "MEEKFIUTEST";
            String password = "1234@MeekFi##";
            String credentials = username + ":" + password;
            String base64Credentials = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
            return BASIC_AUTH_PREFIX + base64Credentials;
        }
    private String getBasicAuth(String username, String password) {
        String credentials = username + ":" + password;
        byte[] credentialsBytes = credentials.getBytes();
        String encodedCredentials = Base64.getEncoder().encodeToString(credentialsBytes);
        return encodedCredentials;
    }
    }

