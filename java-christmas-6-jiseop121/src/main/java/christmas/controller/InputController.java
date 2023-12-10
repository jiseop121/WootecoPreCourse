package christmas.controller;

import christmas.domain.user.UserDay;
import christmas.domain.user.UserOrderMenu;
import christmas.view.InputView;

public class InputController {
    private static final int MAX_INPUT_COUNT = 10;

    public static UserDay inputDayAgain(InputParser inputParser, InputView inputView) {
        int inputCount = 0;
        while (inputCount < MAX_INPUT_COUNT) {
            try {
                return new UserDay(inputParser.parseInputDay(inputView.inputDay()));
            } catch (IllegalArgumentException e) {
                inputCount++;
            }
        }
        throw new RuntimeException();
    }

    public static UserOrderMenu inputOrderMenuAgain(InputParser inputParser, InputView inputView) {
        int inputCount = 0;
        while (inputCount < MAX_INPUT_COUNT) {
            try {
                inputParser.parseInputOrderMenuToList(inputView.inputOrderMenu());
                return new UserOrderMenu(inputParser.getMenuList(), inputParser.getCountList());
            } catch (IllegalArgumentException e) {
                inputCount++;
            }
        }
        throw new RuntimeException();
    }
}
