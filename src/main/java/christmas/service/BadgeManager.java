package christmas.service;

public enum BadgeManager {
    STAR("별"),
    TREE("트리"),
    SANTA("산타"),
    NONE("없음");

    private final String badge;

    BadgeManager(String badge) {
        this.badge = badge;
    }

    public static String getBadge(int totalBenefitAmount) {
        totalBenefitAmount = Math.abs(totalBenefitAmount);
        if (totalBenefitAmount >= 20000) {
            return SANTA.badge;
        } else if (totalBenefitAmount >= 10000) {
            return TREE.badge;
        } else if (totalBenefitAmount >= 5000) {
            return STAR.badge;
        }
        return NONE.badge;
    }
}
