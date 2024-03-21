package com.gadashov.instagram.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Value;


/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/21/2024
 * Time: 1:43 PM
 */

@Data
public class CreateCommentRequest {
    @JsonProperty("parent_id")
    Long parentId;
    String message;
}
