package fashionmanager.lee.develop.service;



import fashionmanager.lee.develop.dto.CommentResponseDTO;
import fashionmanager.lee.develop.entity.Comment;
import fashionmanager.lee.develop.entity.FashionPost;
import fashionmanager.lee.develop.entity.Member;
import fashionmanager.lee.develop.entity.ReviewPost;
import fashionmanager.lee.develop.repository.CommentRepository;
import fashionmanager.lee.develop.repository.FashionPostRepository;
import fashionmanager.lee.develop.repository.MemberRepository;
import fashionmanager.lee.develop.repository.ReviewPostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class CommentService {


    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final FashionPostRepository fashionPostRepository;
    private final ReviewPostRepository reviewPostRepository;


    public CommentService(CommentRepository commentRepository,
                          MemberRepository memberRepository,
                          FashionPostRepository fashionPostRepository,
                          ReviewPostRepository reviewPostRepository) {
        this.commentRepository = commentRepository;
        this.memberRepository = memberRepository;
        this.fashionPostRepository = fashionPostRepository;
        this.reviewPostRepository = reviewPostRepository;
    }


    // 1) 회원이 패션 게시판 게시물에 댓글 작성
    public CommentResponseDTO createOnFashion(Integer fashionPostId, Integer memberId, String content) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));


        FashionPost post = fashionPostRepository.findById(fashionPostId)
                .orElseThrow(() -> new EntityNotFoundException("Fashion post not found"));


        Comment c = new Comment();
        c.setMember(member);
        c.setFashionPost(post); // 패션 글에만 연결
        c.setContent(content);


        Comment saved = commentRepository.save(c);
        return new CommentResponseDTO(saved.getId(), saved.getMember().getId(), saved.getContent());
    }


    // 2) 회원이 패션 게시물의 댓글 수정(소유자만)
    public CommentResponseDTO updateOnFashion(Integer commentId, Integer memberId, String content) {
        Comment c = commentRepository.findByIdAndMember_Id(commentId, memberId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found or not owned by member"));


// 무결성: 패션 댓글인지 확인
        if (c.getFashionPost() == null || c.getReviewPost() != null) {
            throw new IllegalStateException("Not a fashion-post comment");
        }
        c.updateContent(content);
        return new CommentResponseDTO(c.getId(), c.getMember().getId(), c.getContent());
    }


    // 3) 패션 게시물의 댓글 삭제(소유자만)
    public void deleteOnFashion(Integer commentId, Integer memberId) {
        Comment c = commentRepository.findByIdAndMember_Id(commentId, memberId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found or not owned by member"));
        if (c.getFashionPost() == null || c.getReviewPost() != null) {
            throw new IllegalStateException("Not a fashion-post comment");
        }
        commentRepository.delete(c);
    }
    // 4) 회원이 후기 게시판 게시물에 댓글 작성
    public CommentResponseDTO createOnReview(Integer reviewPostId, Integer memberId, String
            content) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));
        ReviewPost post = reviewPostRepository.findById(reviewPostId)
                .orElseThrow(() -> new EntityNotFoundException("Review post not found"));
        Comment c = new Comment();
        c.setMember(member);
        c.setReviewPost(post); // 후기 글에만 연결
        c.setContent(content);
        Comment saved = commentRepository.save(c);
        return new CommentResponseDTO(saved.getId(), saved.getMember().getId(), saved.getContent());
    }
    // 5) 회원이 후기 게시물의 댓글 수정(소유자만)
    public CommentResponseDTO updateOnReview(Integer commentId, Integer memberId, String
            content) {
        Comment c = commentRepository.findByIdAndMember_Id(commentId, memberId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found or not owned by member"));
        if (c.getReviewPost() == null || c.getFashionPost() != null) {
            throw new IllegalStateException("Not a review-post comment");
        }
        c.updateContent(content);
        return new CommentResponseDTO(c.getId(), c.getMember().getId(), c.getContent());
    }
    // 6) 후기 게시물의 댓글 삭제(소유자만)
    public void deleteOnReview(Integer commentId, Integer memberId) {
        Comment c = commentRepository.findByIdAndMember_Id(commentId, memberId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found or not owned by member"));
        if (c.getReviewPost() == null || c.getFashionPost() != null) {
            throw new IllegalStateException("Not a review-post comment");
        }
        commentRepository.delete(c);
    }
}