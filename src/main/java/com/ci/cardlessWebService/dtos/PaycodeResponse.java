package com.ci.cardlessWebService.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaycodeResponse {
    private String statusCode;
    private String statusMessage;
    private String requestId;
    private String payCode;

}
