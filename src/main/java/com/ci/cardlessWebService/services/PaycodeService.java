package com.ci.cardlessWebService.services;

import com.ci.cardlessWebService.dtos.CancelPaycodeRequest;
import com.ci.cardlessWebService.dtos.CancelPaycodeResponse;
import com.ci.cardlessWebService.dtos.PaycodeResponse;
import com.ci.cardlessWebService.models.Paycode;

public interface PaycodeService {
    PaycodeResponse generatePaycode(Paycode request,String countryCode);

    CancelPaycodeResponse cancelPaycode(CancelPaycodeRequest request);
}
