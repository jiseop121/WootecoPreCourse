package christmas.service;

public enum BadgeManager {
    STAR("별"),
    TREE("트리"),
    SANTA("산타"),
    NONE("없음");

    private static final int SANTA_MINIMUM = 20000;
    private static final int TREE_MINIMUM = 10000;
    private static final int STAR_MINIMUM = 5000;
    private final String badge;

    BadgeManager(String badge) {
        this.badge = badge;
    }

    public static String getBadge(int totalBenefitAmount) {
        totalBenefitAmount = Math.abs(totalBenefitAmount);
        if (totalBenefitAmount >= SANTA_MINIMUM) {
            return SANTA.badge;
        } else if (totalBenefitAmount >= TREE_MINIMUM) {
            return TREE.badge;
        } else if (totalBenefitAmount >= STAR_MINIMUM) {
            return STAR.badge;
        }
        return NONE.badge;
    }

    public static int nextBadgeAmount(int totalBenefitAmount) {
        if (totalBenefitAmount >= SANTA_MINIMUM) {
            return 0;
        } else if (totalBenefitAmount > TREE_MINIMUM) {
            return SANTA_MINIMUM - totalBenefitAmount;
        } else if (totalBenefitAmount > STAR_MINIMUM) {
            return TREE_MINIMUM - totalBenefitAmount;
        }
        return STAR_MINIMUM - totalBenefitAmount;
    }
}
