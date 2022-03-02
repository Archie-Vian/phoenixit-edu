package phoenixit.education.components;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import phoenixit.education.exceptions.EntityNotFoundException;
import phoenixit.education.exceptions.EntitySaveException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * Хэндлер исключений rest запросов.
 */
@Slf4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            EntitySaveException.class,
            EntityNotFoundException.class
    })
    public ResponseEntity<String> handleCustomException(RuntimeException ex, WebRequest request) {
        log.error(ex.getLocalizedMessage());
        return new ResponseEntity<>(ex.getLocalizedMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<String> handleEntityValidationException(TransactionSystemException ex) {
        Throwable cause = ex.getMostSpecificCause();
        if (cause instanceof ConstraintViolationException) {
            ConstraintViolationException validationException = (ConstraintViolationException) cause;
            String message = formConstraintViolationMessage(validationException);
            log.error("Нарушение валидности сущности:\n" + message);
            return new ResponseEntity<>(message, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        return handleAnyException(ex);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAnyException(Exception ex) {
        log.error("Ошибка выполнения запроса.", ex);
        return new ResponseEntity<>("Во время работы произошла системная ошибка. Обратитесь к администратору.",
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String formConstraintViolationMessage(ConstraintViolationException ex) {
        return ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("\n"));
    }

}
