package fashionmanager.baek.develop.controller;

import fashionmanager.baek.develop.aggregate.PostType;
import fashionmanager.baek.develop.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PostServiceFactory {
    private final Map<PostType, PostService> serviceMap = new HashMap<>();

    @Autowired
    public PostServiceFactory(List<PostService> postServices) {
        for(PostService service: postServices){
            serviceMap.put(service.getPostType(), service);
        }
    }

    public PostService getService(PostType postType){
        PostService service = serviceMap.get(postType);
        if(service == null){
            throw new IllegalArgumentException("해당 게시판은 존재하지 않습니다!");
        }
        return service;
    }
}
