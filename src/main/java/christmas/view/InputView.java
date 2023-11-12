package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.view.message.view.ProcessMessage;

public class InputView {
    public String inputDay() {
        displayIntro();
        ProcessMessage.INPUT_VISIT_DAY.displayMessage();
        return inputValue();
    }

    public String inputOrderMenu() {
        ProcessMessage.INPUT_MENU_AND_COUNT.displayMessage();
        return inputValue();
    }

    private void displayIntro() {
        ProcessMessage.HELLO_WOOTECO_RESTAURANT.displayMessage();
    }

    private String inputValue() {
        return Console.readLine();
    }
}
