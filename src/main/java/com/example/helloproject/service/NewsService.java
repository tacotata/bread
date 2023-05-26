package com.example.helloproject.service;

import com.example.helloproject.data.dto.news.NewsListResponseDto;
import com.example.helloproject.data.dto.news.NewsResponseDto;
import com.example.helloproject.data.dto.news.NewsSaveRequestDto;
import com.example.helloproject.data.dto.news.NewsUpdateRequestDto;
import com.example.helloproject.data.entity.news.News;
import com.example.helloproject.data.entity.news.NewsType;
import com.example.helloproject.data.dto.news.NewsDetailDto;
import com.example.helloproject.data.repository.news.NewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        news.update(requestDto.getSubject(), requestDto.getContents(), requestDto.getUpdId(), requestDto.getFileCnt(), requestDto.getType());
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

    //검색
    @Transactional
    public Page<News> searchNewsList (String search, Pageable pageable) {
        return newsRepository.findBySubjectContaining(search, pageable);

    }

    @Transactional
    public Page<News> searchNewsAll (NewsType newsType, String search, Pageable pageable){
        return newsRepository.searchNewsAll(newsType, search, pageable);
    }

    @Transactional(readOnly = true)
    public List<NewsDetailDto> getNewsAndNewsFile (){
        List <NewsDetailDto> newsDetailDtoList = new ArrayList<>();
        newsDetailDtoList = newsRepository.findNewsAndNewsFile();
        return newsDetailDtoList;
    }
}
