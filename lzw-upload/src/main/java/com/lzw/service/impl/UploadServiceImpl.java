package com.lzw.service.impl;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.lzw.config.UploadProperties;
import com.lzw.core.exception.FtpException;
import com.lzw.service.IUploadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by LZW on 2019/4/9.
 */
@Service
@Slf4j
public class UploadServiceImpl implements IUploadService {

    @Autowired
    private UploadProperties prop;

    @Autowired
    private FastFileStorageClient storageClient;

    @Override
    public String uploadImage(MultipartFile file) {
        String contentType = file.getContentType();
        if (!prop.getAllowTypes().contains(contentType)) {
            throw new FtpException("【文件上传】上传文件格式错误");
        }

        //检验文件内容
        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null) {
                log.info("【文件上传】上传文件格式错误");
                throw new FtpException("【文件上传】上传文件格式错误");
            }
        } catch (IOException e) {
            log.info("【文件上传】文件上传失败", e);
            throw new FtpException("【文件上传】文件上传失败");
        }

        //保存图片
        try {
            String extensionName = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
            StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), extensionName, null);
            //返回保存图片的完整url
            return prop.getBaseUrl() + storePath.getFullPath();
        } catch (IOException e) {
            throw new FtpException("【文件上传】文件上传失败");
        }
    }
}
