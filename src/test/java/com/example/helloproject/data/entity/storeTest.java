/*
package com.example.helloproject.data.entity;


import com.example.helloproject.data.dto.store.StoreResponseDto;
import com.example.helloproject.data.dto.store.StoreSaveRequestDto;
import com.example.helloproject.data.dto.store.StoreUpdateRequestDto;
import com.example.helloproject.data.entity.store.Store;
import com.example.helloproject.data.entity.store.StoreImg;
import com.example.helloproject.data.repository.store.StoreImgRepository;
import com.example.helloproject.data.repository.store.StoreRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class storeTest {
    @Autowired
    StoreRepository storeRepository;

    @Autowired
    StoreImgRepository storeImgRepository;

    @Test
    public void Store_oneToone() {


        Store store = Store.builder().name("태평점").tel("02-123-1234").build();
        StoreImg storeImg = StoreImg.builder().imgName("태평정이미지").build();
        StoreSaveRequestDto storeSaveRequestDto = StoreSaveRequestDto.builder().name("서면점").address("주소").hours("12:00~17:00").tel("051-123-1234").hide_yn(false).build();
        Store store =

        storeRepository.save(store);
        StoreUpdateRequestDto storeUpdateRequestDto = StoreUpdateRequestDto.builder().name("서면점").address("주소").hours("12:00~17:00").tel("051-123-1234").hide_yn(false).build();
        store.update(StoreUpdateRequestDto.builder().name("서면점").address("주소").hours("12:00~17:00").tel("051-123-1234").hide_yn(false).build(););
        studentsRepository.save(student);

        Students ss = studentsRepository.findById(student.getId()).get();
        assertThat(ss.getCourse().getName().equals(courseName));

    }
}
*/
