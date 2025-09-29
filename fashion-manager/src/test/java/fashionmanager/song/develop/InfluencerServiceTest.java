package fashionmanager.song.develop;

import fashionmanager.song.develop.InfluencerApply.dto.InfluencerApplyResponseDTO;
import fashionmanager.song.develop.InfluencerApply.service.InfluencerApplyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class InfluencerServiceTest {

    @Autowired
    private InfluencerApplyService influencerApplyService;

    @DisplayName("인플루언서 신청 조회 테스트")
    @Test
    void testSelcetInfluencerApply() {
        Assertions.assertDoesNotThrow(
                () -> {
            List<InfluencerApplyResponseDTO> influencerList = influencerApplyService.selectResultApply();
            influencerList.forEach(System.out::println);
        });
    }
}
