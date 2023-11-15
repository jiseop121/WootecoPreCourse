package christmas.service;

import christmas.domain.user.UserBenefit;
import christmas.domain.user.UserDay;
import christmas.domain.user.UserOrderMenu;
import christmas.view.message.exception.RecommendMessage;
import java.util.Map;

public class RecommendGenerator {
    private static final int EVENT_NOT_AVAILABLE_AMOUNT = 100000;
    private static final int CHRISTMAS_DDAY = 25;
    private static final int RECOMMEND_DAY_DIFFERENT = 5;
    private static final int ZERO = 0;

    public static String recommendMenu(UserOrderMenu userOrderMenu) {
        StringBuilder message = new StringBuilder();
        Map<String, Integer> menuCategoryCount = userOrderMenu.getMenuCategoryCount();
        for (String category : menuCategoryCount.keySet()) {
            if (menuCategoryCount.get(category) == ZERO && !category.equals("증정 이벤트")) {
                message.append(oneCategoryMessage(category));
            }
        }
        return message.toString();
    }

    private static String oneCategoryMessage(String category) {
        return category
                + RecommendMessage.RECOMMEND_CATEGORY.getMessage()
                + RecommendMessage.ENTER.getMessage()
                + category
                + RecommendMessage.RECOMMEND_MENU.getMessage()
                + RecommendMessage.getBestMenu(category)
                + RecommendMessage.ENTER.getMessage();
    }

    public static String recommendLowAmountBeforeBenefit(int amountBeforeBenefit) {
        if (amountBeforeBenefit < EVENT_NOT_AVAILABLE_AMOUNT) {
            return RecommendMessage.EVENT_NOT_AVAILABLE.getMessage()
                    + RecommendMessage.ENTER.getMessage();
        }
        return "";
    }

    public static String recommendNextBadge(UserBenefit userBenefit) {
        if (BadgeManager.nextBadgeAmount(userBenefit.totalBenefitAmount()) == ZERO) {
            return RecommendMessage.LAST_BADGE_CONGRATULATIONS.getMessage()
                    + RecommendMessage.ENTER.getMessage();
        }
        return RecommendMessage.NEXT_BADGE_ALARM.getMessage()
                + BadgeManager.nextBadgeAmount(userBenefit.totalBenefitAmount())
                + RecommendMessage.MONEY_COUNT.getMessage()
                + RecommendMessage.ENTER.getMessage();
    }

    public static String recommendChristmasDday(UserDay userDay) {
        if ((CHRISTMAS_DDAY - userDay.getDay()) < RECOMMEND_DAY_DIFFERENT) {
            return RecommendMessage.CHRISTMAS_DDAY_MESSAGE.getMessage()
                    + RecommendMessage.ENTER.getMessage();
        }
        return "";
    }
}
