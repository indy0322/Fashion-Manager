package fashionmanager.baek.develop.controller;


import fashionmanager.baek.develop.aggregate.PostType;
import fashionmanager.baek.develop.dto.ModifyRequestDTO;
import fashionmanager.baek.develop.dto.ModifyResponseDTO;
import fashionmanager.baek.develop.dto.RegistResponseDTO;
import fashionmanager.baek.develop.dto.RegistRequestDTO;
import fashionmanager.baek.develop.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostServiceFactory postServiceFactory;

    @Autowired
    public PostController(PostServiceFactory postServiceFactory) {
        this.postServiceFactory = postServiceFactory;
    }

    @PostMapping("/{postType}")   // Path로 값을 받아 알맞은 service로 연결돼 글 작성
    @Transactional
    public ResponseEntity<RegistResponseDTO> registPost
            (@PathVariable String postType, @RequestPart("newPost") RegistRequestDTO newPost,
             @RequestPart(value = "postImages", required = false) List<MultipartFile> imageFiles,
            @RequestPart(value= "itemImages", required = false)  List<MultipartFile> itemImages) {
            // 사진은 Nullable하므로 required = false
        PostType type = PostType.valueOf(postType.toUpperCase());
        PostService postService = postServiceFactory.getService(type);  // postService 결정

        RegistResponseDTO response = postService.registPost(newPost, imageFiles); // method 작동

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{postType}/{postNum}")
    @Transactional
    public ResponseEntity<ModifyResponseDTO> modifyPost
            (@PathVariable String postType, @PathVariable int postNum,
             @RequestPart("modifyPost") ModifyRequestDTO modifyRequestDTO,
             @RequestPart(value = "images", required = false) List<MultipartFile> imageFiles) {
        PostType type = PostType.valueOf(postType.toUpperCase());
        PostService postService = postServiceFactory.getService(type);

        ModifyResponseDTO response = postService.modifyPost(postNum, modifyRequestDTO, imageFiles);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{postType}/{postNum}")
    @Transactional
    public ResponseEntity deletePost
            (@PathVariable String postType, @PathVariable int postNum) {
        PostType type = PostType.valueOf(postType.toUpperCase());
        PostService postService = postServiceFactory.getService(type);

        postService.deletePost(postNum);

        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(IllegalArgumentException.class)  // Factory 생성, 게시글 수정/삭제 과정에서 Exception 설정
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }
}
