package christmas.validator.input;

import christmas.view.message.exception.ErrorMessage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputValidator {
    private static final int COUNT_INDEX = 1;

    public void validateInputDay(String inputDay) {
        if (!isNumber(inputDay)) {
            displayAndThrowException(ErrorMessage.INCORRECT_DAY_FORMAT);
        }
    }

    public void validateInputOrderMenu(String inputOrderMenu) {
        if (isWrongFormatOrderMenu(inputOrderMenu)) {
            displayAndThrowException(ErrorMessage.INCORRECT_ORDER_FORMAT);
        }
    }

    private boolean isWrongFormatOrderMenu(String inputOrderMenu) {
        for (String item : inputSplitByComma(inputOrderMenu)) {
            if (!isCorrectFormatMenuSplitByDash(item)) {
                return true;
            }
        }
        return false;
    }

    private List<String> inputSplitByComma(String inputOrderMenu) {
        return Arrays.stream(inputOrderMenu.split(InputConstant.COMMA.getConstant()))
                .collect(Collectors.toList());
    }

    private boolean isCorrectFormatMenuSplitByDash(String oneOrderMenu) {
        List<String> menuAndCount = new ArrayList<>(List.of(oneOrderMenu.split(InputConstant.DASH.getConstant())));
        return !isNull(menuAndCount) && isNumber(menuAndCount.get(COUNT_INDEX));
    }

    private boolean isNull(List<String> menuAndCount) {
        if (menuAndCount.isEmpty()) {
            return true;
        }

        for (String item : menuAndCount) {
            if (item.trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }

//    private boolean isMenuKoreanOrEnglish(String menu) {
//        menu = menu.trim();
//        return menu.matches(InputConstant.KOREAN_ENGLISH_PATTERN.getConstant());
//    }

    private boolean isNumber(String input) {
        return input.trim().matches(InputConstant.DIGIT_PATTERN.getConstant());
    }

    private void displayAndThrowException(ErrorMessage errorType) {
        System.out.println(errorType.getMessage());
        throw new IllegalArgumentException();
    }
}
