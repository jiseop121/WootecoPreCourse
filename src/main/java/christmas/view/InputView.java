package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.validator.input.InputValidator;
import christmas.view.message.view.ProcessMessage;

public class InputView {
    private final InputValidator inputValidator = new InputValidator();

    public String inputDay() {
        ProcessMessage.INPUT_VISIT_DAY.displayMessage();
        String inputDay = inputValue();
        inputValidator.validateInputDay(inputDay);
        return inputDay;
    }

    public String inputOrderMenu() {
        ProcessMessage.INPUT_MENU_AND_COUNT.displayMessage();
        String inputOrderMenu = inputValue();
        inputValidator.validateInputOrderMenu(inputOrderMenu);
        return inputOrderMenu;
    }

    public void displayIntro() {
        ProcessMessage.HELLO_WOOTECO_RESTAURANT.displayMessage();
    }

    private String inputValue() {
        return Console.readLine();
    }
}
