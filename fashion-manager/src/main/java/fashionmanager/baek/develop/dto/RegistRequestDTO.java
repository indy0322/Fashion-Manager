package fashionmanager.baek.develop.dto;

import lombok.*;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RegistRequestDTO {
    private String title;
    private String content;
    private int member_num;
    private List<Integer> hashtag;
    private List<Integer> items;
    private int review_category_num;
    private boolean finish;
}
