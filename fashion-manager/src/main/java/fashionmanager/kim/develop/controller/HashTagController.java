package fashionmanager.kim.develop.controller;

import fashionmanager.kim.develop.dto.HashTagAndPostDTO;
import fashionmanager.kim.develop.dto.HashTagDTO;
import fashionmanager.kim.develop.service.HashTagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/hashtage")
public class HashTagController {

    private final HashTagService hs;

    @Autowired
    public HashTagController(HashTagService hashTagService){
        this.hs = hashTagService;
    }

    @GetMapping("/selecthashtag")
    public List<HashTagDTO> selectHashTags() {
        List<HashTagDTO> hashTagList = hs.selectHashTags();
        for (HashTagDTO hashTagDTO : hashTagList) {
            log.info("hashTagDTO: {}", hashTagDTO);
        }
        return hashTagList;
    }

    @GetMapping("/selecthashtagandpost")
    public List<HashTagAndPostDTO> selectHashTagsAndPosts() {
        List<HashTagAndPostDTO> hashTagAndPostList = hs.selectHashTagsAndPosts();
        for (HashTagAndPostDTO hashTagAndPostDTO : hashTagAndPostList) {
            log.info("hashTagAndPostDTO: {}", hashTagAndPostDTO);
        }
        return hashTagAndPostList;
    }

    @PostMapping("/inserthashtag")
    public String insertHashTag(String insertHashTagName) {
        List<HashTagDTO> hashTagList = hs.selectHashTags();
        for (HashTagDTO hashTagDTO : hashTagList) {
            if(hashTagDTO.getHashTagName().equals(insertHashTagName)){
                log.info("새로운 해시태그 등록에 실패했습니다.");
                return "새로운 해시태그 등록에 실패했습니다.";
            }
        }

        int result = hs.insertHashTag(insertHashTagName);
        if(result == 1){
            log.info("새로운 해시태그 요소가 등록되었습니다.");
            return "새로운 해시태그 요소가 등록되었습니다.";
        }else{
            log.info("새로운 해시태그 등록에 실패했습니다.");
            return "새로운 해시태그 등록에 실패했습니다.";
        }
    }

    @PostMapping("/updatehashtag")
    public String updateHashTag(HashTagDTO updateHashTagDTO){
        List<HashTagDTO> hashTagList = hs.selectHashTags();
        for (HashTagDTO hashTagDTO : hashTagList) {
            if(hashTagDTO.getHashTagName().equals(updateHashTagDTO.getHashTagName())){
                log.info("해시태그 수정에 실패했습니다.");
                return "해시태그 수정에 실패했습니다.";
            }
        }

        int result = hs.updateHashTag(updateHashTagDTO);
        if(result == 1){
            log.info("해시태그 요소가 수정되었습니다.");
            return "해시태그 요소가 수정되었습니다.";
        }else{
            log.info("해시태그 수정에 실패했습니다.");
            return "해시태그 수정에 실패했습니다.";
        }
    }

    @PostMapping("/deletehashtag")
    public String deleteHashTag(int deleteHashTagNum){
        int result = hs.deleteHashTag(deleteHashTagNum);
        if(result == 1){
            log.info("해시태그 요소가 삭제 되었습니다.");
            return "해시태그 요소가 삭제 되었습니다.";
        }else{
            log.info("해시태그 요소가 삭제에 실패했습니다.");
            return "해시태그 요소가 삭제에 실패했습니다.";
        }
    }
}
