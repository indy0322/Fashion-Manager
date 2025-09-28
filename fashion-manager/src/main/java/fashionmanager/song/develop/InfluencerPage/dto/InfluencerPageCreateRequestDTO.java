package fashionmanager.song.develop.influencerPage.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class InfluencerPageCreateRequestDTO {

    private int num;
    private String title;
    private String content;
    private String insta;
    private String phone;
    private int memberNum;


    private String memberName;


}
