package christmas.domain.user;

public record UserBadge(String badge) {
    public String getMessageBadge() {
        return badge;
    }
}