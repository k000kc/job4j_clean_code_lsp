package ru.job4j.ood.isp.menu;

public class Printer implements MenuPrinter{
    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo item : menu) {
            String number = item.getNumber();
            String name = item.getName();
            int levelMenu = (number.length() / 2) - 1;
            String tab = "----".repeat(levelMenu);
            System.out.printf("%s%s %s%n", tab, number, name);
        }
    }
}
