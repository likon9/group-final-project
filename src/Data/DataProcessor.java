package Data;

import java.util.List;

import Data.Strategies.ForBarrel.*;
import Data.Strategies.ForAnimal.*;
import Data.Strategies.ForPerson.*;
import Data.Strategies.ItemTypeStrategy;
import Data.Strategies.ForPerson.PersonsFromFileStrategy;
import Entity.Item;
import UserInput.CollectionType;
import UserInput.DataSourceType;

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
