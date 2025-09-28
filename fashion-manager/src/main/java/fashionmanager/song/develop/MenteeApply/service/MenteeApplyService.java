package fashionmanager.song.develop.menteeApply.service;

import fashionmanager.song.develop.menteeApply.aggregate.MenteeApplyEntity;
import fashionmanager.song.develop.menteeApply.dto.MenteeApplyCreateRequestDTO;
import fashionmanager.song.develop.menteeApply.dto.MenteeApplyResponseDTO;
import fashionmanager.song.develop.menteeApply.mapper.MenteeApplyMapper;
import fashionmanager.song.develop.menteeApply.repository.MenteeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenteeApplyService {

    private final MenteeApplyMapper menteeApplyMapper;
    private final MenteeRepository menteeRepository;

    @Autowired
    public MenteeApplyService(MenteeApplyMapper menteeApplyMapper,
                              MenteeRepository menteeRepository) {
        this.menteeApplyMapper = menteeApplyMapper;
        this.menteeRepository = menteeRepository;
    }

    // 멘토링 신청 조회
    public List<MenteeApplyResponseDTO> selectResultApply(String content,
                                                          String accept,
                                                          Integer mentoringPostNum,
                                                          String memberName) {
        return menteeApplyMapper.selectResultApply(content, accept, mentoringPostNum, memberName);
    }

    @Transactional
    public MenteeApplyCreateRequestDTO insertMenteeApply(MenteeApplyCreateRequestDTO req) {

        if (req.getAccept() == null || req.getAccept().isBlank()) {
            req.setAccept("대기");
        }

        MenteeApplyEntity entity = new  MenteeApplyEntity();
        entity.setContent(req.getContent());
        entity.setAccept(req.getAccept());
        entity.setMentoringPostNum(req.getMentoringPostNum());
        entity.setMemberNum(req.getMemberNum());

        MenteeApplyEntity entitySaved = menteeRepository.save(entity);

        MenteeApplyCreateRequestDTO reqSaved = new MenteeApplyCreateRequestDTO();
        reqSaved.setContent(entitySaved.getContent());
        reqSaved.setAccept(entitySaved.getAccept());
        reqSaved.setMentoringPostNum(entitySaved.getMentoringPostNum());
        reqSaved.setMemberNum(entitySaved.getMemberNum());
        return reqSaved;
    }

    @Transactional
    public int updateMenteeApply(MenteeApplyResponseDTO req) {
        return menteeRepository.findById(req.getNum())
                .map(entity -> {
                    entity.setContent(req.getContent());
                    menteeRepository.save(entity);
                    return 1;
                })
                .orElse(0);
    }

    public int deleteMenteeApplyByMentoringPostNumAndMemberNum(String content, int mentoringPostNum, int memberNum) {
        return menteeRepository.deleteMenteeApplyByMentoringPostNumAndMemberNum(content, mentoringPostNum, memberNum);
    }
}
