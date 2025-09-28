package fashionmanager.lee.develop.controller;

import fashionmanager.lee.develop.scheduler.MonthlyCountScheduler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SchedulerController {

    private final MonthlyCountScheduler monthlyCountScheduler;

    @GetMapping("/scheduler/reset-counts")
    public String ResetMonthlyCounts() {
        monthlyCountScheduler.resetMonthlyGoodCountManually();
        return "Monthly good counts가 성공적으로 초기화 되었습니다!";
    }
}