package fashionmanager.baek.develop.controller;


import fashionmanager.baek.develop.aggregate.PostType;
import fashionmanager.baek.develop.dto.ModifyDTO;
import fashionmanager.baek.develop.dto.ResponseRegistPostDTO;
import fashionmanager.baek.develop.dto.RequestRegistPostDTO;
import fashionmanager.baek.develop.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ResponseRegistPostDTO> registPost
            (@PathVariable String postType, @RequestBody RequestRegistPostDTO newPost) {
        PostType type = PostType.valueOf(postType.toUpperCase());
        PostService postService = postServiceFactory.getService(type);  // postService 결정

        ResponseRegistPostDTO response = postService.registPost(newPost); // method 작동

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{postType}/{postNum}")
    public ResponseEntity<ModifyDTO> modifyPost
            (@PathVariable String postType, @PathVariable int postNum,
             @RequestBody ModifyDTO modifyDTO) {
        PostType type = PostType.valueOf(postType.toUpperCase());
        PostService postService = postServiceFactory.getService(type);

        ModifyDTO response = postService.modifyPost(postNum, modifyDTO);

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
