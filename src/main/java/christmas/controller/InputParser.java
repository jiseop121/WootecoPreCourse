package christmas.controller;

import christmas.validator.input.InputConstant;
import christmas.validator.input.InputValidator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputParser {
    private static final String DASH = "-";
    private static final String COMMA = ",";
    private static final int MENU_INDEX = 0;
    private static final int COUNT_INDEX = 1;

    private final InputValidator inputValidator = new InputValidator();
    private List<String> menuList = new ArrayList<>();
    private List<Integer> countList = new ArrayList<>();
    private int day;

    public int parseInputDay(String inputDay) {
        day = Integer.parseInt(inputDay.trim());
        return day;
    }

    public void parseInputOrderMenuToList(String inputOrderMenu) {
        clearList();
        for (String inputOneMenu : inputSplitByComma(inputOrderMenu)) {
            addMenuAndCountList(oneMenuSplitByDash(inputOneMenu));
        }
    }

    private void clearList() {
        menuList.clear();
        countList.clear();
    }

    public List<String> getMenuList() {
        return menuList;
    }

    public List<Integer> getCountList() {
        return countList;
    }

    private void addMenuAndCountList(List<String> oneMenu) {
        menuList.add(getMenuName(oneMenu));
        countList.add(getMenuCount(oneMenu));
    }

    private String getMenuName(List<String> oneMenu) {
        return oneMenu.get(MENU_INDEX).trim();
    }

    private int getMenuCount(List<String> oneMenu) {
        return Integer.parseInt(oneMenu.get(COUNT_INDEX).trim());
    }

    private List<String> inputSplitByComma(String inputOrderMenu) {
        return Arrays.stream(inputOrderMenu.split(InputConstant.COMMA.getConstant()))
                .collect(Collectors.toList());
    }

    private List<String> oneMenuSplitByDash(String oneOrderMenu) {
        return new ArrayList<>(List.of(oneOrderMenu.split(InputConstant.DASH.getConstant())));
    }
}
