package christmas.service;

public enum BadgeManager {
    STAR("별"),
    TREE("트리"),
    SANTA("산타");

    private String badge;

    BadgeManager(String badge) {
        this.badge = badge;
    }

    public static String getBadge(int totalBenefitAmount) {
        if (totalBenefitAmount > 20000) {
            return SANTA.badge;
        } else if (totalBenefitAmount > 10000) {
            return TREE.badge;
        }
        return STAR.badge;
    }
}
