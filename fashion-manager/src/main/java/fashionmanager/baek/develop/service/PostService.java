package fashionmanager.baek.develop.service;

import fashionmanager.baek.develop.aggregate.PostType;
import fashionmanager.baek.develop.dto.ModifyDTO;
import fashionmanager.baek.develop.dto.RegistResponseDTO;
import fashionmanager.baek.develop.dto.RegistRequestDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {
    RegistResponseDTO registPost(RegistRequestDTO newPost, List<MultipartFile> imageFiles);
    PostType getPostType();

    ModifyDTO modifyPost(int postNum, ModifyDTO updatePost, List<MultipartFile> imageFiles);

    String deletePost(int postNum);
}
