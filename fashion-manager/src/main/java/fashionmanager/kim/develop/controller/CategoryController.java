package fashionmanager.kim.develop.controller;

import fashionmanager.kim.develop.MessageCategoryDTO;
import fashionmanager.kim.develop.ReportCategoryDTO;
import fashionmanager.kim.develop.ReviewCategoryDTO;
import fashionmanager.kim.develop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {

    private final CategoryService cs;
//
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.cs = categoryService;
    }

    @GetMapping("/selectreviewcategory")
    public List<ReviewCategoryDTO> selectAllReviewCategories() {
        List<ReviewCategoryDTO> reviewCategoryList = cs.selectAllReviewCategories();
        return reviewCategoryList;
    }

    @GetMapping("/selectreportcategory")
    public List<ReportCategoryDTO> selectAllReportCategories() {
        List<ReportCategoryDTO> reportCategoryList = cs.selectAllReportCategories();
        return reportCategoryList;
    }

    @GetMapping("/selectmessagecategory")
    public List<MessageCategoryDTO> selectAllMessageCategories() {
        List<MessageCategoryDTO> messageCategoryList = cs.selectAllMessageCategories();
        return messageCategoryList;
    }

}
