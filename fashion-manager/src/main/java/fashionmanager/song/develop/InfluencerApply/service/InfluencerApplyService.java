package fashionmanager.song.develop.InfluencerApply.service;

import fashionmanager.song.develop.InfluencerApply.aggregate.InfluencerApplyEntity;
import fashionmanager.song.develop.InfluencerApply.dto.InfluencerApplyCreateRequestDTO;
import fashionmanager.song.develop.InfluencerApply.dto.InfluencerApplyResponseDTO;
import fashionmanager.song.develop.InfluencerApply.mapper.InfluencerApplyMapper;
import fashionmanager.song.develop.InfluencerApply.repository.InfluencerApplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InfluencerApplyService {

    private final InfluencerApplyMapper influencerApplyMapper;
    private final InfluencerApplyRepository influencerApplyRepository;

    @Autowired
    public InfluencerApplyService(InfluencerApplyMapper influencerApplyMapper,
                                  InfluencerApplyRepository influencerApplyRepository) {
        this.influencerApplyMapper = influencerApplyMapper;
        this.influencerApplyRepository = influencerApplyRepository;
    }
    // 인플루언서 신청 전체 조회
    public List<InfluencerApplyResponseDTO> selectResultApply() {
        return influencerApplyMapper.selectResultApply();
    }

    // 인플루언서 신청
    @Transactional
    public InfluencerApplyCreateRequestDTO insertInfluencerApply(InfluencerApplyCreateRequestDTO req) {
//         신청서 상태는 회원이 넣는 것이 아니기 때문에 null 혹은 blank 일떄는 default 상태 -> '대기'
        if (req.getAccept() == null || req.getAccept().isBlank()) {
            req.setAccept("대기");
        }

        InfluencerApplyEntity entity = new InfluencerApplyEntity();
        entity.setTitle(req.getTitle());
        entity.setContent(req.getContent());
        entity.setAccept(req.getAccept());
        entity.setMemberNum(req.getMemberNum());

        InfluencerApplyEntity entitySaved = influencerApplyRepository.save(entity);

        return new InfluencerApplyCreateRequestDTO(
                entitySaved.getNum(),
                entitySaved.getTitle(),
                entitySaved.getContent(),
                entitySaved.getAccept(),
                entitySaved.getMemberNum()
        );
    }
    // 인플루언서 신청 수정
    // 왜 이렇게 했냐 하면
    // repository는 db연결만 하게 하려고 하고
    // service 는 비즈니스 로직들을 하게 하기 위해 하지만 insert는

    @Transactional
    public int updateInfluencerApply(InfluencerApplyResponseDTO req) {
        return influencerApplyRepository.findByNumAndMemberNum(req.getNum(), req.getMemberNum())
                .map(entity -> {
                    entity.setTitle(req.getTitle());
                    entity.setContent(req.getContent());
                    influencerApplyRepository.save(entity);
                    return 1;
                })
                .orElse(0);
    }

    public int deleteInfluencerApplyByTitleAndMemberNum(String title, int memberNum) {
        return influencerApplyRepository.deleteInfluencerApplyByTitleAndMemberNum(title, memberNum);
    }
}