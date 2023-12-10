package christmas.view;

import christmas.domain.user.UserBenefit;
import christmas.domain.user.UserDay;
import christmas.domain.user.UserOrderMenu;
import christmas.service.RecommendGenerator;
import christmas.view.message.view.OutputMessage;

public class OutputView {
    public void displayMessages(String message, OutputMessage outputMessage) {
        displayStartMessage(outputMessage);
        System.out.println(message);
    }

    public void displayMessageIntro(UserDay userDay) {
        System.out.println(String.format(OutputMessage.OUTPUT_INTRO.getMessage(), userDay.getDay()));
    }

    public void displayUserMenuList(String message, UserOrderMenu userOrderMenu) {
        displayStartMessage(OutputMessage.ORDERED_MENU);
        displayMessageAndEnter(message);
        displayMessageAndEnter(RecommendGenerator.recommendMenu(userOrderMenu));
    }

    public void displayAmountBeforeBenefitMessage(String message, int amountBeforeBenefit) {
        displayStartMessage(OutputMessage.AMOUNT_BEFORE_BENEFIT);
        displayMessageAndEnter(message);
        displayMessageAndEnter(RecommendGenerator.recommendLowAmountBeforeBenefit(amountBeforeBenefit));
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

    public void displayBadgeMessage(String message, UserBenefit userBenefit, UserDay userDay) {
        displayStartMessage(OutputMessage.BADGE);
        displayMessageAndEnter(message);
        displayMessageAndEnter(RecommendGenerator.recommendNextBadge(userBenefit));
        displayMessageAndEnter(RecommendGenerator.recommendChristmasDday(userDay));
    }

    private void displayStartMessage(OutputMessage outputMessage) {
        System.out.println(outputMessage.getMessage());
    }

    private void displayMessageAndEnter(String message) {
        System.out.println(message);
    }
}
