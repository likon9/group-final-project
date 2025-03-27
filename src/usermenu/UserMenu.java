package usermenu;

import Data.DataProcessor;
import Entity.*;
import UserInput.CollectionType;
import UserInput.DataSourceType;
import UserInput.TablePrinting;
import customlist.CustomArrayList;
import filelogger.FileLogger;
import search.CustomBinarySearch;
import sorting.InsertionSort;

import java.util.Iterator;
import java.util.Scanner;

public class UserMenu {
    private static CustomArrayList entityList;
    private static CollectionType entityType;
    private static DataSourceType dataSourceType;
    private static int collectionLength;
    private static Scanner scan = new Scanner(System.in);


    public static void doSteps() {

        while (true) {
            boolean isOk = chooseEntity();
            if (!isOk) return;

            isOk = chooseLoader();
            if (!isOk) return;

            isOk = chooseCollectionLength();
            if (!isOk) return;

            loadCollection();

            System.out.println("┌——————————————————————————————————————————————————————————————————————┐");
            System.out.println("| Коллекция до сортировки                                              |");
            System.out.println("└——————————————————————————————————————————————————————————————————————┘");

            TablePrinting.printTable(entityList);

            System.out.println("┌——————————————————————————————————————————————————————————————————————┐");
            System.out.println("| Коллекция после сортировки                                           |");
            System.out.println("└——————————————————————————————————————————————————————————————————————┘");

            InsertionSort.insertionSort(entityList);
            TablePrinting.printTable(entityList);

            boolean escape = false;
            while (!escape) {
                System.out.println("┌——————————————————————————————————————————————————————————————————————┐");
                System.out.println("| Выберите дальнейшее действие                                         |");
                System.out.println("|——————————————————————————————————————————————————————————————————————|");
                System.out.println("| 1: Вернуться к выбору типа данных; 2: Поиск элемента;                |");
                System.out.println("| 3: Сортировка коллекции по числовому полю;                           |");
                System.out.println("| 4: Сохранить коллекцию в файл; exit: выход из программы              |");
                System.out.println("└——————————————————————————————————————————————————————————————————————┘");

                boolean wrongChoice = true;
                while (wrongChoice) {
                    String answ = scan.nextLine().toLowerCase();
                    switch (answ) {
                        case "1":
                            wrongChoice = false;
                            escape = true;
                            break;
                        case "2":
                            wrongChoice = false;
                            isOk = searchItem();
                            escape = false;
                            break;
                        case "3":
                            wrongChoice = false;
                            isOk = secondSorting();
                            break;
                        case "4":
                            wrongChoice = false;
                            isOk = saveToFile();
                            break;
                        case "exit":
                            wrongChoice = false;
                            escape = true;
                            isOk = false;
                            break;
                        default:
                            System.out.println("Варианта \"" + answ + "\" нет. Попробуйте еще раз.");
                    }
                }
            }
            if (!isOk) return;
        }
    }

