package com.ci.cardlessWebService.controllers;

import com.ci.cardlessWebService.dtos.TokenResponse;
import com.ci.cardlessWebService.models.Token;
import com.ci.cardlessWebService.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenController {
    private final TokenService tokenService;

    @Autowired
    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/generate_token")
    public ResponseEntity<TokenResponse> generateToken(@RequestBody Token tokenRequest) {
        TokenResponse tokenResponse = tokenService.generateToken(tokenRequest);
        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
    }
}
