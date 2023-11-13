package christmas.validator.system;

import christmas.view.message.exception.ErrorMessage;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SystemValidator {
    private static final int MAX_ORDER_COUNT = 1000;
    private static final int FIRST = 1;
    private static final int THIRTY_FIRST = 31;

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

    private static boolean isNotDecemberDay(int day) {
        if (THIRTY_FIRST >= day && day >= FIRST) {
            return false;
        }
        return true;
    }

    private static boolean isOverMaxOrder(List<Integer> countList) {
        for (int count : countList) {
            if (count > MAX_ORDER_COUNT) {
                return true;
            }
        }
        return false;
    }

    public static void validateMenuName(List<String> menuList, List<Integer> countList) {
        if (hasDuplicateMenuName(menuList) || isOverMaxOrder(countList)) {
            displayAndThrowException(ErrorMessage.INCORRECT_ORDER_FORMAT);
        }
    }

    private static boolean hasDuplicateMenuName(List<String> menuList) {
        Set<String> menuWithoutDuplicate = new HashSet<>(menuList);
        return menuWithoutDuplicate.size() < menuList.size();
    }

    public boolean isDuplicateMenuName(List<String> keySet, String menuName) {
        return keySet.contains(menuName);
    }

    private static void displayAndThrowException(ErrorMessage errorType) {
        System.out.println(errorType.getMessage());
        throw new IllegalArgumentException();
    }
}
