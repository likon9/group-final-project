package Data.Strategies.ForPerson;

import Data.Strategies.ItemTypeStrategy;

import java.util.List;

public class PersonsFromRandomGeneratorStrategy implements ItemTypeStrategy {
    @Override
    public List<Comparable<?>> getCollection(int collectionLength) {
        return List.of();
    }
}
