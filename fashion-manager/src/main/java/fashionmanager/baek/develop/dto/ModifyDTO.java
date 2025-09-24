package fashionmanager.baek.develop.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ModifyDTO {
    private int num;
    private String title;
    private String content;
    private int member_num;
    private List<String> item_names;
    private String photo_name;
}
