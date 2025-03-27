package data.strategies.ForBarrel;

import data.services.ManualInputDataHelper;
import data.services.MappingService;
import data.strategies.ItemTypeStrategy;
import data.veiwmodels.BarrelViewModel;
import entity.Barrel;
import entity.BarrelMaterial;
import entity.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class BarrelsFromManualInputStrategy implements ItemTypeStrategy {
    @Override
    public List<Item> getCollection(int collectionLength) {
        ManualInputDataHelper manualInputDataHelper = new ManualInputDataHelper();
        MappingService mappingService = new MappingService();
        List<BarrelViewModel> barrelViewModels = new ArrayList<>();


        while (collectionLength > 0) {

            manualInputDataHelper.showMessageToUserInConsole("Please enter data to create Barrel.\n");

            List<String> userData = manualInputDataHelper.getUserData(
                    "volume (number)",
                    "stored contents",
                    "body material code " + Arrays.stream(BarrelMaterial.values()).map(barrelMaterial -> barrelMaterial.ordinal() + " for " + barrelMaterial).toList());

            int bodyMaterialCode = 0;
            double volume = 0;
            String storedContents = null;
            try {
                volume = Double.parseDouble(Objects.requireNonNull(userData.get(0)));
                storedContents = userData.get(1);
                bodyMaterialCode = Integer.parseInt(Objects.requireNonNull(userData.get(2)));

            } catch (NumberFormatException e) {
                manualInputDataHelper.showMessageToUserInConsole("\nYou have entered invalid data: " + e.getMessage());
            }


            var barrelVM = new BarrelViewModel(volume, storedContents, bodyMaterialCode);
            barrelViewModels.add(barrelVM);

            manualInputDataHelper.showMessageToUserInConsole("\nNext data is added for validation: " + barrelVM);

            collectionLength--;
        }

        return barrelViewModels.stream()
                .map(mappingService::BarrelViewModelToBarrel)
                .filter(Barrel::isValid)
                .map(a -> (Item) a)
                .toList();
    }
}
