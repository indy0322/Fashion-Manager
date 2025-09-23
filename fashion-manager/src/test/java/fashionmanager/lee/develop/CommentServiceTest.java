package fashionmanager.lee.develop;

import fashionmanager.lee.develop.dto.CommentDTO;
import fashionmanager.lee.develop.dto.CommentPostDTO;
import fashionmanager.lee.develop.service.CommentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional // 테스트 후 데이터베이스 롤백을 위해 사용
class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Test
    @DisplayName("패션 게시물 댓글 조회 테스트")
    void findComments_fashionPost() {
        // given
        String postType = "fashion";
        Integer postNum = 1;

        // when
        List<CommentDTO> comments = commentService.findComments(postType, postNum);

        // then
        assertThat(comments).hasSize(2);
        assertThat(comments.get(0).getContent()).isEqualTo("와, 코디 너무 예뻐요! 정보 좀 알 수 있을까요?");
        assertThat(comments.get(0).getMemberName()).isEqualTo("최서아");
    }

    @Test
    @DisplayName("후기 게시물 댓글 조회 테스트")
    void findComments_reviewPost() {
        // given
        String postType = "review";
        Integer postNum = 1;

        // when
        List<CommentDTO> comments = commentService.findComments(postType, postNum);

        // then
        assertThat(comments).hasSize(1);
        assertThat(comments.get(0).getMemberName()).isEqualTo("김패션");
    }

    @Test
    @DisplayName("유효하지 않은 postType으로 댓글 조회 시 예외 발생 테스트")
    void findComments_invalidPostType() {
        // given
        String invalidPostType = "invalid";
        Integer postNum = 1;

        // when & then
        assertThrows(IllegalArgumentException.class, () -> {
            commentService.findComments(invalidPostType, postNum);
        });
    }

    @Test
    @DisplayName("패션 게시물에 새 댓글 작성 테스트")
    void createComment_fashionPost() {
        // given
        CommentPostDTO newComment = new CommentPostDTO();
        newComment.setContent("새로운 댓글입니다!");
        newComment.setMemberNum(4); // user01 이민준
        newComment.setPostNum(1);
        newComment.setPostType("fashion");

        // when
        CommentDTO createdComment = commentService.createComment(newComment);

        // then
        assertThat(createdComment).isNotNull();
        assertThat(createdComment.getNum()).isNotNull();
        assertThat(createdComment.getContent()).isEqualTo("새로운 댓글입니다!");
        assertThat(createdComment.getMemberName()).isEqualTo("이민준");

        // 확인: 전체 댓글 수가 3개로 늘었는지 검증
        List<CommentDTO> comments = commentService.findComments("fashion", 1);
        assertThat(comments).hasSize(3);
    }

    @Test
    @DisplayName("댓글 수정 테스트")
    void updateComment() {
        // given
        Integer commentNum = 1; // "와, 코디 너무 예뻐요!" 댓글
        String updatedContent = "수정된 댓글 내용입니다.";

        // when
        CommentDTO updatedComment = commentService.updateComment(commentNum, updatedContent);

        // then
        assertThat(updatedComment).isNotNull();
        assertThat(updatedComment.getNum()).isEqualTo(commentNum);
        assertThat(updatedComment.getContent()).isEqualTo(updatedContent);
    }

    @Test
    @DisplayName("존재하지 않는 댓글 수정 시 예외 발생 테스트")
    void updateComment_notFound() {
        // given
        Integer nonExistentCommentNum = 999;
        String content = "아무 내용";

        // when & then
        assertThrows(IllegalArgumentException.class, () -> {
            commentService.updateComment(nonExistentCommentNum, content);
        });
    }

    @Test
    @DisplayName("댓글 삭제 테스트")
    void deleteComment() {
        // given
        Integer commentNumToDelete = 1;
        String postType = "fashion";
        Integer postNum = 1;

        // when
        commentService.deleteComment(commentNumToDelete);

        // then
        List<CommentDTO> comments = commentService.findComments(postType, postNum);
        assertThat(comments).hasSize(1);
        // 삭제된 댓글 번호가 목록에 없는지 확인
        assertThat(comments).allSatisfy(comment -> {
            assertThat(comment.getNum()).isNotEqualTo(commentNumToDelete);
        });
    }
}
