package com.example.helloproject.service;


import com.example.helloproject.data.dto.cs.ContactResponseDto;
import com.example.helloproject.data.dto.users.UsersResponseDto;
import com.example.helloproject.data.entity.cs.ContactFile;
import com.example.helloproject.data.repository.cs.ContactFileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    private final ContactFileRepository contactFileRepository;
    private final UsersService usersService;


    //Contact 메일발송
    public boolean sendContact(List<MultipartFile> multipartFiles, ContactResponseDto contactResponseDto)  {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // use multipart (true)

            mimeMessageHelper.setSubject(contactResponseDto.getTitle());
            mimeMessageHelper.setTo("siyeon0u0v@gmail.com");
            mimeMessageHelper.setText("이메일 :"+contactResponseDto.getEmail()+ "\n유형 : "+contactResponseDto.getCsType()+"\n"+contactResponseDto.getContents());
            if(!CollectionUtils.isEmpty(multipartFiles)) {
             List<ContactFile> contactFileList =  contactFileRepository.findByContactId(contactResponseDto.getId());
               for(int i=0; i < contactFileList.size(); i++ ) {
                    File dir = new File(contactFileList.get(i).getFilePath() + contactFileList.get(i).getFileName());
                    mimeMessageHelper.addAttachment(contactFileList.get(i).getFileName(), dir);
               }
            }
            javaMailSender.send(mimeMessage);
        }catch (Exception e){
            log.info(e.getMessage());
            return false;
        }
        return true ;
    }

    //임시비밀번호 생성
    public String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',   'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z', '!','@','#','$','%','^','&','*','(',')'  };

        String str = "";

        int idx = 0;
        for (int i = 0; i < 9; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        log.info("임시비밀번호{}",str);
        return str;
    }

    //비밀번호 찾기
    public boolean sendTempPw(Long id, String pw){

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            UsersResponseDto usersResponseDto =usersService.findById(id);
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // use multipart (true)
            mimeMessageHelper.setSubject("안녕하세요 고객님 BREAD. 입니다.");
            mimeMessageHelper.setTo(usersResponseDto.getEmail());
            mimeMessageHelper.setText("임시 비밀번호 보내드립니다.\n임시 비밀번호 : "+pw+"\n로그인 후 꼭 비밀번호를 변경해주세요.\n감사합니다." );
            javaMailSender.send(mimeMessage);
        }catch (Exception e){
            log.info(e.getMessage());
            return false;
        }
        return true;
    }


}
