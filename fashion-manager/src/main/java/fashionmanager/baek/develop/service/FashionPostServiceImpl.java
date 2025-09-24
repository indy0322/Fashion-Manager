package fashionmanager.baek.develop.service;

import fashionmanager.baek.develop.aggregate.PostType;
import fashionmanager.baek.develop.dto.ModifyDTO;
import fashionmanager.baek.develop.dto.RegistResponseDTO;
import fashionmanager.baek.develop.dto.RegistRequestDTO;
import fashionmanager.baek.develop.entity.FashionHashTagEntity;
import fashionmanager.baek.develop.entity.FashionPostEntity;
import fashionmanager.baek.develop.entity.FashionPostItemEntity;
import fashionmanager.baek.develop.entity.PhotoEntity;
import fashionmanager.baek.develop.entity.pk.FashionHashTagPK;
import fashionmanager.baek.develop.entity.pk.FashionPostItemPK;
import fashionmanager.baek.develop.repository.FashionHashRepository;
import fashionmanager.baek.develop.repository.FashionItemRepository;
import fashionmanager.baek.develop.repository.FashionPostRepository;
import fashionmanager.baek.develop.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class FashionPostServiceImpl implements PostService {
    private final FashionPostRepository fashionPostRepository;
    private final FashionHashRepository fashionHashRepository;
    private final FashionItemRepository fashionItemRepository;
    private final PhotoRepository photoRepository;
    private String uploadPath = "C:\\uploadFiles";


    @Autowired
    public FashionPostServiceImpl(FashionPostRepository fashionPostRepository, FashionHashRepository fashionHashRepository,
                                  FashionItemRepository fashionItemRepository, PhotoRepository photoRepository) {
        this.fashionPostRepository = fashionPostRepository;
        this.fashionHashRepository = fashionHashRepository;
        this.fashionItemRepository = fashionItemRepository;
        this.photoRepository = photoRepository;
    }

    @Override
    public RegistResponseDTO registPost(RegistRequestDTO newPost, List<MultipartFile> imageFiles) {
        /* 설명. 1. fashion_post table에 게시글 등록 */
        FashionPostEntity fashionPostEntity = changeToRegistPost(newPost);
        FashionPostEntity registFashionPost = fashionPostRepository.save(fashionPostEntity);
        int postNum = registFashionPost.getNum();

        /* 설명. 2. fashion_hash table에 해시태그 등록 */
        for (Integer tagNums : newPost.getHashtag()) {
            FashionHashTagPK fashionHashTagPK = new FashionHashTagPK(postNum, tagNums);
            FashionHashTagEntity fashionHashTagEntity = new FashionHashTagEntity(fashionHashTagPK);
            fashionHashRepository.save(fashionHashTagEntity);
        }
        /* 설명. 3. photo table에 사진 등록, 사진 카테고리 번호 1 = 패션 게시물 */
        if (imageFiles != null && !imageFiles.isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs(); // 경로에 해당하는 폴더가 없으면 생성해줌 (하위 폴더까지 포함)
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
                photoEntity.setName(savedFileName); // 고유한 이름으로 저장
                photoEntity.setPostNum(postNum);
                photoEntity.setPath(uploadPath);
                photoEntity.setPhotoCategoryNum(1);

                photoRepository.save(photoEntity);
            }
        }

        /* 설명. 4. fashion_item table에 아이템 등록 */
        for (Integer itemNums : newPost.getItems()) {
            FashionPostItemPK fashionPostItemPK = new FashionPostItemPK(postNum, itemNums);
            FashionPostItemEntity fashionPostItemEntity = new FashionPostItemEntity(fashionPostItemPK);
            fashionItemRepository.save(fashionPostItemEntity); // 반복문 안에서 매번 저장
        }

        /* 설명. 5. responseDTO 생성 */
        RegistResponseDTO response = new RegistResponseDTO();
        response.setNum(postNum);
        response.setTitle(registFashionPost.getTitle());
        response.setContent(registFashionPost.getContent());
        response.setMember_num(registFashionPost.getMember_num());
        return response;
    }
    private FashionPostEntity changeToRegistPost(RegistRequestDTO newPost) {
        FashionPostEntity fashionPostEntity = new FashionPostEntity();
        fashionPostEntity.setTitle(newPost.getTitle());
        fashionPostEntity.setContent(newPost.getContent());
        fashionPostEntity.setGood(0);
        fashionPostEntity.setCheer(0);
        fashionPostEntity.setMember_num(newPost.getMember_num());
        return fashionPostEntity;
    }

    @Override
    public PostType getPostType() {
        return PostType.FASHION;
    }

    @Override
    @Transactional
    public ModifyDTO modifyPost(int postNum, ModifyDTO updatePost, List<MultipartFile> imageFiles) {
        FashionPostEntity fashionPostEntity = fashionPostRepository.findById(postNum)
                        .orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다. id=" + postNum));
        fashionPostRepository.save(changeToUpdate(fashionPostEntity, updatePost));
        ModifyDTO modifyPost = new ModifyDTO();
        modifyPost.setNum(fashionPostEntity.getNum());
        modifyPost.setTitle(fashionPostEntity.getTitle());
        modifyPost.setContent(fashionPostEntity.getContent());
        modifyPost.setMember_num(fashionPostEntity.getMember_num());
        return modifyPost;
    }

    private FashionPostEntity changeToUpdate(FashionPostEntity updatePost, ModifyDTO modifyPost) {
        updatePost.setTitle(modifyPost.getTitle());
        updatePost.setContent(modifyPost.getContent());
        return updatePost;
    }

    @Override
    @Transactional
    public String deletePost(int postNum) {
        FashionPostEntity postToDelete = fashionPostRepository.findById(postNum)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글은 존재하지 않습니다."));
        fashionPostRepository.delete(postToDelete);
        return "게시글이 성공적으로 삭제됐습니다.";
    }
}
