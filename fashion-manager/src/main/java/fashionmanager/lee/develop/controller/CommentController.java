package fashionmanager.lee.develop.controller;

import fashionmanager.lee.develop.dto.CommentDTO;
import fashionmanager.lee.develop.dto.CommentPostDTO;
import fashionmanager.lee.develop.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /**
     * 특정 게시물의 댓글 목록을 조회하는 API
     * 예) GET /api/comments/fashion/10
     */
    @GetMapping("/{postType}/{postNum}")
    public ResponseEntity<List<CommentDTO>> getCommentsByPost(
            @PathVariable String postType,
            @PathVariable Integer postNum) {
        List<CommentDTO> comments = commentService.findComments(postType, postNum);
        return ResponseEntity.ok(comments);
    }

    /**
     * 새로운 댓글을 생성하는 API
     * POST /api/comments
     */
    @PostMapping
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentPostDTO commentPostDTO) {
        CommentDTO createdComment = commentService.createComment(commentPostDTO);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    /**
     * 댓글을 수정하는 API
     * PUT /api/comments/5
     */
    @PutMapping("/{commentNum}")
    public ResponseEntity<CommentDTO> updateComment(
            @PathVariable Integer commentNum,
            @RequestBody Map<String, String> requestBody) {
        String content = requestBody.get("content");
        if (content == null) {
            return ResponseEntity.badRequest().build();
        }
        CommentDTO updatedComment = commentService.updateComment(commentNum, content);
        return ResponseEntity.ok(updatedComment);
    }

    /**
     * 댓글을 삭제하는 API
     * DELETE /api/comments/5
     */
    @DeleteMapping("/{commentNum}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer commentNum) {
        commentService.deleteComment(commentNum);
        return ResponseEntity.noContent().build();
    }
}
