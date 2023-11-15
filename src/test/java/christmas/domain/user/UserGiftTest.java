package christmas.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.MenuList;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class UserGiftTest {
    private static final int MINIMUM_GIFT_AMOUNT = 120000;
    private static final int MAXIMUM_NOT_GIFT_AMOUNT = 119999;

    @ParameterizedTest(name = "[{0}] 총 금액 : {1}")
    @MethodSource("overAmountTest")
    @DisplayName("증정 조건 만족")
    void userGiftTest1(String caseMessage, int amountBeforeBenefit) {
        UserGift userGift = new UserGift(amountBeforeBenefit);
        assertThat(userGift.getGiftMenuPrice())
                .isEqualTo(MenuList.GIFT_MENU.getMenuDetails().get("샴페인"));
    }

    private static Stream<Arguments> overAmountTest() {
        return Stream.of(
                Arguments.arguments("증정 O", MINIMUM_GIFT_AMOUNT)
        );
    }

    @ParameterizedTest(name = "[{0}] 총 금액 : {1}")
    @MethodSource("lowAmountTest")
    @DisplayName("증정 조건 불만족")
    void userGiftTest2(String caseMessage, int amountBeforeBenefit) {
        UserGift userGift = new UserGift(amountBeforeBenefit);
        assertThat(userGift.getGiftMenuPrice())
                .isEqualTo(0);
    }

    private static Stream<Arguments> lowAmountTest() {
        return Stream.of(
                Arguments.arguments("증정 X", MAXIMUM_NOT_GIFT_AMOUNT)
        );
    }

}