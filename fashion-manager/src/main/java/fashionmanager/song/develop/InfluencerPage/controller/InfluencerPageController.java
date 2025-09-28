package fashionmanager.song.develop.influencerPage.controller;

import fashionmanager.baek.develop.entity.PhotoEntity;
import fashionmanager.song.develop.influencerPage.dto.InfluencerPageCreateRequestDTO;
import fashionmanager.song.develop.influencerPage.dto.InfluencerPageResponseDTO;
import fashionmanager.song.develop.influencerPage.service.InfluencerPageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/influencerPage")
@Slf4j
public class InfluencerPageController {
    private final InfluencerPageService influencerPageService;

    @Autowired
    public InfluencerPageController(InfluencerPageService influencerPageService) {
        this.influencerPageService = influencerPageService;
    }


    // 인플루언서 페이지 조건 조회
    @GetMapping("/selectInfluencerPage")
    public ResponseEntity<List<InfluencerPageResponseDTO>> searchResultPage(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String insta,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) Integer memberNum) {
        List<InfluencerPageResponseDTO> searchPageList
                = influencerPageService.selectResultPage(title, insta, phone, memberNum);
        for (InfluencerPageResponseDTO influencerPage : searchPageList) {
            log.info("인플루언서 페이지 조건 조회: {}", influencerPage);
        }

        return ResponseEntity.ok(searchPageList);
    }


    // 인플루언서 페이지 생성 + 사진 추가
    // 파일 이미지와 DTO를 같이 받으려면 RequestPart를 써야함
    @PostMapping("/insertInfluencerPage")
    public ResponseEntity<InfluencerPageCreateRequestDTO> insertInfluencerPage(
            @RequestPart("data") InfluencerPageCreateRequestDTO req,
            @RequestPart(value = "files", required = false) List<MultipartFile> files) {

        InfluencerPageCreateRequestDTO saved = influencerPageService.insertInfluencerPage(req, files);

        if (saved != null) {
            log.info("인플루언서 페이지 생성 완료!: {}", saved);
            return ResponseEntity.ok(saved);
        } else {
            log.info("인플루언서 페이지 생성 실패!: {}", req);
            return ResponseEntity.badRequest().build();
        }
    }

    // 인플루언서 페이지 수정
    @PutMapping("/updateInfluencerPage")
    public ResponseEntity<Map<String, Object>> updateInfluencerPage(
                                                @RequestBody InfluencerPageResponseDTO req) {
        int result = influencerPageService.updateInfluencerPage(req);

        Map<String, Object> body = new HashMap<>();
        body.put("인플루언서 페이지 수정", result);

        if (result == 1) {
            log.info("인플루언서 페이지 생성 완료!: {}", result);
            return ResponseEntity.ok(body);
        } else {
            log.info("인플루언서 페이지 수정 실패: {}", req);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/deleteInfluencerPage")
    public ResponseEntity<Map<String, Object>> deleteInfluencerPageTitleAndMemberNum(
            @RequestParam String title,
            @RequestParam int memberNum) {

        int delete = influencerPageService.deleteInfluencerPageTitleAndMemberNum(title, memberNum);

        Map<String, Object> body = new HashMap<>();
        body.put("인플루언서 페이지 삭제", delete);

        if (delete > 0) {
            log.info("인플루언서 페이지 삭제 완료!: {}", delete);
            return ResponseEntity.ok(body); // 200 OK
        } else {
            log.info("인플루언서 페이지 삭제 실패!: {}", delete);
            return ResponseEntity.badRequest().body(body); // 400 Bad Request
        }
    }


}
