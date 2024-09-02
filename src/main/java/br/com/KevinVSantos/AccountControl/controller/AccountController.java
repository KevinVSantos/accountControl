package br.com.KevinVSantos.AccountControl.controller;

import br.com.KevinVSantos.AccountControl.domain.entity.account.Account;
import br.com.KevinVSantos.AccountControl.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(value = "/account")
public class AccountController{

    @Autowired
    private AccountService service;

    @GetMapping
    public ResponseEntity get(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{agency}/{id}")
    public ResponseEntity get(@PathVariable("agency") Integer agency,
                              @PathVariable("id") Integer id)
    {
        return ResponseEntity.ok(service.findById(id, agency));
    }

    @PostMapping
    public ResponseEntity post(@RequestHeader String password,
                               @RequestBody @Valid Account entity){
        return ResponseEntity.ok(service.create(entity));
    }

    @PutMapping
    public ResponseEntity put(@RequestHeader String password,
                              @RequestBody @Valid Account entity){
        return ResponseEntity.ok(service.update(entity));
    }

    @DeleteMapping("/{agency}/{id}")
    public ResponseEntity delete(@PathVariable("agency") Integer agency,
                                 @PathVariable("id") Integer id)
    {
        var account= service.findById(id, agency);
        service.delete(account.getGenericId());
        return new ResponseEntity(HttpStatusCode.valueOf(200));
    }

}
