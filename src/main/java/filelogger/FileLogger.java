package filelogger;

import customlist.CustomArrayList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

public class FileLogger {
    private static final String LOG_FILE = "output.log";

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Завершение работы - сохранение данных...");
        }));
    }

    public static <T> void logFromExternal(CustomArrayList<T> items, String type) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write("\n/// " + type + " ///\n");
            for (T item : items) {
                writer.write(item.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Ошибка записи: " + e.getMessage());
        }
    }

    private static void tryLogMainData() {
        try {
            Class<?> mainClass = Class.forName("Main");
            Field animalsField = mainClass.getDeclaredField("animals");
            animalsField.setAccessible(true);

        } catch (Exception e) {
            System.err.println("Не удалось получить данные из Main: " + e.getMessage());
        }
        try {
            Class<?> mainClass = Class.forName("Main");
            Field barrelsField = mainClass.getDeclaredField("barrels");
            barrelsField.setAccessible(true);

        } catch (Exception e) {
            System.err.println("Не удалось получить данные из Main: " + e.getMessage());
        }
        try {
            Class<?> mainClass = Class.forName("Main");
            Field personsField = mainClass.getDeclaredField("persons");
            personsField.setAccessible(true);

        } catch (Exception e) {
            System.err.println("Не удалось получить данные из Main: " + e.getMessage());
        }
    }
}