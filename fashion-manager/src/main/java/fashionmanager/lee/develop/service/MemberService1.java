package fashionmanager.lee.develop.service;

import fashionmanager.lee.develop.repository.MemberRepository1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService1 {

    private final MemberRepository1 memberRepository;


    @Transactional
    public void resetMonthlyGoodCounts() {
        memberRepository.resetAllMonthlyGoodCounts();
    }
}