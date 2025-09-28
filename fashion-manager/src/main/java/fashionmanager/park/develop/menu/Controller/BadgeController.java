package fashionmanager.park.develop.menu.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import fashionmanager.park.develop.menu.Service.BadgeService;
import fashionmanager.park.develop.menu.Service.UserService;
import fashionmanager.park.develop.menu.repository.BadgeRepository;

@Controller
@RequestMapping("/menu")
public class BadgeController {

    private final BadgeService badgeService;
    private final UserService userService; // 유저 조회용
    private final BadgeRepository badgeRepository;

    public BadgeController(BadgeService badgeService,
                           UserService userService,
                           BadgeRepository badgeRepository) {
        this.badgeService = badgeService;
        this.userService = userService;
        this.badgeRepository = badgeRepository;
    }

    @GetMapping("/assignBadge")
    public void assignBadgeForm() {
        // 뱃지 부여 입력 폼
    }

    @PostMapping("/assignBadge")
    public String assignBadge(@RequestParam int userNum, @RequestParam int badgeNum) {
        badgeService.assignBadge(userNum, badgeNum);
        return "redirect:/menu/selectResult?userNum=" + userNum;
    }


    @GetMapping("/removeBadge")
    public void removeBadgeForm() {
        // 그냥 menu/removeBadge.html 열어줌
    }

    @PostMapping("/removeBadge")
    public String removeBadge(@RequestParam int userNum, @RequestParam int badgeNum) {
        badgeService.removeBadge(userNum, badgeNum);
        return "redirect:/menu/selectResult?userNum=" + userNum;
    }
}