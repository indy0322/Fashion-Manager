package fashionmanager.kim.develop;

import fashionmanager.kim.develop.dto.MessageCategoryDTO;
import fashionmanager.kim.develop.dto.ReportCategoryDTO;
import fashionmanager.kim.develop.dto.ReviewCategoryDTO;
import fashionmanager.kim.develop.service.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @DisplayName("후기 카테고리 목록 조회 테스트")
    @Test
    void testSelectAllReviewCategories() {
        Assertions.assertDoesNotThrow(
                () -> {
                    List<ReviewCategoryDTO> category = categoryService.selectAllReviewCategories();
                    category.forEach(System.out::println);
                }
        );
    }

    @DisplayName("신고 카테고리 목록 조회 테스트")
    @Test
    void testSelectAllReportCategories() {
        Assertions.assertDoesNotThrow(
                () -> {
                    List<ReportCategoryDTO> category = categoryService.selectAllReportCategories();
                    category.forEach(System.out::println);
                }
        );
    }

    @DisplayName("쪽지 카테고리 목록 조회 테스트")
    @Test
    void testSelectAllMessageCategories() {
        Assertions.assertDoesNotThrow(
                () -> {
                    List<MessageCategoryDTO> category = categoryService.selectAllMessageCategories();
                    category.forEach(System.out::println);
                }
        );
    }

    @DisplayName("후기 카테고리 요소 추가 테스트")
    @Test
    void testInsertReviewCategory() {
        String name = "아우터";

        int result = categoryService.insertReviewCategory(name);
        Assertions.assertTrue(1 == result);
    }

    @DisplayName("신고 카테고리 요소 추가 테스트")
    @Test
    void testInsertReportCategory() {
        String name = "인플루언서";

        int result = categoryService.insertReportCategory(name);
        Assertions.assertTrue(1 == result);
    }

    @DisplayName("메세지 카테고리 요소 추가 테스트")
    @Test
    void testInsertMessageCategory() {
        String name = "조언";

        int result = categoryService.insertMessageCategory(name);
        Assertions.assertTrue(1 == result);
    }
}
