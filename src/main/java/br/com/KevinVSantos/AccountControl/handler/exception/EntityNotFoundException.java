package br.com.KevinVSantos.AccountControl.handler.exception;

import br.com.KevinVSantos.AccountControl.handler.AbstractException;
import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends AbstractException {


    public EntityNotFoundException(){
        super("Entity not found");
    }

    public EntityNotFoundException(String message){
        super(message);
    }

    public HttpStatus getHttpStatus(){
        return HttpStatus.NOT_FOUND;
    }
}
