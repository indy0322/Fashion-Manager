package fashionmanager.baek.develop.controller;


import fashionmanager.baek.develop.aggregate.PostType;
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

    @PostMapping("/{postType}")
    public ResponseEntity<ResponseRegistPostDTO> registPostByPath
            (@PathVariable String postType, @RequestBody RequestRegistPostDTO newPost) {
        PostType type = PostType.valueOf(postType.toUpperCase());
        PostService postService = postServiceFactory.getService(type);
        ResponseRegistPostDTO response = postService.registPost(newPost);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
