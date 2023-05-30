package com.example.helloproject.data.entity.news;

import com.example.helloproject.data.repository.news.NewsFileRepository;
import com.example.helloproject.data.repository.news.NewsRepository;
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
    public void newsSave(){
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

//    @Test
//    public void BaseTimeEntity_save(){
//        //given
//        LocalDateTime now  = LocalDateTime.of(2023,2,22,0,0,0);
//        newsRepository.save(News.builder()
//                .type(NewsType.EVENT)
//                .subject("subject")
//                .contents("contents")
//                .build());
//        //when
//        List<News> newsList = newsRepository.findAll();
//
//        //then
//        News news = newsList.get(0);
//        System.out.println(">>>>>>>> regDate =" + news.getRegDate()+", updateDate = "+news.getUpdDate());
//        assertThat(news.getRegDate()).isAfter(now);
//        assertThat(news.getUpdDate()).isAfter(now);
//    }

//    @Test
//    public void update(){
//        News savedNews = newsRepository.save(News.builder().type(NewsType.valueOf("EVENT")).subject("subject").contents("contents").regId("admin").build());
//
//        Long updateId = savedNews.getId();
//        String expectedSubject = "subject2";
//        String expectedContent = "content2";
//        String expectedUpdId = "admin2";
//
//        NewsUpdateRequestDto requestDto = NewsUpdateRequestDto.builder().subject(expectedSubject).contents(expectedContent).updId(expectedUpdId).build();
//
//
//
//    }


//        @Test
//        public void delete(){
//            //마찬가지로 select를 먼저하고
//            Optional<User> user = userRepository.findById(3L);
//
//            //데이터가 조회가 되어야 삭제를 할 수 있기 때문에 반드시 true여야 한다.
//            Assert.assertTrue(user.isPresent());
//
//            user.ifPresent(selectUser->{
//                userRepository.delete(selectUser);
//            });
//
//            //삭제가 제대로 이뤄졌는지 확인
//            Optional<User> deleteUser = userRepository.findById(3L);
//
//            //데이터가 삭제됐으면 isPresent는 false가 나와야 한다.
//            Assert.assertFalse(deleteUser.isPresent());
//
//        /*if(deleteUser.isPresent()){
//            System.out.println("데이터 존재 : "+deleteUser.get());
//        }else{
//            System.out.println("데이터 없음");
//        }*/
//        }

}