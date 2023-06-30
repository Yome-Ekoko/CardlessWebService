package com.ci.cardlessWebService.controllers;

import com.ci.cardlessWebService.dtos.CancelPaycodeRequest;
import com.ci.cardlessWebService.dtos.CancelPaycodeResponse;
import com.ci.cardlessWebService.dtos.PaycodeResponse;
import com.ci.cardlessWebService.models.Paycode;
import com.ci.cardlessWebService.services.PaycodeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paycode")
public class PaycodeController {

    private PaycodeService paycodeService;

    public PaycodeController(PaycodeService paycodeService) {
        this.paycodeService = paycodeService;
    }

    @PostMapping("/generatePaycode")
    public PaycodeResponse generatePaycode(@RequestBody Paycode request) {

        PaycodeResponse response = paycodeService.generatePaycode(request);

        return response;
    }
    @PostMapping("/cancelPaycode")
    public CancelPaycodeResponse cancelPaycode(@RequestBody CancelPaycodeRequest request) {

        CancelPaycodeResponse response = paycodeService.cancelPaycode(request);

        return response;
    }
}
