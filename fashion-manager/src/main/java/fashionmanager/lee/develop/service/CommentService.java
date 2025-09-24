package fashionmanager.lee.develop.service;

import fashionmanager.lee.develop.dto.CommentDTO;
import fashionmanager.lee.develop.dto.CommentPostDTO;
import fashionmanager.lee.develop.entity.Comment;
import fashionmanager.lee.develop.mapper.CommentMapper;
import fashionmanager.lee.develop.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor // final 필드에 대한 생성자 자동 주입
public class CommentService {

    // JPA Repository: 간단한 CRUD 담당
    private final CommentRepository commentRepository;
    // MyBatis Mapper: 복잡한 조회 쿼리 담당
    private final CommentMapper commentMapper;

    @Transactional(readOnly = true)
    public List<CommentDTO> findComments(String postType, Integer postNum) {
        // 유효한 postType인지 확인
        if (!List.of("fashion", "review", "mentoring").contains(postType)) {
            throw new IllegalArgumentException("Invalid post type provided");
        }
        return commentMapper.findCommentsByPost(postType, postNum);
    }

    @Transactional
    public CommentDTO createComment(CommentPostDTO commentPostDto) {
        // 1. DTO를 JPA Entity로 변환
        Comment commentToSave = new Comment(
                commentPostDto.getContent(),
                commentPostDto.getMemberNum(),
                commentPostDto.getPostNum(),
                commentPostDto.getPostType()
        );

        // 2. JPA Repository를 사용해 저장
        Comment savedComment = commentRepository.save(commentToSave);

        // 3. 저장된 정보를 바탕으로 사용자 이름 등 추가 정보를 조회하여 DTO로 반환 (MyBatis)
        return commentMapper.findCommentByNum(savedComment.getNum())
                .orElseThrow(() -> new RuntimeException("Failed to retrieve comment after creation"));
    }

    @Transactional
    public CommentDTO updateComment(Integer commentNum, String content) {
        // 1. JPA Repository로 댓글 엔티티 조회
        Comment comment = commentRepository.findById(commentNum)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found with num: " + commentNum));

        // 2. 내용 수정 (JPA의 '더티 체킹' 기능으로 트랜잭션 종료 시 자동 업데이트)
        comment.setContent(content);

        // 3. 수정된 정보를 포함한 DTO 반환 (MyBatis)
        return commentMapper.findCommentByNum(commentNum)
                .orElseThrow(() -> new RuntimeException("Failed to retrieve comment after update"));
    }

    @Transactional
    public void deleteComment(Integer commentNum) {
        if (!commentRepository.existsById(commentNum)) {
            throw new IllegalArgumentException("Comment not found with num: " + commentNum);
        }
        commentRepository.deleteById(commentNum);
    }
}