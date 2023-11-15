package christmas.view;

import christmas.view.message.view.OutputMessage;

public class OutputView {
    public void displayMessages(String message, OutputMessage outputMessage) {
        displayStartMessage(outputMessage);
        System.out.println(message);
    }

    public void displayUserMenuList(String message) {
        displayStartMessage(OutputMessage.ORDERED_MENU);
        displayMessageAndEnter(message);
    }

    public void displayAmountBeforeBenefitMessage(String message) {
        displayStartMessage(OutputMessage.AMOUNT_BEFORE_BENEFIT);
        displayMessageAndEnter(message);
    }

    public void displayBenefitMessage(String message) {
        displayStartMessage(OutputMessage.BENEFIT_HISTORY);
        displayMessageAndEnter(message);
    }

    public void displayGiftMenuMessage(String message) {
        displayStartMessage(OutputMessage.GIFT_MENU);
        displayMessageAndEnter(message);
    }

    public void displayTotalBenefitAmountMessage(String message) {
        displayStartMessage(OutputMessage.TOTAL_BENEFIT_AMOUNT);
        displayMessageAndEnter(message);
    }

    public void displayAmountAfterBenefitMessage(String message) {
        displayStartMessage(OutputMessage.AMOUNT_AFTER_BENEFIT);
        displayMessageAndEnter(message);
    }

    public void displayBadgeMessage(String message) {
        displayStartMessage(OutputMessage.BADGE);
        displayMessageAndEnter(message);
    }

    private void displayStartMessage(OutputMessage outputMessage) {
        System.out.println(outputMessage.getMessage());
    }

    private void displayMessageAndEnter(String message) {
        System.out.println(message);
    }
}
