package br.com.KevinVSantos.AccountControl.controller;

import br.com.KevinVSantos.AccountControl.domain.entity.account.Account;
import br.com.KevinVSantos.AccountControl.domain.entity.payment.Payment;
import br.com.KevinVSantos.AccountControl.service.AccountService;
import br.com.KevinVSantos.AccountControl.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/payment")
public class PaymentController{

    @Autowired
    private PaymentService service;

    @PostMapping
    public ResponseEntity post(
            @RequestHeader String password,
            @RequestBody @Valid Payment entity){

        return ResponseEntity.ok(service.create(entity, password));
    }

    @PostMapping("/revert/{id}")
    public ResponseEntity revert(
            @RequestHeader String password,
            @PathVariable("id") Long id,
            @RequestParam("justification") String justification ){

        return ResponseEntity.ok(service.revertPayment(id, justification , password));
    }

}
