package com.gadashov.instagram.model.exceptions;

import com.gadashov.instagram.model.enums.Exceptions;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/16/2024
 * Time: 3:07 PM
 */

public class OtpException extends GlobalException {

    public OtpException(Exceptions exceptions) {
        super(exceptions);
    }
}
