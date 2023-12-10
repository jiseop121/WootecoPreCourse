package christmas.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.MenuList;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class UserGiftTest {
    private static final int MINIMUM_GIFT_AMOUNT = 120000;
    private static final int MAXIMUM_NOT_GIFT_AMOUNT = 119999;

    @ParameterizedTest(name = "[{0}] 총 금액 : {1}")
    @MethodSource("notOnlyBeverageTest")
    @DisplayName("음료 외의 메뉴가 있을 때")
    void userGiftTest1(String caseMessage, int amountBeforeBenefit, int result) {
        Map<String, Integer> userOrderMenuCategoryCount = Map.of(
                MenuList.APPETIZER.getMenuCategory(), 1,
                MenuList.MAIN_COURSE.getMenuCategory(), 1,
                MenuList.DESSERT.getMenuCategory(), 1,
                MenuList.BEVERAGE.getMenuCategory(), 1
        );

        UserGift userGift = new UserGift(amountBeforeBenefit, userOrderMenuCategoryCount);
        assertThat(userGift.getGiftMenuPrice())
                .isEqualTo(result);
    }

    private static Stream<Arguments> notOnlyBeverageTest() {
        return Stream.of(
                Arguments.arguments("증정 O 금액", MINIMUM_GIFT_AMOUNT, MenuList.GIFT_MENU.getMenuDetails().get("샴페인")),
                Arguments.arguments("증정 X 금액", MAXIMUM_NOT_GIFT_AMOUNT, 0)
        );
    }

    @ParameterizedTest(name = "[{0}] 총 금액 : {1}")
    @MethodSource("onlyBeverageTest")
    @DisplayName("증정 조건 불만족")
    void userGiftTest2(String caseMessage, int amountBeforeBenefit) {
        Map<String, Integer> userOrderMenuCategoryCount = Map.of(
                MenuList.APPETIZER.getMenuCategory(), 0,
                MenuList.MAIN_COURSE.getMenuCategory(), 0,
                MenuList.DESSERT.getMenuCategory(), 0,
                MenuList.BEVERAGE.getMenuCategory(), 1
        );

        UserGift userGift = new UserGift(amountBeforeBenefit, userOrderMenuCategoryCount);
        assertThat(userGift.getGiftMenuPrice())
                .isEqualTo(0);
    }

    private static Stream<Arguments> onlyBeverageTest() {
        return Stream.of(
                Arguments.arguments("증정 X", MINIMUM_GIFT_AMOUNT)
        );
    }

}