package com.example.helloproject.service;


import com.example.helloproject.data.dto.cs.ContactResponseDto;
import com.example.helloproject.data.entity.cs.ContactFile;
import com.example.helloproject.data.repository.cs.ContactFileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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



}
