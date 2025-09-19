package fashionmanager.kim.develop.service;

import fashionmanager.kim.develop.dto.MessageCategoryDTO;
import fashionmanager.kim.develop.dto.ReportCategoryDTO;
import fashionmanager.kim.develop.dto.ReviewCategoryDTO;
import fashionmanager.kim.develop.entity.ReportCategory;
import fashionmanager.kim.develop.entity.ReviewCategory;
import fashionmanager.kim.develop.mapper.MessageCategoryMapper;
import fashionmanager.kim.develop.mapper.ReportCategoryMapper;
import fashionmanager.kim.develop.mapper.ReviewCategoryMapper;
import fashionmanager.kim.develop.repository.ReportCategoryRepository;
import fashionmanager.kim.develop.repository.ReviewCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final ReviewCategoryMapper reviewCategoryMapper;
    private final ReportCategoryMapper reportCategoryMapper;
    private final MessageCategoryMapper messageCategoryMapper;

    private final ReviewCategoryRepository reviewCategoryRepository;
    private final ReportCategoryRepository reportCategoryRepository;

    @Autowired
    public CategoryService(ReviewCategoryMapper categoryMapper, ReportCategoryMapper reportCategoryMapper, MessageCategoryMapper messageCategoryMapper,
                           ReviewCategoryRepository reviewCategoryRepository, ReportCategoryRepository reportCategoryRepository) {
        this.reviewCategoryMapper = categoryMapper;
        this.reportCategoryMapper = reportCategoryMapper;
        this.messageCategoryMapper = messageCategoryMapper;

        this.reviewCategoryRepository = reviewCategoryRepository;
        this.reportCategoryRepository = reportCategoryRepository;
    }

    public List<ReviewCategoryDTO> selectAllReviewCategories() {
        return reviewCategoryMapper.selectAllReviewCategories();
    }

    public int insertReviewCategory(String insertReviewCategoryName){

        if(insertReviewCategoryName == null || insertReviewCategoryName.isEmpty()){
            return 0;
        }
        int num = reviewCategoryRepository.findMaxNum() + 1;
        String name = insertReviewCategoryName;
        reviewCategoryRepository.save(new ReviewCategory(num, name));
        return 1;
    }

    public List<ReportCategoryDTO> selectAllReportCategories() {
        return reportCategoryMapper.selectAllReportCategories();
    }

    public int insertReportCategory(String insertReportCategoryName) {

        if(insertReportCategoryName == null || insertReportCategoryName.isEmpty()){
            return 0;
        }
        int num = reportCategoryRepository.findMaxNum() + 1;
        String name = insertReportCategoryName;
        reportCategoryRepository.save(new ReportCategory(num, name));
        return 1;
    }

    public List<MessageCategoryDTO> selectAllMessageCategories() {
        return messageCategoryMapper.selectAllMessageCategories();
    }

    public int insertMessageCategory(String insertMessageCategoryName) {
        if(insertMessageCategoryName == null || insertMessageCategoryName.isEmpty()){
            return 0;
        }
        int num = reportCategoryRepository.findMaxNum() + 1;
        String name = insertMessageCategoryName;
        reportCategoryRepository.save(new ReportCategory(num, name));
        return 1;
    }
}
