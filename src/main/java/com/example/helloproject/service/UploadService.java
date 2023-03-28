
package com.example.helloproject.service;

import com.example.helloproject.data.dto.news.NewsFileResponseDto;
import com.example.helloproject.data.dto.news.NewsFileSaveDto;
import com.example.helloproject.data.entity.news.NewsFile;
import com.example.helloproject.data.repository.news.NewsFileRepository;
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
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class UploadService {

    @Value("${file.path}")
    private String uploadDir;

    private final NewsFileRepository newsFileRepository;

    //파일 저장
    public boolean saveFile(List<MultipartFile> multipartFiles, Long id, String type)  {
        String localDateTimeFormat
                = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String localDateFormat
                = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String regId = "admin";
        Boolean result = false;
        try {
            if (!CollectionUtils.isEmpty(multipartFiles) && id != null) {
                if (multipartFiles.size() > 0) {
                    for (MultipartFile multipartFile : multipartFiles) {
                        String oriFileName = multipartFile.getOriginalFilename();
                        String extension = oriFileName.substring(oriFileName.lastIndexOf("."));
                        String savedFileName = UUID.randomUUID() + "_" + localDateTimeFormat+extension;
                        String savedFilePath = uploadDir + File.separator + type.toLowerCase() + File.separator + localDateFormat + File.separator;
                        File dir = new File(savedFilePath);
                        if (!dir.exists()) {
                            dir.mkdir();
                        }
                        File targetFile = new File(savedFilePath, savedFileName);
                        multipartFile.transferTo(targetFile);

                        targetFile.setWritable(false);
                        targetFile.setReadable(true);
                        NewsFileSaveDto newsFileSaveDto = NewsFileSaveDto.builder().newsId(id)
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
                            result = true;
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    //news-file 테이블 insert
    @Transactional
    public Long insertFile(NewsFile newsFile) {
        return newsFileRepository.save(newsFile).getId();
    }

    //newsFile 테이블 newsId로  조회
    @Transactional(readOnly = true)
    public List<NewsFileResponseDto> findByNewsId(Long newsId){
        return newsFileRepository.findByNewsId(newsId).stream().map(NewsFileResponseDto::new).collect(Collectors.toList());
    }

    //news File 전체 삭제
    @Transactional
    public List<Long> delete (Long newsId) {
        List<Long> newsFileIds = newsFileRepository.findByNewsFileIds(newsId);
        newsFileRepository.deleteAllByNewsFileIds(newsFileIds);
        return newsFileIds;
    }

    //news File 단건 삭제
    @Transactional
    public void fileDelete (Long id) {
        NewsFile newsFile = newsFileRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        newsFileRepository.delete(newsFile);
    }
}

