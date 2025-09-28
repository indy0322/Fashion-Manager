package fashionmanager.lee.develop.service; // 패키지 경로는 사용자님 프로젝트에 맞게 유지

// import 경로가 MemberRepository를 올바르게 가리키는지 확인
import fashionmanager.lee.develop.repository.SchedulerMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SchedulerService {

    private final SchedulerMemberRepository schedulerMemberRepository;

    @Transactional
    public void resetMonthlyGoodCounts() {
        schedulerMemberRepository.resetAllMonthlyGoodCounts();
    }
}