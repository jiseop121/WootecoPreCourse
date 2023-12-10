package christmas.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import christmas.domain.BenefitCalendar;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class UserDayTest {
    @ParameterizedTest(name = "[{0}] DAY 입력: {1}")
    @MethodSource("dayCorrectTest")
    @DisplayName("정상적인 날짜 입력")
    void userDayTest(String caseMessage, int inputDay) {
        UserDay userDay = new UserDay(inputDay);
        assertThat(new UserDay(inputDay).getDay())
                .isEqualTo(inputDay);
    }

    private static Stream<Arguments> dayCorrectTest() {
        return Stream.of(
                Arguments.arguments("정상 범위 숫자", 30),
                Arguments.arguments("정상 범위 숫자", 31),
                Arguments.arguments("정상 범위 숫자", 1)
        );
    }

    @ParameterizedTest(name = "[{0}] DAY 입력: {1}")
    @MethodSource("dayCategoryTest")
    @DisplayName("날짜별 혜택 카테고리 확인")
    void userDayTest2(String caseMessage, int inputDay, List<String> benefitCategories) {
        UserDay userDay = new UserDay(inputDay);

        assertThat(isCorrectCategories(userDay, benefitCategories))
                .isEqualTo(true);
    }

    private boolean isCorrectCategories(UserDay userDay, List<String> benefitCategories) {
        for (int i = 0; i < userDay.getBenefitCategorySize(); i++) {
            if (!isAllContain(userDay, benefitCategories)) {
                return false;
            }
        }
        return true;
    }

    private boolean isAllContain(UserDay userDay, List<String> benefitCategories) {
        for (String benefit : benefitCategories) {
            if (!userDay.isContainCategory(benefit)) {
                return false;
            }
        }
        return true;
    }

    private static Stream<Arguments> dayCategoryTest() {
        return Stream.of(
                Arguments.arguments("별, 평일, 크리스마스 디데이", 25,
                        List.of(
                                BenefitCalendar.STAR.getCategory(),
                                BenefitCalendar.WEEKDAY.getCategory(),
                                BenefitCalendar.CHRISTMAS_DDAY.getCategory()
                        )),
                Arguments.arguments("평일", 28,
                        List.of(
                                BenefitCalendar.WEEKDAY.getCategory()
                        )),
                Arguments.arguments("주말, 크리스마스 디데이", 2,
                        List.of(
                                BenefitCalendar.WEEKEND.getCategory(),
                                BenefitCalendar.CHRISTMAS_DDAY.getCategory()
                        ))
        );
    }

    @ParameterizedTest(name = "[{0}] DAY 입력: {1}")
    @MethodSource("dayNumberErrorTest")
    @DisplayName("Day 숫자 입력")
    void userDayTest3(String caseMessage, int inputDay) {
        UserDay userDay;
        assertThatThrownBy(() -> new UserDay(inputDay))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> dayNumberErrorTest() {
        return Stream.of(
                Arguments.arguments("범위 밖", "0"),
                Arguments.arguments("범위 밖", "32")
        );
    }
}