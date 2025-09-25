package fashionmanager.baek.develop.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RequestRegistPostDTO {
    private String title;
    private String content;
    private int member_num;
//    private List<String> items;
//    private String photo_name;
}
