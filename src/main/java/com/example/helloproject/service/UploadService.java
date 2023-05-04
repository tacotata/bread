
package com.example.helloproject.service;

import com.example.helloproject.data.dto.item.ItemFileSaveDto;
import com.example.helloproject.data.dto.news.NewsFileResponseDto;
import com.example.helloproject.data.dto.news.NewsFileSaveDto;
import com.example.helloproject.data.dto.store.StoreFileResponseDto;
import com.example.helloproject.data.dto.store.StoreFileSaveDto;
import com.example.helloproject.data.entity.menu.Items;
import com.example.helloproject.data.entity.menu.ItemsFile;
import com.example.helloproject.data.entity.news.NewsFile;
import com.example.helloproject.data.entity.store.StoreFile;
import com.example.helloproject.data.repository.items.ItemsFileRepository;
import com.example.helloproject.data.repository.news.NewsFileRepository;
import com.example.helloproject.data.repository.store.StoreFileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityExistsException;
import java.io.File;
import java.io.FileOutputStream;
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
    private final StoreFileRepository storeFileRepository;
    private final ItemsFileRepository itemsFileRepository;

    //파일 저장
    public Long saveFiles(List<MultipartFile> multipartFiles, Long id, String type)  {
        String localDateTimeFormat
                = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String localDateFormat
                = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String regId = "admin";
        Long fileId =0L;
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

                        if("store".equals(type)){
                            StoreFileSaveDto storeFileSaveDto = StoreFileSaveDto.builder()
                                    .storeId(id)
                                    .fileName(savedFileName)
                                    .oriFileName(oriFileName)
                                    .filePath(savedFilePath)
                                    .extension(extension)
                                    .build();
                            fileId = insertStoreFile(storeFileSaveDto.toEntity());

                        }else if("item".equals(type)){
                            ItemFileSaveDto itemFileSaveDto = ItemFileSaveDto.builder()
                                    .items(Items.builder().id(id).build())
                                    .fileName(savedFileName)
                                    .oriFileName(oriFileName)
                                    .filePath(savedFilePath)
                                    .extension(extension)
                                    .build();

                            fileId = insertItemFile(itemFileSaveDto.toEntity());

                        }else{
                            NewsFileSaveDto newsFileSaveDto = NewsFileSaveDto.builder().newsId(id)
                                    .fileName(savedFileName)
                                    .oriFileName(oriFileName)
                                    .filePath(savedFilePath)
                                    .extension(extension)
                                    .regId(regId)
                                    .build();

                            fileId = insertFile(newsFileSaveDto.toEntity());

                        }

                        log.info("===================================");
                        log.info("file ID : {}", fileId);
                        log.info("oriFileName : {}", oriFileName);
                        log.info("file name : {}", savedFileName);
                        log.info("file path : {}", savedFilePath);
                        log.info("===================================");
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return fileId;
    }

    //아이템 파일 업데이트
    public void updateFile(Long id, MultipartFile multipartFile, String type) throws Exception{

        if(!multipartFile.isEmpty()){
            String localDateFormat
                    = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            ItemsFile savedItemsFile = itemsFileRepository.findById(id).orElseThrow(EntityExistsException::new);

            if(!StringUtils.isEmpty(savedItemsFile.getOriFileName())){
                this.deleteFile(savedItemsFile.getFilePath()+"/"+savedItemsFile.getFileName());
            }

            String oriFileName = multipartFile.getOriginalFilename();
            String fileName = this.uploadFile(uploadDir, oriFileName, multipartFile.getBytes(), type);
            String imgUrl = uploadDir + File.separator + type.toLowerCase() + File.separator + localDateFormat + File.separator;
            String extension = fileName.substring(fileName.lastIndexOf("."));

            log.info("ORI FILE NAME : {}", oriFileName);
            savedItemsFile.updateItemFile(fileName, oriFileName, imgUrl, extension);

        }
    }

    //아이템 파일 업로드
    public String uploadFile(String uploadPath, String originalFileName, byte[] fileData, String type) throws Exception{

        String localDateFormat
                = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String localDateTimeFormat
                = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String savedFileName = UUID.randomUUID() + "_" + localDateTimeFormat+extension;
        String fileUploadFullUrl =  uploadDir + File.separator + type.toLowerCase() + File.separator + localDateFormat + File.separator;

        File dir = new File(fileUploadFullUrl);
        if (!dir.exists()) {
            dir.mkdir();
        }

        log.info("SAVED FILE NAME : {}", savedFileName);
        log.info("FILE PATH : {}", fileUploadFullUrl);

        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl+savedFileName);
        fos.write(fileData);
        fos.close();

        return savedFileName;
    }

    //아이템 파일 삭제
    public void deleteFile(String filePath)throws Exception{

        File deleteFile = new File(filePath);
        if(deleteFile.exists()){
            deleteFile.delete();
            log.info("파일을 삭제했습니다.");
        }else{
            log.info("파일이 존재하지 않습니다.");
        }
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


    //store-file 테이블 insert
    @Transactional
    public Long insertStoreFile(StoreFile storeFile) {
        return storeFileRepository.save(storeFile).getId();
    }

    //store Id로 store_file 데이터 찾기
    @Transactional(readOnly = true)
    public StoreFileResponseDto findByStoreId (Long id){
        StoreFile entity = storeFileRepository.findByStoreId(id);
        return new StoreFileResponseDto(entity);
    }

    //store File 삭제
    @Transactional
    public void storeFileDelete (Long id) {
        StoreFile storeFile = storeFileRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 이미지가 없습니다. id=" + id));
        storeFileRepository.delete(storeFile);
    }

    //item_file insert
    @Transactional
    public Long insertItemFile(ItemsFile itemsFile) {
        return itemsFileRepository.save(itemsFile).getId();
    }

}

