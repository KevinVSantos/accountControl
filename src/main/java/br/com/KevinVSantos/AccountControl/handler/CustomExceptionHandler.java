package br.com.KevinVSantos.AccountControl.handler;

import br.com.KevinVSantos.AccountControl.domain.dto.error.DefaultErrorDto;
import br.com.KevinVSantos.AccountControl.handler.exception.EntityAlreadyExistsException;
import br.com.KevinVSantos.AccountControl.handler.exception.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Date;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(AbstractException.class)
    public ResponseEntity<DefaultErrorDto> entityNotFound(AbstractException e, HttpServletRequest request){
        return ResponseEntity.status(e.getHttpStatus()).body(new DefaultErrorDto(e, request));
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

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDto);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<DefaultErrorDto> httpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request)
    {
        var errorDto =
                new DefaultErrorDto(
                        new Date(),
                        e.getMessage(),
                        HttpStatus.BAD_REQUEST,
                        400,
                        request.getRequestURL().toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DefaultErrorDto> methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request){

        var error = e.getBindingResult().getFieldError();
        var message = String.format("%s %s",   error.getField(), error.getDefaultMessage());
        var errorDto =
                new DefaultErrorDto(
                        new Date(),
                        message,
                        HttpStatus.BAD_REQUEST,
                        400,
                        request.getRequestURL().toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<DefaultErrorDto> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HttpServletRequest request){

        var errorDto =
                new DefaultErrorDto(
                        new Date(),
                        e.getMessage(),
                        HttpStatus.BAD_REQUEST,
                        400,
                        request.getRequestURL().toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<DefaultErrorDto> missingServletRequestParameterException(MissingServletRequestParameterException e, HttpServletRequest request)
    {
        var errorDto =
                new DefaultErrorDto(
                        new Date(),
                        e.getMessage(),
                        HttpStatus.BAD_REQUEST,
                        400,
                        request.getRequestURL().toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<DefaultErrorDto> missingRequestHeaderException(MissingRequestHeaderException e, HttpServletRequest request)
    {
        var errorDto =
                new DefaultErrorDto(
                        new Date(),
                        e.getMessage(),
                        HttpStatus.BAD_REQUEST,
                        400,
                        request.getRequestURL().toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }
}
