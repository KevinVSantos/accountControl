package br.com.KevinVSantos.AccountControl.handler.exception;

import br.com.KevinVSantos.AccountControl.handler.AbstractException;
import org.springframework.http.HttpStatus;

public class PaymentInsuficientBalanceException extends AbstractException {


    public PaymentInsuficientBalanceException(){
        super("Insuficient balance");
    }

    public HttpStatus getHttpStatus(){
        return HttpStatus.BAD_REQUEST;
    }
}
