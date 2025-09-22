package fashionmanager.lee.develop.controller;

import fashionmanager.lee.develop.dto.CommentDTO;
import fashionmanager.lee.develop.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/review-posts/{postId}/comments")
@RequiredArgsConstructor
public class ReviewCommentController {

    private final CommentService commentService;
    private static final Long CURRENT_MEMBER_ID = 5L; // 임시 사용자 ID

    // 1. 후기 게시물 댓글 목록 조회
    @GetMapping
    public ResponseEntity<?> getComments(@PathVariable Long postId) {
        try {
            List<CommentDTO> comments = commentService.findReviewComments(postId);
            return ResponseEntity.ok(comments);
        } catch (Exception e) {
            log.error("후기 게시글 {} 댓글 조회 실패", postId, e);
            return ResponseEntity.badRequest().body("댓글 조회에 실패했습니다.");
        }
    }

    // 2. 후기 게시물 댓글 작성
    @PostMapping
    public ResponseEntity<String> createComment(@PathVariable Long postId, @RequestBody CommentDTO dto) {
        try {
            dto.setReviewPostNum(postId);
            dto.setMemberNum(CURRENT_MEMBER_ID);
            commentService.createReviewComment(dto);
            return ResponseEntity.ok("댓글이 성공적으로 등록되었습니다.");
        } catch (Exception e) {
            log.error("후기 게시글 댓글 등록 실패", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 3. 후기 게시물 댓글 수정
    @PutMapping("/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable Long commentId, @RequestBody CommentDTO dto) {
        try {
            dto.setMemberNum(CURRENT_MEMBER_ID);
            commentService.updateComment(commentId, dto);
            return ResponseEntity.ok("댓글이 성공적으로 수정되었습니다.");
        } catch (Exception e) {
            log.error("댓글 {} 수정 실패", commentId, e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 4. 후기 게시물 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
        try {
            commentService.deleteComment(commentId, CURRENT_MEMBER_ID);
            return ResponseEntity.ok("댓글이 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            log.error("댓글 {} 삭제 실패", commentId, e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}