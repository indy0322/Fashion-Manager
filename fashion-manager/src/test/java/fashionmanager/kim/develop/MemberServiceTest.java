package fashionmanager.kim.develop;

import fashionmanager.kim.develop.dto.MemberDTO;
import fashionmanager.kim.develop.dto.SelectMassageDTO;
import fashionmanager.kim.develop.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @DisplayName("회원 번호로 회원 조회")
    @ParameterizedTest
    @ValueSource(ints = {1,2})
    void testSelectMemberByNum(int memberNum){

        Assertions.assertDoesNotThrow(
                ()->{
                    MemberDTO member = memberService.selectMemberByNum(memberNum);
                    System.out.println(member);
                }
        );
    }
}
