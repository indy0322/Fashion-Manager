package fashionmanager.song.develop.MenteeApply.service;

import fashionmanager.song.develop.MenteeApply.aggregate.MenteeApplyEntity;
import fashionmanager.song.develop.MenteeApply.dto.MenteeApplyCreateRequestDTO;
import fashionmanager.song.develop.MenteeApply.dto.MenteeApplyResponseDTO;
import fashionmanager.song.develop.MenteeApply.mapper.MenteeApplyMapper;
import fashionmanager.song.develop.MenteeApply.repository.MenteeRepository;
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

    public List<MenteeApplyResponseDTO> selectResultApply() {
        return menteeApplyMapper.selectResultApply();
    }

    @Transactional
    public MenteeApplyResponseDTO insertMenteeApply(MenteeApplyCreateRequestDTO req) {

        if (req.getAccept() == null || req.getAccept().isBlank()) {
            req.setAccept("대기");
        }

        MenteeApplyEntity entity = new  MenteeApplyEntity();
        entity.setContent(req.getContent());
        entity.setAccept(req.getAccept());
        entity.setMentoringPostNum(req.getMentoringPostNum());
        entity.setMemberNum(req.getMemberNum());

        MenteeApplyEntity entitySaved = menteeRepository.save(entity);

        return new MenteeApplyResponseDTO(
                entitySaved.getNum(),
                entitySaved.getContent(),
                entitySaved.getAccept(),
                entitySaved.getMentoringPostNum(),
                entitySaved.getMemberNum()
        );

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
