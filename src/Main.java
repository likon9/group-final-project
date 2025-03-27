import Data.DataProcessor;
import Entity.*;
import UserInput.*;
import customlist.CustomArrayList;
import usermenu.UserMenu;
import sorting.InsertionSort;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // fetching data  example
//        var collectionType = CollectionType.ANIMAL;
//        var dataSourceType = DataSourceType.RANDOM;
//        var arrayLength = 15;
//        var dataProcessor = new DataProcessor(collectionType, dataSourceType, arrayLength);
//        List<Item> items = dataProcessor.getItems();
//        TablePrinting.printTable(items);
//
//        showDataFetchingAndSortingExample();


        UserMenu.doSteps();
    }

    private static void showDataFetchingAndSortingExample() {
        // data fetching and sorting examples
        var collectionType = CollectionType.PERSON;
        var dataSourceType = DataSourceType.RANDOM;
        var arrayLength = 12;
        var dataProcessor = new DataProcessor(collectionType, dataSourceType, arrayLength);
        List<Item> items = dataProcessor.getItems();

        CustomArrayList<Animal> animals = null;
        CustomArrayList<Barrel> barrels = null;
        CustomArrayList<Person> persons = null;

        switch (collectionType) {
            case ANIMAL -> {
                animals = new CustomArrayList<>();
                animals.addAll(items.stream().map(x -> (Animal)x).toList());
            }
            case PERSON -> {
                persons = new CustomArrayList<>();
                persons.addAll(items.stream().map(x -> (Person)x).toList());
            }
            case BARREL -> {
                barrels = new CustomArrayList<>();
                barrels.addAll(items.stream().map(x -> (Barrel)x).toList());
            }
        }

        System.out.println("\n///UNSORTED:///");
        if (animals != null){
            TablePrinting.printTable(animals);
            InsertionSort.insertionSort(animals);
            System.out.println("\n///SORTED:///");
            TablePrinting.printTable(animals);
        }

        if (barrels != null){
            TablePrinting.printTable(barrels);
            InsertionSort.insertionSort(barrels);
            System.out.println("\n///SORTED:///");
            TablePrinting.printTable(barrels);
        }

        if (persons != null){
            TablePrinting.printTable(persons);
            InsertionSort.insertionSort(persons);
            System.out.println("\n///SORTED:///");
            TablePrinting.printTable(persons);
        }
    }
}