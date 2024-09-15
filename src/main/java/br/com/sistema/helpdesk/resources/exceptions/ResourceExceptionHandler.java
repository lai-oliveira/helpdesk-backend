package br.com.sistema.helpdesk.resources.exceptions;

import br.com.sistema.helpdesk.services.exceptions.DataIntegrityViolationException;
import br.com.sistema.helpdesk.services.exceptions.ObjNotFoundExceptions;
import br.com.sistema.helpdesk.services.exceptions.ValidationError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request) {
        {
            StandardError error = new StandardError();
            error.setTimestamp(System.currentTimeMillis());
            error.setStatus(HttpStatus.BAD_REQUEST.value());
            error.setError("Violação de dados");
            error.setMessage(e.getMessage());
            error.setPath(request.getRequestURI());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validationErrors(MethodArgumentNotValidException e, HttpServletRequest request) {
        {
            ValidationError errors = new ValidationError();
            errors.setTimestamp(System.currentTimeMillis());
            errors.setStatus(HttpStatus.BAD_REQUEST.value());
            errors.setError("Validation error");
            errors.setMessage("Erro nas validações dos campos");
            errors.setPath(request.getRequestURI());

            for(FieldError error : e.getBindingResult().getFieldErrors()) {
                errors.addError(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(errors);
        }
    }
}
