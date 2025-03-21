package Data.Strategies;

import java.util.List;

public interface ItemTypeStrategy {
    List<Comparable<?>> getCollection(int collectionLength);
}
