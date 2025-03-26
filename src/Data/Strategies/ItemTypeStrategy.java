package Data.Strategies;

import Entity.Item;

import java.util.List;

public interface ItemTypeStrategy {
    List<Item> getCollection(int collectionLength);
}
