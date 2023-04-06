package com.maxmilhas.identitycheckapi.exception;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class StandardError implements Serializable {
    final private Instant timestamp;
    final private Integer status;
    final private String error;
    final private String message;
    final private String path;
}
