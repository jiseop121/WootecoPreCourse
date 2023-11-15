package christmas.domain.user;

import christmas.domain.MenuList;
import christmas.validator.system.SystemValidator;
import java.util.Map;

public class UserGift {
    private String giftMenuName = "";
    private int giftMenuPrice = 0;
    private static final int GIFT_COUNT = 1;

    public UserGift(int amountBeforeBenefit, Map<String, Integer> userOrderMenuCategoryCount) {
        setUserGift(amountBeforeBenefit, userOrderMenuCategoryCount);
    }

    private void setUserGift(int amountBeforeBenefit, Map<String, Integer> userOrderMenuCategoryCount) {
        if (SystemValidator.isPossibleGift(amountBeforeBenefit, userOrderMenuCategoryCount)) {
            this.giftMenuName = giftMenu();
            this.giftMenuPrice = giftMenuPrice();
        }
    }

    public int getGiftMenuPrice() {
        return giftMenuPrice;
    }

    public String getGiftMenuNameMessage() {
        return giftMenuName;
    }

    public String getGiftCountMessage() {
        return String.valueOf(GIFT_COUNT);
    }

    private String giftMenu() {
        return MenuList.getGiftMenuName();
    }

    private int giftMenuPrice() {
        return MenuList.getGiftMenuPrice();
    }
}
