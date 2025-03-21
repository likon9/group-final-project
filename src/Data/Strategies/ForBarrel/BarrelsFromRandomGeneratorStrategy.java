package Data.Strategies.ForBarrel;

import Data.Strategies.ItemTypeStrategy;

import java.util.List;

public class BarrelsFromRandomGeneratorStrategy implements ItemTypeStrategy {
    @Override
    public List<Comparable<?>> getCollection(int collectionLength) {
        return List.of();
    }
}
