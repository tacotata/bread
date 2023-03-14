package com.example.helloproject.service.admin;

import com.example.helloproject.data.dto.admin.news.NewsListResponseDto;
import com.example.helloproject.data.dto.admin.news.NewsResponseDto;
import com.example.helloproject.data.dto.admin.news.NewsSaveRequestDto;
import com.example.helloproject.data.dto.admin.news.NewsUpdateRequestDto;
import com.example.helloproject.data.entity.admin.news.News;
import com.example.helloproject.data.entity.admin.news.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NewsService {
    private final NewsRepository newsRepository;

    //news 테이블 insert
    @Transactional
    public Long save(NewsSaveRequestDto requestDto){
        return newsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, NewsUpdateRequestDto requestDto){
        News news = newsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        news.update(requestDto.getSubject(), requestDto.getContents(), requestDto.getUpdId(), requestDto.getFileCnt());
        return id;
    }

    //news 테이블 fileCnt update
    @Transactional
    public Long updateFileCnt(Long id, int fileCnt){
        News news = newsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        news.updateFileCnt(fileCnt);
        return id;
    }

    //news-detail
    @Transactional(readOnly = true)
    public NewsResponseDto findById (Long id){
        News entity = newsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new NewsResponseDto(entity);
    }

    //news 테이블 전체 조회
    @Transactional(readOnly = true)
    public List<NewsListResponseDto> findNoticeAllDesc(){
        return newsRepository.findNoticeAllDesc().stream().map(NewsListResponseDto::new).collect(Collectors.toList());
    }
}
