import Data.DataProcessor;
import Entity.Animal;
import Entity.AnimalEyeColor;
import Entity.Item;
import Sorting.QuickSort;
import UserInput.CollectionType;
import UserInput.DataSourceType;
import customlist.CustomArrayList;
import usermenu.UserMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // fetching data  example
        var collectionType = CollectionType.ANIMAL;
        var dataSourceType = DataSourceType.FILE;
        var arrayLength = 12;
        var dataProcessor = new DataProcessor(collectionType, dataSourceType, arrayLength);
        List<Comparable<?>> items = dataProcessor.getItems();
        items.forEach(System.out::println);

        boolean exit = false;
        Scanner scan = new Scanner(System.in);
        String answ = "0";

        while (true) {
            UserMenu.chooseStep(answ);
            answ = scan.nextLine();
            if (answ.toLowerCase().equals("exit"))
                break;
        }
    }
}