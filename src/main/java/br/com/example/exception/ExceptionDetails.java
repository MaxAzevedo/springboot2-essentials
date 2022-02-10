package br.com.example.exception;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class ExceptionDetails {
    protected String title;
    protected LocalDateTime timestamp;
    protected Integer status;
    protected String details;
    protected String developerMessage;
}
