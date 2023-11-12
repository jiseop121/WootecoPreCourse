package christmas.view.message.exception;

public enum ErrorMessage {
    ERROR_HEADER("[ERROR]"),
    INCORRECT_DAY_FORMAT(ERROR_HEADER.getMessage() + " 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INCORRECT_ORDER_FORMAT(ERROR_HEADER.getMessage() + " 유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
