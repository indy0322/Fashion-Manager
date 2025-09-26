package fashionmanager.baek.develop.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SelectAllPostDTO {
    private int num;
    private String title;
    private int memberNum;
    private int good;
    private int cheer;
}
