package fashionmanager.song.develop.influencerPage.service;

import fashionmanager.baek.develop.entity.PhotoEntity;
import fashionmanager.baek.develop.repository.PhotoRepository;
import fashionmanager.song.develop.influencerPage.aggregate.InfluencerPageEntity;
import fashionmanager.song.develop.influencerPage.dto.InfluencerPageCreateRequestDTO;
import fashionmanager.song.develop.influencerPage.dto.InfluencerPageResponseDTO;
import fashionmanager.song.develop.influencerPage.mapper.InfluencerPageMapper;
import fashionmanager.song.develop.influencerPage.repository.InfluencerPageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor   // 생성자 자동으로 해주는 어노테이션
public class InfluencerPageService {

    private final InfluencerPageMapper influencerPageMapper;
    private final InfluencerPageRepository influencerPageRepository;

//    /*이미지 파일 업로드 코드 추가*/
//    private final PhotoRepository photoRepository;
//    private String UploadPath = "C:\\uploadFiles\\Influencer_Page";
//    private static final int CATEGORY_CODE = 6;
//
//    /*이미지 파일 경로 지정*/
//    @Value(("${C:/lecture/uploadFiles}"))
//    private String filePath;


    // 페이지 조건 조회, 혹은 전체 조회
    public List<InfluencerPageResponseDTO> selectResultPage(String title,
                                                            String insta,
                                                            String phone,
                                                            Integer memberNum) {

        return influencerPageMapper.selectResultPage(title, insta, phone, memberNum);

//        사진 테스트중
//        return List<InfluencerPageResponseDTO> list
//                = influencerPageMapper.selectResultPage(title, insta, phone, memberNum);
//
//        for (InfluencerPageResponseDTO influencerPageResponseDTO : list) {
//            int pageNum = influencerPageResponseDTO.getNum();
//
//            List<String> paths = photoRepository
//                    .findAllByPostNumAndPhotoCategoryNum(pageNum, CATEGORY_CODE)
//                    .stream()
//                    .map(p -> p.getPath() + File.separator + p.getName())
//                    .toList();
//            influencerPageResponseDTO.setPhotoPaths(paths);
//        }
//        return list;
    }


    // 페이지 생성
    @Transactional
    public InfluencerPageCreateRequestDTO insertInfluencerPage(InfluencerPageCreateRequestDTO req, List<MultipartFile> files) {

        //  여기서 인스타랑 폰은 null 확인
        InfluencerPageEntity entity = new InfluencerPageEntity();
        entity.setTitle(req.getTitle());
        entity.setContent(req.getContent());
        entity.setInsta(req.getInsta());
        entity.setPhone(req.getPhone());
        entity.setMemberNum(req.getMemberNum());

        InfluencerPageEntity saved = influencerPageRepository.save(entity);


        InfluencerPageCreateRequestDTO reqSaved = new InfluencerPageCreateRequestDTO();
        reqSaved.setNum(saved.getNum());
        reqSaved.setTitle(saved.getTitle());
        reqSaved.setContent(saved.getContent());
        reqSaved.setInsta(saved.getInsta());
        reqSaved.setPhone(saved.getPhone());
        reqSaved.setMemberNum(saved.getMemberNum());
        return reqSaved;
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
