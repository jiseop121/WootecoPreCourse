package christmas.domain.user;

import christmas.domain.BenefitCalendar;
import christmas.validator.system.SystemValidator;
import java.util.List;

public class UserDay {
    private final int Day;
    private final List<String> benefitCategories;

    public UserDay(int day) {
        //SystemValidator 적용
        SystemValidator.validateDay(day);
        Day = day;
        benefitCategories = BenefitCalendar.getCategories(day);
    }

    public int getDay() {
        return Day;
    }

    public int getBenefitCategorySize() {
        return benefitCategories.size();
    }

    public String getBenefitCategory(int index) {
        return benefitCategories.get(index);
    }

    public boolean isContainCategory(String category) {
        return benefitCategories.contains(category);
    }
}
