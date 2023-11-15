package christmas.service;

import christmas.domain.MenuList;
import christmas.domain.user.UserBenefit;
import christmas.domain.user.UserOrderMenu;

public class AmountCalculator {
    public static int calculateTotalBenefitAmount(UserBenefit userBenefit) {
        return 0;
    }

    public static int calculateAmountBeforeBenefit(UserOrderMenu userOrderMenu) {
        int totalPrice = 0;
        for (String menuName : userOrderMenu.getOrderMenuNames()) {
            totalPrice += getOneMenuCountPrice(menuName, userOrderMenu);
        }
        return totalPrice;
    }

    private static int getOneMenuCountPrice(String menuName, UserOrderMenu userOrderMenu) {
        return MenuList.getMenuPrice(menuName) * userOrderMenu.getMenuCount(menuName);
    }
}
