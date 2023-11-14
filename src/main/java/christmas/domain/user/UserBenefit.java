package christmas.domain.user;

import christmas.service.BenefitCalculator;

public class UserBenefit {
    private final int christmas;
    private final int weekday;
    private final int weekend;
    private final int star;
    private final int gift;

    public UserBenefit(UserDay userDay, UserOrderMenu userOrderMenu, UserGift userGift) {
        this.christmas = BenefitCalculator.applyChristmasDiscount(userDay);
        this.weekday = BenefitCalculator.applyWeekdayDiscount(userDay, userOrderMenu);
        this.weekend = BenefitCalculator.applyWeekendDiscount(userDay, userOrderMenu);
        this.star = BenefitCalculator.applyStarDiscount(userDay);
        this.gift = BenefitCalculator.calculateAmountGiftMenu(userGift);
    }

    public int totalBenefitAmount() {
        return christmas + weekend + weekday + star + gift;
    }
//
//    public int getMessageAllBenefit() {
//    }

    public int getChristmas() {
        return christmas;
    }

    public int getGift() {
        return gift;
    }

    public int getStar() {
        return star;
    }

    public int getWeekday() {
        return weekday;
    }

    public int getWeekend() {
        return weekend;
    }
}
