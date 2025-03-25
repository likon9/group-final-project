package usermenu;

import customlist.CustomArrayList;

public class UserMenu {
    private static String step = "";
    private static CustomArrayList entityList;

    public static void chooseStep(String answ) {
        String oldStep = step;
        step += answ;
        switch (step) {
            case "0":
                chooseEntity();
                return;
            case "1":
                chooseAnimalLoader();
                return;
            case "11":
            default:
                System.out.println("Варианта \"" + answ + "\" нет. Попробуйте ещё раз.");
                step = oldStep;
                return;
        }
    }

    public static void chooseEntity() {
        System.out.println(" ______________________________________________________________________");
        System.out.println("| Выберите с каким типом данных будете работать                        |");
        System.out.println(" ______________________________________________________________________");
        System.out.println("| 1: Animal; 2: Barrel; 3: Person; exit: Выход из программы            |");
        System.out.println(" ______________________________________________________________________");


    }

    public static void chooseAnimalLoader() {
        System.out.println(" _______________________________________________________________________________");
        System.out.println("| Выберите способ ввода данных                                                  |");
        System.out.println(" _______________________________________________________________________________");
        System.out.println("| 1: Из файла; 2: Случайная генерация; 3: Ручной ввод; exit: Выход из программы |");
        System.out.println(" _______________________________________________________________________________");
    }
}
