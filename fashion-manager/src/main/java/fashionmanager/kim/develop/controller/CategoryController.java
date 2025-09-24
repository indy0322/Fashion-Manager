package fashionmanager.kim.develop.controller;

import fashionmanager.kim.develop.dto.MessageCategoryDTO;
import fashionmanager.kim.develop.dto.ReportCategoryDTO;
import fashionmanager.kim.develop.dto.ReviewCategoryDTO;
import fashionmanager.kim.develop.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    private final CategoryService cs;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.cs = categoryService;
    }

    @GetMapping("/selectreviewcategory")
    public ResponseEntity<List<ReviewCategoryDTO>> selectAllReviewCategories() {
        List<ReviewCategoryDTO> reviewCategoryList = cs.selectAllReviewCategories();
        for (ReviewCategoryDTO reviewCategoryDTO : reviewCategoryList) {
            log.info("reviewCategoryDTO: {}", reviewCategoryDTO);
        }
        return ResponseEntity.ok(reviewCategoryList);
    }

    @PostMapping("/insertreviewcategory")
    public ResponseEntity<String> insertReviewCategory(String insertReviewCategoryName) {
        List<ReviewCategoryDTO> list = cs.selectAllReviewCategories();
        for (ReviewCategoryDTO reviewCategoryDTO : list) {
            if(reviewCategoryDTO.getReviewCategoryName().equals(insertReviewCategoryName)){
                log.info("새로운 후기 카테고리 요소 등록에 실패했습니다.");
                return ResponseEntity.ok("새로운 후기 카테고리 요소 등록에 실패했습니다.");
            }
        }

        int result = cs.insertReviewCategory(insertReviewCategoryName);
        if(result == 1){
            log.info("새로운 후기 카테고리 " + insertReviewCategoryName + "요소가 등록되었습니다.");
            return ResponseEntity.ok("새로운 후기 카테고리 " +insertReviewCategoryName + "요소가 등록되었습니다.");
        }else{
            log.info("새로운 후기 카테고리 요소 등록에 실패했습니다.");
            return ResponseEntity.ok("새로운 후기 카테고리 요소 등록에 실패했습니다.");
        }
    }

    @PostMapping("updatereviewcategory")
    public ResponseEntity<String> updateReviewCategory(@RequestBody ReviewCategoryDTO reviewCategoryDTO) {
        List<ReviewCategoryDTO> list = cs.selectAllReviewCategories();
        for (ReviewCategoryDTO reviewCategory : list) {
            if(reviewCategory.getReviewCategoryName().equals(reviewCategoryDTO.getReviewCategoryName())){
                log.info("새로운 후기 카테고리 " + reviewCategoryDTO.getReviewCategoryName() + "요소가 제대로 수정되지 못했습니다.");
                return ResponseEntity.ok("새로운 후기 카테고리 " + reviewCategoryDTO.getReviewCategoryName() + "요소가 제대로 수정되지 못했습니다.");
            }
        }

        int result = cs.updateReviewCategory(reviewCategoryDTO);
        if(result == 1){
            log.info("새로운 후기 카테고리 " + reviewCategoryDTO.getReviewCategoryName() + "요소가 수정되었습니다.");
            return ResponseEntity.ok("새로운 후기 카테고리 " + reviewCategoryDTO.getReviewCategoryName() + "요소가 수정되었습니다.");
        }else{
            log.info("새로운 후기 카테고리 " + reviewCategoryDTO.getReviewCategoryName() + "요소가 제대로 수정되지 못했습니다.");
            return ResponseEntity.ok("새로운 후기 카테고리 " + reviewCategoryDTO.getReviewCategoryName() + "요소가 제대로 수정되지 못했습니다.");
        }
    }

    @PostMapping("/deletereviewcategory")
    public ResponseEntity<String> deleteReviewCategory(int deleteReviewCategoryNum){
        int result = cs.deleteReviewCategory(deleteReviewCategoryNum);
        if(result == 1){
            log.info("후기 카테고리 " + deleteReviewCategoryNum + "번이 삭제되었습니다.");
            return ResponseEntity.ok("후기 카테고리 " + deleteReviewCategoryNum + "번이 삭제되었습니다.");
        }else{
            log.info("후기 카테고리 " + deleteReviewCategoryNum + "번이 삭제에 실패했습니다.");
            return ResponseEntity.ok("후기 카테고리 " + deleteReviewCategoryNum + "번이 삭제에 실패했습니다.");
        }
    }

    @GetMapping("/selectreportcategory")
    public ResponseEntity<List<ReportCategoryDTO>> selectAllReportCategories() {
        List<ReportCategoryDTO> reportCategoryList = cs.selectAllReportCategories();
        for (ReportCategoryDTO reportCategoryDTO : reportCategoryList) {
            log.info("reportCategoryDTO: {}", reportCategoryDTO);
        }
        return ResponseEntity.ok(reportCategoryList);
    }

    @PostMapping("/insertreportcategory")
    public ResponseEntity<String> insertReportCategory(String insertReportCategoryName) {
        List<ReportCategoryDTO> list = cs.selectAllReportCategories();
        for (ReportCategoryDTO reportCategoryDTO : list) {
            if(reportCategoryDTO.getReportCategoryName().equals(insertReportCategoryName)){
                log.info("새로운 신고 카테고리 요소 등록에 실패했습니다.");
                return ResponseEntity.ok("새로운 신고 카테고리 요소 등록에 실패했습니다.");
            }
        }

        int result = cs.insertReportCategory(insertReportCategoryName);
        if(result == 1){
            log.info("새로운 신고 카테고리 " + insertReportCategoryName + "요소가 등록되었습니다.");
            return ResponseEntity.ok("새로운 신고 카테고리 " +insertReportCategoryName + "요소가 등록되었습니다.");
        }else{
            log.info("새로운 신고 카테고리 요소 등록에 실패했습니다.");
            return ResponseEntity.ok("새로운 신고 카테고리 요소 등록에 실패했습니다.");
        }
    }

    @PostMapping("/updatereportcategory")
    public ResponseEntity<String> updateReportCategory(@RequestBody ReportCategoryDTO reportCategoryDTO) {
        List<ReportCategoryDTO> list = cs.selectAllReportCategories();
        for (ReportCategoryDTO reportCategory : list) {
            if(reportCategory.getReportCategoryName().equals(reportCategoryDTO.getReportCategoryName())){
                log.info("새로운 신고 카테고리 " + reportCategoryDTO.getReportCategoryName() + "요소가 제대로 수정되지 못했습니다.");
                return ResponseEntity.ok("새로운 신고 카테고리 " + reportCategoryDTO.getReportCategoryName() + "요소가 제대로 수정되지 못했습니다.");
            }
        }

        int result = cs.updateReportCategory(reportCategoryDTO);
        if(result == 1){
            log.info("새로운 신고 카테고리 " + reportCategoryDTO.getReportCategoryName() + "요소가 수정되었습니다.");
            return ResponseEntity.ok("새로운 신고 카테고리 " + reportCategoryDTO.getReportCategoryName() + "요소가 수정되었습니다.");
        }else{
            log.info("새로운 신고 카테고리 " + reportCategoryDTO.getReportCategoryName() + "요소가 제대로 수정되지 못했습니다.");
            return ResponseEntity.ok("새로운 신고 카테고리 " + reportCategoryDTO.getReportCategoryName() + "요소가 제대로 수정되지 못했습니다.");
        }
    }

    @PostMapping("/deletereportcategory")
    public ResponseEntity<String> deleteReportCategory(int deleteReportCategoryNum) {
        int result = cs.deleteReportCategory(deleteReportCategoryNum);
        if(result == 1){
            log.info("신고 카테고리 " + deleteReportCategoryNum + "번이 삭제되었습니다.");
            return ResponseEntity.ok("신고 카테고리 " + deleteReportCategoryNum + "번이 삭제되었습니다.");
        }else{
            log.info("신고 카테고리 " + deleteReportCategoryNum + "번이 삭제에 실패했습니다.");
            return ResponseEntity.ok("신고 카테고리 " + deleteReportCategoryNum + "번이 삭제에 실패했습니다.");
        }
    }

    @GetMapping("/selectmessagecategory")
    public ResponseEntity<List<MessageCategoryDTO>> selectAllMessageCategories() {
        List<MessageCategoryDTO> messageCategoryList = cs.selectAllMessageCategories();
        for (MessageCategoryDTO messageCategoryDTO : messageCategoryList) {
            log.info("messageCategoryDTO: {}", messageCategoryDTO);
        }
        return ResponseEntity.ok(messageCategoryList);
    }

    @PostMapping("/insertmessagecategory")
    public ResponseEntity<String> insertMessageCategory(String insertMessageCategoryName) {
        List<MessageCategoryDTO> list = cs.selectAllMessageCategories();
        for (MessageCategoryDTO messageCategoryDTO : list) {
            if(messageCategoryDTO.getMessageCategoryName().equals(insertMessageCategoryName)){
                log.info("새로운 쪽지 카테고리 요소 등록에 실패했습니다.");
                return ResponseEntity.ok("새로운 쪽지 카테고리 요소 등록에 실패했습니다.");
            }
        }

        int result = cs.insertMessageCategory(insertMessageCategoryName);
        if(result == 1){
            log.info("새로운 쪽지 카테고리 " + insertMessageCategoryName + "요소가 등록되었습니다.");
            return ResponseEntity.ok("새로운 쪽지 카테고리 " + insertMessageCategoryName + "요소가 등록되었습니다.");
        }else{
            log.info("새로운 쪽지 카테고리 요소 등록에 실패했습니다.");
            return ResponseEntity.ok("새로운 쪽지 카테고리 요소 등록에 실패했습니다.");
        }
    }

    @PostMapping("/updatemessagecategory")
    public ResponseEntity<String> updateMessageCategory(@RequestBody MessageCategoryDTO messageCategoryDTO) {
        List<MessageCategoryDTO> list = cs.selectAllMessageCategories();
        for(MessageCategoryDTO messageCategory : list){
            if(messageCategory.getMessageCategoryName().equals(messageCategoryDTO.getMessageCategoryName())){
                log.info("새로운 쪽지 카테고리 " + messageCategoryDTO.getMessageCategoryName() + "요소가 제대로 수정되지 못했습니다.");
                return ResponseEntity.ok("새로운 쪽지 카테고리 " + messageCategoryDTO.getMessageCategoryName() + "요소가 제대로 수정되지 못했습니다.");
            }
        }

        int result = cs.updateMessageCategory(messageCategoryDTO);
        if(result == 1){
            log.info("새로운 쪽지 카테고리 " + messageCategoryDTO.getMessageCategoryName() + "요소가 수정되었습니다.");
            return ResponseEntity.ok("새로운 쪽지 카테고리 " + messageCategoryDTO.getMessageCategoryName() + "요소가 수정되었습니다.");
        }else{
            log.info("새로운 쪽지 카테고리 " + messageCategoryDTO.getMessageCategoryName() + "요소가 제대로 수정되지 못했습니다.");
            return ResponseEntity.ok("새로운 쪽지 카테고리 " + messageCategoryDTO.getMessageCategoryName() + "요소가 제대로 수정되지 못했습니다.");
        }
    }

    @PostMapping("/deletemessagecategory")
    public ResponseEntity<String> deleteMessageCategory(int deleteMessageCategoryNum) {
        int result = cs.deleteMessageCategory(deleteMessageCategoryNum);
        if(result == 1){
            log.info("쪽지 카테고리 " + deleteMessageCategoryNum + "번이 삭제되었습니다.");
            return ResponseEntity.ok("쪽지 카테고리 " + deleteMessageCategoryNum + "번이 삭제되었습니다.");
        }else{
            log.info("쪽지 카테고리 " + deleteMessageCategoryNum + "번이 삭제에 실패했습니다.");
            return ResponseEntity.ok("쪽지 카테고리 " + deleteMessageCategoryNum + "번이 삭제에 실패했습니다.");
        }
    }

}
