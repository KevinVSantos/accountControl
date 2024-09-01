package br.com.KevinVSantos.AccountControl.handler.exception;

import br.com.KevinVSantos.AccountControl.handler.AbstractException;
import org.springframework.http.HttpStatus;

public class PaymentAmountException extends AbstractException {


    public PaymentAmountException(){
        super("Amount must be greater than zero");
    }

    public HttpStatus getHttpStatus(){
        return HttpStatus.BAD_REQUEST;
    }
}