    public static boolean chooseEntity() {
        System.out.println("┌——————————————————————————————————————————————————————————————————————┐");
        System.out.println("| Выберите с каким типом данных будете работать                        |");
        System.out.println("|——————————————————————————————————————————————————————————————————————|");
        System.out.println("| 1: Animal; 2: Barrel; 3: Person; exit: Выход из программы            |");
        System.out.println("└——————————————————————————————————————————————————————————————————————┘");

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
                default:
                    System.out.println("Варианта \"" + answ + "\" нет. Попробуйте еще раз.");
            }
        }
    }

    public static boolean chooseLoader() {
        System.out.println("┌———————————————————————————————————————————————————————————————————————————————┐");
        System.out.println("| Выберите способ ввода данных                                                  |");
        System.out.println("|———————————————————————————————————————————————————————————————————————————————|");
        System.out.println("| 1: Из файла; 2: Случайная генерация; 3: Ручной ввод; exit: Выход из программы |");
        System.out.println("└———————————————————————————————————————————————————————————————————————————————┘");

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
        System.out.println("┌———————————————————————————————————————————————————————————————————————————————┐");
        System.out.println("| Введите длинну коллекции или exit для выхода из программы                     |");
        System.out.println("└———————————————————————————————————————————————————————————————————————————————┘");

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

    public static boolean searchItem() {

        InsertionSort.insertionSort(entityList);

        boolean isOk = switch (entityType) {
            case ANIMAL -> animalSearch();
            case BARREL -> barrelSearch();
            case PERSON -> personSearch();
        };

        return isOk;
    }

    public static boolean animalSearch() {
        Animal[] animalArray = new Animal[entityList.size()];
        animalArray = (Animal[]) entityList.toArray(animalArray);
        Animal.AnimalBuilder builder;

        while (true) {
            System.out.println("┌———————————————————————————————————————————————————————————————————————————————┐");
            System.out.println("| Через пробел вводите:                                                         |");
            System.out.println("|   -Id                                                                         |");
            System.out.println("|   -Вид животного                                                              |");
            System.out.println("|   -Цвет глаз: RED, BLUE, GREEN, YELLOW, BLACK, WHITE                          |");
            System.out.println("|   -Есть ли шерсть? 1: да; 2: нет                                              |");
            System.out.println("| exit: для выхода из поиска                                                    |");
            System.out.println("└———————————————————————————————————————————————————————————————————————————————┘");

            try {
                String[] answ = scan.nextLine().split(" ");
                if (answ[0].equalsIgnoreCase("exit")) {
                    return false;
                } else {
                    builder = new Animal.AnimalBuilder(answ[1]);
                    builder.setId(Integer.parseInt(answ[0]));
                    builder.setEyeColor(AnimalEyeColor.valueOf(answ[2]));
                    if (answ[3].equals("1"))
                        builder.setHasFur(true);
                    else
                        builder.setHasFur(false);
                }
                break;
            } catch (Exception e) {
                System.out.println("Что-то пошло не так....");
            }
        }

        Animal item = builder.build();
        int index = CustomBinarySearch.search(animalArray, item);
        if (index == -1)
            System.out.println("Элемент не найден");
        else
            System.out.println(entityList.get(index) + " найден под индексом: " + index + 1);
        return true;
    }

    public static boolean barrelSearch() {
        Barrel[] barrelArray = new Barrel[entityList.size()];
        barrelArray = (Barrel[]) entityList.toArray(barrelArray);
        Barrel.BarrelBuilder builder;

        while (true) {
            System.out.println("┌———————————————————————————————————————————————————————————————————————————————┐");
            System.out.println("| Через пробел вводите:                                                         |");
            System.out.println("|   -Объем бочки                                                                |");
            System.out.println("|   -Хранимый материал                                                          |");
            System.out.println("|   -Материал из которого изготовлена: WOOD, IRON, ALUMINIUM, STEEL, PLASTIC    |");
            System.out.println("| exit: для выхода из поиска                                                    |");
            System.out.println("└———————————————————————————————————————————————————————————————————————————————┘");

            try {
                String[] answ = scan.nextLine().split(" ");
                if (answ[0].equalsIgnoreCase("exit")) {
                    return false;
                } else {
                    builder = new Barrel.BarrelBuilder(Double.parseDouble(answ[0]));
                    builder.setStorageMaterial(answ[1]);
                    builder.setManufacturingMaterial(BarrelMaterial.valueOf(answ[2]));
                }
                break;
            } catch (Exception e) {
                System.out.println("Что-то пошло не так....");
            }
        }

        Barrel item = builder.build();
        int index = CustomBinarySearch.search(barrelArray, item);
        if (index == -1)
            System.out.println("Элемент не найден");
        else
            System.out.println(entityList.get(index) + " найден под индексом: " + index + 1);
        return true;
    }

    public static boolean personSearch() {
        Person[] personArray = new Person[entityList.size()];
        personArray = (Person[]) entityList.toArray(personArray);
        Person.PersonBuilder builder;

        while (true) {
            System.out.println("┌———————————————————————————————————————————————————————————————————————————————┐");
            System.out.println("| Через пробел вводите:                                                         |");
            System.out.println("|   -Пол: MALE, FEMALE                                                          |");
            System.out.println("|   -Возраст                                                                    |");
            System.out.println("|   -Фамилия                                                                    |");
            System.out.println("| exit: для выхода из поиска                                                    |");
            System.out.println("└———————————————————————————————————————————————————————————————————————————————┘");
            try {
                String[] answ = scan.nextLine().split(" ");
                if (answ[0].equalsIgnoreCase("exit")) {
                    return false;
                } else {
                    builder = new Person.PersonBuilder(Gender.valueOf(answ[0]));
                    builder.setAge(Integer.parseInt(answ[1]));
                    builder.setLastName(answ[2]);
                }
                break;
            } catch (Exception e) {
                System.out.println("Что-то пошло не так....");
            }
        }

        Person item = builder.build();
        int index = CustomBinarySearch.search(personArray, item);
        if (index == -1)
            System.out.println("Элемент не найден");
        else
            System.out.println(entityList.get(index) + " найден под индексом: " + index + 1);
        return true;
    }

    public static boolean secondSorting() {
        String sortingField = "";
        switch (entityType) {
            case ANIMAL:
                sortingField = "id";
                break;
            case BARREL:
                System.out.println("Эта операция недоступна для Barrel");
                return true;
            case PERSON:
                sortingField = "age";
                break;
        }
        InsertionSort.sortByEvenIntField(entityList, sortingField);

        System.out.println("┌——————————————————————————————————————————————————————————————————————┐");
        System.out.println("| Коллекция после сортировки                                           |");
        System.out.println("└——————————————————————————————————————————————————————————————————————┘");

        TablePrinting.printTable(entityList);

        return true;
    }

    public static boolean saveToFile() {
        try {
            FileLogger.logFromExternal(entityList, entityType.toString());
            System.out.println("Данные успешно записаны в файл!");
        } catch (Exception e) {
            System.out.println("Что-то пошло не так....");
        }
        return true;
    }
}
