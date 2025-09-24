package fashionmanager.baek.develop.dto;

import lombok.*;

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
//    private List<String> items;
//    private String photo_name;
}
