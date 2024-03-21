package com.gadashov.instagram.model.exceptions;

import com.gadashov.instagram.model.enums.Exceptions;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/16/2024
 * Time: 3:19 PM
 */
public class TokenException extends GlobalException {

    public TokenException(Exceptions exceptions) {
        super(exceptions);
    }
}
