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
    private static final int MAX_INPUT_COUNT = 10;

    private final InputParser inputParser = new InputParser();
    private final InputView inputView = new InputView();

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
        InputView inputView = new InputView();
        inputView.displayIntro();

        userDay = inputDayAgain();
        userOrderMenu = inputOrderMenuAgain();
    }

    private void christmasEvent() {
        OutputView outputView = new OutputView();
        //인트로
        outputView.displayMessageIntro(userDay);
        OutputMessageGenerator outputMessageGenerator = new OutputMessageGenerator();
        //주문 내역 출력
        outputView.displayUserMenuList(outputMessageGenerator.getMenuListMessage(userOrderMenu), userOrderMenu);
        //+추천

        //총 주문 금액
        int calculateAmountBeforeBenefit = MenuAmountCalculator.calculateAmountBeforeBenefit(userOrderMenu);
        userAmount = new UserAmount(calculateAmountBeforeBenefit);
        //+총 주문 금액 출력
        outputView.displayAmountBeforeBenefitMessage(outputMessageGenerator.getAmountBeforeBenefitMessage(userAmount),
                calculateAmountBeforeBenefit);

        //증정 메뉴
        userGift = new UserGift(calculateAmountBeforeBenefit, userOrderMenu.getMenuCategoryCount());
        //+증정 메뉴 출력
        outputView.displayGiftMenuMessage(outputMessageGenerator.getGiftMenuMessage(userGift));

        //혜택 내역
        userBenefit = new UserBenefit(userDay, userOrderMenu, userGift);
        userAmount.applyTotalBenefitAmount(userBenefit.getAllBenefitDiscount());
        //+혜택 내역 출력
        outputView.displayBenefitMessage(outputMessageGenerator.getBenefitMessage(userBenefit));

        //총혜택 금액
        //+총혜택 내역 출력
        outputView.displayTotalBenefitAmountMessage(outputMessageGenerator.getTotalBenefitAmountMessage(userBenefit));

        //예상 결제 금액
        //+예상 결제 금액 출력
        outputView.displayAmountAfterBenefitMessage(outputMessageGenerator.getAmountAfterBenefitMessage(userAmount));

        //이벤트 배지
        UserBadge userBadge = new UserBadge(BadgeManager.getBadge(userBenefit.totalBenefitAmount()));
        //+이벤트 배지 출력
        outputView.displayBadgeMessage(outputMessageGenerator.getBadgeMessage(userBadge), userBenefit, userDay);
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
                inputParser.parseInputOrderMenuToList(inputView.inputOrderMenu());
                return new UserOrderMenu(inputParser.getMenuList(), inputParser.getCountList());
            } catch (IllegalArgumentException e) {
                inputCount++;
            }
        }
        throw new RuntimeException();
    }
}
