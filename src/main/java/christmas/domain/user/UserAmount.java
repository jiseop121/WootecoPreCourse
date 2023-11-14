package christmas.domain.user;

public class UserAmount {
    private final int amountBeforeBenefit;
    private int amountGiftMenu;
    private int totalBenefitAmount;
    private int amountAfterBenefit;

    public UserAmount(int amountBeforeBenefit) {
        this.amountBeforeBenefit = amountBeforeBenefit;
        this.amountAfterBenefit = amountBeforeBenefit;
        this.amountGiftMenu = 0;
        this.totalBenefitAmount = 0;
    }

    public void applyAmountGiftMenu(int amountGiftMenu) {
        this.amountGiftMenu = amountGiftMenu;
        amountAfterBenefit -= amountGiftMenu;
    }

    public void applyTotalBenefitAmount(int totalBenefitAmount) {
        this.totalBenefitAmount = totalBenefitAmount;
        amountAfterBenefit += totalBenefitAmount;
    }

    public int getTotalBenefitAmount() {
        return totalBenefitAmount;
    }

    public String getMessageTotalBenefitAmount() {
        return getMessageAmount(totalBenefitAmount);
    }

    public String getMessageAmountBeforeBenefit() {
        return getMessageAmount(amountBeforeBenefit);
    }

    public String getMessageAmountAfterBenefit() {
        return getMessageAmount(amountAfterBenefit);
    }

    public String getMessageAmountGiftMenu() {
        return getMessageAmount(amountGiftMenu);
    }

    private String getMessageAmount(int amount) {
        return String.valueOf(amount);
    }
}
