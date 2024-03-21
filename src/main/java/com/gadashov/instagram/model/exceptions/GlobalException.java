package com.gadashov.instagram.model.exceptions;

import com.gadashov.instagram.model.enums.Exceptions;
import lombok.Getter;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/9/2024
 * Time: 8:29 PM
 */


@Getter
public class GlobalException extends RuntimeException {
    private final Exceptions exceptions;
    public GlobalException(Exceptions exceptions) {
        super(exceptions.getMessage());
        this.exceptions = exceptions;
    }
}
