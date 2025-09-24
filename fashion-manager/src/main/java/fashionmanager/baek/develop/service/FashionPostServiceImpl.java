package fashionmanager.baek.develop.service;

import fashionmanager.baek.develop.aggregate.PostType;
import fashionmanager.baek.develop.dto.ModifyDTO;
import fashionmanager.baek.develop.dto.ResponseRegistPostDTO;
import fashionmanager.baek.develop.dto.RequestRegistPostDTO;
import fashionmanager.baek.develop.entity.FashionPost;
import fashionmanager.baek.develop.repository.FashionPostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FashionPostServiceImpl implements PostService {
    private final FashionPostRepository fashionPostRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public FashionPostServiceImpl(FashionPostRepository fashionPostRepository, ModelMapper modelMapper) {
        this.fashionPostRepository = fashionPostRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseRegistPostDTO registPost(RequestRegistPostDTO newPost) {
        FashionPost fashionPost = changeToRegist(newPost);
        fashionPostRepository.save(fashionPost);
        ResponseRegistPostDTO response = new ResponseRegistPostDTO();
        response.setNum(fashionPost.getNum());
        return response;
    }

    private FashionPost changeToRegist(RequestRegistPostDTO newPost) {
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

    @Override
    @Transactional
    public ModifyDTO modifyPost(int postNum, ModifyDTO updatePost) {
        FashionPost fashionPost = fashionPostRepository.findById(postNum)
                        .orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다. id=" + postNum));
        fashionPostRepository.save(changeToUpdate(fashionPost, updatePost));
        ModifyDTO modifyPost = new ModifyDTO();
        modifyPost.setNum(fashionPost.getNum());
        modifyPost.setTitle(fashionPost.getTitle());
        modifyPost.setContent(fashionPost.getContent());
        modifyPost.setMember_num(fashionPost.getMember_num());
        return modifyPost;
    }

    private FashionPost changeToUpdate(FashionPost updatePost, ModifyDTO modifyPost) {
        updatePost.setTitle(modifyPost.getTitle());
        updatePost.setContent(modifyPost.getContent());
        return updatePost;
    }

    @Override
    @Transactional
    public String deletePost(int postNum) {
        FashionPost postToDelete = fashionPostRepository.findById(postNum)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글은 존재하지 않습니다."));
        fashionPostRepository.delete(postToDelete);
        return "게시글이 성공적으로 삭제됐습니다.";
    }
}
