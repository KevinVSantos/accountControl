package br.com.KevinVSantos.AccountControl.domain.dto.error;

import br.com.KevinVSantos.AccountControl.handler.AbstractException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.aspectj.bridge.Message;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@AllArgsConstructor
public class DefaultErrorDto {

    public Date dateTime;

    public String message;

    public HttpStatus httpStatus;

    public Integer httpStatusCode;

    public String requestUrl;

    public DefaultErrorDto(AbstractException e, HttpServletRequest request){

        this.dateTime = new Date();

        this.requestUrl = request.getRequestURL().toString();

        this.message = e.getMessage();
        this.httpStatus = e.getHttpStatus();
        this.httpStatusCode = e.getHttpStatus().value();

    }

}
