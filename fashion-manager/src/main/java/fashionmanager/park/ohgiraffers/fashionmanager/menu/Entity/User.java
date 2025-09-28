package park.ohgiraffers.fashionmanager.menu.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "member")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB의 AUTO_INCREMENT 활용
    @Column(name="num")
    private int userNum;

    @Column(name="id")
    private String userId;

    @Column(name="pwd")
    private String userPwd;

    @Column(name="email")
    private String userEmail;

    @Column(name="NAME")
    private String userName;

    @Column(name="age")
    private int userAge;

    @Column(name="gender")
    private String userGender;

    @Column(nullable = false)
    private String status = "ACTIVE";

    @Column(name="MessageAllow")
    private int userMessageAllow;

    @Column(name="ReportCount")
    private int ReportCount;

    @Column(name="DailyReportCount")
    private int DailyReportCount;




}
