package data.services;

import data.veiwmodels.*;
import entity.*;

public interface Mapping {
    Animal AnimalViewModelToAnimal(AnimalViewModel animalVM);
    Barrel BarrelViewModelToBarrel(BarrelViewModel barrelVM);
    Person PersonViewModelToPerson(PersonViewModel PersonVM);
}
