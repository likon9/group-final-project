package data.veiwmodels;

public class PersonViewModel {
    private int genderCode;
    private int age;
    private String lastName;

    public PersonViewModel(int genderCode, int age, String lastName) {
        this.genderCode = genderCode;
        this.age = age;
        this.lastName = lastName;
    }

    public PersonViewModel() {
    }

    public int getGenderCode() {
        return genderCode;
    }

    public int getAge() {
        return age;
    }

    public String getLastName() {
        return lastName;
    }

    public void setGenderCode(int genderCode) {
        this.genderCode = genderCode;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "PersonViewModel{" +
                "genderCode=" + genderCode +
                ", age=" + age +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
