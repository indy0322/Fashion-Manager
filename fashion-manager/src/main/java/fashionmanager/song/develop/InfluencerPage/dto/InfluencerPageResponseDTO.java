package fashionmanager.song.develop.InfluencerPage.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class InfluencerPageResponseDTO {

    private int num;
    private String title;
    private String content;
    private String insta;
    private String phone;
    private int memberNum;

    private String memberName;
}
