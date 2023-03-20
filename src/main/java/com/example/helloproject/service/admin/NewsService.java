package com.example.helloproject.service.admin;

import com.example.helloproject.data.dto.admin.news.NewsListResponseDto;
import com.example.helloproject.data.dto.admin.news.NewsResponseDto;
import com.example.helloproject.data.dto.admin.news.NewsSaveRequestDto;
import com.example.helloproject.data.dto.admin.news.NewsUpdateRequestDto;
import com.example.helloproject.data.entity.admin.news.News;
import com.example.helloproject.data.entity.admin.news.NewsRepository;
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
    public List<NewsListResponseDto> findNewsAllDesc(){
        return newsRepository.findNewsAllDesc().stream().map(NewsListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public int delete (Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        newsRepository.delete(news);
        return news.getFileCnt();
    }

    /* Paging */
    @Transactional(readOnly = true)
    public Page<News> pageList(Pageable pageable) {
        return newsRepository.findAll(pageable);
    }
}
