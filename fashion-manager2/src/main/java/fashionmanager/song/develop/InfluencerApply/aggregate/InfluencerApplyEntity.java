package fashionmanager.song.develop.InfluencerApply.aggregate;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "influencer_apply")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InfluencerApplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // auto increament
    @Column(name = "num")
    private int influencerApplyNum;

    @Column(name = "title")
    private String influencerApplyTitle;

    @Column(name = "content", nullable = false)       // 내용 null 포함
    private String influencerApplyContent;

    @Column(name = "accept")
    private String influencerApplyAccept;              // 대기, 승인, 거절

    @Column(name = "member_num")
    private int memberNum;
}




