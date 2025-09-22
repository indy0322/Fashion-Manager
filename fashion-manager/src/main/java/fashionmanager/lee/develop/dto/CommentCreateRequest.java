package fashionmanager.lee.develop.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class CommentCreateRequest {


    @NotNull
    private Integer memberId;


    @NotBlank
    private String content;


    public Integer getMemberId() { return memberId; }
    public void setMemberId(Integer memberId) { this.memberId = memberId; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}