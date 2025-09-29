package fashionmanager.baek.develop.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ReactionResponseDTO {
    private int num;
    private int postNum;
    private int postCategoryNum;
    private String reactionType;
    private int memberNum;
}
