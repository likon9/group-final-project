package Data.Strategies.ForBarrel;

import Data.Services.DataGenerationServiceBuilder;
import Data.Services.MappingService;
import Data.Strategies.ItemTypeStrategy;
import Data.VeiwModels.BarrelViewModel;
import Entity.Barrel;
import Entity.Item;

import java.util.List;

public class BarrelsFromRandomGeneratorStrategy implements ItemTypeStrategy  {
    @Override
    public List<Item> getCollection(int collectionLength) {
        var mappingService = new MappingService();
        var dataGenBuilder = new DataGenerationServiceBuilder();
        dataGenBuilder.setSeedNumber();
        var fakerService = dataGenBuilder.getService();
        List<BarrelViewModel> barrelViewModels = fakerService.getBarrelVModels(collectionLength);

        return barrelViewModels.stream()
                .map(mappingService::BarrelViewModelToBarrel)
                .filter(Barrel::isValid)
                .map(a -> (Item)a)
                .toList();
    }
}
