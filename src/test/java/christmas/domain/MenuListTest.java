package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuListTest {
    @DisplayName("APPETIZER 메뉴 리스트의 값이 오타 없이 채워져있는지 확인")
    @Test
    void categoryAndMenuNamesTest1() {
        assertThat(MenuList.APPETIZER.getMenuCategory())
                .isEqualTo("애피타이저");

        assertThat(MenuList.APPETIZER.getMenuDetails())
                .isEqualTo(Map.of(
                        "양송이수프", 6000,
                        "타파스", 5500,
                        "시저샐러드", 8000));
    }

    @DisplayName("MAIN_COURSE 메뉴 리스트의 값이 오타 없이 채워져있는지 확인")
    @Test
    void categoryAndMenuNamesTest2() {
        assertThat(MenuList.MAIN_COURSE.getMenuCategory())
                .isEqualTo("메인");

        assertThat(MenuList.MAIN_COURSE.getMenuDetails())
                .isEqualTo(Map.of(
                        "티본스테이크", 55000,
                        "바비큐립", 54000,
                        "해산물파스타", 35000,
                        "크리스마스파스타", 25000
                ));
    }

    @DisplayName("DESSERT 메뉴 리스트의 값이 오타 없이 채워져있는지 확인")
    @Test
    void categoryAndMenuNamesTest3() {
        assertThat(MenuList.DESSERT.getMenuCategory())
                .isEqualTo("디저트");

        assertThat(MenuList.DESSERT.getMenuDetails())
                .isEqualTo(Map.of(
                        "초코케이크", 15000,
                        "아이스크림", 5000
                ));
    }

    @DisplayName("BEVERAGE 메뉴 리스트의 값이 오타 없이 채워져있는지 확인")
    @Test
    void categoryAndMenuNamesTest4() {
        assertThat(MenuList.BEVERAGE.getMenuCategory())
                .isEqualTo("음료");
        assertThat(MenuList.BEVERAGE.getMenuDetails())
                .isEqualTo(Map.of(
                        "제로콜라", 3000,
                        "레드와인", 60000,
                        "샴페인", 25000
                ));
    }

}