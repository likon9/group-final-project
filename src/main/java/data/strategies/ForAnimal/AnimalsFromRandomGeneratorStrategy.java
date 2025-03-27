package data.strategies.ForAnimal;

import data.services.DataGenerationServiceBuilder;
import data.services.MappingService;
import data.strategies.ItemTypeStrategy;
import data.veiwmodels.AnimalViewModel;
import entity.Animal;
import entity.Item;

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
