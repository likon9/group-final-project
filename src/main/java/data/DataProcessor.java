package data;

import java.util.List;

import data.strategies.ForBarrel.*;
import data.strategies.ForAnimal.*;
import data.strategies.ForPerson.*;
import data.strategies.ItemTypeStrategy;
import data.strategies.ForPerson.PersonsFromFileStrategy;
import entity.Item;
import userInput.CollectionType;
import userInput.DataSourceType;

public class DataProcessor {
    private ItemTypeStrategy itemTypeStrategy;
    private int collecitonLenght;

    public DataProcessor(CollectionType collectionType, DataSourceType dataSourceType, int collectionLength) {
        this.collecitonLenght = collectionLength;

        switch (collectionType) {
            case PERSON:
                switch (dataSourceType) {
                    case FILE -> itemTypeStrategy = new PersonsFromFileStrategy();
                    case MANUAL -> itemTypeStrategy = new PersonsFromManualInputStrategy();
                    case RANDOM -> itemTypeStrategy = new PersonsFromRandomGeneratorStrategy();
                }
                break;
            case ANIMAL:
                switch (dataSourceType) {
                    case FILE -> itemTypeStrategy = new AnimalsFromFileStrategy();
                    case MANUAL -> itemTypeStrategy = new AnimalsFromManualInputStrategy();
                    case RANDOM -> itemTypeStrategy = new AnimalsFromRandomGeneratorStrategy();
                }
                break;
            case BARREL:
                switch (dataSourceType) {
                    case FILE -> itemTypeStrategy = new BarrelsFromFileStrategy();
                    case MANUAL -> itemTypeStrategy = new BarrelsFromManualInputStrategy();
                    case RANDOM -> itemTypeStrategy = new BarrelsFromRandomGeneratorStrategy();
                }
                break;
        }
    }

    public List<Item> getItems() {
        return itemTypeStrategy.getCollection(collecitonLenght);
    }
}
