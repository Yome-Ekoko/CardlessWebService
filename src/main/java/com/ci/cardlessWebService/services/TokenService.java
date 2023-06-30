package com.ci.cardlessWebService.services;

import com.ci.cardlessWebService.dtos.TokenResponse;
import com.ci.cardlessWebService.models.Token;

public interface TokenService {
    TokenResponse generateToken(Token tokenRequest);
}
