package com.gadashov.instagram.service;

import com.gadashov.instagram.model.dto.response.MediaResponse;
import com.gadashov.instagram.model.entity.Media;
import com.gadashov.instagram.model.entity.Post;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/21/2024
 * Time: 2:04 PM
 */
public interface MediaService {
    void save(Post post, MultipartFile[] singleMedia);

    List<MediaResponse> getByPost(Post post);

    void delete(Media media);

    void deleteAll(List<Media> media);
}
