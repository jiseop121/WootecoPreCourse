package christmas.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import camp.nextstep.edu.missionutils.Console;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class InputOrderMenuTest {
    void setUp(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    @ParameterizedTest(name = "[{0}] DAY 입력: {1}")
    @MethodSource("orderMenuTest")
    @DisplayName("메뉴 올바른 형식 입력")
    void inputOrderMenuForTest(String caseMessage, String inputOrderMenu) {
        InputView inputView = new InputView();
        setUp(inputOrderMenu);

        assertThat(inputView.inputOrderMenu())
                .isEqualTo(inputOrderMenu);

        Console.close();
    }

    private static Stream<Arguments> orderMenuTest() {
        return Stream.of(
                Arguments.arguments("정상적", "불고기-1,피자-1"),
                Arguments.arguments("중복도 통과", "불고기-1,불고기-1"),
                Arguments.arguments("없는 메뉴도 통과", "없는메뉴-1,없는메뉴2-1"),
                Arguments.arguments("공백 통과", "불고기 - 1, 피자 - 1")
        );
    }

    @ParameterizedTest(name = "[{0}] DAY 입력: {1}")
    @MethodSource("orderMenuErrorTest")
    @DisplayName("메뉴 비정상적인 형식 입력")
    void inputOrderMenuErrorForTest(String caseMessage, String inputDay) {
        InputView inputView = new InputView();
        setUp(inputDay);

        assertThatThrownBy(inputView::inputOrderMenu)
                .isInstanceOf(IllegalArgumentException.class);

        Console.close();
    }

    private static Stream<Arguments> orderMenuErrorTest() {
        return Stream.of(
                Arguments.arguments("개수에 소수 입력", "불고기 - 12.2"),
                Arguments.arguments("개수에 문자 입력", "피자 - 12a"),
                Arguments.arguments("메뉴 칸이 공백", " - 12,피자 - 1"),
                Arguments.arguments("개수 칸이 공백", "피자 - ,불고기 -2"),
                Arguments.arguments("연속 콤마", "피자 - 1,,불고기 -2")

        );
    }
}
