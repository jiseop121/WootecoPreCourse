package christmas.validator.input;

import christmas.view.message.exception.ErrorMessage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputValidator {
    private static final int MENU_INDEX = 0;
    private static final int COUNT_INDEX = 1;

    public void validateInputDay(String inputDay) {
        if (!isDayNumber(inputDay)) {
            displayAndThrowException(ErrorMessage.INCORRECT_DAY_FORMAT);
        }
    }

    public void validateInputOrderMenu(String inputOrderMenu) {
        for (String item : inputSplitByComma(inputOrderMenu)) {
            if (!isCorrectFormatMenuSplitByDash(item)) {
                displayAndThrowException(ErrorMessage.INCORRECT_ORDER_FORMAT);
            }
        }
    }

    private List<String> inputSplitByComma(String inputOrderMenu) {
        return Arrays.stream(inputOrderMenu.split(InputConstant.COMMA.getConstant()))
                .collect(Collectors.toList());
    }

    private boolean isCorrectFormatMenuSplitByDash(String oneOrderMenu) {
        List<String> menuAndCount = new ArrayList<>(List.of(oneOrderMenu.split(InputConstant.DASH.getConstant())));
        return (isMenuKoreanOrEnglish(menuAndCount.get(MENU_INDEX))
                && isCountNumber(menuAndCount.get(COUNT_INDEX)));
    }

    private boolean isMenuKoreanOrEnglish(String menu) {
        return menu.matches(InputConstant.KOREAN_ENGLISH_PATTERN.getConstant());
    }

    private boolean isCountNumber(String count) {
        return count.matches(InputConstant.DIGIT_PATTERN.getConstant());
    }

    private boolean isDayNumber(String inputDay) {
        return inputDay.matches(InputConstant.DIGIT_PATTERN.getConstant());
    }

    private void displayAndThrowException(ErrorMessage errorType) {
        System.out.println(errorType.getMessage());
        throw new IllegalArgumentException();
    }
}
