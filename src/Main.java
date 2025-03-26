//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import Entity.Animal;
import Entity.Gender;
import Entity.Person;
import Sorting.InsertionSort;
import Customlist.CustomArrayList;
import java.io.PrintStream;
import java.util.Objects;
import java.util.stream.Stream;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        Animal animal1 = (new Animal.AnimalBuilder("Dog")).build();
        Animal animal2 = (new Animal.AnimalBuilder("Cat")).build();
        CustomArrayList<Animal> animals = new CustomArrayList();
        animals.add(animal1);
        animals.add(animal2);
        Person person1 = (new Person.PersonBuilder(Gender.FEMALE)).build();
        Person person2 = (new Person.PersonBuilder(Gender.MALE)).build();
        CustomArrayList<Person> persons = new CustomArrayList();
        persons.add(person1);
        persons.add(person2);
        Stream var10000 = animals.stream();
        PrintStream var10001 = System.out;
        Objects.requireNonNull(var10001);
        var10000.forEach(var10001::println);
        System.out.println();
        var10000 = persons.stream();
        var10001 = System.out;
        Objects.requireNonNull(var10001);
        var10000.forEach(var10001::println);
        System.out.println();
        InsertionSort.insertionSort(animals);
        InsertionSort.insertionSort(persons);
        var10000 = animals.stream();
        var10001 = System.out;
        Objects.requireNonNull(var10001);
        var10000.forEach(var10001::println);
        System.out.println();
        var10000 = persons.stream();
        var10001 = System.out;
        Objects.requireNonNull(var10001);
        var10000.forEach(var10001::println);
        System.out.println();
    }
}
