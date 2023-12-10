package christmas.service;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.MenuList;
import christmas.domain.user.UserOrderMenu;
import christmas.view.message.exception.RecommendMessage;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RecommendGeneratorTest {
    @ParameterizedTest(name = "[{0}] 메뉴: {1}, 개수 : {2}")
    @MethodSource("recommendGeneratorTest")
    @DisplayName("정상적인 메뉴 오류 없이 입력")
    void recommendGeneratorTest(String caseMessage, List<String> menuList, List<Integer> countList, String result) {
        assertThat(RecommendGenerator.recommendMenu(new UserOrderMenu(menuList, countList)))
                .contains(result);
    }

    private static Stream<Arguments> recommendGeneratorTest() {
        return Stream.of(
                Arguments.arguments("에피타이저만 주문", List.of(
                                MenuList.APPETIZER.getMenuDetailsNames().get(0),
                                MenuList.APPETIZER.getMenuDetailsNames().get(1)
                        ),
                        List.of(
                                1, 2
                        ), RecommendMessage.BEST_DESSERT.getMessage())
        );
    }
}