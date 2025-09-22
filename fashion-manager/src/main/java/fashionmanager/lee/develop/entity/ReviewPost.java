package fashionmanager.lee.develop.entity;


import jakarta.persistence.*;


@Entity
@Table(name = "Review_Post")
public class ReviewPost {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num")
    private Integer id; // PK


    @Column(name = "title", nullable = false)
    private String title;


    @Column(name = "content")
    private String content; // NULL 허용


    @Column(name = "good", nullable = false)
    private Integer good = 0;


    @Column(name = "cheer", nullable = false)
    private Integer cheer = 0;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_num", nullable = false)
    private Member member; // 작성자 FK


    @Column(name = "review_category_num", nullable = false)
    private Integer reviewCategoryNum; // 단순 숫자 FK (엔티티로 안 뺌)

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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Integer getReviewCategoryNum() {
        return reviewCategoryNum;
    }

    public void setReviewCategoryNum(Integer reviewCategoryNum) {
        this.reviewCategoryNum = reviewCategoryNum;
    }

    @Override
    public String toString() {
        return "ReviewPost{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", good=" + good +
                ", cheer=" + cheer +
                ", member=" + member +
                ", reviewCategoryNum=" + reviewCategoryNum +
                '}';
    }
}