package christmas.domain;

import christmas.validator.system.SystemValidator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserOrderMenu {
    private final Map<String, Integer> orderMenu;

    public UserOrderMenu(List<String> menuList, List<Integer> countList) {
        SystemValidator.validateMenuName(menuList, countList);
        orderMenu = generateMap(menuList, countList);
    }

    private Map<String, Integer> generateMap(List<String> menuList, List<Integer> countList) {
        Map<String, Integer> inputOrderMenu = new HashMap<>();
        for (int i = 0; i < menuList.size(); i++) {
            inputOrderMenu.put(menuList.get(i), countList.get(i));
        }
        return inputOrderMenu;
    }

    public Map<String, Integer> getMenuCategoryCount() {
        return MenuList.getCategoryCount(orderMenu.keySet());
    }
}
