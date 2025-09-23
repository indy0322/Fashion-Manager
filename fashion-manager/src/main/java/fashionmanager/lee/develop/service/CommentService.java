package fashionmanager.lee.develop.service;

import fashionmanager.lee.develop.dto.CommentDTO;
import fashionmanager.lee.develop.dto.CommentPostDTO;
import fashionmanager.lee.develop.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentMapper commentMapper;

    /**
     * 게시물에 해당하는 댓글 목록을 조회합니다.
     */
    @Transactional(readOnly = true)
    public List<CommentDTO> findComments(String postType, Integer postNum) {
        if (!List.of("fashion", "review", "mentoring").contains(postType)) {
            throw new IllegalArgumentException("Invalid post type: " + postType);
        }
        return commentMapper.findCommentsByPost(postType, postNum);
    }

    /**
     * 새로운 댓글을 생성합니다.
     */
    @Transactional
    public CommentDTO createComment(CommentPostDTO commentPostDto) {
        commentMapper.insertComment(commentPostDto);
        return commentMapper.findCommentByNum(commentPostDto.getNum()).orElse(null);
    }

    /**
     * 댓글을 수정합니다.
     */
    @Transactional
    public CommentDTO updateComment(Integer commentNum, String content) {
        commentMapper.findCommentByNum(commentNum)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found: " + commentNum));

        // TODO: 댓글 수정 권한 확인 로직

        commentMapper.updateComment(commentNum, content);
        return commentMapper.findCommentByNum(commentNum).orElse(null);
    }

    /**
     * 댓글을 삭제합니다.
     */
    @Transactional
    public void deleteComment(Integer commentNum) {
        commentMapper.findCommentByNum(commentNum)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found: " + commentNum));

        // TODO: 댓글 삭제 권한 확인 로직

        commentMapper.deleteComment(commentNum);
    }
}
