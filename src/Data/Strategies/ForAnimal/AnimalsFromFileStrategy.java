package Data.Strategies.ForAnimal;

import Data.Services.JsonService;
import Data.Services.MappingService;
import Data.Strategies.ItemTypeStrategy;
import Data.VeiwModels.AnimalViewModel;
import Entity.Animal;
import Entity.Item;

import java.util.List;

public class AnimalsFromFileStrategy implements ItemTypeStrategy {
    @Override
    public List<Item> getCollection(int collectionLength) {
        var path = "CollectionFiles/animals.json";
        var jsonService = new JsonService<AnimalViewModel>();
        var mappingService = new MappingService();
        List<AnimalViewModel> animalViewModels = jsonService.readJson(path, AnimalViewModel.class);

        return animalViewModels.stream()
                .map(mappingService::AnimalViewModelToAnimal)
                .filter(Animal::isValid)
                .limit(collectionLength)
                .map(a -> (Item)a)
                .toList();
    }
}
