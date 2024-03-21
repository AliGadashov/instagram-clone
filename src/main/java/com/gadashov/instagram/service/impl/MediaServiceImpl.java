package com.gadashov.instagram.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.gadashov.instagram.model.dto.response.MediaResponse;
import com.gadashov.instagram.model.entity.Media;
import com.gadashov.instagram.model.entity.Post;
import com.gadashov.instagram.model.enums.Exceptions;
import com.gadashov.instagram.model.enums.PostType;
import com.gadashov.instagram.model.exceptions.GlobalException;
import com.gadashov.instagram.repository.MediaRepository;
import com.gadashov.instagram.service.MediaService;
import com.gadashov.instagram.mapper.MediaMapper;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/21/2024
 * Time: 2:09 PM
 */

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {
    private final MediaRepository repository;
    private final MediaMapper mapper;
    private final Cloudinary cloudinary;

    private static List<String> imageTypes = List.of("jpg", "jpeg", "png");
    private static List<String> videoTypes = List.of("mp4", "avi");

    @Override
    @Transactional
    public void save(Post post, MultipartFile[] files) {


        for (MultipartFile file : files) {
            PostType mediaType = isAllowedFiles(file);
            String url = saveCloudinary(file);
            Media media = Media.builder()
                    .post(post)
                    .type(mediaType)
                    .url(url)
                    .build();
            repository.save(media);
        }
    }

    @Override
    public List<MediaResponse> getByPost(Post post) {
        List<Media> media = repository.findByPost(post);
        List<MediaResponse> response = mapper.toResponse(media);
        return response;
    }

    @Override
    public void delete(Media media) {
        try {
            cloudinary.uploader().destroy(media.getUrl(), ObjectUtils.emptyMap());
            repository.delete(media);
        } catch (IOException e) {
            throw new GlobalException(Exceptions.FILE_DELETING_FAIL);
        }
    }

    @Override
    public void deleteAll(List<Media> media) {
        media.forEach(this::delete);
    }

    private PostType isAllowedFiles(@NotNull MultipartFile file) {


        String fileName = file.getOriginalFilename();
        int length = fileName.split("\\.").length;
        if (length < 2)
            throw new GlobalException(Exceptions.FILE_NOT_ALLOWED);

        String extension = fileName.split("\\.")[1];
        if (imageTypes.contains(extension)) {
            return PostType.IMAGE;
        } else if (videoTypes.contains(extension)) {
            return PostType.VIDEO;
        }

        throw new GlobalException(Exceptions.FILE_NOT_ALLOWED);

    }

    private String saveCloudinary(MultipartFile file) {

        HashMap<Object, Object> options = new HashMap<>();
        options.put("folder", "products");
        try {
            Map uploadedFile = cloudinary.uploader().upload(file.getBytes(), options);
            return (String) uploadedFile.get("public_id");
        } catch (IOException e) {
            throw new GlobalException(Exceptions.MEDIA_UPLOAD_FAIL);
        }

    }
}
