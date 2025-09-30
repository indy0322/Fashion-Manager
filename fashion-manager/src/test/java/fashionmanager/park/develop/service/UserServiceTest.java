package fashionmanager.park.develop.service;

import fashionmanager.park.develop.menu.DTO.UserDTO;
import fashionmanager.park.develop.menu.Service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
@Transactional  //  테스트 끝나면 롤백
class UserServiceTest {

    @Autowired
    private UserService userService;

    @DisplayName("회원가입 테스트")
    @Test
    void testRegistUser() {
        UserDTO newUser = new UserDTO();
        newUser.setUserId("testUser123456");
        newUser.setUserEmail("testUser123456@example.com");
        newUser.setUserPwd("password123456!");
        newUser.setUserName("로버트 다우니 주니어");
        newUser.setUserAge(55);
        newUser.setUserGender("남");

        Assertions.assertDoesNotThrow(() -> {
            UserDTO savedUser = userService.registUser(newUser);
            System.out.println(savedUser);
        });
    }
}