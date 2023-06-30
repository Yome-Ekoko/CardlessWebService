package com.ci.cardlessWebService.dtos;

import lombok.Data;

@Data
public class CancelPaycodeRequest {
    public String requestId;

    public String  walletId;

    public String  tempPin;
    public String   payCode;
}
