package park.ohgiraffers.fashionmanager.menu.DTO;


import jakarta.persistence.Column;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {

    private int userNum;
    private String userId;
    private String userPwd;
    private String userEmail;
    private String userName;
    private int userAge;
    private String userGender;
    private int userMessageAllow;
    private int ReportCount;
    private int DailyReportCount;


}
