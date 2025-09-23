package fashionmanager.lee.develop.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentPostDTO {
    private Integer num; // 생성된 댓글 번호를 받아오기 위함
    private String content;
    private Integer memberNum; // 댓글을 작성하는 사용자의 번호

    // 게시물 정보를 담을 필드
    private Integer postNum;
    private String postType; // "fashion", "review", "mentoring"
}
