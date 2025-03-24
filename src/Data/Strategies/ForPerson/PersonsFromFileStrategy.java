package Data.Strategies.ForPerson;

import Data.Services.JsonService;
import Data.Services.MappingService;
import Data.Strategies.ItemTypeStrategy;
import Data.VeiwModels.PersonViewModel;
import Entity.Item;
import Entity.Person;

import java.util.List;

public class PersonsFromFileStrategy implements ItemTypeStrategy {
    @Override
    public List<Item> getCollection(int collectionLength) {
        var jsonService = new JsonService<PersonViewModel>();
        var mappingService = new MappingService();
        var path = "CollectionFiles/persons.json";
        List<PersonViewModel> personViewModels = jsonService.readJson(path, PersonViewModel.class);
        return personViewModels.stream()
                .map(mappingService::PersonViewModelToPerson)
                .filter(Person::isValid)
                .limit(collectionLength)
                .map(a -> (Item)a)
                .toList();
    }
}
