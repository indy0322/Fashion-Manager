package fashionmanager.song.develop.influencerApply.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class InfluencerApplyResponseDTO {
    // int -> Integer : null 가능하게
    private Integer num;
    private String title;
    private String content;
    private String accept;
    private Integer memberNum;

    private String memberName;

}