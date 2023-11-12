package christmas.validator.input;

public enum InputConstant {
    DIGIT_PATTERN("\\d+"),
    KOREAN_ENGLISH_PATTERN("[가-힣a-zA-Z]+"),
    COMMA(","),
    DASH("-");

    private final String constant;

    InputConstant(String constant) {
        this.constant = constant;
    }

    public String getConstant() {
        return constant;
    }
}
