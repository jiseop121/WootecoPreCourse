package christmas.validator.input;

public enum InputConstant {
    DIGIT_PATTERN("\\d+"),
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
