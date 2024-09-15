package br.com.sistema.helpdesk.resources.exceptions;

import br.com.sistema.helpdesk.services.exceptions.ObjNotFoundExceptions;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjNotFoundExceptions.class)
    public ResponseEntity<StandardError> standardErrorException(ObjNotFoundExceptions e, HttpServletRequest request) {
        {
            StandardError error = new StandardError();
            error.setTimestamp(System.currentTimeMillis());
            error.setStatus(HttpStatus.NOT_FOUND.value());
            error.setError("Object not found");
            error.setMessage(e.getMessage());
            error.setPath(request.getRequestURI());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }
}
