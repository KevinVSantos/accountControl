package br.com.KevinVSantos.AccountControl.handler;

import br.com.KevinVSantos.AccountControl.domain.dto.error.DefaultErrorDto;
import br.com.KevinVSantos.AccountControl.handler.exception.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<DefaultErrorDto> entityNotFound(EntityNotFoundException e, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DefaultErrorDto(e, request));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<DefaultErrorDto> entityNotFound(RuntimeException e, HttpServletRequest request){
        var errorDto =
            new DefaultErrorDto(
                new Date(),
        "Erro interno do servidor",
                HttpStatus.INTERNAL_SERVER_ERROR,
                500,
                request.getRequestURL().toString());
        log.error("[Error] RuntimeException: "  + e.getMessage());
        e.printStackTrace();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }
}
