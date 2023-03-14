
package com.example.helloproject.service;

import com.example.helloproject.data.dto.admin.news.NewsFileSaveDto;
import com.example.helloproject.data.entity.admin.news.NewsFile;
import com.example.helloproject.data.entity.admin.news.NewsFileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class UploadService {

    @Value("${file.path}")
    private String uploadDir;

    private final NewsFileRepository newsFileRepository;


    public String saveFile(List<MultipartFile> multipartFiles, Long id, String type)  {

        String localDateTimeFormat
                = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String localDateFormat
                = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        Long newsId = id;
        String regId = "admin";
        String result = "FAIL";
        try {
            if (!CollectionUtils.isEmpty(multipartFiles) && id != null) {
                if (multipartFiles.size() > 0) {
                    for (MultipartFile multipartFile : multipartFiles) {
                        String oriFileName = multipartFile.getOriginalFilename();
                        String extension = oriFileName.substring(oriFileName.lastIndexOf("."));
                        String savedFileName = UUID.randomUUID() + "_" + localDateTimeFormat;
                        String savedFilePath = uploadDir + File.separator + type.toLowerCase() + File.separator + localDateFormat + File.separator;
                        File dir = new File(savedFilePath);
                        if (!dir.exists()) {
                            dir.mkdir();
                        }
                        File targetFile = new File(savedFilePath, savedFileName + extension);
                        multipartFile.transferTo(targetFile);

                        targetFile.setWritable(false);
                        targetFile.setReadable(true);
                        NewsFileSaveDto newsFileSaveDto = NewsFileSaveDto.builder().newsId(newsId)
                                .fileName(savedFileName)
                                .oriFileName(oriFileName)
                                .filePath(savedFilePath)
                                .extension(extension)
                                .regId(regId)
                                .build();

                        Long fileId = insertFile(newsFileSaveDto.toEntity());

                        log.info("===================================");
                        log.info("file ID : {}", fileId);
                        log.info("oriFileName : {}", oriFileName);
                        log.info("file name : {}", savedFileName);
                        log.info("file path : {}", savedFilePath);
                        log.info("===================================");

                        if (fileId != null) {
                            result = "SUCCESS";
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Transactional
    public Long insertFile(NewsFile newsFile) {
        return newsFileRepository.save(newsFile).getId();
    }
}

