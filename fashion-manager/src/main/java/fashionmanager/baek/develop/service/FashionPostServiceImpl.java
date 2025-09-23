package fashionmanager.baek.develop.service;

import fashionmanager.baek.develop.aggregate.PostType;
import fashionmanager.baek.develop.dto.ResponseRegistPostDTO;
import fashionmanager.baek.develop.dto.RequestRegistPostDTO;
import fashionmanager.baek.develop.entity.FashionPost;
import fashionmanager.baek.develop.repository.FashionPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FashionPostServiceImpl implements PostService {
    private final FashionPostRepository fashionPostRepository;

    @Autowired
    public FashionPostServiceImpl(FashionPostRepository fashionPostRepository) {
        this.fashionPostRepository = fashionPostRepository;
    }

    @Override
    public ResponseRegistPostDTO registPost(RequestRegistPostDTO newPost) {
        FashionPost fashionPost = requestToRegist(newPost);
        fashionPostRepository.save(fashionPost);
        ResponseRegistPostDTO response = new ResponseRegistPostDTO();
        response.setNum(fashionPost.getNum());
        return response;
    }

    private FashionPost requestToRegist(RequestRegistPostDTO newPost) {
        FashionPost fashionPost = new FashionPost();
        fashionPost.setTitle(newPost.getTitle());
        fashionPost.setContent(newPost.getContent());
        fashionPost.setGood(0);
        fashionPost.setCheer(0);
        fashionPost.setMember_num(newPost.getMember_num());
        return fashionPost;
    }

    @Override
    public PostType getPostType() {
        return PostType.FASHION;
    }
}
