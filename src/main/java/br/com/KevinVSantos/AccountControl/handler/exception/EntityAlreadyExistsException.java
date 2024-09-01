package br.com.KevinVSantos.AccountControl.handler.exception;

import br.com.KevinVSantos.AccountControl.handler.AbstractException;
import org.springframework.http.HttpStatus;

public class EntityAlreadyExistsException extends AbstractException {


    public EntityAlreadyExistsException(){
        super("Entity already exists");
    }

    public HttpStatus getHttpStatus(){
        return HttpStatus.CONFLICT;
    }
}
