package fashionmanager.baek.develop.dto;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModifyResponseDTO {
    private int num;
    private String title;
    private String content;
    private int member_num;
    private List<Integer> hashtag;
    private List<Integer> items;
}
