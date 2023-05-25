package com.example.helloproject.controller;

import com.example.helloproject.config.auth.LoginUser;
import com.example.helloproject.config.auth.dto.SessionUser;
import com.example.helloproject.data.dto.cs.ContactResponseDto;
import com.example.helloproject.data.dto.cs.ContactSaveRequestDto;
import com.example.helloproject.data.dto.users.UsersDto;
import com.example.helloproject.data.dto.users.UsersResponseDto;
import com.example.helloproject.data.dto.users.UsersUpdateRequestDto;
import com.example.helloproject.data.entity.user.Role;
import com.example.helloproject.data.entity.user.Users;
import com.example.helloproject.data.repository.user.UsersRepository;
import com.example.helloproject.service.EmailService;
import com.example.helloproject.service.UploadService;
import com.example.helloproject.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class UsersController {

    private final UsersService usersService;
    private final PasswordEncoder passwordEncoder;
    private final HttpSession httpSession;
    private final UsersRepository usersRepository;
    private final UploadService uploadService;
    private final EmailService emailService;


    @GetMapping("/join")
    public String join(Model model, @LoginUser SessionUser user){
        if(user !=null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("role", user.getRole());
        }
        model.addAttribute("usersDto", new UsersDto());
        return "/member/join";
    }


    @PostMapping("/join")
    public String usersJoin( @Valid  @ModelAttribute("usersDto") UsersDto usersDto, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()){
            return"/member/join";
        }
        try {
            Users users = Users.createUsers(usersDto, passwordEncoder);
            usersService.saveUser(users);
        } catch (IllegalStateException e) {
            log.info(e.getMessage());
            model.addAttribute("errorMessage", e.getMessage());
            return "/member/join";
        }
        return "/member/login";
    }

    @GetMapping("/login")
    public String login(Model model){
        return "/member/login";
    }

    @GetMapping("/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
        return"/member/login";
    }

    @GetMapping("/mypage")
    public String mypage(Model model, @LoginUser SessionUser user){
        if(user !=null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("role", user.getRole());
        }
        return "/member/mypage";
    }

    @GetMapping("/modify-before")
    public String modifyBefore(Model model, @LoginUser SessionUser user){
        if(user != null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("role", user.getRole());
            model.addAttribute("email", user.getEmail());
        }
        return "/member/modify-before";
    }

    @PostMapping ("/api/v1/checkPwd")
    @ResponseBody
    public boolean checkPwd(@LoginUser SessionUser user, @RequestParam String checkPassword, Model model){
        log.info("CHECK PWD START");
        Long userId = user.getId();
        if(!usersService.chkPwd(userId, checkPassword)){
            log.info("CHECK PWD FAIL");
            return false;
        }
        log.info("CHECK PWD SUCCESS");
        return true;
    }

    @GetMapping("/modify")
    public String modify(Model model, @LoginUser SessionUser user){
        if(user !=null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("role", user.getRole());
            UsersResponseDto member = usersService.findById(user.getId());
            model.addAttribute("user",member);
        }

        List<String> roleList = new ArrayList<>();
        for(Role role : Role.values()){
            roleList.add(String.valueOf(role));
        }
        model.addAttribute("roleList",roleList );

        return "/member/modify";
    }


    @PutMapping(value = {"/modify/{id}", "/modify"})
    @ResponseBody
    public Long modifyUserInfo( @PathVariable(required = false)  Long id, Model model, @LoginUser SessionUser user, @RequestBody UsersUpdateRequestDto requestDto) {
        log.info("modify user info");
        Long userId;
        try {
             userId = usersService.updateUserInfo(id, requestDto);
             UsersResponseDto usersResponseDto = usersService.findById(userId);
             Users users = usersRepository.findByEmail(usersResponseDto.getEmail());
             httpSession.setAttribute("user", new SessionUser(users));
        } catch (IllegalStateException e) {
            log.info(e.getMessage());
            return 0L;
        }
        return userId;
    }

    @GetMapping("/modify-pwd")
    public String modifyPwd(@LoginUser SessionUser user, Model model ){
        if(user != null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("role", user.getRole());
            UsersResponseDto member = usersService.findById(user.getId());
            model.addAttribute("user", member);
        }

        return "/member/modify-pwd";
    }

    @PutMapping(value = {"/api/v1/modify-pwd/{id}", "/api/v1/modify-pwd"})
    @ResponseBody
    public Long modifyPwdInfo( @PathVariable(required = false)  Long id, @LoginUser SessionUser user, @RequestBody Map<String, Object> param) {
        log.info("MODIFY PWD START");
        String checkPassword = (String) param.get("checkPassword");
        String password = (String) param.get("password");
        Long userId = 0L;
        try {
            if(!usersService.chkPwd(id, checkPassword)){
                log.info("CHECK PWD FAIL");
                return userId;
            }
            log.info("CHECK PWD SUCCESS");
            userId = usersService.updateUserPwd(id, password);
        }catch (Exception e) {
            log.info(e.getMessage());
        }
        return userId;
    }

    @GetMapping("/withdraw")
    public String withdraw(@LoginUser SessionUser user, Model model ){
        if(user != null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("role", user.getRole());
            UsersResponseDto member = usersService.findById(user.getId());
            model.addAttribute("user", member);
        }
        return "/member/withdraw";
    }

    @PostMapping ("/api/v1/withdraw/{id}")
    @ResponseBody
    public Long saveWithdraw(@PathVariable(required = false)  Long id, @LoginUser SessionUser user, Model model, @RequestBody Map<String, Object> param){
        log.info("WITHDRAW START");
        String password = (String) param.get("password");
        String reason = (String) param.get("reason");
        if(!usersService.chkPwd(id, password)){
            log.info("CHECK PWD FAIL");
            return -1L;
        }
        log.info("CHECK PWD SUCCESS");
        Users users = usersRepository.findByEmail(user.getEmail());
        return usersService.saveWithdraw(reason, users);
    }

    @GetMapping("/contact")
    public String contact(Model model, @LoginUser SessionUser user ){
        if(user !=null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("role", user.getRole());
            model.addAttribute("userId", user.getId());
        }
        return "/contact";
    }

    @PostMapping("/api/v1/contact")
    @ResponseBody
    public Long saveCs(@LoginUser SessionUser user, @RequestPart(value = "file", required = false) List<MultipartFile> files, @RequestPart(value = "key") ContactSaveRequestDto requestDto) throws IOException {
        log.info("===============CONTACT SAVE START ====================");
        String type ="cs";
        Long contactId =0L;
        try {
            contactId = usersService.saveContact(requestDto);
            ContactResponseDto contactResponseDto = usersService.findByContactId(contactId);
            log.info("INSERT CONTACT ID : {} ", contactId);
            if (contactId != 0) {
                if (!files.get(0).isEmpty()) {
                    Long fileId = uploadService.saveFiles(files, contactId, type);
                    log.info("INSERT CONTACT ID : {} ", fileId);
                }
                boolean sendEmail = emailService.sendContact(files, contactResponseDto);
                log.info("SEND EMAIL : {} ", sendEmail);
            }
        }catch (Exception e){
            log.info(e.getMessage());
        }
        log.info("===============CONTACT SAVE END ====================");
        return contactId;
    }


/*
    @RequestMapping(value = "/pw-search" , method = RequestMethod.GET)
    public String pwSearch( ){
        return "/member/pw-search";
    }

    @RequestMapping(value = "/id-search" , method = RequestMethod.GET)
    public String idSearch( ){
        return "/member/id-search";
    }
*/


}
