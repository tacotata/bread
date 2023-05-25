package com.example.helloproject.service;


import com.example.helloproject.data.dto.cs.ContactResponseDto;
import com.example.helloproject.data.dto.cs.ContactSaveRequestDto;
import com.example.helloproject.data.dto.news.NewsResponseDto;
import com.example.helloproject.data.dto.news.NewsSaveRequestDto;
import com.example.helloproject.data.dto.users.UsersResponseDto;
import com.example.helloproject.data.dto.users.UsersUpdateRequestDto;
import com.example.helloproject.data.entity.cs.Contact;
import com.example.helloproject.data.entity.user.Users;
import com.example.helloproject.data.entity.user.Withdraw;
import com.example.helloproject.data.repository.cs.ContactRepository;
import com.example.helloproject.data.repository.user.UsersRepository;
import com.example.helloproject.data.repository.user.WithdrawRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final WithdrawRepository withdrawRepository;
    private  final ContactRepository contactRepository;

    public Users saveUser(Users users){
        validateDuplicateUser(users);
        return  usersRepository.save(users);
    }

    private void validateDuplicateUser(Users users){
        Users findUser = usersRepository.findByEmail(users.getEmail());
        if(findUser != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    public Long updateJoinSns(Long id, UsersUpdateRequestDto usersUpdateRequestDto){
        Users users = usersRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("다시 시도해주세요." + id));
        users.updateJoinSns( usersUpdateRequestDto.getBirthyear(), usersUpdateRequestDto.getBirthmonth(), usersUpdateRequestDto.getBirthday(), usersUpdateRequestDto.getMobile(), usersUpdateRequestDto.isPromotionAgree());
        return id;
    }

    @Transactional(readOnly = true)
    public UsersResponseDto findById (Long id){
        Users entity = usersRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("다시 시도해주세요." + id));
        return new UsersResponseDto(entity);
    }

    public boolean chkPwd(Long userId, String checkPassword) {
        Users users = usersRepository.findById(userId).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        String realPassword = users.getPassword();
        return this.passwordEncoder.matches(checkPassword, realPassword);
    }


    public Long updateUserInfo( Long id, UsersUpdateRequestDto requestDto){
        Users users = usersRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 아이디가 없습니다. id=" + id));

        if(!users.getEmail().equals(requestDto.getEmail())){
            Users findUser = usersRepository.findByEmail(requestDto.getEmail());
            if(findUser != null){
                throw new IllegalStateException("이미 가입된 회원입니다.");
            }
        }
        users.updateUserInfo(requestDto.getRole(), requestDto.getName(), requestDto.getEmail(), requestDto.getMobile(), requestDto.getBirthyear(), requestDto.getBirthmonth() ,requestDto.getBirthday(), requestDto.isPromotionAgree(), requestDto.getStoreAddress(), requestDto.getStoreName(), requestDto.getStoreTel(), requestDto.getTeam(), requestDto.getTeamTel() );
        return id;
    }


    public Long updateUserPwd( Long id, String password){
        Users users = usersRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 아이디가 없습니다. id=" + id));
        users.UpdatePwd(password, passwordEncoder);
        return id;
    }

    public Long saveWithdraw(String reason, Users users){
        return withdrawRepository.save(Withdraw.builder().users(users).reason(reason).build()).getId();
    }

    @Transactional(readOnly = true)
    public Withdraw findByUserId (Users users){
        return withdrawRepository.findByUsers(users);
    }

    public Long saveContact(ContactSaveRequestDto requestDto){
        return contactRepository.save(requestDto.toEntity()).getId();
    }

    //contact detail
    @Transactional(readOnly = true)
    public ContactResponseDto findByContactId (Long id){
        Contact entity = contactRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 데이터가 없습니다. id=" + id));
        return new ContactResponseDto(entity);
    }
}
