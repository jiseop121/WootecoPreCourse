package christmas.domain;

import christmas.validator.system.SystemValidator;
import java.util.List;

public class UserDay {
    private final int Day;
    private final List<String> categories;

    public UserDay(int day) {
        //SystemValidator 적용
        SystemValidator.validateDay(day);
        Day = day;
        categories = DecemberCalendar.getCategories(day);
    }

    public String getCategory(int index) {
        return categories.get(index);
    }

    //날짜에 따른 day 특성 생성
    
}
