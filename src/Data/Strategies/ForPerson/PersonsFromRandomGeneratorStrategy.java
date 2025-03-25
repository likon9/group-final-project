package Data.Strategies.ForPerson;

import Data.Services.DataGenerationServiceBuilder;
import Data.Services.MappingService;
import Data.Strategies.ItemTypeStrategy;
import Data.VeiwModels.PersonViewModel;
import Entity.Item;
import Entity.Person;

import java.util.List;

public class PersonsFromRandomGeneratorStrategy implements ItemTypeStrategy {
    @Override
    public List<Item> getCollection(int collectionLength) {
        var mappingService = new MappingService();
        var dataGenBuilder = new DataGenerationServiceBuilder();
        dataGenBuilder.setSeedNumber();
        dataGenBuilder.setLocale();
        var fakerService = dataGenBuilder.getService();

        List<PersonViewModel> personViewModels = fakerService.getPersonVModels(collectionLength);

        return personViewModels.stream()
                .map(mappingService::PersonViewModelToPerson)
                .filter(Person::isValid)
                .map(a -> (Item)a)
                .toList();
    }
}
