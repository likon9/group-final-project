package data.services;

import userInput.TablePrinting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DataGenerationServiceBuilder {
    private long seedNumber = System.currentTimeMillis();
    private String locale = "ru";
    private static final Map<String, String> LOCALE_MAP = Map.of(
            "by", "be_BY",
            "en", "en-US",
            "pl", "pl",
            "ru", "ru_RU",
            "tr", "tr"
    );

    private static final Map<String, String> SHOW_LOCALES = Map.of(
            "by", "для белорусской",
            "en", "для английской (США)",
            "pl", "для польской",
            "ru", "для русской",
            "tr", "для турецкой"
    );

    public void setSeedNumber() {
        Scanner scanner = new Scanner(System.in);
        TablePrinting.printMessages(List.of(
                "Пожалуйста, введите Seed номер в консоль ниже.",
                "Если Вы хотите, чтобы номер сгенерировался случайным образом, оставьте поле пустым и нажмите Enter."));
        String input = scanner.nextLine();

        try {
            if (!input.isEmpty()) {
                seedNumber = Long.parseLong(input);
            }
        } catch (NumberFormatException e) {
            TablePrinting.printMessages(List.of("Это был некорректный номер. Номер Seed будет сгенерирован автоматически."));
        }

        TablePrinting.printMessages(List.of("Ваш Seed номер это " + seedNumber,
                "Вы можете получить те же данные, введите это число в следующий раз."));
        System.out.println();
    }

    public void setLocale() {
        Scanner scanner = new Scanner(System.in);
        TablePrinting.printMessages(List.of("Введите два символа, чтобы выбрать язык (локализацию). Местоположение по умолчанию — 'ru'."));
        List<String> messages = new ArrayList<>();
        messages.add("Список всех локализаций:");
        SHOW_LOCALES.forEach((key, value) -> messages.add("Используй код '%s' %s".formatted(key, value)));
        TablePrinting.printMessages(messages);
        System.out.println();
        TablePrinting.printMessages(List.of("Введи необходимую локализацию ниже: "));
        String input = scanner.nextLine();
        if (LOCALE_MAP.containsKey(input.toLowerCase())) {
            locale = input;
        }
    }

    public DataGenerationService getService() {
        return new DataGenerationService(seedNumber, locale);
    }
}
