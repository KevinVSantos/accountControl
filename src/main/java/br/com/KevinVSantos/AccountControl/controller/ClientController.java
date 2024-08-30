package br.com.KevinVSantos.AccountControl.controller;

import br.com.KevinVSantos.AccountControl.handler.exception.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/client")
public class ClientController {

    @GetMapping
    public ResponseEntity<String> get(){

        throw new EntityNotFoundException();
        //return ResponseEntity.ok("Teste212121122");
    }

}
