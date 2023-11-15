package christmas.view.message.view;

public enum OutputMessage {
    OUTPUT_INTRO("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDERED_MENU("<주문 메뉴>"),
    AMOUNT_BEFORE_BENEFIT("<할인 전 총주문 금액>"),
    GIFT_MENU("<증정 메뉴>"),
    BENEFIT_HISTORY("<혜택 내역>"),
    TOTAL_BENEFIT_AMOUNT("<총혜택 금액>"),
    AMOUNT_AFTER_BENEFIT("<할인 후 예상 결제 금액>"),
    BADGE("<12월 이벤트 배지>"),
    SPACE_BAR(" "),
    COUNT_UNIT("개"),
    ENTER("\n"),

    COMMA(":"),
    COMMA_AND_SPACE(COMMA.message + SPACE_BAR.message),
    MONEY_UNIT("원"),
    CHRISTMAS_DDAY("크리스마스 디데이 할인" + COMMA_AND_SPACE.message),
    WEEKDAY("평일 할인" + COMMA_AND_SPACE.message),
    WEEKEND("주말 할인" + COMMA_AND_SPACE.message),
    STAR("특별 할인" + COMMA_AND_SPACE.message),
    GIFT("증정 이벤트" + COMMA_AND_SPACE.message);

    private String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
