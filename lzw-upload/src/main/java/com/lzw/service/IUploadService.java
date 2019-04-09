package com.lzw.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by LZW on 2019/4/9.
 */
public interface IUploadService {

    String uploadImage(MultipartFile file);

}
