package com.ci.cardlessWebService.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse {
    private String statusCode;
    private String statusMessage;
    private String accessToken;
    private String expiresAt;
}
