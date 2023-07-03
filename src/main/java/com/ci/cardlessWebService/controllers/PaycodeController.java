package com.ci.cardlessWebService.controllers;

import com.ci.cardlessWebService.dtos.CancelPaycodeRequest;
import com.ci.cardlessWebService.dtos.CancelPaycodeResponse;
import com.ci.cardlessWebService.dtos.PaycodeResponse;
import com.ci.cardlessWebService.models.Paycode;
import com.ci.cardlessWebService.services.PaycodeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paycode")
public class PaycodeController {

    private PaycodeService paycodeService;

    public PaycodeController(PaycodeService paycodeService) {
        this.paycodeService = paycodeService;
    }

    @PostMapping("/generate_paycode")
    public PaycodeResponse generatePaycode(@RequestBody Paycode request, @RequestParam(name = "countryCode") String countryCode) {

        PaycodeResponse response = paycodeService.generatePaycode(request,countryCode);

        return response;
    }
    @PostMapping("/cancel_paycode")
    public CancelPaycodeResponse cancelPaycode(@RequestBody CancelPaycodeRequest request) {

        CancelPaycodeResponse response = paycodeService.cancelPaycode(request);

        return response;
    }
}
