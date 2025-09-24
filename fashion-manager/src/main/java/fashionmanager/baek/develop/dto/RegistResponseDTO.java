package fashionmanager.baek.develop.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RegistResponseDTO {
    private int num;
    private String title;
    private String content;
    private int member_num;
}
