package com.example.helloproject.data.entity.news;

import com.example.helloproject.data.dto.news.NewsSaveRequestDto;
import com.example.helloproject.data.dto.news.NewsUpdateRequestDto;
import com.example.helloproject.data.repository.news.NewsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NewsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private NewsRepository newsRepository;

    @After
    public void tearDown() throws Exception{
        newsRepository.deleteAll();
    }


    @Test
    public void newsInsert() throws  Exception {
        //given
        NewsType type = NewsType.valueOf("NOTICE");
        String subject = "제목";
        String contents ="내용";
        String regId ="admin";


        NewsSaveRequestDto requestDto = NewsSaveRequestDto.builder().type(type).subject(subject).contents(contents).regId(regId).build();

        String url = "http://localhost:"+port+"/admin/api/v1/news";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<News> all = newsRepository.findAll();
        assertThat(all.get(0).getSubject()).isEqualTo(subject);
        assertThat(all.get(0).getContents()).isEqualTo(contents);

    }

    @Test
    public void NewsUpdate() throws Exception {
        //given
        News savedNews = newsRepository.save(News.builder().type(NewsType.valueOf("EVENT")).subject("subject").contents("contents").regId("admin").build());

        Long updateId = savedNews.getId();
        String expectedSubject = "subject2";
        String expectedContent = "content2";
        String expectedUpdId = "admin2";

        NewsUpdateRequestDto requestDto = NewsUpdateRequestDto.builder().subject(expectedSubject).contents(expectedContent).updId(expectedUpdId).build();

        String url = "http://localhost:" + port + "/admin/api/v1/news/" + updateId;
        HttpEntity<NewsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<News> all = newsRepository.findAll();
        assertThat(all.get(0).getSubject()).isEqualTo(expectedSubject);
        assertThat(all.get(0).getContents()).isEqualTo(expectedContent);


    }

}
