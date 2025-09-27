package fashionmanager.song.develop.InfluencerPage.service;

import fashionmanager.song.develop.InfluencerPage.aggregate.InfluencerPageEntity;
import fashionmanager.song.develop.InfluencerPage.dto.InfluencerPageCreateRequestDTO;
import fashionmanager.song.develop.InfluencerPage.dto.InfluencerPageResponseDTO;
import fashionmanager.song.develop.InfluencerPage.mapper.InfluencerPageMapper;
import fashionmanager.song.develop.InfluencerPage.repository.InfluencerPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InfluencerPageService {

    private final InfluencerPageMapper influencerPageMapper;
    private final InfluencerPageRepository influencerPageRepository;


    @Autowired
    public InfluencerPageService(InfluencerPageMapper influencerPageMapper,
                                 InfluencerPageRepository influencerPageRepository) {
        this.influencerPageMapper = influencerPageMapper;
        this.influencerPageRepository = influencerPageRepository;
    }


    // 페이지 조건 조회, 혹은 전체 조회
    public List<InfluencerPageResponseDTO> selectResultPage(String title,
                                                            String insta,
                                                            String phone,
                                                            Integer memberNum) {
        return influencerPageMapper.selectResultPage(title, insta, phone, memberNum);

    }
    // 페이지 생성
    @Transactional
    public InfluencerPageCreateRequestDTO insertInfluencerPage(InfluencerPageCreateRequestDTO req) {

        //  여기서 인스타랑 폰 이랑은 null 확인

        InfluencerPageEntity entity = new InfluencerPageEntity();
        entity.setTitle(req.getTitle());
        entity.setContent(req.getContent());
        entity.setInsta(req.getInsta());
        entity.setPhone(req.getPhone());
        entity.setMemberNum(req.getMemberNum());

        InfluencerPageEntity saved = influencerPageRepository.save(entity);

        InfluencerPageCreateRequestDTO res = new InfluencerPageCreateRequestDTO();
        res.setNum(saved.getNum());
        res.setTitle(saved.getTitle());
        res.setContent(saved.getContent());
        res.setInsta(saved.getInsta());
        res.setPhone(saved.getPhone());
        res.setMemberNum(saved.getMemberNum());
        return res;
    }

    // 페이지 수정

    @Transactional
    public int updateInfluencerPage(InfluencerPageResponseDTO req) {
        return influencerPageRepository
                .findByNumAndMemberNumAndMember_MemberName(req.getNum(), req.getMemberNum(), req.getMemberName())
                .map(entity -> {
                    entity.setTitle(req.getTitle());
                    entity.setContent(req.getContent());
                    entity.setInsta(req.getInsta());
                    entity.setPhone(req.getPhone());
                    influencerPageRepository.save(entity);
                    return 1;
                })
                .orElse(0);
    }


    public int deleteInfluencerPageTitleAndMemberNum(String title, int memberNum) {
        return influencerPageRepository.deleteInfluencerPageTitleAndMemberNum(title, memberNum);
    }
}
