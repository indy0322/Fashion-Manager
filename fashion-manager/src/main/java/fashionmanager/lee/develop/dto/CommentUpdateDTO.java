package fashionmanager.lee.develop.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentUpdateDTO {

    @NotNull
    private Integer memberId; // 소유자 검증용

    @NotBlank
    private String content;

}