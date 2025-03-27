package Data.Strategies.ForAnimal;

import Data.Services.ManualInputDataHelper;
import Data.Services.MappingService;
import Data.Strategies.ItemTypeStrategy;
import Data.VeiwModels.AnimalViewModel;
import Entity.Animal;
import Entity.AnimalEyeColor;
import Entity.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class AnimalsFromManualInputStrategy implements ItemTypeStrategy {
    @Override
    public List<Item> getCollection(int collectionLength) {
        ManualInputDataHelper manualInputDataHelper = new ManualInputDataHelper();
        MappingService mappingService = new MappingService();
        List<AnimalViewModel> animalViewModels = new ArrayList<>();


        while (collectionLength > 0) {
            manualInputDataHelper.showMessageToUserInConsole("Please enter data to create Animal.\n");

            List<String> userData = manualInputDataHelper.getUserData(
                    "id",
                    "animal species",
                    "eye color code " + Arrays.stream(AnimalEyeColor.values()).map(animalEyeColor -> animalEyeColor.ordinal() + " for " + animalEyeColor).toList(),
                    "has fur? (true/false)");

            int id = 0;
            boolean hasFur = false;
            int eyesColorCode = 0;
            String animalSpecies = null;

            try {
                id = Integer.parseInt(Objects.requireNonNull(userData.get(0)));
                animalSpecies = userData.get(1);
                eyesColorCode = Integer.parseInt(Objects.requireNonNull(userData.get(2)));
                hasFur = Boolean.parseBoolean(userData.get(3));
            } catch (NumberFormatException e) {
                manualInputDataHelper.showMessageToUserInConsole("\nYou have entered invalid data: " + e.getMessage());
            }


            var animalVM = new AnimalViewModel(id, animalSpecies, eyesColorCode, hasFur);
            animalViewModels.add(animalVM);

            manualInputDataHelper.showMessageToUserInConsole("\nNext data is added for validation: " + animalVM);

            collectionLength--;
        }

        return animalViewModels.stream()
                .map(mappingService::AnimalViewModelToAnimal)
                .filter(Animal::isValid)
                .map(a -> (Item) a)
                .toList();
    }
}
