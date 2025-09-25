package fashionmanager.baek.develop.service;


import fashionmanager.baek.develop.aggregate.PostType;
import fashionmanager.baek.develop.dto.ModifyRequestDTO;
import fashionmanager.baek.develop.dto.ModifyResponseDTO;
import fashionmanager.baek.develop.dto.RegistRequestDTO;
import fashionmanager.baek.develop.dto.RegistResponseDTO;
import fashionmanager.baek.develop.entity.PhotoEntity;
import fashionmanager.baek.develop.entity.ReviewPostEntity;
import fashionmanager.baek.develop.entity.ReviewPostItemEntity;
import fashionmanager.baek.develop.entity.pk.ReviewPostItemPK;
import fashionmanager.baek.develop.repository.PhotoRepository;
import fashionmanager.baek.develop.repository.ReviewItemRepository;
import fashionmanager.baek.develop.repository.ReviewPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReviewPostServiceImpl implements PostService{
    private final ReviewPostRepository reviewPostRepository;
    private final ReviewItemRepository reviewItemRepository;
    private final PhotoRepository photoRepository;
    private String uploadPath = "C:\\uploadFiles\\review";

    @Autowired
    public ReviewPostServiceImpl(ReviewPostRepository reviewPostRepository,
                                 ReviewItemRepository reviewItemRepository, PhotoRepository photoRepository) {
        this.reviewPostRepository = reviewPostRepository;
        this.reviewItemRepository = reviewItemRepository;
        this.photoRepository = photoRepository;
    }

    @Override
    @Transactional
    public RegistResponseDTO registPost(RegistRequestDTO newPost, List<MultipartFile> imageFiles) {
        /* 설명. 1. fashion_post table에 게시글 등록 */
        ReviewPostEntity reviewPostEntity = changeToRegistPost(newPost);
        ReviewPostEntity registReviewPost = reviewPostRepository.save(reviewPostEntity);
        int postNum = registReviewPost.getNum();

        /* 설명. 2. review_item table에 아이템 등록 */
        for (Integer itemNums : newPost.getItems()) {
            ReviewPostItemPK reviewPostItemPK = new ReviewPostItemPK(postNum, itemNums);
            ReviewPostItemEntity reviewPostItemEntity = new ReviewPostItemEntity(reviewPostItemPK);
            reviewItemRepository.save(reviewPostItemEntity); // 반복문 안에서 매번 저장
        }

        /* 설명. 3. photo table에 사진 등록, 사진 카테고리 번호 1 = 패션 게시물 */
        if (imageFiles != null && !imageFiles.isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs(); // 경로에 해당하는 폴더가 없으면 생성해줌
            }
            for (MultipartFile imageFile : imageFiles) {
                String originalFileName = imageFile.getOriginalFilename();
                String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
                String savedFileName = UUID.randomUUID().toString() + extension;

                File targetFile = new File(uploadPath + File.separator + savedFileName);
                try {
                    imageFile.transferTo(targetFile);
                } catch (IOException e) {
                    throw new RuntimeException("파일 저장에 실패했습니다", e);
                }

                PhotoEntity photoEntity = new PhotoEntity();
                photoEntity.setName(savedFileName);   // 고유한 이름으로 저장
                photoEntity.setPath(uploadPath);
                photoEntity.setPhotoCategoryNum(2);   // 리뷰 게시판은 2

                photoRepository.save(photoEntity);
            }
        }
        RegistResponseDTO response = new RegistResponseDTO();
        response.setNum(postNum);
        response.setTitle(registReviewPost.getTitle());
        response.setContent(registReviewPost.getContent());
        response.setMember_num(registReviewPost.getMemberNum());
        return response;
    }

    private ReviewPostEntity changeToRegistPost(RegistRequestDTO newPost) {
        ReviewPostEntity reviewPostEntity = new ReviewPostEntity();
        reviewPostEntity.setTitle(newPost.getTitle());
        reviewPostEntity.setContent(newPost.getContent());
        reviewPostEntity.setGood(0);
        reviewPostEntity.setCheer(0);
        reviewPostEntity.setMemberNum(newPost.getMember_num());
        return reviewPostEntity;
    }

    @Override
    public PostType getPostType() {
        return null;
    }

    @Override
    public ModifyResponseDTO modifyPost(int postNum, ModifyRequestDTO updatePost,
                                        List<MultipartFile> imageFiles) {
        ReviewPostEntity reviewPostEntity = reviewPostRepository.findById(postNum)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다. id=" + postNum));

        reviewPostEntity.setTitle(updatePost.getTitle());
        reviewPostEntity.setContent(updatePost.getContent());

        List<Integer> updateItems = updateItems(postNum, updatePost.getItems());
        return null;
    }

    private List<Integer> updateItems(int postNum, List<Integer> newItemId) {
        List<Integer> currentItemsIds = reviewItemRepository.findAllByReviewPostItemPK_PostNum(postNum)
                .stream()
                .map(item -> item.getReviewPostItemPK().getItemNum())
                .collect(Collectors.toList());

        List<Integer> itemsToRemove = currentItemsIds.stream()
                .filter(id -> !newItemId.contains(id))
                .collect(Collectors.toList());
        if(!itemsToRemove.isEmpty()) {
            reviewItemRepository.deleteAllByReviewPostItemPK_PostNumAndReviewPostItemPK_ItemNumIn(postNum, itemsToRemove);
        }
    }

    @Override
    public void deletePost(int postNum) {

    }
}
