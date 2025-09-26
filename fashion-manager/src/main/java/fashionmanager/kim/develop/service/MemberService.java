package fashionmanager.kim.develop.service;

import fashionmanager.kim.develop.dto.AssignedRightDTO;
import fashionmanager.kim.develop.dto.InsertMemberDTO;
import fashionmanager.kim.develop.dto.MemberDTO;
import fashionmanager.kim.develop.dto.UpdateRightDTO;
import fashionmanager.kim.develop.entity.AssignedRight;
import fashionmanager.kim.develop.entity.Member;
import fashionmanager.kim.develop.mapper.MemberMapper;
import fashionmanager.kim.develop.repository.AssignedRightRepository;
import fashionmanager.kim.develop.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberService {

    private final MemberMapper memberMapper;

    private final MemberRepository memberRepository;
    private final AssignedRightRepository assignedRightRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public MemberService(MemberMapper memberMapper, MemberRepository memberRepository, AssignedRightRepository assignedRightRepository, ModelMapper modelMapper) {
        this.memberMapper = memberMapper;
        this.memberRepository = memberRepository;
        this.assignedRightRepository = assignedRightRepository;
        this.modelMapper = modelMapper;
    }

    public MemberDTO selectMessageAllow(String memberId){
        return memberMapper.selectMessageAllow(memberId);
    }

    public MemberDTO selectMemberByNum(int memberNum){
        return memberMapper.selectMemberByNum(memberNum);
    }

    public List<MemberDTO> selectMember() {
        return memberMapper.selectMember();
    }

    public int insertAdmin(InsertMemberDTO insertMemberDTO) {
        boolean check1 = insertMemberDTO.getMemberId() == null || "".equals(insertMemberDTO.getMemberId());
        boolean check2 = insertMemberDTO.getMemberPwd() == null || "".equals(insertMemberDTO.getMemberPwd());
        boolean check3 = insertMemberDTO.getMemberName() == null || "".equals(insertMemberDTO.getMemberName());
        boolean check4 = insertMemberDTO.getMemberEmail() == null || "".equals(insertMemberDTO.getMemberEmail());
        boolean check5 = insertMemberDTO.getMemberAge() == 0;
        boolean check6 = insertMemberDTO.getMemberGender() != '남' && insertMemberDTO.getMemberGender() != '여';
        if (check1 || check2 || check3 || check4 || check5 || check6) {
            return 0;
        }

        memberRepository.save(modelMapper.map(insertMemberDTO, Member.class));
        return 1;
    }

    public int insertAdminRight(AssignedRightDTO assignedRightDTO) {
        boolean check1 = assignedRightDTO.getAssignedRightMemberStateNum() == 0;
        boolean check2 = assignedRightDTO.getAssignedRightMemberStateNum() == 0;
        if (check1 || check2) {
            return 0;
        }

        assignedRightRepository.save(modelMapper.map(assignedRightDTO, AssignedRight.class));
        return 1;
    }

    @Transactional
    public int updateRight(UpdateRightDTO updateRightDTO) {
        boolean check1 = updateRightDTO.getMemberStateNum() == 0;
        boolean check2 = updateRightDTO.getMemberNum() == 0;
        if (check1 || check2) {
            return 0;
        }
        int result = assignedRightRepository.updateRight(updateRightDTO.getMemberNum(),updateRightDTO.getMemberStateNum());
        if (result == 1) {
            return 1;
        }else{
            return 0;
        }
    }
}



