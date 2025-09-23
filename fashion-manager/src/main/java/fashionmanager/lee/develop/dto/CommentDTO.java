package fashionmanager.lee.develop.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Integer num;
    private String content;
    private Integer good;
    private Integer cheer;
    private Integer memberNum;
    private String memberName; // Member 테이블과 조인하여 가져올 사용자 이름
    private Integer mentoringPostNum;
    private Integer reviewPostNum;
    private Integer fashionPostNum;
}
