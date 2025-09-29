package fashionmanager.baek.develop.service;

import fashionmanager.baek.develop.aggregate.PostType;
import fashionmanager.baek.develop.dto.ModifyRequestDTO;
import fashionmanager.baek.develop.dto.ModifyResponseDTO;
import fashionmanager.baek.develop.dto.RegistRequestDTO;
import fashionmanager.baek.develop.dto.RegistResponseDTO;
import fashionmanager.baek.develop.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {
    RegistResponseDTO registPost(RegistRequestDTO newPost, List<MultipartFile> postFiles,
                                 List<MultipartFile> itemFiles);
    PostType getPostType();

    ModifyResponseDTO modifyPost(int postNum, ModifyRequestDTO updatePost,
                                 List<MultipartFile> postFiles, List<MultipartFile> itemFiles);

    void deletePost(int postNum);

    List<SelectAllPostDTO> getPostList();

    SelectDetailPostDTO getDetailPost(int postNum);
}
