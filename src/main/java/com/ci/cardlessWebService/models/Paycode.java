package com.ci.cardlessWebService.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paycode {
    private String requestId;
    private String wallet;
    private String walletId;
    private String amount;
    private String tempPin;
    private String callbackUrl;
}
