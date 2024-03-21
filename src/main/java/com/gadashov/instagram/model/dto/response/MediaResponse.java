package com.gadashov.instagram.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gadashov.instagram.model.enums.PostType;
import lombok.Builder;
import lombok.Data;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/21/2024
 * Time: 1:40 PM
 */

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MediaResponse {
    Long id;
    String url;
    @JsonProperty("media_type")
    PostType mediaType;
}