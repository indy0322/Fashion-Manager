package fashionmanager.song.develop.InfluencerApply.service;

import fashionmanager.song.develop.InfluencerApply.dto.InfluencerApplyCreateRequestDTO;
import fashionmanager.song.develop.InfluencerApply.dto.InfluencerApplyResponseDTO;
import fashionmanager.song.develop.InfluencerApply.mapper.InfluencerApplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfluencerApplyService {

    private final InfluencerApplyMapper influencerApplyMapper;

    @Autowired
    public InfluencerApplyService(InfluencerApplyMapper mapper) {
        this.influencerApplyMapper = mapper;
    }

    public List<InfluencerApplyResponseDTO> selectResultApply() {
        return influencerApplyMapper.selectResultApply();
    }

    public InfluencerApplyResponseDTO insertInfluencerApply(InfluencerApplyCreateRequestDTO req) {
        /* 설명. Accept는 회원 입장에서 작성할 필요가 없는데 추가로 개발 예정 */
        if (req.getAccept() == null || req.getAccept().isBlank()) {
            req.setAccept("대기");
        }
        influencerApplyMapper.insertInfluencerApply(req);

        InfluencerApplyResponseDTO res = new InfluencerApplyResponseDTO();
        res.setNum(req.getNum() == null ? 0 : req.getNum());
        res.setTitle(req.getTitle());
        res.setContent(req.getContent());
        res.setAccept(req.getAccept());
        res.setMemberNum(req.getMemberNum());
        return res;
    }

    public int updateInfluencerApply(InfluencerApplyCreateRequestDTO req) {
        if (req.getNum() == null) return 0; // 방어
        return influencerApplyMapper.updateInfluencerApply(
                req.getNum(),
                req.getTitle(),
                req.getContent(),
                req.getMemberNum()
        );
    }

    public int deleteInfluencerApplyByTitleAndMember(String title, int memberNum) {
        return influencerApplyMapper.deleteInfluencerApplyByTitleAndMember(title, memberNum);
    }
}