package com.bank.BankAPI.controllers;

import com.bank.BankAPI.models.DTO.ThirdPartyDTO;
import com.bank.BankAPI.services.interfaces.ThirdPartyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ThirdPartyController {

    @Autowired
    ThirdPartyServiceInterface thirdPartyService;


    @PatchMapping("/third_party/transfer")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public HttpStatus transferFromThirdPartyToAccounts(@RequestHeader String hashedKey, @RequestBody ThirdPartyDTO thirdPartyDTO){
        // Void o devolver un DTo con datos algo envio.
        try {
            thirdPartyService.makeThirdPartyTransferToAccounts(hashedKey, thirdPartyDTO);
            return HttpStatus.ACCEPTED ;
        } catch (Exception e) {
            System.err.println("The account does not exist");
            return HttpStatus.NOT_FOUND;
        }
    }



}
