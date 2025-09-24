package fashionmanager.baek.develop.controller;


import fashionmanager.baek.develop.aggregate.PostType;
import fashionmanager.baek.develop.dto.ModifyDTO;
import fashionmanager.baek.develop.dto.RegistResponseDTO;
import fashionmanager.baek.develop.dto.RegistRequestDTO;
import fashionmanager.baek.develop.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostServiceFactory postServiceFactory;
    private final ModelMapper modelMapper;

    @Autowired
    public PostController(PostServiceFactory postServiceFactory, ModelMapper modelMapper) {
        this.postServiceFactory = postServiceFactory;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/{postType}")   // Path로 값을 받아 알맞은 service로 연결돼 글 작성
    public ResponseEntity<RegistResponseDTO> registPost
            (@PathVariable String postType, @RequestPart("newPost") RegistRequestDTO newPost,
             @RequestPart(value = "images", required = false) List<MultipartFile> imageFiles) {
            // 사진은 Nullable하므로 required = false
        PostType type = PostType.valueOf(postType.toUpperCase());
        PostService postService = postServiceFactory.getService(type);  // postService 결정

        RegistResponseDTO response = postService.registPost(newPost, imageFiles); // method 작동

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{postType}/{postNum}")
    public ResponseEntity<ModifyDTO> modifyPost
            (@PathVariable String postType, @PathVariable int postNum,
             @RequestPart("newPost") ModifyDTO modifyDTO,
             @RequestPart(value = "images", required = false) List<MultipartFile> imageFiles) {
        PostType type = PostType.valueOf(postType.toUpperCase());
        PostService postService = postServiceFactory.getService(type);

        ModifyDTO response = postService.modifyPost(postNum, modifyDTO, imageFiles);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{postType}/{postNum}")
    public String deletePost
            (@PathVariable String postType, @PathVariable int postNum) {
        PostType type = PostType.valueOf(postType.toUpperCase());
        PostService postService = postServiceFactory.getService(type);

        String message = postService.deletePost(postNum);
        return message;
    }

    @ExceptionHandler(IllegalArgumentException.class)  // Factory 생성, 게시글 수정/삭제 과정에서 Exception 설정
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }
}
