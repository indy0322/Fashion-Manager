package fashionmanager.lee.develop.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class CommentUpdateRequest {


    @NotNull
    private Integer memberId; // 소유자 검증용


    @NotBlank
    private String content;


    public Integer getMemberId() { return memberId; }
    public void setMemberId(Integer memberId) { this.memberId = memberId; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}