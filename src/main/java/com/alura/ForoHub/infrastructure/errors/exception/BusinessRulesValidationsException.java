package com.alura.ForoHub.infrastructure.errors.exception;

public class BusinessRulesValidationsException extends RuntimeException{
    public BusinessRulesValidationsException(String mensaje){
        super(mensaje);
    }
}