package ru.job4j.ood.isp.menu;

/**
 * 6. Создайте простенький класс TodoApp. Этот класс будет представлять собой консольное приложение, которое позволяет:
 * Добавить элемент в корень меню;
 * Добавить элемент к родительскому элементу;
 * Вызвать действие, привязанное к пункту меню (действие можно сделать константой,
 * например, ActionDelete DEFAULT_ACTION = () -> System.out.println("Some action") и указывать при добавлении элемента в меню);
 * Вывести меню в консоль.
 */
public class TodoApp {
    public static void main(String[] args) {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Задача Общая", () -> System.out.println("Выбрана Задача Общая"));
        menu.add("Задача Общая", "Задача Первая", () -> System.out.println("Выбрана Задача Первая"));
        menu.add("Задача Первая", "Цель задачи", () -> System.out.println("Выбрана Цель задачи"));
        menu.add("Задача Первая", "Ограничения и требования к решению", () -> System.out.println("Выбраны Ограничения и требования к решению"));
        menu.add("Задача Общая", "Задача Вторая", () -> System.out.println("Выбрана Задача Вторая"));
        Printer printer = new Printer();
        printer.print(menu);
    }
}
