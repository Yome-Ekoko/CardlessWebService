package com.ci.cardlessWebService.dtos;

import lombok.Data;

@Data
public class CancelPaycodeResponse {
    public String statusCode;
    public String statusMessage;
    public String requestId;
}
