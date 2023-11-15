package christmas.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.service.BadgeManager;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class UserBadgeTest {
    private static final int SANTA_AMOUNT = 20000;
    private static final int TREE_AMOUNT = 10000;
    private static final int STAR_AMOUNT = 5000;
    private static final int NONE_AMOUNT = 4999;

    @ParameterizedTest(name = "총혜택 금액: {0}, 결과: {1}")
    @MethodSource("userAmountCheckTest")
    @DisplayName("혜택 금액 별 뱃지")
    void userAmountTest1(int totalBenefitAmount, String result) {
        UserBadge userBadge = new UserBadge(BadgeManager.getBadge(totalBenefitAmount));
        assertThat(userBadge.getMessageBadge())
                .isEqualTo(result);
    }

    private static Stream<Arguments> userAmountCheckTest() {
        return Stream.of(
                Arguments.arguments(SANTA_AMOUNT, BadgeManager.getBadge(SANTA_AMOUNT)),
                Arguments.arguments(TREE_AMOUNT, BadgeManager.getBadge(TREE_AMOUNT)),
                Arguments.arguments(STAR_AMOUNT, BadgeManager.getBadge(STAR_AMOUNT)),
                Arguments.arguments(NONE_AMOUNT, BadgeManager.getBadge(NONE_AMOUNT))
        );
    }

}