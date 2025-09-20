package fashionmanager.kim.develop.controller;

import fashionmanager.kim.develop.dto.MessageCategoryDTO;
import fashionmanager.kim.develop.dto.ReportCategoryDTO;
import fashionmanager.kim.develop.dto.ReviewCategoryDTO;
import fashionmanager.kim.develop.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/category")
public class CategoryController {

    private final CategoryService cs;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.cs = categoryService;
    }

    @GetMapping("/selectreviewcategory")
    public List<ReviewCategoryDTO> selectAllReviewCategories() {
        List<ReviewCategoryDTO> reviewCategoryList = cs.selectAllReviewCategories();
        return reviewCategoryList;
    }

    @PostMapping("/insertreviewcategory")
    public String insertReviewCategory(String insertReviewCategoryName) {
        int result = cs.insertReviewCategory(insertReviewCategoryName);
        if(result == 1){
            log.info("새로운 후기 카테고리 " + insertReviewCategoryName + "요소가 등록되었습니다.");
            return "새로운 후기 카테고리 " +insertReviewCategoryName + "요소가 등록되었습니다.";
        }else{
            log.info("새로운 후기 카테고리 요소 등록에 실패했습니다.");
            return "새로운 후기 카테고리 요소 등록에 실패했습니다.";
        }
    }

    @PostMapping("updatereviewcategory")
    public String updateReviewCategory(@RequestBody ReviewCategoryDTO reviewCategoryDTO) {
        int result = cs.updateReviewCategory(reviewCategoryDTO);
        if(result == 1){
            log.info("새로운 후기 카테고리 " + reviewCategoryDTO.getReviewCategoryName() + "요소가 수정되었습니다.");
            return "새로운 후기 카테고리 " + reviewCategoryDTO.getReviewCategoryName() + "요소가 수정되었습니다.";
        }else{
            log.info("새로운 후기 카테고리 " + reviewCategoryDTO.getReviewCategoryName() + "요소가 제대로 수정되지 못했습니다.");
            return "새로운 후기 카테고리 " + reviewCategoryDTO.getReviewCategoryName() + "요소가 제대로 수정되지 못했습니다.";
        }
    }

    @GetMapping("/selectreportcategory")
    public List<ReportCategoryDTO> selectAllReportCategories() {
        List<ReportCategoryDTO> reportCategoryList = cs.selectAllReportCategories();
        return reportCategoryList;
    }

    @PostMapping("/insertreportcategory")
    public String insertReportCategory(String insertReportCategoryName) {
        int result = cs.insertReportCategory(insertReportCategoryName);
        if(result == 1){
            log.info("새로운 신고 카테고리 " + insertReportCategoryName + "요소가 등록되었습니다.");
            return "새로운 신고 카테고리 " +insertReportCategoryName + "요소가 등록되었습니다.";
        }else{
            log.info("새로운 신고 카테고리 요소 등록에 실패했습니다.");
            return "새로운 신고 카테고리 요소 등록에 실패했습니다.";
        }
    }

    @PostMapping("/updatereportcategory")
    public String updateReportCategory(@RequestBody ReportCategoryDTO reportCategoryDTO) {
        int result = cs.updateReportCategory(reportCategoryDTO);
        if(result == 1){
            log.info("새로운 신고 카테고리 " + reportCategoryDTO.getReportCategoryName() + "요소가 수정되었습니다.");
            return "새로운 신고 카테고리 " + reportCategoryDTO.getReportCategoryName() + "요소가 수정되었습니다.";
        }else{
            log.info("새로운 신고 카테고리 " + reportCategoryDTO.getReportCategoryName() + "요소가 제대로 수정되지 못했습니다.");
            return "새로운 신고 카테고리 " + reportCategoryDTO.getReportCategoryName() + "요소가 제대로 수정되지 못했습니다.";
        }
    }

    @GetMapping("/selectmessagecategory")
    public List<MessageCategoryDTO> selectAllMessageCategories() {
        List<MessageCategoryDTO> messageCategoryList = cs.selectAllMessageCategories();
        return messageCategoryList;
    }

    @PostMapping("/insertmessagecategory")
    public String insertMessageCategory(String insertMessageCategoryName) {
        int result = cs.insertMessageCategory(insertMessageCategoryName);
        if(result == 1){
            log.info("새로운 쪽지 카테고리 " + insertMessageCategoryName + "요소가 등록되었습니다.");
            return "새로운 쪽지 카테고리 " + insertMessageCategoryName + "요소가 등록되었습니다.";
        }else{
            log.info("새로운 쪽지 카테고리 요소 등록에 실패했습니다.");
            return "새로운 쪽지 카테고리 요소 등록에 실패했습니다.";
        }
    }

    @PostMapping("/updatemessagecategory")
    public String updateMessageCategory(@RequestBody MessageCategoryDTO messageCategoryDTO) {
        int result = cs.updateMessageCategory(messageCategoryDTO);
        if(result == 1){
            log.info("새로운 쪽지 카테고리 " + messageCategoryDTO.getMessageCategoryName() + "요소가 수정되었습니다.");
            return "새로운 쪽지 카테고리 " + messageCategoryDTO.getMessageCategoryName() + "요소가 수정되었습니다.";
        }else{
            log.info("새로운 쪽지 카테고리 " + messageCategoryDTO.getMessageCategoryName() + "요소가 제대로 수정되지 못했습니다.");
            return "새로운 쪽지 카테고리 " + messageCategoryDTO.getMessageCategoryName() + "요소가 제대로 수정되지 못했습니다.";
        }
    }

}
