package data.strategies.ForAnimal;

import data.services.JsonService;
import data.services.MappingService;
import data.strategies.ItemTypeStrategy;
import data.veiwmodels.AnimalViewModel;
import entity.Animal;
import entity.Item;

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
