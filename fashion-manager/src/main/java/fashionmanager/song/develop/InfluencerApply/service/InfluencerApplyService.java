package fashionmanager.song.develop.InfluencerApply.service;

import fashionmanager.song.develop.InfluencerApply.aggregate.InfluencerApplyEntity;
import fashionmanager.song.develop.InfluencerApply.dto.InfluencerApplyCreateRequestDTO;
import fashionmanager.song.develop.InfluencerApply.dto.InfluencerApplyResponseDTO;
import fashionmanager.song.develop.InfluencerApply.mapper.InfluencerApplyMapper;
import fashionmanager.song.develop.InfluencerApply.repository.InfluencerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InfluencerApplyService {

    private final InfluencerApplyMapper influencerApplyMapper;
    private final InfluencerRepository influencerRepository;

    @Autowired
    public InfluencerApplyService(InfluencerApplyMapper influencerApplyMapper,
                                  InfluencerRepository influencerRepository) {
        this.influencerApplyMapper = influencerApplyMapper;
        this.influencerRepository = influencerRepository;
    }

    public List<InfluencerApplyResponseDTO> selectResultApply() {
        return influencerApplyMapper.selectResultApply();
    }

    @Transactional
    public InfluencerApplyResponseDTO insertInfluencerApply(InfluencerApplyCreateRequestDTO req) {
//         신청서 상태는 회원이 넣는 것이 아니기 때문에 null 혹은 blank 일떄는 default 상태 -> '대기'
        if (req.getAccept() == null || req.getAccept().isBlank()) {
            req.setAccept("대기");
        }

        InfluencerApplyEntity entity = new InfluencerApplyEntity();
        entity.setTitle(req.getTitle());
        entity.setContent(req.getContent());
        entity.setAccept(req.getAccept());
        entity.setMemberNum(req.getMemberNum());

        InfluencerApplyEntity entitySaved = influencerRepository.save(entity);

        return new InfluencerApplyResponseDTO(
                entitySaved.getNum(),
                entitySaved.getTitle(),
                entitySaved.getContent(),
                entitySaved.getAccept(),
                entitySaved.getMemberNum()
        );
    }

    @Transactional
    public int updateInfluencerApply(InfluencerApplyResponseDTO req) {
        return influencerRepository.findById(req.getNum())
                                   .map(entity -> {
                                    entity.setTitle(req.getTitle());
                                    entity.setContent(req.getContent());
                                    // 필요하면 accept도 수정
                                    influencerRepository.save(entity);
                                    return 1;
                                    })
                                    .orElse(0);
    }

    public int deleteInfluencerApplyByTitleAndMemberNum(String title, int memberNum) {
        return influencerRepository.deleteInfluencerApplyByTitleAndMemberNum(title, memberNum);
    }
}