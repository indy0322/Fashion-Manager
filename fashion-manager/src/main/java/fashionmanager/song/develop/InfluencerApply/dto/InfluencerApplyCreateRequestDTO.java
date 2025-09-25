package fashionmanager.song.develop.InfluencerApply.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class InfluencerApplyCreateRequestDTO {

    private Integer num;
    private String title;
    private String content;
    private String accept;
    private Integer memberNum;

}
