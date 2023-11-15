package christmas.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum MenuList {
    APPETIZER("애피타이저", Map.of(
            "양송이수프", 6000,
            "타파스", 5500,
            "시저샐러드", 8000)),
    MAIN_COURSE("메인", Map.of(
            "티본스테이크", 55000,
            "바비큐립", 54000,
            "해산물파스타", 35000,
            "크리스마스파스타", 25000
    )),
    DESSERT("디저트", Map.of(
            "초코케이크", 15000,
            "아이스크림", 5000
    )),
    BEVERAGE("음료", Map.of(
            "제로콜라", 3000,
            "레드와인", 60000,
            "샴페인", 25000
    )),
    GIFT_MENU("증정 메뉴", Map.of(
            "샴페인", BEVERAGE.menuDetails.get("샴페인")
    ));

    private static final String GIFT_MENU_NAME = "샴페인";

    private final String menuCategory;
    private final Map<String, Integer> menuDetails;

    MenuList(String menuCategory, Map<String, Integer> menuDetails) {
        this.menuCategory = menuCategory;
        this.menuDetails = menuDetails;
    }

    public Map<String, Integer> getMenuDetails() {
        return menuDetails;
    }

    public String getMenuCategory() {
        return menuCategory;
    }

    public static List<String> getAllMenu() {
        List<String> allMenu = new ArrayList<>();
        allMenu.addAll(oneCategoryAllMenu(APPETIZER));
        allMenu.addAll(oneCategoryAllMenu(MAIN_COURSE));
        allMenu.addAll(oneCategoryAllMenu(DESSERT));
        allMenu.addAll(oneCategoryAllMenu(BEVERAGE));

        return allMenu;
    }

    private static List<String> oneCategoryAllMenu(MenuList menuList) {
        return new ArrayList<>(menuList.menuDetails.keySet());
    }

    public static Map<String, Integer> getCategoryCount(Map<String, Integer> orderMenu) {
        Map<String, Integer> menuCategoryCount = initiateMenuCategoryCount();

        for (MenuList menuList : values()) {
            int categoryCount = oneCategoryCount(orderMenu, menuList);
            menuCategoryCount.put(menuList.menuCategory, categoryCount);
        }
        return menuCategoryCount;
    }

    private static Map<String, Integer> initiateMenuCategoryCount() {
        return new HashMap<>(Map.of(
                APPETIZER.menuCategory, 0,
                MAIN_COURSE.menuCategory, 0,
                DESSERT.menuCategory, 0,
                BEVERAGE.menuCategory, 0
        ));
    }

    private static int oneCategoryCount(Map<String, Integer> orderMenu, MenuList menuList) {
        int categoryCount = 0;
        for (String menu : orderMenu.keySet()) {
            if (menuList.menuDetails.containsKey(menu)) {
                categoryCount += orderMenu.get(menu);
            }
        }
        return categoryCount;
    }

    public static String getGiftMenuName() {
        for (String menu : BEVERAGE.menuDetails.keySet()) {
            if (menu.equals(GIFT_MENU)) {
                return menu;
            }
        }
        return "";
    }

    public static int getGiftMenuPrice() {
        return GIFT_MENU.menuDetails.get(GIFT_MENU_NAME);
    }

    public static int getMenuPrice(String userMenuName) {
        for (MenuList menuList : values()) {
            for (String menuName : menuList.menuDetails.keySet()) {
                if (menuName.equals(userMenuName)) {
                    return menuList.menuDetails.get(menuName);
                }
            }
        }
        return 0;
    }
}
