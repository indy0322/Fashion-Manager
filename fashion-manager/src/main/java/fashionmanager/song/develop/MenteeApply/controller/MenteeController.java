package fashionmanager.song.develop.MenteeApply.controller;

import fashionmanager.song.develop.MenteeApply.dto.MenteeApplyCreateRequestDTO;
import fashionmanager.song.develop.MenteeApply.dto.MenteeApplyResponseDTO;
import fashionmanager.song.develop.MenteeApply.service.MenteeApplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/menteeApply")
@Slf4j
public class MenteeController {

    private final MenteeApplyService menteeApplyService;

    @Autowired
    public MenteeController(MenteeApplyService menteeApplyService) {
        this.menteeApplyService = menteeApplyService;
    }


    // 멘토링 신청 조회
    @GetMapping("/selectMenteeApply")
    public ResponseEntity<List<MenteeApplyResponseDTO>> selectResultApply() {
        List<MenteeApplyResponseDTO> MenteeApply = menteeApplyService.selectResultApply();
        for (MenteeApplyResponseDTO MenteeApplyDTO : MenteeApply) {
            log.info("멘토링 신청 조회: {}", MenteeApplyDTO);
        }
        return ResponseEntity.ok(MenteeApply);
    }

    // 멘토링 신청
    @PostMapping("/insertMenteeApply")
    public ResponseEntity<MenteeApplyCreateRequestDTO> insertMenteeApply(
            @RequestBody MenteeApplyCreateRequestDTO req) {
        MenteeApplyCreateRequestDTO saved = menteeApplyService.insertMenteeApply(req);

        if (saved != null) {
            log.info("멘토링 신청 완료!: {}", saved);
            return ResponseEntity.ok(saved);
        } else {
            log.info("멘토링 신청 실패!: {}", req);
            return ResponseEntity.badRequest().build();
        }
    }

    // 멘토링 신청 수정
    @PostMapping("/updateMenteeApply")
    public ResponseEntity<Map<String,Object>> updateMenteeApply(
            @RequestBody MenteeApplyResponseDTO req) {
        int result = menteeApplyService.updateMenteeApply(req);

        Map<String, Object> body = new HashMap<>();
        body.put("멘토링 신청서 수정", result);

        if (result == 1) {
            log.info("멘토링 신청서 수정 완료!: {}", result);
            return ResponseEntity.ok(body);
        } else {
            log.warn("멘토링 신청서 수정 실패!: {}", result);
            return ResponseEntity.badRequest().body(body);
        }
    }


    @DeleteMapping("/deleteMenteeApply")
    public ResponseEntity<Map<String, Object>> deleteMenteeApplyByMentoringPostNumAndMemberNum(
            @RequestParam String content,
            @RequestParam int mentoringPostNum,
            @RequestParam int memberNum) {

        int delete = menteeApplyService
                .deleteMenteeApplyByMentoringPostNumAndMemberNum(content, mentoringPostNum, memberNum);

        Map<String, Object> body = new HashMap<>();
        body.put("delete", delete);

        if (delete > 0) {
            log.info("멘토링 신청서 삭제 완료!: {}", delete);
            return ResponseEntity.ok(body); // 200 OK
        } else {
            log.info("멘토링 신청서 삭제 실패!: {}", delete);
            return ResponseEntity.badRequest().body(body); // 400 Bad Request
        }
    }

}
