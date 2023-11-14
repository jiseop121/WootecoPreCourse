package christmas.domain;

import java.util.ArrayList;
import java.util.List;

//https://techblog.woowahan.com/2527/
public enum BenefitCalendar {
    STAR("특별 할인", List.of(3, 10, 17, 24, 25, 31)),
    WEEKDAY("평일 할인", List.of(3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31)),
    WEEKEND("주말 할인", List.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30)),
    CHRISTMAS_DDAY("크리스마스 디데이 할인", List.of(
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25
    ));

    private final String category;
    private final List<Integer> dayList;

    BenefitCalendar(String category, List<Integer> dayList) {
        this.category = category;
        this.dayList = dayList;
    }

    public static List<String> getCategories(int day) {
        List<String> categories = new ArrayList<>();
        for (BenefitCalendar benefitCalendar : values()) {
            if (benefitCalendar.dayList.contains(day)) {
                categories.add(benefitCalendar.category);
            }
        }
        return categories;
    }

    public String getCategory() {
        return category;
    }

    public List<Integer> getDayList() {
        return dayList;
    }
}
