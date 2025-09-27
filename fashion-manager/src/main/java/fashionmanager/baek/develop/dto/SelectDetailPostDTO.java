package fashionmanager.baek.develop.dto;

import fashionmanager.kim.develop.dto.HashTagDTO;
import lombok.Data;

import java.util.List;

@Data
public class SelectDetailPostDTO {
    private int num;
    private String title;
    private String content;
    private int member_num;
    private int good;
    private int cheer;
    private double temp;
    private List<ItemDTO> items;
    private List<HashTagDTO> tags;
    private List<PhotoDTO> photos;
}
