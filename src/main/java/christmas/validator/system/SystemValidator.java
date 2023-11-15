package christmas.validator.system;

import christmas.domain.MenuList;
import christmas.view.message.exception.ErrorMessage;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SystemValidator {
    private static final int MAX_ORDER_COUNT = 1000;
    private static final int MIN_ORDER_COUNT = 1;
    private static final int FIRST = 1;
    private static final int THIRTY_FIRST = 31;
    private static final int MINIMUM_AMOUNT_FOR_BADGE = 120000;
    private static final int ZERO = 0;

//    public void validateOrderMenu(Map<String, Integer> orderMenu) {
//        if (isOverMaxOrder(orderMenu.values())) {
//            displayAndThrowException(ErrorMessage.INCORRECT_ORDER_FORMAT);
//        }
//    }

    public static void validateDay(int day) {
        if (isNotDecemberDay(day)) {
            displayAndThrowException(ErrorMessage.INCORRECT_DAY_FORMAT);
        }
    }

    public static boolean isPossibleGift(int amountBeforeBenefit, Map<String, Integer> userOrderMenuCategoryCount) {
        return !isOnlyBeverage(userOrderMenuCategoryCount) && !isLessThanMinimumAmount(amountBeforeBenefit);
    }

    private static boolean isOnlyBeverage(Map<String, Integer> userOrderMenuCategoryCount) {
        return isZeroCount(userOrderMenuCategoryCount, MenuList.APPETIZER.getMenuCategory())
                && isZeroCount(userOrderMenuCategoryCount, MenuList.MAIN_COURSE.getMenuCategory())
                && isZeroCount(userOrderMenuCategoryCount, MenuList.DESSERT.getMenuCategory());
    }

    private static boolean isZeroCount(Map<String, Integer> userOrderMenuCategoryCount, String category) {
        return userOrderMenuCategoryCount.get(category).equals(ZERO);
    }

    private static boolean isLessThanMinimumAmount(int amountBeforeBenefit) {
        return amountBeforeBenefit < MINIMUM_AMOUNT_FOR_BADGE;
    }

    private static boolean isNotDecemberDay(int day) {
        if (THIRTY_FIRST >= day && day >= FIRST) {
            return false;
        }
        return true;
    }

    private static boolean isOutOfRangeOrderCount(List<Integer> countList) {
        for (int count : countList) {
            if (count > MAX_ORDER_COUNT || count < MIN_ORDER_COUNT) {
                return true;
            }
        }
        return false;
    }

    public static void validateMenuName(List<String> menuList, List<Integer> countList) {
        if (hasDuplicateMenuName(menuList) || isOutOfRangeOrderCount(countList) || noMenuInMenuList(menuList)) {
            displayAndThrowException(ErrorMessage.INCORRECT_ORDER_FORMAT);
        }
    }

    private static boolean noMenuInMenuList(List<String> menuList) {
        for (String menu : menuList) {
            if (!MenuList.getAllMenu().contains(menu)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasDuplicateMenuName(List<String> menuList) {
        Set<String> menuWithoutDuplicate = new HashSet<>(menuList);
        return menuWithoutDuplicate.size() < menuList.size();
    }

    private static void displayAndThrowException(ErrorMessage errorType) {
        System.out.println(errorType.getMessage());
        throw new IllegalArgumentException();
    }
}
