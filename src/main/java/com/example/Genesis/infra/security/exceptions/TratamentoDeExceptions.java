package com.example.Genesis.infra.security.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TratamentoDeExceptions {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        String message = "Erro de integridade de dados: verifique se os dados relacionados existem.";

        if (ex.getMessage().contains("foreign key") || ex.getCause().toString().contains("ConstraintViolationException")) {
            message = "Empresa relacionada não encontrada ou inválida.";
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
}
