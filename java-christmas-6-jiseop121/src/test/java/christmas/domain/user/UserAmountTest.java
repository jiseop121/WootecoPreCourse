package christmas.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class UserAmountTest {
    @ParameterizedTest(name = "총구매: {0}, 증정: {1}, 혜택: {2}, 예상결제금액: {3}")
    @MethodSource("userAmountCheckTest")
    @DisplayName("사용자 혜택 적용 후 예상 결제 금액")
    void userAmountTest1(int amountBeforeBenefit, int totalBenefitAmount,
                         String result) {
        UserAmount userAmount = new UserAmount(amountBeforeBenefit);
        userAmount.applyTotalBenefitAmount(totalBenefitAmount);

        assertThat(userAmount.getMessageAmountAfterBenefit())
                .isEqualTo(result);
    }

    private static Stream<Arguments> userAmountCheckTest() {
        return Stream.of(
                Arguments.arguments(120000, -26000, String.valueOf(120000 - 26000))
        );
    }

    @ParameterizedTest(name = "총구매: {0}")
    @MethodSource("userAmountLowTest")
    @DisplayName("사용자 혜택 적용 후 예상 결제 금액")
    void userAmountTest2(int amountBeforeBenefit) {
        assertThatThrownBy(() -> new UserAmount(amountBeforeBenefit))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> userAmountLowTest() {
        return Stream.of(
                Arguments.arguments(99999)
        );
    }

}