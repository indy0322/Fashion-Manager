package fashionmanager.lee.develop.controller;

import fashionmanager.lee.develop.dto.CommentCreateDTO;
import fashionmanager.lee.develop.dto.CommentResponseDTO;
import fashionmanager.lee.develop.dto.CommentUpdateDTO;
import fashionmanager.lee.develop.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class CommentController {
    private final CommentService service;
    public CommentController(CommentService service) {
        this.service = service;
    }
    // 1. 회원이 패션 게시판 게시물에 댓글 작성
    @PostMapping("/fashion-posts/{postId}/comments")
    public ResponseEntity<CommentResponseDTO> createOnFashion(@PathVariable Integer postId,
                                                              @RequestBody @Valid CommentCreateDTO req) {
        CommentResponseDTO res = service.createOnFashion(postId, req.getMemberId(), req.getContent());
        return ResponseEntity.created(URI.create("/api/fashion-posts/" + postId + "/comments/" +
                        res.getId()))
                .body(res);
    }
    // 2. 회원이 패션 게시물의 댓글 수정
    @PatchMapping("/fashion-posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentResponseDTO> updateOnFashion(@PathVariable Integer postId,
                                                           @PathVariable Integer commentId,
                                                           @RequestBody @Valid CommentUpdateDTO req) {
        CommentResponseDTO res = service.updateOnFashion(commentId, req.getMemberId(),
                req.getContent());
        return ResponseEntity.ok(res);
    }
    // 3. 패션 게시물의 댓글 삭제
    @DeleteMapping("/fashion-posts/{postId}/comments/{commentId}")
    public ResponseEntity<?> deleteOnFashion(@PathVariable Integer postId,
                                             @PathVariable Integer commentId,
                                             @RequestParam("memberId") Integer memberId) {
        service.deleteOnFashion(commentId, memberId);
        return ResponseEntity.noContent().build();
    }
    // 4. 회원이 후기 게시판 게시물에 댓글 작성
    @PostMapping("/review-posts/{postId}/comments")
    public ResponseEntity<CommentResponseDTO> createOnReview(@PathVariable Integer postId,
                                                          @RequestBody @Valid CommentCreateDTO req) {
        CommentResponseDTO res = service.createOnReview(postId, req.getMemberId(), req.getContent());
        return ResponseEntity.created(URI.create("/api/review-posts/" + postId + "/comments/" +
                        res.getId()))
                .body(res);
    }
    // 5. 회원이 후기 게시물의 댓글 수정
    @PatchMapping("/review-posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentResponseDTO> updateOnReview(@PathVariable Integer postId,
                                                          @PathVariable Integer commentId,
                                                          @RequestBody @Valid CommentUpdateDTO req) {
        CommentResponseDTO res = service.updateOnReview(commentId, req.getMemberId(),
                req.getContent());
        return ResponseEntity.ok(res);
    }
    // 6. 후기 게시물의 댓글 삭제
    @DeleteMapping("/review-posts/{postId}/comments/{commentId}")
    public ResponseEntity<?> deleteOnReview(@PathVariable Integer postId,
                                            @PathVariable Integer commentId,
                                            @RequestParam("memberId") Integer memberId) {
        service.deleteOnReview(commentId, memberId);
        return ResponseEntity.noContent().build();
    }
}
