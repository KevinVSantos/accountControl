package br.com.KevinVSantos.AccountControl.handler;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

public abstract class AbstractException extends RuntimeException{

    public AbstractException(String message){
        super(message);
    }

    public abstract HttpStatus getHttpStatus();
}
