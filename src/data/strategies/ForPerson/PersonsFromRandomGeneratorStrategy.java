package data.strategies.ForPerson;

import data.services.DataGenerationServiceBuilder;
import data.services.MappingService;
import data.strategies.ItemTypeStrategy;
import data.veiwmodels.PersonViewModel;
import entity.Item;
import entity.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonsFromRandomGeneratorStrategy implements ItemTypeStrategy {
    @Override
    public List<Item> getCollection(int collectionLength) {
        var mappingService = new MappingService();
        var dataGenBuilder = new DataGenerationServiceBuilder();
        dataGenBuilder.setSeedNumber();
        dataGenBuilder.setLocale();
        var fakerService = dataGenBuilder.getService();

        var requiredElementsNumber = collectionLength;
        List<Item> items = new ArrayList<>();
        do {
            List<PersonViewModel> personViewModels = fakerService.getPersonVModels(requiredElementsNumber);
            items.addAll(personViewModels.stream()
                    .map(mappingService::PersonViewModelToPerson)
                    .filter(Person::isValid)
                    .map(a -> (Item) a)
                    .toList());
            requiredElementsNumber = collectionLength - items.size();
        } while (requiredElementsNumber > 0);

        return items;
    }
}
