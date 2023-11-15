package christmas.controller;

import christmas.domain.user.UserAmount;
import christmas.domain.user.UserBadge;
import christmas.domain.user.UserBenefit;
import christmas.domain.user.UserGift;
import christmas.domain.user.UserOrderMenu;
import christmas.view.message.view.OutputMessage;

public class OutputMessageGenerator {
    private static final String NO_DATA = "없음";
    private static final int ZERO_COUNT = 0;
    private static final String ZERO_COUNT_MESSAGE = "0";

    public String getMenuListMessage(UserOrderMenu userOrderMenu) {
        if (userOrderMenu.getOrderMenuNames().isEmpty()) {
            return getNoDataMessage();
        }
        StringBuilder message = new StringBuilder();
        for (String menuName : userOrderMenu.getOrderMenuNames()) {
            if (userOrderMenu.getMenuCount(menuName) != ZERO_COUNT) {
                message.append(menuName)
                        .append(OutputMessage.SPACE_BAR.getMessage())
                        .append(userOrderMenu.getMenuCount(menuName))
                        .append(OutputMessage.COUNT_UNIT.getMessage())
                        .append(OutputMessage.ENTER.getMessage());
            }
        }
        return message.toString();
    }

    public String getAmountBeforeBenefitMessage(UserAmount userAmount) {
        return userAmount.getMessageAmountBeforeBenefit()
                + OutputMessage.MONEY_UNIT.getMessage()
                + OutputMessage.ENTER.getMessage();
    }

    public String getBenefitMessage(UserBenefit userBenefit) {
        if (userBenefit.getAllBenefitDiscount() == ZERO_COUNT) {
            return getNoDataMessage();
        }
        StringBuilder message = new StringBuilder();
        for (String benefit : userBenefit.getBenefitCategories()) {
            if (!userBenefit.getBenefitDiscount(benefit).equals(ZERO_COUNT_MESSAGE)) {
                message.append(benefit)
                        .append(OutputMessage.COMMA_AND_SPACE.getMessage())
                        .append(userBenefit.getBenefitDiscount(benefit))
                        .append(OutputMessage.MONEY_UNIT.getMessage())
                        .append(OutputMessage.ENTER.getMessage());
            }
        }
        return message.toString();
    }

    public String getGiftMenuMessage(UserGift userGift) {
        if (userGift.getGiftMenuPrice() == ZERO_COUNT) {
            return getNoDataMessage();
        }
        return userGift.getGiftMenuNameMessage()
                + userGift.getGiftMenuNameMessage()
                + OutputMessage.SPACE_BAR.getMessage()
                + userGift.getGiftCountMessage()
                + OutputMessage.COUNT_UNIT.getMessage()
                + OutputMessage.ENTER.getMessage();
    }

    public String getTotalBenefitAmountMessage(UserAmount userAmount) {
        if (userAmount.getMessageTotalBenefitAmount().equals(ZERO_COUNT_MESSAGE)) {
            return getNoDataMessage();
        }
        return userAmount.getMessageTotalBenefitAmount()
                + OutputMessage.MONEY_UNIT.getMessage()
                + OutputMessage.ENTER.getMessage();
    }

    public String getAmountAfterBenefitMessage(UserAmount userAmount) {
        return userAmount.getMessageAmountAfterBenefit()
                + OutputMessage.MONEY_UNIT.getMessage()
                + OutputMessage.ENTER.getMessage();
    }

    public String getBadgeMessage(UserBadge userBadge) {
        if (userBadge.getMessageBadge().isEmpty()) {
            return NO_DATA;
        }
        return userBadge.getMessageBadge();
    }

    private String getNoDataMessage() {
        return NO_DATA + OutputMessage.ENTER.getMessage();
    }
}
