package fashionmanager.baek.develop.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ReactionRequestDTO {
    private int postNum;
    private int postCategoryNum; // 패션 게시물은 1, 후기 게시물은 2
    private int memberNum;
    private String reactionType;
}
