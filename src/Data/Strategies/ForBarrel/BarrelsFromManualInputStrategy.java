package Data.Strategies.ForBarrel;

import Data.Strategies.ItemTypeStrategy;
import Entity.Item;

import java.util.List;

public class BarrelsFromManualInputStrategy implements ItemTypeStrategy  {
    @Override
    public List<Item> getCollection(int collectionLength) {
        return List.of();
    }
}
