package christmas.domain.user;

import christmas.domain.MenuList;
import christmas.view.message.view.OutputMessage;

public class UserGift {
    private static final int MINIMUM_AMOUNT_FOR_BADGE = 120000;
    private final String giftMenuName;
    private final int giftMenuPrice;
    private static final int GIFT_COUNT = 1;

    public UserGift(int amountBeforeBenefit) {
        this.giftMenuName = giftMenu(amountBeforeBenefit);
        this.giftMenuPrice = giftMenuPrice(amountBeforeBenefit);
    }

    public int getGiftMenuPrice() {
        return giftMenuPrice;
    }

    public String getGiftMenuAndCount() {
        StringBuilder sb = new StringBuilder();
        sb.append(giftMenuName)
                .append(OutputMessage.SPACE_BAR.getMessage())
                .append(GIFT_COUNT)
                .append(OutputMessage.COUNT_UNIT.getMessage());

        return sb.toString();
    }

    private String giftMenu(int amountBeforeBenefit) {
        if (isAvailableGetGift(amountBeforeBenefit)) {
            return MenuList.getGiftMenuName();
        }
        return "";
    }

    private int giftMenuPrice(int amountBeforeBenefit) {
        if (isAvailableGetGift(amountBeforeBenefit)) {
            return MenuList.getGiftMenuPrice();
        }
        return 0;
    }

    private boolean isAvailableGetGift(int amountBeforeBenefit) {
        return amountBeforeBenefit > MINIMUM_AMOUNT_FOR_BADGE;
    }

}
