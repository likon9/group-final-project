package usermenu;

import customlist.CustomArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.StringJoiner;

public class TableFileLogger {
    private static final String LOG_FILE = "table_output.log";

    // Метод для логирования всей таблицы
    public static void logTableToFile(CustomArrayList<?> list) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write("\n=== TABLE DUMP ===\n");

            if (list == null || list.size() == 0) {
                writer.write("Empty collection\n");
                return;
            }

            Object firstItem = list.get(0);
            Class<?> itemClass = firstItem.getClass();
            Field[] fields = itemClass.getDeclaredFields();

            StringJoiner header = new StringJoiner(" | ");
            for (Field field : fields) {
                header.add(field.getName());
            }
            writer.write(header.toString() + "\n");
            writer.write("-".repeat(header.length()) + "\n");

            for (int i = 0; i < list.size(); i++) {
                Object item = list.get(i);
                StringJoiner row = new StringJoiner(" | ");

                for (Field field : fields) {
                    field.setAccessible(true);
                    Object value = field.get(item);
                    row.add(value != null ? value.toString() : "null");
                }

                writer.write(row.toString() + "\n");
            }

        } catch (IOException | IllegalAccessException e) {
            System.err.println("Ошибка записи таблицы в файл: " + e.getMessage());
        }
    }

    // Новый метод для логирования результатов поиска
    public static void logSearchResult(Object item, int index) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write("\n=== SEARCH RESULT ===\n");
            writer.write("Found item: " + item.toString() + "\n");
            writer.write("At index: " + (index + 1) + "\n"); // +1 для соответствия выводу в консоль
            writer.write("----------------------\n");
        } catch (IOException e) {
            System.err.println("Ошибка записи результата поиска: " + e.getMessage());
        }
    }
}