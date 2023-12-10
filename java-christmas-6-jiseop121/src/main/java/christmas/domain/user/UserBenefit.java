package christmas.domain.user;

import christmas.domain.BenefitCalendar;
import christmas.domain.MenuList;
import christmas.service.BenefitCalculator;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserBenefit {
    private final Map<String, Integer> benefitDiscounts = new HashMap<>(
            Map.of(
                    BenefitCalendar.CHRISTMAS_DDAY.getCategory(), 0,
                    BenefitCalendar.WEEKDAY.getCategory(), 0,
                    BenefitCalendar.WEEKEND.getCategory(), 0,
                    BenefitCalendar.STAR.getCategory(), 0,
                    MenuList.GIFT_MENU.getMenuCategory(), 0
            )
    );

    public UserBenefit(UserDay userDay, UserOrderMenu userOrderMenu, UserGift userGift) {
        putDiscount(BenefitCalendar.CHRISTMAS_DDAY.getCategory(), BenefitCalculator.applyChristmasDiscount(userDay));
        putDiscount(BenefitCalendar.WEEKDAY.getCategory(),
                BenefitCalculator.applyWeekdayDiscount(userDay, userOrderMenu));
        putDiscount(BenefitCalendar.WEEKEND.getCategory(),
                BenefitCalculator.applyWeekendDiscount(userDay, userOrderMenu));
        putDiscount(BenefitCalendar.STAR.getCategory(), BenefitCalculator.applyStarDiscount(userDay));
        putDiscount(MenuList.GIFT_MENU.getMenuCategory(), BenefitCalculator.calculateAmountGiftMenu(userGift));
    }

    public void putDiscount(String benefitType, int discount) {
        benefitDiscounts.put(benefitType, discount);
    }

    public String getBenefitDiscount(String benefit) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(benefitDiscounts.get(benefit));
    }

    public String getMessageTotalBenefitAmount() {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(sumAllBenefitDiscount());
    }

    public List<String> getBenefitCategories() {
        return new ArrayList<>(benefitDiscounts.keySet());
    }

    public int getAllBenefitDiscount() {
        return sumAllBenefitDiscount() - benefitDiscounts.get(MenuList.GIFT_MENU.getMenuCategory());
    }

    public int totalBenefitAmount() {
        return sumAllBenefitDiscount();
    }

    private int sumAllBenefitDiscount() {
        int allBenefit = 0;
        for (String benefitType : benefitDiscounts.keySet()) {
            allBenefit += benefitDiscounts.get(benefitType);
        }

        return allBenefit;
    }
//
//    public int getMessageAllBenefit() {
//    }

}
