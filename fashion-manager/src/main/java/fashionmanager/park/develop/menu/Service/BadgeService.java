package fashionmanager.park.develop.menu.Service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import fashionmanager.park.develop.menu.Entity.Badge;
import fashionmanager.park.develop.menu.Entity.User;
import fashionmanager.park.develop.menu.repository.BadgeRepository;
import fashionmanager.park.develop.menu.repository.UserRepository;

@Service
public class BadgeService {

    private final UserRepository userRepository;
    private final BadgeRepository badgeRepository;

    public BadgeService(UserRepository userRepository, BadgeRepository badgeRepository) {
        this.userRepository = userRepository;
        this.badgeRepository = badgeRepository;
    }


    // 뱃지 수여
    @Transactional
    public void assignBadge(int userNum, int badgeNum) {
        User user = userRepository.findById(userNum)
                .orElseThrow(() -> new RuntimeException("회원 없음"));

        Badge badge = badgeRepository.findById(badgeNum)
                .orElseThrow(() -> new RuntimeException("뱃지 없음"));

        // 관계 추가
        user.addBadge(badge);

        // User 저장 → assigned_badge 테이블에도 insert 발생
        userRepository.save(user);
    }



    // 뱃지 삭제
    @Transactional
    public void removeBadge(int userNum, int badgeNum) {
        User user = userRepository.findById(userNum)
                .orElseThrow(() -> new RuntimeException("회원 없음"));
        Badge badge = badgeRepository.findById(badgeNum)
                .orElseThrow(() -> new RuntimeException("뱃지 없음"));

        user.getBadges().remove(badge);
        badge.getUsers().remove(user);

        userRepository.save(user); // JPA가 assigned_badge 테이블에서 DELETE 실행
    }
}