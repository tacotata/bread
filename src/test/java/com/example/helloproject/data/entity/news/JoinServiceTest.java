package com.example.helloproject.data.entity.news;

import com.example.helloproject.data.dto.users.UsersDto;
import com.example.helloproject.data.entity.user.Users;
/*import com.example.helloproject.data.entity.user.Users;*/
import com.example.helloproject.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@Transactional
@TestPropertySource(properties = {"src/main/resources/application.properties"})
public class JoinServiceTest {

    @Autowired
    UsersService usersService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Users createMember() {
        UsersDto memberFormDto = UsersDto.builder()
                .email("test@email.com")
                .name("테스트")
                .password("1111aaaaaaa")
                .build();
        return Users.createUsers(memberFormDto, passwordEncoder);
    }

/*    @Test
    @DisplayName("회원가입 테스트")
    public void saveMemberTest() {
        UserSns member = createMember();
        UserSns savedMember = usersService.saveUser(member);

        assertEquals(member.getEmail(), savedMember.getEmail());
    }*/
}
