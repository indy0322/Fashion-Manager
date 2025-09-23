package fashionmanager.lee.develop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDTO {
    private Integer id;
    private Integer memberId;
    private String content;

}