package fashionmanager.kim.develop.service;

import fashionmanager.kim.develop.dto.MemberDTO;
import fashionmanager.kim.develop.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberMapper memberMapper;

    @Autowired
    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public MemberDTO selectMessageAllow(String memberId){
        return memberMapper.selectMessageAllow(memberId);
    }

    public MemberDTO selectMemberByNum(int memberNum){
        return memberMapper.selectMemberByNum(memberNum);
    }
}


