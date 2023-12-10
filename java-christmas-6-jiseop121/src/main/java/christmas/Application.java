package christmas;

import christmas.controller.ChristmasEventSystem;

public class Application {
    public static void main(String[] args) {
        ChristmasEventSystem christmasEventSystem = new ChristmasEventSystem();
        christmasEventSystem.controller();
    }
}
