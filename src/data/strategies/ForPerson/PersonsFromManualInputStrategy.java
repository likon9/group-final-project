package data.strategies.ForPerson;

import data.services.ManualInputDataHelper;
import data.services.MappingService;
import data.strategies.ItemTypeStrategy;
import data.veiwmodels.PersonViewModel;
import entity.Gender;
import entity.Item;
import entity.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PersonsFromManualInputStrategy implements ItemTypeStrategy {
    @Override
    public List<Item> getCollection(int collectionLength) {
        ManualInputDataHelper manualInputDataHelper = new ManualInputDataHelper();
        MappingService mappingService = new MappingService();
        List<PersonViewModel> personViewModels = new ArrayList<>();


        while (collectionLength > 0) {

            manualInputDataHelper.showMessageToUserInConsole("Please enter data to create Person.\n");

            List<String> userData = manualInputDataHelper.getUserData(
                    "gender code " + Arrays.stream(Gender.values()).map(gender -> gender.ordinal() + " for " + gender).toList(),
                    "age",
                    "lastName");

            int genderCode = 0;
            int age = 0;
            String lastName = null;

            try {
                genderCode = Integer.parseInt(Objects.requireNonNull(userData.get(0)));
                age = Integer.parseInt(Objects.requireNonNull(userData.get(1)));
                lastName = userData.get(2);
            } catch (NumberFormatException e) {
                manualInputDataHelper.showMessageToUserInConsole("\nYou have entered invalid data: " + e.getMessage());
            }

            var personVM = new PersonViewModel(genderCode, age, lastName);
            personViewModels.add(personVM);

            manualInputDataHelper.showMessageToUserInConsole("\nNext data is added for validation: " + personVM);

            collectionLength--;
        }

        return personViewModels.stream()
                .map(mappingService::PersonViewModelToPerson)
                .filter(Person::isValid)
                .map(a -> (Item) a)
                .toList();
    }
}
