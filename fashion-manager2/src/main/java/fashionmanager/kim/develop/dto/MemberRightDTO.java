package fashionmanager.kim.develop.dto;

import lombok.*;


import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberRightDTO{
    private int memberNum;
    private String memberId;
    private String memberPwd;
    private String memberEmail;
    private String memberName;
    private int memberAge;
    private char memberGender;
    private int memberHeight;
    private int memberWeight;
    private String memberStatus;
    private int memberReportCount;
    private int memberDailyReportCount;
    private int memberGoodCount;
    private int memberMonthlyGoodCount;
    private int memberCheerCount;
    private boolean memberMessageAllow;

    private int memberStateNum;
    private String memberStateName;
}
