package fashionmanager.baek.develop.service;

import fashionmanager.baek.develop.aggregate.PostType;
import fashionmanager.baek.develop.dto.*;
import fashionmanager.baek.develop.entity.MentoringPostEntity;
import fashionmanager.baek.develop.entity.PhotoEntity;
import fashionmanager.baek.develop.mapper.MentoringPostMapper;
import fashionmanager.baek.develop.repository.MentoringPostRespository;
import fashionmanager.baek.develop.repository.PhotoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class MentoringPostServiceImpl implements PostService {
    private final MentoringPostRespository mentoringPostRespository;
    private final PhotoRepository photoRepository;
    private final MentoringPostMapper mentoringPostMapper;
    private String postUploadPath = "C:\\uploadFiles\\mentoring";

    @Autowired
    public MentoringPostServiceImpl(MentoringPostRespository mentoringPostRespository,
                                    PhotoRepository photoRepository, MentoringPostMapper mentoringPostMapper) {
        this.mentoringPostRespository = mentoringPostRespository;
        this.photoRepository = photoRepository;
        this.mentoringPostMapper = mentoringPostMapper;
    }

    @Override
    public List<SelectAllPostDTO> getPostList() {
        return mentoringPostMapper.findAll();
    }

    @Override
    @Transactional
    public RegistResponseDTO registPost(RegistRequestDTO newPost, List<MultipartFile> postFiles,
                                        List<MultipartFile> itemFiles) {
        MentoringPostEntity mentoringPostEntity = changeToRegistPost(newPost);
        MentoringPostEntity registMentoringPost =  mentoringPostRespository.save(mentoringPostEntity);
        int postNum = registMentoringPost.getNum();

        if (postFiles != null && !postFiles.isEmpty()) {
            File uploadDir = new File(postUploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs(); // 경로에 해당하는 폴더가 없으면 생성해줌
            }
            for (MultipartFile imageFile : postFiles) {
                String originalFileName = imageFile.getOriginalFilename();
                String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
                String savedFileName = UUID.randomUUID().toString() + extension;

                File targetFile = new File(postUploadPath + File.separator + savedFileName);
                try {
                    imageFile.transferTo(targetFile);
                } catch (IOException e) {
                    throw new RuntimeException("파일 저장에 실패했습니다", e);
                }
                PhotoEntity photoEntity = new PhotoEntity();
                photoEntity.setName(savedFileName); // 고유한 이름으로 저장
                photoEntity.setPath(postUploadPath);
                photoEntity.setPostNum(postNum);    // postNum과 CategoryNum 지정
                photoEntity.setPhotoCategoryNum(3); // 멘토링 게시물 사진은 3
                photoRepository.save(photoEntity);
            }
        }

        RegistResponseDTO response = new RegistResponseDTO();
        response.setNum(postNum);
        response.setTitle(registMentoringPost.getTitle());
        response.setContent(registMentoringPost.getContent());
        response.setMember_num(registMentoringPost.getAuthorNum());
        response.setFinish(registMentoringPost.isFinish());
        return response;
    }

    private MentoringPostEntity changeToRegistPost(RegistRequestDTO newPost) {
        MentoringPostEntity mentoringPostEntity = new MentoringPostEntity();
        mentoringPostEntity.setTitle(newPost.getTitle());
        mentoringPostEntity.setContent(newPost.getContent());
        mentoringPostEntity.setAuthorNum(newPost.getMember_num());

        return mentoringPostEntity;
    }

    @Override
    public PostType getPostType() {
        return PostType.MENTORING;
    }

    @Override
    @Transactional
    public ModifyResponseDTO modifyPost(int postNum, ModifyRequestDTO updatePost,
                                        List<MultipartFile> postFiles, List<MultipartFile> itemFiles) {
        MentoringPostEntity mentoringPostEntity = mentoringPostRespository.findById(postNum)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다. id=" + postNum));

        mentoringPostEntity.setTitle(updatePost.getTitle());
        mentoringPostEntity.setContent(updatePost.getContent());
        mentoringPostEntity.setFinish(updatePost.isFinish());
        mentoringPostEntity.setAuthorNum(updatePost.getMember_num());

        updatePhotos(mentoringPostEntity,this.postUploadPath, postFiles, 3);

        ModifyResponseDTO response = new ModifyResponseDTO();
        response.setNum(postNum);
        response.setTitle(mentoringPostEntity.getTitle());
        response.setContent(mentoringPostEntity.getContent());
        response.setFinish(mentoringPostEntity.isFinish());
        response.setMember_num(mentoringPostEntity.getAuthorNum());
        return response;
    }

    private void updatePhotos(MentoringPostEntity post, String uploadPath, List<MultipartFile> newImageFiles, int categoryNum) {
        int postNum = post.getNum();
        List<PhotoEntity> photosToUpdate = photoRepository.findAllByPostNumAndPhotoCategoryNum(postNum, categoryNum);
        for (PhotoEntity photo : photosToUpdate) {
            File fileToDelete = new File(photo.getPath() + File.separator + photo.getName());
            if (fileToDelete.exists()) {
                fileToDelete.delete();
            }
        }
        photoRepository.deleteAll(photosToUpdate);

        if (newImageFiles != null && !newImageFiles.isEmpty()) {
            saveNewPhotos(post, uploadPath, newImageFiles, categoryNum);
        }
    }
    private void saveNewPhotos(MentoringPostEntity post, String uploadPath,
                               List<MultipartFile> imageFiles, int categoryNum) {
        int postNum = post.getNum();
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) { uploadDir.mkdirs(); }
        for (MultipartFile imageFile : imageFiles) {
            String originalFileName = imageFile.getOriginalFilename();
            String extension = "";
            if (originalFileName != null) {
                extension = originalFileName.substring(originalFileName.lastIndexOf("."));
            }
            String savedFileName = UUID.randomUUID().toString() + extension;

            File targetFile = new File(uploadPath + File.separator + savedFileName);
            try {
                imageFile.transferTo(targetFile);
            } catch (IOException e) {
                throw new RuntimeException("새로운 파일 저장에 실패했습니다", e);
            }

            PhotoEntity newPhoto = new PhotoEntity();
            newPhoto.setName(savedFileName);
            newPhoto.setPath(uploadPath);
            newPhoto.setPostNum(postNum);
            newPhoto.setPhotoCategoryNum(categoryNum); // 해당 카테고리 넘버
            photoRepository.save(newPhoto);
        }
    }

    @Override
    @Transactional
    public void deletePost(int postNum) {
        MentoringPostEntity mentoringToDelete = mentoringPostRespository.findById(postNum)
                .orElseThrow(() ->new IllegalArgumentException("해당 게시글은 존재하지 않습니다."));

        List<PhotoEntity> photosToDelete = photoRepository.findAllByPostNumAndPhotoCategoryNum(postNum, 3);
        for (PhotoEntity photo : photosToDelete) {
            File file = new File(photo.getPath() + File.separator + photo.getName());
            if(file.exists()) {
                file.delete();
            }
        }
        photoRepository.deleteAll(photosToDelete);

        mentoringPostRespository.deleteById(postNum);
    }
}
