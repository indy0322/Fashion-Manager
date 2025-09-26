package fashionmanager.song.develop.MenteeApply.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MenteeApplyResponseDTO {

    private Integer num;
    private String content;
    private String accept;
    private Integer mentoringPostNum;
    private Integer memberNum;
}
