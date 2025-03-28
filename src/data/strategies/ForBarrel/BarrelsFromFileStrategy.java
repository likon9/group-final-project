package data.strategies.ForBarrel;

import data.services.JsonService;
import data.services.MappingService;
import data.strategies.ItemTypeStrategy;
import data.veiwmodels.BarrelViewModel;
import entity.Barrel;
import entity.Item;

import java.util.List;

public class BarrelsFromFileStrategy implements ItemTypeStrategy {
    @Override
    public List<Item> getCollection(int collectionLength) {
        var path = "CollectionFiles/barrels.json";
        var jsonService = new JsonService<BarrelViewModel>();
        List<BarrelViewModel> barrelViewModels = jsonService.readJson(path, BarrelViewModel.class);
        var mappingService = new MappingService();

        return barrelViewModels.stream()
                .map(mappingService::BarrelViewModelToBarrel)
                .filter(Barrel::isValid)
                .limit(collectionLength)
                .map(a -> (Item)a)
                .toList();
    }
}
