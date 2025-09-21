package fashionmanager.lee.develop.service;

import com.fashion.community.dto.CommentDTO;
import com.fashion.community.entity.Comment;
import com.fashion.community.entity.FashionPost;
import com.fashion.community.entity.Member;
import com.fashion.community.mapper.CommentMapper;
import com.fashion.community.repository.CommentRepository;
import com.fashion.community.repository.FashionPostRepository;
import com.fashion.community.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    // MyBatis Mapper와 JPA Repository 모두 주입
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final FashionPostRepository fashionPostRepository;

    /**
     * 댓글 목록 조회 (Read) - MyBatis Mapper 사용
     */
    @Transactional(readOnly = true)
    public List<CommentDTO> findComments(Long postId) {
        return commentMapper.findByFashionPostNum(postId);
    }

    /**
     * 댓글 생성 (Create) - JPA Repository 사용
     */
    @Transactional
    public void createComment(CommentDTO dto) {
        if (dto.getContent() == null || dto.getContent().isBlank()) {
            throw new IllegalArgumentException("댓글 내용이 없습니다.");
        }
        // 연관 엔티티 조회
        Member member = memberRepository.findById(dto.getMemberNum())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        FashionPost post = fashionPostRepository.findById(dto.getFashionPostNum())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        Comment newComment = new Comment(dto.getContent(), member, post);
        commentRepository.save(newComment);
    }

    /**
     * 댓글 수정 (Update) - JPA Repository 사용
     */
    @Transactional
    public void updateComment(Long commentId, CommentDTO dto) {
        if (dto.getContent() == null || dto.getContent().isBlank()) {
            throw new IllegalArgumentException("댓글 내용이 없습니다.");
        }

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));

        // 작성자 본인 확인
        if (!comment.getMember().getMemberNum().equals(dto.getMemberNum())) {
            throw new SecurityException("댓글을 수정할 권한이 없습니다.");
        }

        comment.updateContent(dto.getContent()); // Dirty Checking으로 DB 업데이트
    }

    /**
     * 댓글 삭제 (Delete) - JPA Repository 사용
     */
    @Transactional
    public void deleteComment(Long commentId, Long memberNum) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));

        // 작성자 본인 확인
        if (!comment.getMember().getMemberNum().equals(memberNum)) {
            throw new SecurityException("댓글을 삭제할 권한이 없습니다.");
        }

        commentRepository.delete(comment);
    }
}