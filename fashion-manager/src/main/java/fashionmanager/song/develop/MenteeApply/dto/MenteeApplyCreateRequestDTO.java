package fashionmanager.song.develop.MenteeApply.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MenteeApplyCreateRequestDTO {
    // int -> Integer : null 가능하게
    private Integer num;
    private String content;
    private String accept;
    private Integer mentoringPostNum;
    private Integer memberNum;

//    private String memberName;

}
