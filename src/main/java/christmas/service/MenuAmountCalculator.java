package christmas.service;

import christmas.domain.MenuList;
import christmas.domain.user.UserOrderMenu;
import java.util.List;

public class MenuAmountCalculator {
    public static int calculateAmountBeforeBenefit(UserOrderMenu userOrderMenu) {
        int totalPrice = 0;
        for (String menuName : userOrderMenu.getOrderMenuNames()) {
            totalPrice += getOneMenuCountPrice(menuName, userOrderMenu);
        }
        return totalPrice;
    }

    private static int getOneMenuCountPrice(String menuName, UserOrderMenu userOrderMenu) {
        return getMenuPrice(menuName) * userOrderMenu.getMenuCount(menuName);
    }

    private static int getMenuPrice(String userMenuName) {
        for (MenuList menuList : MenuList.values()) {
            if (hasUserMenuName(menuList.getMenuDetailsNames(), userMenuName)) {
                return menuList.getMenuPrice(userMenuName);
            }
        }
        return 0;
    }

    private static boolean hasUserMenuName(List<String> menuDetailsNames, String userMenuName) {
        return menuDetailsNames.contains(userMenuName);
    }
}
