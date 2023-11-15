package christmas.view.message.exception;

import christmas.domain.MenuList;

public enum RecommendMessage {
    EVENT_NOT_AVAILABLE("총 구매 금액이 100,000원 이상부터 이벤트 적용이 가능합니다."),
    NEXT_BADGE_ALARM("다음 배지까지 남은 할인 금액 : "),
    LAST_BADGE_CONGRATULATIONS("축하드립니다! 가장 높은 배지를 얻으셨습니다!"),
    RECOMMEND_CATEGORY("의 메뉴를 주문하지 않으셨군요!"),
    RECOMMEND_MENU("의 이런 메뉴는 어떤가요? : "),
    CHRISTMAS_DDAY_MESSAGE("크리스마스 디데이 할인은 25일부로 종료됩니다!"),
    BEST_MAIN_COURSE("티본스테이크"),
    BEST_APPETIZER("양송이수프"),
    BEST_DESSERT("초코케이크"),
    BEST_BEVERAGE("제로콜라"),
    MONEY_COUNT("원"),
    ENTER("\n");

    private final String message;

    RecommendMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static String getBestMenu(String category) {
        if (MenuList.APPETIZER.getMenuCategory().equals(category)) {
            return BEST_APPETIZER.getMessage();
        }
        if (MenuList.DESSERT.getMenuCategory().equals(category)) {
            return BEST_DESSERT.getMessage();
        }
        if (MenuList.MAIN_COURSE.getMenuCategory().equals(category)) {
            return BEST_MAIN_COURSE.getMessage();
        }
        if (MenuList.BEVERAGE.getMenuCategory().equals(category)) {
            return BEST_BEVERAGE.getMessage();
        }
        return "";
    }
}
