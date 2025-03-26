package Data.Strategies.ForAnimal;

import Data.Services.DataGenerationServiceBuilder;
import Data.Services.MappingService;
import Data.Strategies.ItemTypeStrategy;
import Data.VeiwModels.AnimalViewModel;
import Entity.Animal;
import Entity.Item;

import java.util.List;

public class AnimalsFromRandomGeneratorStrategy implements ItemTypeStrategy {
    @Override
    public List<Item> getCollection(int collectionLength) {
        var mappingService = new MappingService();
        var dataGenBuilder = new DataGenerationServiceBuilder();
        dataGenBuilder.setSeedNumber();
        var fakerService = dataGenBuilder.getService();
        List<AnimalViewModel> animalViewModels = fakerService.getAnimalVModels(collectionLength);

        return animalViewModels.stream()
                .map(mappingService::AnimalViewModelToAnimal)
                .filter(Animal::isValid)
                .map(a -> (Item)a)
                .toList();
    }
}
