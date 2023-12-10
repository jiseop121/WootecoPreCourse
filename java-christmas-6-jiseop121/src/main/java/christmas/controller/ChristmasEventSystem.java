package christmas.controller;

import christmas.domain.user.UserAmount;
import christmas.domain.user.UserBadge;
import christmas.domain.user.UserBenefit;
import christmas.domain.user.UserDay;
import christmas.domain.user.UserGift;
import christmas.domain.user.UserOrderMenu;
import christmas.service.BadgeManager;
import christmas.service.MenuAmountCalculator;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasEventSystem {
    private final InputParser inputParser = new InputParser();
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final OutputMessageGenerator outputMessageGenerator = new OutputMessageGenerator();

    private UserDay userDay;
    private UserOrderMenu userOrderMenu;
    private UserGift userGift;
    private UserAmount userAmount;
    private UserBenefit userBenefit;

    public void controller() {
        inputUserDemand();
        christmasEvent();
    }

    private void inputUserDemand() {
        inputView.displayIntro();

        userDay = InputController.inputDayAgain(inputParser, inputView);
        userOrderMenu = InputController.inputOrderMenuAgain(inputParser, inputView);
    }

    private void christmasEvent() {
        OutputView outputView = new OutputView();
        int calculateAmountBeforeBenefit = 0;

        outputView.displayMessageIntro(userDay);

        menuListControl();
        calculateAmountBeforeBenefit = amountBeforeBenefitControl(calculateAmountBeforeBenefit);
        giftControl(calculateAmountBeforeBenefit);
        benefitControl();
        amountAfterBenefitControl();
        badgeControl();
    }

    private void menuListControl() {
        outputView.displayUserMenuList(outputMessageGenerator.getMenuListMessage(userOrderMenu), userOrderMenu);
    }

    private int amountBeforeBenefitControl(int calculateAmountBeforeBenefit) {
        calculateAmountBeforeBenefit = MenuAmountCalculator.calculateAmountBeforeBenefit(userOrderMenu);
        userAmount = new UserAmount(calculateAmountBeforeBenefit);

        outputView.displayAmountBeforeBenefitMessage(outputMessageGenerator.getAmountBeforeBenefitMessage(userAmount),
                calculateAmountBeforeBenefit);

        return calculateAmountBeforeBenefit;
    }

    private void giftControl(int calculateAmountBeforeBenefit) {
        userGift = new UserGift(calculateAmountBeforeBenefit, userOrderMenu.getMenuCategoryCount());
        outputView.displayGiftMenuMessage(outputMessageGenerator.getGiftMenuMessage(userGift));

    }

    private void benefitControl() {
        userBenefit = new UserBenefit(userDay, userOrderMenu, userGift);
        userAmount.applyTotalBenefitAmount(userBenefit.getAllBenefitDiscount());

        outputView.displayBenefitMessage(outputMessageGenerator.getBenefitMessage(userBenefit));
        outputView.displayTotalBenefitAmountMessage(outputMessageGenerator.getTotalBenefitAmountMessage(userBenefit));

    }

    private void amountAfterBenefitControl() {
        outputView.displayAmountAfterBenefitMessage(outputMessageGenerator.getAmountAfterBenefitMessage(userAmount));
    }

    private void badgeControl() {
        UserBadge userBadge = new UserBadge(BadgeManager.getBadge(userBenefit.totalBenefitAmount()));
        outputView.displayBadgeMessage(outputMessageGenerator.getBadgeMessage(userBadge), userBenefit, userDay);
    }
}
