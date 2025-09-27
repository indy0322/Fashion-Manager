package fashionmanager.song.develop.InfluencerApply.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class InfluencerApplyCreateRequestDTO {
    // int -> Integer : null 가능하게
    private Integer num;
    private String title;
    private String content;
    private String accept;
    private Integer memberNum;

    // 이거 멤버 이름 넣을 계획
//    private String memberName;

}