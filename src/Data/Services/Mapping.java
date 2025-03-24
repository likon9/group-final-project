package Data.Services;

import Data.VeiwModels.*;
import Entity.*;

public interface Mapping {
    Animal AnimalViewModelToAnimal(AnimalViewModel animalVM);
    Barrel BarrelViewModelToBarrel(BarrelViewModel barrelVM);
    Person PersonViewModelToPerson(PersonViewModel PersonVM);
}
