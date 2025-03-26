package usermenu;

import Data.DataProcessor;
import UserInput.CollectionType;
import UserInput.DataSourceType;
import UserInput.TablePrinting;
import customlist.CustomArrayList;
import sorting.InsertionSort;

import java.util.Scanner;

public class UserMenu {
    private static CustomArrayList entityList;
    private static CollectionType entityType;
    private static DataSourceType dataSourceType;
    private static int collectionLength;
    private static Scanner scan = new Scanner(System.in);


    public static void doSteps() {
        boolean isOk = chooseEntity();
        if (!isOk) return;

        isOk = chooseLoader();
        if (!isOk) return;

        isOk = chooseCollectionLength();
        if (!isOk) return;

        loadCollection();

        System.out.println(" ______________________________________________________________________");
        System.out.println("| Коллекция до сортировки                                              |");
        System.out.println(" ______________________________________________________________________");

        TablePrinting.printTable(entityList);

        System.out.println(" ______________________________________________________________________");
        System.out.println("| Коллекция после сортировки                                           |");
        System.out.println(" ______________________________________________________________________");

        InsertionSort.insertionSort(entityList);
        TablePrinting.printTable(entityList);
    }

    public static boolean chooseEntity() {
        System.out.println(" ______________________________________________________________________");
        System.out.println("| Выберите с каким типом данных будете работать                        |");
        System.out.println(" ______________________________________________________________________");
        System.out.println("| 1: Animal; 2: Barrel; 3: Person; exit: Выход из программы            |");
        System.out.println(" ______________________________________________________________________");

        while (true) {
            String answ = scan.nextLine().toLowerCase();
            switch (answ) {
                case "1":
                    entityType = CollectionType.ANIMAL;
                    return true;
                case "2":
                    entityType = CollectionType.BARREL;
                    return true;
                case "3":
                    entityType = CollectionType.PERSON;
                    return true;
                case "exit": return false;
                default: System.out.println("Варианта \"" + answ + "\" нет. Попробуйте еще раз.");
            }
        }
    }

    public static boolean chooseLoader() {
        System.out.println(" _______________________________________________________________________________");
        System.out.println("| Выберите способ ввода данных                                                  |");
        System.out.println(" _______________________________________________________________________________");
        System.out.println("| 1: Из файла; 2: Случайная генерация; 3: Ручной ввод; exit: Выход из программы |");
        System.out.println(" _______________________________________________________________________________");

        while (true) {
            String answ = scan.nextLine().toLowerCase();
            switch (answ) {
                case "1":
                    dataSourceType = DataSourceType.FILE;
                    return true;
                case "2":
                    dataSourceType = DataSourceType.RANDOM;
                    return true;
                case "3":
                    dataSourceType = DataSourceType.MANUAL;
                    return true;
                case "exit": return false;
                default: System.out.println("Варианта \"" + answ + "\" нет. Попробуйте еще раз.");
            }
        }
    }

    public static boolean chooseCollectionLength() {
        System.out.println(" _______________________________________________________________________________");
        System.out.println("| Введите длинну коллекции или exit для выхода из программы                     |");
        System.out.println(" _______________________________________________________________________________");

        while (true) {
            String answ = scan.nextLine();
            try {
                collectionLength = Integer.parseInt(answ);
                return true;
            } catch (NumberFormatException e) {
                if (answ.equalsIgnoreCase("exit")){
                    return false;
                } else {
                    System.out.println(" \"" + answ + "\" - недопустимое значение! Попробуйте еще раз.");
                }
            }
        }
    }

    public static void loadCollection() {
        DataProcessor dataProcessor = new DataProcessor(entityType, dataSourceType, collectionLength);
        entityList = new CustomArrayList<>(dataProcessor.getItems());
    }
}
