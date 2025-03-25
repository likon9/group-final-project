package Data.Strategies.ForPerson;

import Data.Strategies.ItemTypeStrategy;
import Entity.Item;

import java.util.List;

public class PersonsFromManualInputStrategy implements ItemTypeStrategy {
    @Override
    public List<Item> getCollection(int collectionLength) {
        return List.of();
    }
}
