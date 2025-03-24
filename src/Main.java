import Data.DataProcessor;
import Entity.*;
import Sorting.QuickSort;
import UserInput.*;
import customlist.CustomArrayList;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        var collectionType = CollectionType.BARREL;
        var dataSourceType = DataSourceType.RANDOM;
        var arrayLength = 15;
        var dataProcessor = new DataProcessor(collectionType, dataSourceType, arrayLength);
        List<Item> items = dataProcessor.getItems();
        items.forEach(System.out::println);
        // showDataFetchingAndSortingExample();
    }

    private static void showDataFetchingAndSortingExample() {
        // data fetching and sorting examples
        var collectionType = CollectionType.BARREL;
        var dataSourceType = DataSourceType.FILE;
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
            animals.forEach(System.out::println);
            QuickSort<Animal> animalSorter = new QuickSort<>();
            animalSorter.quickSort(animals);
            System.out.println("\n///SORTED:///");
            animals.forEach(System.out::println);
        }

        if (barrels != null){
            barrels.forEach(System.out::println);
            QuickSort<Barrel> barrelSorter = new QuickSort<>();
            barrelSorter.quickSort(barrels);
            System.out.println("\n///SORTED:///");
            barrels.forEach(System.out::println);
        }

        if (persons != null){
            persons.forEach(System.out::println);
            QuickSort<Person> personSorter = new QuickSort<>();
            personSorter.quickSort(persons);
            System.out.println("\n///SORTED:///");
            persons.forEach(System.out::println);
        }
    }
}
