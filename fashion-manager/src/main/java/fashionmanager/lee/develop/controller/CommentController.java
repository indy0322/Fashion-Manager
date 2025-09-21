package fashionmanager.lee.develop.controller;

import com.fashion.community.dto.CommentDTO;
import com.fashion.community.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/fashion-posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 임시로 사용할 현재 로그인 회원 ID
    private static final Long CURRENT_MEMBER_ID = 4L; // '이민준' 님

    @GetMapping
    public ResponseEntity<?> getCommentsByPost(@PathVariable Long postId) {
        try {
            List<CommentDTO> comments = commentService.findComments(postId);
            return ResponseEntity.ok(comments);
        } catch (Exception e) {
            log.error("게시글 {}의 댓글 조회 실패", postId, e);
            return ResponseEntity.badRequest().body("댓글 조회에 실패했습니다.");
        }
    }

    @PostMapping
    public ResponseEntity<String> createComment(@PathVariable Long postId, @RequestBody CommentDTO requestDto) {
        try {
            requestDto.setFashionPostNum(postId);
            requestDto.setMemberNum(CURRENT_MEMBER_ID); // 실제로는 SecurityContext에서 사용자 정보 가져와야 함
            commentService.createComment(requestDto);
            log.info("게시글 {}에 새 댓글이 등록되었습니다.", postId);
            return ResponseEntity.ok("댓글이 성공적으로 등록되었습니다.");
        } catch (Exception e) {
            log.error("댓글 등록 실패", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 댓글 수정/삭제는 게시글ID와 무관하게 댓글 고유ID로 처리하므로 경로를 분리
    @PutMapping("/{commentId}")
    public ResponseEntity<String> updateComment(
            @PathVariable Long postId, // 경로 일관성을 위해 유지
            @PathVariable Long commentId,
            @RequestBody CommentDTO requestDto) {
        try {
            requestDto.setMemberNum(CURRENT_MEMBER_ID); // 수정 권한 확인용
            commentService.updateComment(commentId, requestDto);
            log.info("댓글 {}이 수정되었습니다.", commentId);
            return ResponseEntity.ok("댓글이 성공적으로 수정되었습니다.");
        } catch (Exception e) {
            log.error("댓글 수정 실패", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable Long postId, // 경로 일관성을 위해 유지
            @PathVariable Long commentId) {
        try {
            commentService.deleteComment(commentId, CURRENT_MEMBER_ID); // 삭제 권한 확인용
            log.info("댓글 {}이 삭제되었습니다.", commentId);
            return ResponseEntity.ok("댓글이 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            log.error("댓글 삭제 실패", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}