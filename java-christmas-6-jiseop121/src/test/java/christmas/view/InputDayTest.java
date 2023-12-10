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


public class InputDayTest {
    void setUp(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    @ParameterizedTest(name = "[{0}] DAY 입력: {1}")
    @MethodSource("dayNumberTest")
    @DisplayName("Day 숫자 입력")
    void parseInputDayForTest(String caseMessage, String inputDay) {
        InputView inputView = new InputView();
        setUp(inputDay);

        assertThat(inputView.inputDay())
                .isEqualTo(inputDay);

        Console.close();
    }

    private static Stream<Arguments> dayNumberTest() {
        return Stream.of(
                Arguments.arguments("범위 밖 숫자", "123"),
                Arguments.arguments("정상 범위 숫자", "30"),
                Arguments.arguments("앞뒤 공백 존재", " 1 ")
        );
    }

    @ParameterizedTest(name = "[{0}] DAY 입력: {1}")
    @MethodSource("dayNumberErrorTest")
    @DisplayName("Day 숫자 입력")
    void parseInputDayErrorForTest(String caseMessage, String inputDay) {
        InputView inputView = new InputView();
        setUp(inputDay);

        assertThatThrownBy(inputView::inputDay)
                .isInstanceOf(IllegalArgumentException.class);

        Console.close();
    }

    private static Stream<Arguments> dayNumberErrorTest() {
        return Stream.of(
                Arguments.arguments("소수", "12.2"),
                Arguments.arguments("문자 혼용", "12a"),
                Arguments.arguments("앞뒤 공백 존재", " a ")
        );
    }


}
