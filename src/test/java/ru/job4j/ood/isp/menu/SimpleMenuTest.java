package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class SimpleMenuTest {

    public static final ActionDelegate STUB_ACTION = System.out::println;
    Menu menu = new SimpleMenu();

    @BeforeEach
    public void before() {
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
    }
    @Test
    public void whenAddThenReturnSame() {
        assertThat(new Menu.MenuItemInfo("Сходить в магазин",
                List.of("Купить продукты"), STUB_ACTION, "1."))
                .isEqualTo(menu.select("Сходить в магазин").get());
        assertThat(new Menu.MenuItemInfo(
                "Купить продукты",
                List.of("Купить хлеб", "Купить молоко"), STUB_ACTION, "1.1."))
                .isEqualTo(menu.select("Купить продукты").get());
        assertThat(new Menu.MenuItemInfo(
                "Покормить собаку", List.of(), STUB_ACTION, "2."))
                .isEqualTo(menu.select("Покормить собаку").get());
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }

    @Test
    public void whenExistItemThenTrue() {
        assertThat(true).isEqualTo(menu.select("Сходить в магазин").isPresent());
    }

    @Test
    public void whenNonExistItemThenFalse() {
        assertThat(false).isEqualTo(menu.select("Полежать на диване").isPresent());
    }

    @Test
    public void whenPrintItems() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Printer printer = new Printer();
        printer.print(menu);
        String expected = """
            1. Сходить в магазин
            ----1.1. Купить продукты
            --------1.1.1. Купить хлеб
            --------1.1.2. Купить молоко
            2. Покормить собаку""";
        assertThat(expected.replaceAll("\\s+", "")).isEqualTo(out.toString().replaceAll("\\s+", ""));
    }
}