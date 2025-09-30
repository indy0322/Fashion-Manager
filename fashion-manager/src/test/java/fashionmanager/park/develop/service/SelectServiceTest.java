package fashionmanager.park.develop.service;

import fashionmanager.park.develop.menu.DTO.UserDTO;
import fashionmanager.park.develop.menu.Service.SelectService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SelectServiceTest {

    @Autowired
    SelectService selectService;

    @DisplayName("전체 회원 조회 테스트")
    @Test
    void testFindAllUsers() {
        Assertions.assertDoesNotThrow(() -> {
            List<UserDTO> users = selectService.findAllUsers();
            users.forEach(System.out::println); // 콘솔 출력 확인
        });
    }
}
