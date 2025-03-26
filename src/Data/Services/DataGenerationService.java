package Data.Services;

import Data.VeiwModels.AnimalViewModel;
import Data.VeiwModels.BarrelViewModel;
import Data.VeiwModels.PersonViewModel;
import Entity.BarrelMaterial;
import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class DataGenerationService {
    private final Faker faker;
    private static final String[] CONTENTS = { null, "\t", "", "Water", "Oil", "Grain",
            "Wine", "Honey", "Whiskey", "Beer", "Fuel"};

    public DataGenerationService(long seedNumber, String locale) {
        var seed = new Random(seedNumber);
        var localeObj = new Locale(locale);
        faker = new Faker(localeObj, seed);
    }

    public List<BarrelViewModel> getBarrelVModels(int listLength) {
        var barrels = new ArrayList<BarrelViewModel>();
        for (int i = 0; i < listLength; i++) {
            String contents = CONTENTS[faker.random().nextInt(CONTENTS.length)];
            double volume = faker.number().randomDouble(2, -10, 500);
            var maxMaterialCodeValue = BarrelMaterial.values().length;
            int errorCodes = 1;
            int bodyMaterialCode = faker.number().numberBetween(0, maxMaterialCodeValue + errorCodes);
            var barrelVM = new BarrelViewModel(volume, contents, bodyMaterialCode);
            barrels.add(barrelVM);
        }

        return barrels;
    }

    public List<AnimalViewModel> getAnimalVModels(int listLength) {
        var animals = new ArrayList<AnimalViewModel>();
        for (int i = 0; i < listLength; i++) {
            var animalProvider = faker.animal();
            int id = faker.number().numberBetween(-10, Integer.MAX_VALUE - 1);
            int maxEyeColorCodeValue = BarrelMaterial.values().length;
            int errorCodes = 1;
            int eyeColorCodeValue = faker.number().numberBetween(0, maxEyeColorCodeValue + errorCodes);
            boolean hasFur = faker.bool().bool();
            var animalVM = new AnimalViewModel(id, animalProvider.name(), eyeColorCodeValue, hasFur);
            animals.add(animalVM);
        }

        return animals;
    }

    public List<PersonViewModel> getPersonVModels(int listLength) {
        var persons = new ArrayList<PersonViewModel>();
        for (int i = 0; i < listLength; i++) {
            var lastName = faker.name().lastName();
            var age = faker.number().numberBetween(-10, 110);
            var genderCode = faker.number().numberBetween(0, 2);
            var personVM = new PersonViewModel(genderCode, age, lastName);
            persons.add(personVM);
        }

        return persons;
    }
}
