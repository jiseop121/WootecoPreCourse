package christmas.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.service.MenuAmountCalculator;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class UserBenefitTest {
    @ParameterizedTest(name = "[{0}]")
    @MethodSource("userBenefitCheckTest")
    @DisplayName("정상적인 혜택 적용")
    void userBenefitTest1(String caseMessage, int inputDay, List<String> menuList, List<Integer> countList,
                          int result) {
        UserBenefit userBenefit = new UserBenefit(
                new UserDay(inputDay),
                new UserOrderMenu(menuList, countList),
                new UserGift(MenuAmountCalculator.calculateAmountBeforeBenefit(new UserOrderMenu(menuList, countList)),
                        new UserOrderMenu(menuList, countList).getMenuCategoryCount())
        );

        assertThat(userBenefit.getAllBenefitDiscount())
                .isEqualTo(result);
    }

    private static Stream<Arguments> userBenefitCheckTest() {
        return Stream.of(
                Arguments.arguments(" 확인", 28,
                        List.of(
                                "양송이수프"
                        ),
                        List.of(
                                20
                        ),
                        0
                ),
                Arguments.arguments("평일 디저트 할인 적용 확인", 26,
                        List.of(
                                "초코케이크"
                        ),
                        List.of(
                                7
                        ),
                        -2023 * 7
                ),
                Arguments.arguments("주말 메인 할인 적용 확인", 30,
                        List.of(
                                "티본스테이크"
                        ),
                        List.of(
                                2
                        ),
                        -2023 * 2),
                Arguments.arguments("별표 총 주문에서 할인 적용 확인", 31,
                        List.of(
                                "양송이수프"
                        ),
                        List.of(
                                18
                        ),
                        -1000),
                Arguments.arguments("크리스마스 디데이 확인", 18,
                        List.of(
                                "양송이수프"
                        ),
                        List.of(
                                17
                        ),
                        -(18 - 1) * 100 - 1000
                )
        );
    }
}