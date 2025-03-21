package Data.Strategies.ForBarrel;

import Data.Services.JsonService;
import Data.Strategies.ItemTypeStrategy;
import Data.VeiwModels.BarrelViewModel;
import Entity.Barrel;
import Entity.BarrelMaterial;

import java.util.ArrayList;
import java.util.List;

public class BarrelsFromFileStrategy implements ItemTypeStrategy {
    @Override
    public List<Comparable<?>> getCollection(int collectionLength) {
        var path = "CollectionFiles/barrels.json";
        var jsonService = new JsonService<BarrelViewModel>();
        List<BarrelViewModel> barrelViewModels = jsonService.readJson(path, BarrelViewModel.class);
        List<Comparable<?>> barrels = new ArrayList<>();
        for (var barrelViewModel : barrelViewModels) {
            Barrel barrel = null;
            try {
                var barrelBuilder = new Entity.Barrel.BarrelBuilder(barrelViewModel.getVolume());
                barrelBuilder.setStorageMaterial(barrelViewModel.getStoredContents());
                barrelBuilder.setManufacturingMaterial(BarrelMaterial.values()[barrelViewModel.getBodyMaterialCode()]);
                barrel = barrelBuilder.build();
            } catch (ArrayIndexOutOfBoundsException e) {
                continue;
            }

            barrels.add(barrel);
            if (barrels.size() == collectionLength) return barrels;
        }

        return barrels;
    }
}
