package fashionmanager.baek.develop.service;

import fashionmanager.baek.develop.aggregate.PostType;
import fashionmanager.baek.develop.dto.ModifyDTO;
import fashionmanager.baek.develop.dto.ResponseRegistPostDTO;
import fashionmanager.baek.develop.dto.RequestRegistPostDTO;
import fashionmanager.baek.develop.entity.FashionPost;
import org.springframework.http.ResponseEntity;

public interface PostService {
    ResponseRegistPostDTO registPost(RequestRegistPostDTO newPost);
    PostType getPostType();

    ModifyDTO modifyPost(int postNum, ModifyDTO updatePost);

    String deletePost(int postNum);
}
