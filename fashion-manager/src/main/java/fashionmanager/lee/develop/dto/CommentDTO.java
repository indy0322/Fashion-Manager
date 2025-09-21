package fashionmanager.lee.develop.dto;

import lombok.*;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    // 응답용 필드
    private Long commentNum;
    private String content;
    private String authorName; // 작성자 이름
    private Long authorId;     // 작성자 ID (수정/삭제 권한 확인용)

    // 요청용 필드
    private Long fashionPostNum;
    private Long memberNum; // 댓글 작성자 ID
}