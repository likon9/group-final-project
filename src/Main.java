import Data.DataProcessor;
import Entity.Animal;
import Entity.AnimalEyeColor;
import Entity.Item;
import Sorting.QuickSort;
import UserInput.CollectionType;
import UserInput.DataSourceType;
import customlist.CustomArrayList;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // fetching data  example
        var collectionType = CollectionType.ANIMAL;
        var dataSourceType = DataSourceType.FILE;
        var arrayLength = 12;
        var dataProcessor = new DataProcessor(collectionType, dataSourceType, arrayLength);
        List<Item> items = dataProcessor.getItems();
        items.forEach(System.out::println);
    }
}