package data.strategies.ForBarrel;

import data.services.DataGenerationServiceBuilder;
import data.services.MappingService;
import data.strategies.ItemTypeStrategy;
import data.veiwmodels.BarrelViewModel;
import entity.Barrel;
import entity.Item;

import java.util.ArrayList;
import java.util.List;

public class BarrelsFromRandomGeneratorStrategy implements ItemTypeStrategy  {
    @Override
    public List<Item> getCollection(int collectionLength) {
        var mappingService = new MappingService();
        var dataGenBuilder = new DataGenerationServiceBuilder();
        dataGenBuilder.setSeedNumber();
        var fakerService = dataGenBuilder.getService();

        var requiredElementsNumber = collectionLength;
        List<Item> items = new ArrayList<>();
        do {
            List<BarrelViewModel> barrelViewModels = fakerService.getBarrelVModels(requiredElementsNumber);
            items.addAll(barrelViewModels.stream()
                    .map(mappingService::BarrelViewModelToBarrel)
                    .filter(Barrel::isValid)
                    .map(a -> (Item) a)
                    .toList());
            requiredElementsNumber = collectionLength - items.size();
        } while (requiredElementsNumber > 0);

        return items;
    }
}
