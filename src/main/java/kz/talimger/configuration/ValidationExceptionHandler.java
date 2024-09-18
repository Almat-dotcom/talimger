package kz.talimger.configuration;

import jakarta.servlet.http.HttpServletRequest;
import kz.talimger.exception.KazNpuException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ControllerAdvice
@Slf4j
public class ValidationExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleAllExceptions(Exception ex, HttpServletRequest request) {
        ErrorResponseDto errorResponse = new ErrorResponseDto(ex);
        errorResponse.setMessage(ex.getMessage());
        log.error("Unhandled exception occurred: {}", ex.getMessage(), ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(KazNpuException.class)
    protected ResponseEntity<ErrorResponseDto> handleHttpError(KazNpuException e,
                                                               HttpServletRequest httpServletRequest) {
        String message = getMessage(httpServletRequest);
        ErrorResponseDto body = new ErrorResponseDto(e);
        log.error(body.id + ": " + message, e);
        return ResponseEntity.status(e.getStatus())
                .body(body);
    }

    private String getMessage(HttpServletRequest httpServletRequest) {
        return "Запрос ручки " + httpServletRequest.getRequestURI() + " завершился провалом:";
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class ErrorResponseDto {

        private final String id = UUID.randomUUID().toString().substring(0, 8);
        private final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        private String type;
        private String code;
        private String message;

        ErrorResponseDto(Exception e) {
            message = e.getMessage();
            type = e.getClass().getSimpleName();
        }

        ErrorResponseDto(KazNpuException e) {
            message = e.getMessage();
            code = e.getCode();
            type = e.getClass().getSimpleName();
        }
    }
}
