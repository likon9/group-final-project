package Data.Strategies.ForAnimal;

import Data.Services.JsonService;
import Data.Strategies.ItemTypeStrategy;
import Data.VeiwModels.AnimalViewModel;
import Entity.Animal;
import Entity.AnimalEyeColor;

import java.util.ArrayList;
import java.util.List;

public class AnimalsFromFileStrategy implements ItemTypeStrategy {
    @Override
    public List<Comparable<?>> getCollection(int collectionLength) {
        var path = "CollectionFiles/animals.json";
        var jsonService = new JsonService<AnimalViewModel>();
        List<AnimalViewModel> animalViewModels = jsonService.readJson(path, AnimalViewModel.class);
        List<Comparable<?>> animals = new ArrayList<>();
        for (var animalViewModel : animalViewModels) {
            Animal animal = null;
            try {
                var animalBuilder = new Animal.AnimalBuilder(animalViewModel.getSpecies());
                animalBuilder.setId(animalViewModel.getId());
                animalBuilder.setHasFur(animalViewModel.isHasFur());
                animalBuilder.setEyeColor(AnimalEyeColor.values()[animalViewModel.getEyesColorCode()]);
                animal = animalBuilder.build();
            } catch (ArrayIndexOutOfBoundsException e) {
                continue;
            }

            animals.add(animal);
            if (animals.size() == collectionLength) return animals;
        }

        return animals;
    }
}
