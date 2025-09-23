package fashionmanager.lee.develop.entity;


import jakarta.persistence.*;


@Entity
@Table(name = "`COMMENT`") // 예약어 보호
public class Comment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num")
    private Integer id; // PK


    @Column(name = "content", nullable = false, length = 255)
    private String content;


    @Column(name = "good", nullable = false)
    private Integer good = 0;


    @Column(name = "cheer", nullable = false)
    private Integer cheer = 0;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_num", nullable = false)
    private Member member; // 작성자 FK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_post_num")
    private ReviewPost reviewPost; // NULL 허용


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fashion_post_num")
    private FashionPost fashionPost; // NULL 허용


    // ===== 편의 메서드 =====
    public void updateContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public ReviewPost getReviewPost() {
        return reviewPost;
    }

    public void setReviewPost(ReviewPost reviewPost) {
        this.reviewPost = reviewPost;
    }

    public FashionPost getFashionPost() {
        return fashionPost;
    }

    public void setFashionPost(FashionPost fashionPost) {
        this.fashionPost = fashionPost;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", good=" + good +
                ", cheer=" + cheer +
                ", member=" + member +
                ", reviewPost=" + reviewPost +
                ", fashionPost=" + fashionPost +
                '}';
    }
}