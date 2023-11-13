package christmas.controller;

import christmas.domain.UserDay;
import christmas.domain.UserOrderMenu;
import christmas.view.InputView;

public class ChristmasEventSystem {
    private final InputView inputView;
    private static final int MAX_INPUT_COUNT = 10;

    private final InputParser inputParser = new InputParser();

    public ChristmasEventSystem(InputView inputView) {
        this.inputView = inputView;
    }

    public void controller() {
        inputUserDemand();
    }

    private void inputUserDemand() {
        inputView.displayIntro();

        UserDay userDay = inputDayAgain();
        UserOrderMenu userOrderMenu = inputOrderMenuAgain();
    }

    private void christmasEvent() {

    }

    private UserDay inputDayAgain() {
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

    private UserOrderMenu inputOrderMenuAgain() {
        int inputCount = 0;
        while (inputCount < MAX_INPUT_COUNT) {
            try {
                inputView.inputOrderMenu();
                return new UserOrderMenu(inputParser.getMenuList(), inputParser.getCountList());
            } catch (IllegalArgumentException e) {
                inputCount++;
            }
        }

        throw new RuntimeException();
    }
}
