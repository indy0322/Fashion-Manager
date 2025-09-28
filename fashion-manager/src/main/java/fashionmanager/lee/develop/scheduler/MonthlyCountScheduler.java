package fashionmanager.lee.develop.scheduler;

import fashionmanager.lee.develop.service.MemberService1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MonthlyCountScheduler {

    private final MemberService1 memberService;


    @Scheduled(cron = "0 0 0 1 * ?")
    public void scheduleMonthlyReset() {
        log.info("매월 누적 좋아요 카운트 초기화 작업을 시작합니다.");
        try {

            memberService.resetMonthlyGoodCounts();
            log.info("모든 회원의 누적 좋아요 카운트를 0으로 성공적으로 초기화했습니다.");
        } catch (Exception e) {
            log.error("누적 좋아요 카운트 초기화 작업 중 오류가 발생했습니다.", e);
        }
    }


    public void resetMonthlyGoodCountManually() {
        memberService.resetMonthlyGoodCounts();
    }
}