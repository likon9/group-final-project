package Data.Strategies.ForAnimal;

import Data.Strategies.ItemTypeStrategy;

import java.util.List;

public class AnimalsFromRandomGeneratorStrategy implements ItemTypeStrategy {
    @Override
    public List<Comparable<?>> getCollection(int collectionLength) {
        return List.of();
    }
}
