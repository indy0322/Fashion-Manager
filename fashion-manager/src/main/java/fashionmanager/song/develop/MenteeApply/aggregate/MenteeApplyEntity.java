package fashionmanager.song.develop.MenteeApply.aggregate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mentee_apply")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MenteeApplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // auto increament
    @Column(name = "num")
    private int num;

    @Column(name = "content", nullable = false)       // 내용 null 포함
    private String content;

    @Column(name = "accept")
    private String accept;     // 대기, 승인, 거절

    @Column(name = "mentoring_post_num")
    private int mentoringPostNum;

    @Column(name = "member_num")
    private int memberNum;

}
