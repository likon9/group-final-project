package data.services;

import data.veiwmodels.*;
import entity.*;

public class MappingService implements Mapping {
    public Animal AnimalViewModelToAnimal(AnimalViewModel animalVM){
        Animal animal;
        var animalBuilder = new Animal.AnimalBuilder(animalVM.getSpecies());
        animalBuilder.setId(animalVM.getId());
        animalBuilder.setHasFur(animalVM.isHasFur());
        var colorCode = animalVM.getEyesColorCode();
        animalBuilder.setEyeColor(AnimalEyeColor.values().length > colorCode ?
                AnimalEyeColor.values()[colorCode] : null);
        animal = animalBuilder.build();
        return animal;
    }

    @Override
    public Barrel BarrelViewModelToBarrel(BarrelViewModel barrelVM) {
        Barrel barrel;
        var barrelBuilder = new Barrel.BarrelBuilder(barrelVM.getVolume());
        barrelBuilder.setStorageMaterial(barrelVM.getStoredContents());
        var bodyMaterialCode = barrelVM.getBodyMaterialCode();
        barrelBuilder.setManufacturingMaterial(BarrelMaterial.values().length > bodyMaterialCode ?
                BarrelMaterial.values()[bodyMaterialCode] : null);
        barrel = barrelBuilder.build();
        return barrel;
    }

    @Override
    public Person PersonViewModelToPerson(PersonViewModel personVM) {
        Person person;
        var genderCode = personVM.getGenderCode();
        var gender = Gender.values().length > genderCode ?
                Gender.values()[genderCode] : null;
        var personBuilder = new Person.PersonBuilder(gender);
        personBuilder.setAge(personVM.getAge());
        personBuilder.setLastName(personVM.getLastName());
        person = personBuilder.build();
        return person;
    }
}
