package com.gadashov.instagram.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/21/2024
 * Time: 12:44 PM
 */

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    Integer status;
    String message;
    Object data;

    private Response(Builder builder) {
        status = builder.status;
        message = builder.message;
        data = builder.data;
    }


    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer status;
        private String message;
        private Object data;

        private Builder() {
        }

        public Builder status(HttpStatus val) {
            status = val.value();
            return this;
        }

        public Builder message(String val) {
            message = val;
            return this;
        }

        public Builder data(Object val) {
            data = val;
            return this;
        }

        public Response build() {
            return new Response(this);
        }
    }
}
