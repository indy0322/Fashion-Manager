package fashionmanager.lee.develop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentPostDTO {
    private String content;
    private int memberNum;
    private int postNum;
    private String postType; // "fashion", "review", "mentoring"
}
