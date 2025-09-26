package fashionmanager.song.develop.MenteeApply.controller;

import fashionmanager.song.develop.MenteeApply.dto.MenteeApplyCreateRequestDTO;
import fashionmanager.song.develop.MenteeApply.dto.MenteeApplyResponseDTO;
import fashionmanager.song.develop.MenteeApply.service.MenteeApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menteeApply")
public class MenteeController {

    private final MenteeApplyService menteeApplyService;

    @Autowired
    public MenteeController(MenteeApplyService menteeApplyService) {
        this.menteeApplyService = menteeApplyService;
    }

    @GetMapping("/selectMenteeApply")
    public List<MenteeApplyResponseDTO> selectResultApply() {
        return menteeApplyService.selectResultApply();
    }

    @PostMapping("/insertMenteeApply")
    public MenteeApplyResponseDTO insertMenteeApply(@RequestBody MenteeApplyCreateRequestDTO req) {
        return menteeApplyService.insertMenteeApply(req);
    }

    @PostMapping("/updateMenteeApply")
    public int updateMenteeApply(@RequestBody MenteeApplyResponseDTO req) {
        return menteeApplyService.updateMenteeApply(req);
    }
    @DeleteMapping("/deleteMenteeApply")
    public int deleteMenteeApplyByMentoringPostNumAndMemberNum(@RequestParam String content,
                                                               @RequestParam int mentoringPostNum,
                                                               @RequestParam int memberNum){
        return menteeApplyService
                .deleteMenteeApplyByMentoringPostNumAndMemberNum(content, mentoringPostNum, memberNum);

    }

}
