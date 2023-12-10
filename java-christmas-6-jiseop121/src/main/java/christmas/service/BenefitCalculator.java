package christmas.service;

import christmas.domain.BenefitCalendar;
import christmas.domain.MenuList;
import christmas.domain.user.UserDay;
import christmas.domain.user.UserGift;
import christmas.domain.user.UserOrderMenu;

public class BenefitCalculator {
    private static final int FIRST_DAY = 1;
    private static final int STAR_DISCOUNT_MONEY = -1000;
    private static final int WEEKDAY_DISCOUNT_MONEY = -2023;
    private static final int WEEKEND_DISCOUNT_MONEY = -2023;
    private static final int NO_BENEFIT = 0;

    public static int applyStarDiscount(UserDay userDay) {
        if (isCorrectBenefitType(userDay, BenefitCalendar.STAR)) {
            return STAR_DISCOUNT_MONEY;
        }
        return NO_BENEFIT;
    }

    public static int calculateAmountGiftMenu(UserGift userGift) {
        return -userGift.getGiftMenuPrice();
    }

    public static int applyWeekdayDiscount(UserDay userDay, UserOrderMenu userOrderMenu) {
        if (isCorrectBenefitType(userDay, BenefitCalendar.WEEKDAY)) {
            return WEEKDAY_DISCOUNT_MONEY * discountCount(userOrderMenu, MenuList.DESSERT.getMenuCategory());
        }
        return NO_BENEFIT;
    }

    public static int applyWeekendDiscount(UserDay userDay, UserOrderMenu userOrderMenu) {
        if (isCorrectBenefitType(userDay, BenefitCalendar.WEEKEND)) {
            return WEEKEND_DISCOUNT_MONEY * discountCount(userOrderMenu, MenuList.MAIN_COURSE.getMenuCategory());
        }
        return NO_BENEFIT;
    }

    public static int applyChristmasDiscount(UserDay userDay) {
        if (isCorrectBenefitType(userDay, BenefitCalendar.CHRISTMAS_DDAY)) {
            return -(dayPerThousand(userDay) + 1000);
        }
        return NO_BENEFIT;
    }

    private static int discountCount(UserOrderMenu userOrderMenu, String menuCategory) {
        try {
            return userOrderMenu.getMenuCategoryCount().get(menuCategory);
        } catch (NullPointerException e) {
            return 0;
        }

    }

    private static int dayPerThousand(UserDay userDay) {
        return (userDay.getDay() - FIRST_DAY) * 100;
    }

    private static boolean isCorrectBenefitType(UserDay userDay, BenefitCalendar benefitCalendar) {
        return userDay.isContainCategory(benefitCalendar.getCategory());
    }
}
