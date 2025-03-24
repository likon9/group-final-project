package Data.Strategies.ForAnimal;

import Data.Strategies.ItemTypeStrategy;
import Entity.Item;

import java.util.List;

public class AnimalsFromManualInputStrategy implements ItemTypeStrategy {
    @Override
    public List<Item> getCollection(int collectionLength) {
        return List.of();
    }
}
