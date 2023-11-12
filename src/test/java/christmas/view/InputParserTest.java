package christmas.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class InputParserTest {
    private final InputParser inputParser = new InputParser();

    @ParameterizedTest(name = "{0} => DAY 입력: {1}, 결과: {2}")
    @MethodSource("dayNumberTest")
    @DisplayName("Day 숫자 입력")
    void parseInputDayForTest(String caseMessage, String inputDay, int dayNumber) {
        assertThat(inputParser.parseInputDay(inputDay)).isEqualTo(dayNumber);
    }

    private static Stream<Arguments> dayNumberTest() {
        return Stream.of(
                Arguments.arguments("범위 밖 숫자", "123", 123),
                Arguments.arguments("정상 범위 숫자", "30", 30),
                Arguments.arguments("앞뒤 공백 존재", " 1 ", 1)
        );
    }

    @ParameterizedTest(name = "DAY 입력: {0},결과: IllegalArgumentException")
    @MethodSource("dayNumberErrorTest")
    @DisplayName("Day 숫자가 아닌 문자 입력")
    void parseInputDayForTest2(String inputDay) {
        assertThatCode(() -> {
            inputParser.parseInputDay(inputDay);
        }).isInstanceOf(IllegalArgumentException.class);

    }

    private static Stream<Arguments> dayNumberErrorTest() {
        return Stream.of(
                Arguments.arguments("12a"),
                Arguments.arguments("12.2"),
                Arguments.arguments("12 3")
        );
    }
}