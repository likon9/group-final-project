package Data.VeiwModels;

public class PeronViewModel {
    private int genderCode;
    private int age;
    private String lastName;

    public PeronViewModel(int genderCode, int age, String lastName) {
        this.genderCode = genderCode;
        this.age = age;
        this.lastName = lastName;
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
}
