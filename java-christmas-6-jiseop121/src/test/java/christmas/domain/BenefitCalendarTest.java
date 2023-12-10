package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BenefitCalendarTest {
    @DisplayName("STAR 혜택 날짜 확인")
    @Test
    void benefitCalendarCategoryTest1() {
        assertThat(BenefitCalendar.STAR.getCategory())
                .isEqualTo("특별 할인");

        assertThat(BenefitCalendar.STAR.getDayList())
                .isEqualTo(List.of(3, 10, 17, 24, 25, 31));
    }

    @DisplayName("WEEKDAY 혜택 날짜 확인")
    @Test
    void benefitCalendarCategoryTest2() {
        assertThat(BenefitCalendar.WEEKDAY.getCategory())
                .isEqualTo("평일 할인");

        assertThat(BenefitCalendar.WEEKDAY.getDayList())
                .isEqualTo(List.of(3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31));
    }

    @DisplayName("WEEKEND 혜택 날짜 확인")
    @Test
    void benefitCalendarCategoryTest3() {
        assertThat(BenefitCalendar.WEEKEND.getCategory())
                .isEqualTo("주말 할인");

        assertThat(BenefitCalendar.WEEKEND.getDayList())
                .isEqualTo(List.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30));
    }

    @DisplayName("CHRISTMAS_DDAY 혜택 날짜 확인")
    @Test
    void benefitCalendarCategoryTest4() {
        assertThat(BenefitCalendar.CHRISTMAS_DDAY.getCategory())
                .isEqualTo("크리스마스 디데이 할인");

        assertThat(BenefitCalendar.CHRISTMAS_DDAY.getDayList())
                .isEqualTo(List.of(
                        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25
                ));
    }
}