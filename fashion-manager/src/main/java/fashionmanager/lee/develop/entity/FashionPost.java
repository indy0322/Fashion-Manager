package fashionmanager.lee.develop.entity;


import jakarta.persistence.*;


@Entity
@Table(name = "Fashion_Post")
public class FashionPost {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num")
    private Integer id; // PK


    @Column(name = "title", nullable = false)
    private String title;


    @Column(name = "content")
    private String content;


    @Column(name = "good", nullable = false)
    private Integer good = 0;


    @Column(name = "cheer", nullable = false)
    private Integer cheer = 0;


    @Column(name = "temp", nullable = false)
    private Double temp = 0.0;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_num", nullable = false)
    private Member member; // 작성자 FK

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getGood() {
        return good;
    }

    public void setGood(Integer good) {
        this.good = good;
    }

    public Integer getCheer() {
        return cheer;
    }

    public void setCheer(Integer cheer) {
        this.cheer = cheer;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "FashionPost{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", good=" + good +
                ", cheer=" + cheer +
                ", temp=" + temp +
                ", member=" + member +
                '}';
    }
}