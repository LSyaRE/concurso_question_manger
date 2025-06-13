package com.luminesway.concurso.core;

import com.luminesway.concurso.dtos.GenericResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
public class Result<T> {
    private T result;
    private int code;
    private List<String> errors;

    public  static <F> Result<F> success(F value, int code) {
        return new Result<>(value, code, null);
    }

    public  static <F> Result<F> error(List<String> value, int code) {
        return new Result<>(null, code, value);
    }

    public GenericResponse<T> toJson(String message) {
        return GenericResponse.<T>builder()
                .code(code)
                .message(message)
                .status(HttpStatus.resolve(code))
                .data(result)
                .build();
    }
}
