package christmas.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import christmas.domain.MenuList;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class UserOrderMenuTest {
    @ParameterizedTest(name = "[{0}] 메뉴: {1}, 개수 : {2}")
    @MethodSource("orderMenuCorrectTest")
    @DisplayName("정상적인 메뉴 입력")
    void userOrderMenuTest(String caseMessage, List<String> menuList, List<Integer> countList) {
        UserOrderMenu userOrderMenu = new UserOrderMenu(menuList, countList);
        assertThat(userOrderMenu.getOrderMenuNames())
                .isEqualTo(menuList);

        assertThat(getCountList(userOrderMenu))
                .isEqualTo(countList);
    }

    private List<Integer> getCountList(UserOrderMenu userOrderMenu) {
        List<Integer> countList = new ArrayList<>();
        for (String menuName : userOrderMenu.getOrderMenuNames()) {
            countList.add(userOrderMenu.getMenuCount(menuName));
        }

        return countList;
    }

    private static Stream<Arguments> orderMenuCorrectTest() {
        return Stream.of(
                Arguments.arguments("정상 메뉴", List.of(
                                new ArrayList<>(MenuList.APPETIZER.getMenuDetails().keySet()).get(0),
                                new ArrayList<>(MenuList.APPETIZER.getMenuDetails().keySet()).get(1)
                        ),
                        List.of(
                                1, 2
                        ))
        );
    }

    @ParameterizedTest(name = "[{0}] 메뉴: {1}, 개수 : {2}")
    @MethodSource("orderMenuErrorTest")
    @DisplayName("비정상적 메뉴 입력")
    void userOrderMenuTest2(String caseMessage, List<String> menuList, List<Integer> countList) {
        assertThatThrownBy(() -> new UserOrderMenu(menuList, countList))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> orderMenuErrorTest() {
        return Stream.of(
                Arguments.arguments("중복 메뉴", List.of(
                                new ArrayList<>(MenuList.APPETIZER.getMenuDetails().keySet()).get(1),
                                new ArrayList<>(MenuList.APPETIZER.getMenuDetails().keySet()).get(1)
                        ),
                        List.of(
                                1, 2
                        )),
                Arguments.arguments("없는 메뉴", List.of(
                                "수육 국밥",
                                new ArrayList<>(MenuList.APPETIZER.getMenuDetails().keySet()).get(1)
                        ),
                        List.of(
                                1, 2
                        )),
                Arguments.arguments("개수 1개 미만", List.of(
                                new ArrayList<>(MenuList.APPETIZER.getMenuDetails().keySet()).get(0),
                                new ArrayList<>(MenuList.APPETIZER.getMenuDetails().keySet()).get(1)
                        ),
                        List.of(
                                0, 1
                        )),
                Arguments.arguments("주문 최대 수량 초과", List.of(
                                new ArrayList<>(MenuList.APPETIZER.getMenuDetails().keySet()).get(0),
                                new ArrayList<>(MenuList.APPETIZER.getMenuDetails().keySet()).get(1)
                        ),
                        List.of(
                                0, 1000
                        ))
        );
    }

}