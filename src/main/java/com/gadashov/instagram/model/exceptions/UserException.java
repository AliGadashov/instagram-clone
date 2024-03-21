package com.gadashov.instagram.model.exceptions;

import com.gadashov.instagram.model.enums.Exceptions;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/14/2024
 * Time: 1:20 PM
 */

public class UserException extends GlobalException {

    public UserException(Exceptions exceptions) {
        super(exceptions);
    }
}
