package Data.Strategies.ForPerson;

import Data.Services.JsonService;
import Data.Strategies.ItemTypeStrategy;
import Data.VeiwModels.PersonViewModel;
import Entity.Gender;
import Entity.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonsFromFileStrategy implements ItemTypeStrategy {
    @Override
    public List<Comparable<?>> getCollection(int collectionLength) {
        var path = "CollectionFiles/persons.json";
        var jsonService = new JsonService<PersonViewModel>();
        List<PersonViewModel> personViewModels = jsonService.readJson(path, PersonViewModel.class);
        List<Comparable<?>> persons = new ArrayList<>();
        for (var PersonViewModel : personViewModels) {
            Person person = null;
            try {
                var personBuilder = new Person.PersonBuilder(Gender.values()[PersonViewModel.getGenderCode()]);
                personBuilder.setLastName(PersonViewModel.getLastName());
                personBuilder.setAge(PersonViewModel.getAge());
                person = personBuilder.build();
            } catch (ArrayIndexOutOfBoundsException e) {
                continue;
            }

            persons.add(person);
            if (persons.size() == collectionLength) return persons;
        }

        return persons;
    }
}
