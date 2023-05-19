package com.example.helloproject.service;


import com.example.helloproject.data.dto.users.UsersResponseDto;
import com.example.helloproject.data.dto.users.UsersUpdateRequestDto;
import com.example.helloproject.data.entity.user.Users;
import com.example.helloproject.data.repository.user.UsersRepository;
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
}
