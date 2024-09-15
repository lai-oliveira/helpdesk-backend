package br.com.sistema.helpdesk.services.exceptions;

public class ObjNotFoundExceptions extends RuntimeException{

    public ObjNotFoundExceptions(String message) {
        super(message);
    }

    public ObjNotFoundExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}
