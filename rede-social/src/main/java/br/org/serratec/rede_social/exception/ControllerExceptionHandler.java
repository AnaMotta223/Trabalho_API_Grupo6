package br.org.serratec.rede_social.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmailException.class)
   private ResponseEntity<Object> handleEmailException( EmailException ex){
       return ResponseEntity.unprocessableEntity().body(ex.getMessage());
   }

   @ExceptionHandler(SenhaException.class)
    private ResponseEntity<Object> handleSenhaException( SenhaException ex){
        return ResponseEntity.unprocessableEntity().body(ex.getMessage());
   }
}

