package com.accend.book.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorDetails {

    private LocalDateTime timeStamp;
    private String details;
    private String message;

}
