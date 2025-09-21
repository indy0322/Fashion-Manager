package fashionmanager.lee.develop.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDateTime;

@Entity
@Table(name = "COMMENT")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num")
    private Long commentNum;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "good", nullable = false)
    private int goodCount = 0;

    @Column(name = "cheer", nullable = false)
    private int cheerCount = 0;

    // 댓글(N) - 회원(1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_num", nullable = false)
    private Member member;

    // 댓글(N) - 패션게시글(1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fashion_post_num")
    private FashionPost fashionPost;

    // 생성자
    public Comment(String content, Member member, FashionPost fashionPost) {
        this.content = content;
        this.member = member;
        this.fashionPost = fashionPost;
    }

    // 수정 메서드
    public void updateContent(String newContent) {
        this.content = newContent;
    }
}