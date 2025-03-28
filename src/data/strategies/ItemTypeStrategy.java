package data.strategies;

import entity.Item;

import java.util.List;

public interface ItemTypeStrategy {
    List<Item> getCollection(int collectionLength);
}
