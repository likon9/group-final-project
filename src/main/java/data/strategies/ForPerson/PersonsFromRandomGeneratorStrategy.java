package data.strategies.ForPerson;

import data.services.DataGenerationServiceBuilder;
import data.services.MappingService;
import data.strategies.ItemTypeStrategy;
import data.veiwmodels.PersonViewModel;
import entity.Item;
import entity.Person;

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
