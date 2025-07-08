package com.luminesway.concurso.utils;

import com.luminesway.concurso.dtos.GenericResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class SpringResult<T> {
    @Getter
    private final T result;
    @Getter
    private final int code;
    private final String message;

    private final List<String> errors;

    public SpringResult(ResultParameters<T> resultParameters, int code) {
        this.result = resultParameters.result();
        this.code = code;
        this.message = resultParameters.message();
        this.errors = resultParameters.errors() != null ? resultParameters.errors() : new ArrayList<>();
    }

    public  static <F> SpringResult<F> success(ResultParameters<F> resultParameters, int code) {
        return new SpringResult<>(resultParameters, code);
    }

    public  static <F> SpringResult<F> error(ResultParameters<F> resultParameters, int code) {
        return new SpringResult<>(resultParameters, code);
    }

    public GenericResponse<?> toJson() {
        HttpStatus status = HttpStatus.valueOf(code);

        return GenericResponse.builder()
                .code(code)
                .message(message != null ? message : status.getReasonPhrase())
                .status(status)
                .data(errors.isEmpty() ? result : errors)
                .build();
    }
}
