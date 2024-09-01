package br.com.KevinVSantos.AccountControl.handler.exception;

import br.com.KevinVSantos.AccountControl.handler.AbstractException;
import org.springframework.http.HttpStatus;

public class ForbiddenException extends AbstractException {


    public ForbiddenException(){
        super("Forbidden");
    }

    public HttpStatus getHttpStatus(){
        return HttpStatus.FORBIDDEN;
    }
}
