package fashionmanager.song.develop.menteeApply.dto;

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

    private String memberName;
}
