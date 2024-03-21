package com.gadashov.instagram.model.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/9/2024
 * Time: 8:28 PM
 */

@Getter
public enum Exceptions {

    POST_NOT_FOUND(HttpStatus.BAD_REQUEST, "exception.post.not_found"),
    FILE_NOT_ALLOWED(HttpStatus.BAD_REQUEST, "exception.file.not_allowed"),
    FILE_UPLOAD_FAIL(HttpStatus.BAD_REQUEST, "exception.file.upload.fail"),
    FILE_REQUIRED(HttpStatus.BAD_REQUEST, "exception.file.required"),
    FILE_DELETING_FAIL(HttpStatus.BAD_REQUEST, "exception.file.delete.fail"),

    USERNAME_OR_EMAIL_EXISTS(HttpStatus.BAD_REQUEST, "exception.user.fail.account.username_or_email"),
    USER_INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "exception.user.fail.account.invalid.password"),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "exception.user.fail.not_found"),
    USER_NOT_ACTIVATED(HttpStatus.FORBIDDEN, "exception.user.fail.account.not_activated"),
    USER_IS_LOCKED(HttpStatus.FORBIDDEN, "exception.user.fail.account.is_locked"),
    USER_MISMATCH_PASSWORD(HttpStatus.BAD_REQUEST, "exception.user.fail.account.mismatch_password"),
    CURRENT_PASSWORD_NEW_PASSWORD(HttpStatus.BAD_REQUEST, "exception.user.fail.account.current_and_new_password_match"),

    EMAIL_SEND_ERROR(HttpStatus.BAD_REQUEST, "exception.email.fail.send"),
    OPT_NOT_FOUND(HttpStatus.BAD_REQUEST, "exception.otp.not_found"),
    OTP_EXPIRED(HttpStatus.BAD_REQUEST, "exception.otp.expired"),
    LIKE_NOT_FOUND(HttpStatus.BAD_REQUEST, "exception.like.not_found"),
    LIKE_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "exception.like.exists"),
    FOLLOWING_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "exception.follow.not_exists"),
    FOLLOWING_NOT_FOUND(HttpStatus.BAD_REQUEST, "exception.follow.not_found"),
    COMMENT_NOT_FOUND(HttpStatus.BAD_REQUEST, "exception.comment.not_found"),
    NOT_YOUR_POST(HttpStatus.FORBIDDEN, "exception.post.not_your_post"),
    MEDIA_UPLOAD_FAIL(HttpStatus.BAD_REQUEST, "exception.media.failed_to_upload");


    private final String message;
    private final HttpStatus status;
    Exceptions(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}