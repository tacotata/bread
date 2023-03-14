package com.example.helloproject.data.entity.admin;

import com.example.helloproject.data.entity.admin.news.*;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsTest {

    @Autowired
    NewsRepository newsRepository;

    @Autowired
    NewsFileRepository newsFileRepository;

    @After
    public void cleanup(){
        newsRepository.deleteAll();
    }

    @Test
    public void noticeSave(){
        //given
        NewsType type = NewsType.valueOf("EVENT");
        String subject = "제목";
        String contents = "내용입니다.";
        String regId = "admin";

        newsRepository.save(News.builder().type(type).subject(subject).contents(contents).regId(regId).build());

        //when
        List<News> newsList = newsRepository.findAll();

        //then
        News news = newsList.get(0);
        System.out.println("id  ="+ news.getId());
        System.out.println("content  ="+ news.getContents());
        System.out.println("type  ="+ news.getType());
        System.out.println("regDate  ="+ news.getRegDate());

        assertThat(news.getSubject()).isEqualTo(subject);

    }

    @Test
    public void newsFindId(){
        //given
        NewsType type = NewsType.valueOf("EVENT");
        String subject = "제목";
        String contents = "내용입니다.";
        String regId = "admin";

        newsRepository.save(News.builder().type(type).subject(subject).contents(contents).regId(regId).build());

        //when
        Long id = 1L;
        Optional<News> news = newsRepository.findById(id);

        //then
        Assertions.assertThat(news.isPresent());
    }

    @Test
    public void newsFileSave()
            throws Exception {
        //given
        MockMultipartFile file1
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );

        MockMultipartFile file2
                = new MockMultipartFile(
                "image",
                "hello.jpg",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, Universe!".getBytes()
        );

        newsFileRepository.save(NewsFile.builder().newsId(1L).fileName(file1.getName()).oriFileName(file1.getOriginalFilename()).filePath("C:\\resourses\\images").extension(file1.getOriginalFilename().substring(file1.getOriginalFilename().lastIndexOf("."))).newsId(0L).regId("admin").build());

        //when
        List<NewsFile> newsFileList = newsFileRepository.findAll();

        //then
        NewsFile newsFile = newsFileList.get(0);
        System.out.println("id  = "+ newsFile.getId());
        System.out.println("oriName  = "+ newsFile.getOriFileName());
        System.out.println("extension  = "+ newsFile.getExtension());
        System.out.println("filename  = "+ newsFile.getFileName());

        assertThat(newsFile.getFileName()).isEqualTo(file1.getName());
    }
}