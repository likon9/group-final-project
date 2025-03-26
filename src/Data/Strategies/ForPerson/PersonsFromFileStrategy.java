package Data.Strategies.ForPerson;

import Data.Strategies.ItemTypeStrategy;
import Entity.Gender;
import Entity.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonsFromFileStrategy implements ItemTypeStrategy {
    @Override
    public List<Comparable<?>> getCollection(int collectionLength) {
        List<Comparable<?>> list = new ArrayList<>();
        var personBuilder = new Person.PersonBuilder(Gender.MALE);
        personBuilder.setAge(29);
        personBuilder.setLastName("Shynkevich");
        Person person = personBuilder.build();
        list.add(person);
        return list;
    }
}
