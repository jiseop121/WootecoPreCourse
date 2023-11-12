package christmas.view;

import christmas.validator.input.InputConstant;
import christmas.validator.input.InputValidator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InputParser {
    private static final String DASH = "-";
    private static final String COMMA = ",";
    private static final int MENU_INDEX = 0;
    private static final int COUNT_INDEX = 1;

    private final InputValidator inputValidator = new InputValidator();

    public int parseInputDay(String inputDay) {
        inputDay = inputDay.strip();
        inputValidator.validateInputDay(inputDay);
        return Integer.parseInt(inputDay);
    }

    public Map<String, Integer> parseInputOrderMenuToMap(String inputOrderMenu) {
        inputValidator.validateInputOrderMenu(inputOrderMenu);

        Map<String, Integer> orderMenu = new HashMap<>();
        for (String item : inputSplitByComma(inputOrderMenu)) {
            List<String> oneMenu = oneMenuSplitByDash(item);
            orderMenu.put(oneMenu.get(MENU_INDEX), Integer.parseInt(oneMenu.get(COUNT_INDEX)));
        }
        return orderMenu;
    }

    private List<String> inputSplitByComma(String inputOrderMenu) {
        return Arrays.stream(inputOrderMenu.split(InputConstant.COMMA.getConstant()))
                .collect(Collectors.toList());
    }

    private List<String> oneMenuSplitByDash(String oneOrderMenu) {
        return new ArrayList<>(List.of(oneOrderMenu.split(InputConstant.DASH.getConstant())));
    }
}
