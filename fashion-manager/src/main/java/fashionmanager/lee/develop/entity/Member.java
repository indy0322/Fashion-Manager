package fashionmanager.lee.develop.entity;


import jakarta.persistence.*;


@Entity
@Table(name = "Member")
public class Member {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num")
    private Integer id; // PK


    @Column(name = "id", nullable = false)
    private String loginId;


    @Column(name = "pwd", nullable = false)
    private String pwd;


    @Column(name = "email", nullable = false)
    private String email;


    @Column(name = "NAME", nullable = false)
    private String name;


    @Column(name = "age", nullable = false)
    private Integer age;


    @Column(name = "gender", nullable = false, columnDefinition = "CHAR(1)")
    private String gender; // 체크 제약: ('남','여')


    @Column(name = "height")
    private Integer height; // NULL 허용


    @Column(name = "weight")
    private Integer weight; // NULL 허용


    @Column(name = "STATUS", nullable = false)
    private String status;


    @Column(name = "report_count", nullable = false)
    private Integer reportCount = 0;


    @Column(name = "daily_report_count", nullable = false)
    private Integer dailyReportCount = 3;


    @Column(name = "good_count", nullable = false)
    private Integer goodCount = 0;


    @Column(name = "monthly_good_count", nullable = false)
    private Integer monthlyGoodCount = 0;


    @Column(name = "cheer_count", nullable = false)
    private Integer cheerCount = 0;


    @Column(name = "message_allow", nullable = false)
    private Boolean messageAllow = true;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getReportCount() {
        return reportCount;
    }

    public void setReportCount(Integer reportCount) {
        this.reportCount = reportCount;
    }

    public Integer getDailyReportCount() {
        return dailyReportCount;
    }

    public void setDailyReportCount(Integer dailyReportCount) {
        this.dailyReportCount = dailyReportCount;
    }

    public Integer getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(Integer goodCount) {
        this.goodCount = goodCount;
    }

    public Integer getMonthlyGoodCount() {
        return monthlyGoodCount;
    }

    public void setMonthlyGoodCount(Integer monthlyGoodCount) {
        this.monthlyGoodCount = monthlyGoodCount;
    }

    public Integer getCheerCount() {
        return cheerCount;
    }

    public void setCheerCount(Integer cheerCount) {
        this.cheerCount = cheerCount;
    }

    public Boolean getMessageAllow() {
        return messageAllow;
    }

    public void setMessageAllow(Boolean messageAllow) {
        this.messageAllow = messageAllow;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", loginId='" + loginId + '\'' +
                ", pwd='" + pwd + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", status='" + status + '\'' +
                ", reportCount=" + reportCount +
                ", dailyReportCount=" + dailyReportCount +
                ", goodCount=" + goodCount +
                ", monthlyGoodCount=" + monthlyGoodCount +
                ", cheerCount=" + cheerCount +
                ", messageAllow=" + messageAllow +
                '}';
    }
}