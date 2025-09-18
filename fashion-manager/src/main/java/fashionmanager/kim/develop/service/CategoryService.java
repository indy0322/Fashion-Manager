package fashionmanager.kim.develop.service;

import fashionmanager.kim.develop.MessageCategoryDTO;
import fashionmanager.kim.develop.ReportCategoryDTO;
import fashionmanager.kim.develop.ReviewCategoryDTO;
import fashionmanager.kim.develop.mapper.MessageCategoryMapper;
import fashionmanager.kim.develop.mapper.ReportCategoryMapper;
import fashionmanager.kim.develop.mapper.ReviewCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final ReviewCategoryMapper reviewCategoryMapper;
    private final ReportCategoryMapper reportCategoryMapper;
    private final MessageCategoryMapper messageCategoryMapper;


    @Autowired
    public CategoryService(ReviewCategoryMapper categoryMapper, ReportCategoryMapper reportCategoryMapper, MessageCategoryMapper messageCategoryMapper) {
        this.reviewCategoryMapper = categoryMapper;
        this.reportCategoryMapper = reportCategoryMapper;
        this.messageCategoryMapper = messageCategoryMapper;
    }

    public List<ReviewCategoryDTO> selectAllReviewCategories() {
        return reviewCategoryMapper.selectAllReviewCategories();
    }

    public List<ReportCategoryDTO> selectAllReportCategories() {
        return reportCategoryMapper.selectAllReportCategories();
    }

    public List<MessageCategoryDTO> selectAllMessageCategories() {
        return messageCategoryMapper.selectAllMessageCategories();
    }
}
