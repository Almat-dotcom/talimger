package kz.talimger.exception;

import kz.talimger.util.Bundle;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KazNpuException extends RuntimeException {

    HttpStatus status;
    String code;

    public KazNpuException(HttpStatus status, String code, String message, Object... objects) {
        super(Bundle.resolve(message, objects));
        this.status = status;
        this.code = code;
    }

    public KazNpuException(String message, HttpStatus status, String code, Throwable cause) {
        super(message, cause);
        this.status = status;
        this.code = code;
    }

    public static Supplier<KazNpuException> QSightCoreExceptionSupplier(
            HttpStatus status,
            String errorCodeConstant,
            String message,
            Object... objects
    ) {
        return () -> new KazNpuException(
                status,
                errorCodeConstant,
                Bundle.resolve(message, objects)
        );
    }
}