package fashionmanager.lee.develop.dto;


public class CommentResponse {
    private Integer id;
    private Integer memberId;
    private String content;


    public CommentResponse(Integer id, Integer memberId, String content) {
        this.id = id;
        this.memberId = memberId;
        this.content = content;
    }


    public Integer getId() { return id; }
    public Integer getMemberId() { return memberId; }
    public String getContent() { return content; }
}