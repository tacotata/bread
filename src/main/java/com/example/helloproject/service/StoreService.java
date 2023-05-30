package com.example.helloproject.service;

import com.example.helloproject.data.dto.store.MainStoreDto;
import com.example.helloproject.data.dto.store.StoreResponseDto;
import com.example.helloproject.data.dto.store.StoreSaveRequestDto;
import com.example.helloproject.data.dto.store.StoreUpdateRequestDto;
import com.example.helloproject.data.entity.store.Store;
import com.example.helloproject.data.repository.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class StoreService {

    private final StoreRepository storeRepository;

    @Transactional
    public Long save(StoreSaveRequestDto requestDto){
        return storeRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, StoreUpdateRequestDto requestDto){
        Store store = storeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        store.update(requestDto.getName(), requestDto.getTel(), requestDto.getAddress(), requestDto.getInfo(), requestDto.getLastOrder(), requestDto.isHide_yn(), requestDto.getOpen(), requestDto.getClose(), requestDto.getStartPickupTime(), requestDto.getEndPickupTime(),requestDto.getReserveNum() );
        return id;
    }

    @Transactional
    public Page<Store> searchStore (String search, Pageable pageable){
        return storeRepository.searchStore( search, pageable);
    }

    @Transactional(readOnly = true)
    public StoreResponseDto findById (Long id){
        Store entity = storeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 매장이 없습니다. id =" + id));
        return new StoreResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public Page<MainStoreDto> getStorePage(String search, Pageable pageable){
        return storeRepository.getStorePage(search, pageable);
    }

    @Transactional(readOnly = true)
    public List<StoreResponseDto> findAll(){
        return storeRepository.findAll().stream().map(StoreResponseDto::new).collect(Collectors.toList());
    }
}
