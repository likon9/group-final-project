package Entity;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

public class Person implements Serializable, Comparable<Person> {

    private Gender gender ;
    private int age;
    private String lastName;

    public Person(PersonBuilder personBuilder) {
        this.gender = personBuilder.gender;
        this.age = personBuilder.age;
        this.lastName = personBuilder.lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && gender == person.gender && Objects.equals(lastName, person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gender, age, lastName);
    }

    @Override
    public String toString() {
        return "Person{" +
                "gender=" + gender +
                ", age=" + age +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        Comparator<Person> comparator = Comparator.
                comparing(Person::getGender).
                thenComparing(Person::getAge).
                thenComparing(Person::getLastName);

        return comparator.compare(this, o);
    }

    public static class PersonBuilder {
        private Gender gender ;
        private int age;
        private String lastName;

        public PersonBuilder(Gender gender) {
            this.gender = gender;
            this.age = 20;
            this.lastName = "";
        }

        public PersonBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        public PersonBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }
}
