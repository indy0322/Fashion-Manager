package fashionmanager.song.develop.InfluencerApply.controller;

import fashionmanager.song.develop.InfluencerApply.dto.InfluencerApplyCreateRequestDTO;
import fashionmanager.song.develop.InfluencerApply.dto.InfluencerApplyResponseDTO;
import fashionmanager.song.develop.InfluencerApply.service.InfluencerApplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/influencerApply")
@Slf4j
public class InfluencerController {

    private final InfluencerApplyService influencerApplyService;

    @Autowired
    public InfluencerController(InfluencerApplyService influencerService) {
        this.influencerApplyService = influencerService;
    }

    @GetMapping("/selectInfluencerApply")
    public List<InfluencerApplyResponseDTO> selectResultApply() {
        return influencerApplyService.selectResultApply();
    }

    @PostMapping("/insertInfluencerApply")
    public InfluencerApplyResponseDTO insertInfluencerApply(@RequestBody InfluencerApplyCreateRequestDTO req) {
        return influencerApplyService.insertInfluencerApply(req);
    }

    @PostMapping("/updateInfluencerApply")
    public int updateInfluencerApply(@RequestBody InfluencerApplyResponseDTO req) {
        return influencerApplyService.updateInfluencerApply(req);
    }

    @DeleteMapping("/deleteInfluencerApply")
    public int deleteInfluencerApplyByTitleAndMemberNum(@RequestParam String title,
                                                        @RequestParam int memberNum) {
        return influencerApplyService.deleteInfluencerApplyByTitleAndMemberNum(title, memberNum);
    }
}