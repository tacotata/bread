package com.example.helloproject.service;

import com.example.helloproject.data.dto.item.ItemFormDto;
import com.example.helloproject.data.dto.item.ItemsFileResponseDto;
import com.example.helloproject.data.dto.item.MainItemDto;
import com.example.helloproject.data.entity.menu.Items;
import com.example.helloproject.data.entity.menu.ItemsFile;
import com.example.helloproject.data.repository.items.ItemsFileRepository;
import com.example.helloproject.data.repository.items.ItemsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class ItemsService {

    private final  ItemsRepository itemsRepository;
    private final ItemsFileRepository itemsFileRepository;
    private final  UploadService uploadService;

    public Long saveItem(String  type, ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList
    ) throws Exception{
        Long id =  itemsRepository.save(itemFormDto.toEntity()).getId();
        Long fileId =   uploadService.saveFiles(itemImgFileList, id, type);
        return fileId;
    }

    @Transactional(readOnly = true)
    public Page<MainItemDto> getItemPage(String search, Pageable pageable){
        return itemsRepository.getItemPage(search, pageable);
    }

    @Transactional(readOnly = true)
    public ItemFormDto getItemDetail(Long itemsId){
        List <ItemsFile> itemFileList = itemsFileRepository.findByItemsIdOrderByIdAsc(itemsId);
        List<ItemsFileResponseDto> itemsFileResponseDtoList = new ArrayList<>();
        for(ItemsFile itemsFile : itemFileList){
            ItemsFileResponseDto itemsFileResponseDto = ItemsFileResponseDto.builder().entity(itemsFile).build();
            itemsFileResponseDtoList.add(itemsFileResponseDto);
        }

        Items items = itemsRepository.findById(itemsId).orElseThrow(EntityNotFoundException::new);
        ItemFormDto itemFormDto = ItemFormDto.builder().entity(items).build();
        itemFormDto.setItemsFileResponseDtoList(itemsFileResponseDtoList);
        return itemFormDto;
    }

    //아이템 업데이트
    public Long updateItem(ItemFormDto itemFormDto, List<MultipartFile>  itemImgFileList, String type) throws Exception{

        Items item = itemsRepository.findById(itemFormDto.getId()).orElseThrow(EntityNotFoundException::new);
        item.updateItem(itemFormDto);
        log.info("변경 ITEMS ID : {}", item.getId());
        List<Long> itemFileIds = itemFormDto.getItemFileIds();

        for(int i =0; i < itemFileIds.size(); i++){
            uploadService.updateFile(itemFileIds.get(i), itemImgFileList.get(i), type);
       }
        return item.getId();
    }

    //아이템 상태 업데이트
    public Long updateItemStatus(Long itemsId , String itemsStatus){
        Items item = itemsRepository.findById(itemsId).orElseThrow(EntityNotFoundException::new);
        item.updateItemStatus(itemsStatus);
        return item.getId();
    }

}
