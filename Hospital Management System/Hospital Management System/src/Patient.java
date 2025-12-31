
public class Patient {
    private int id;
    private String name;
    private int age;
    private String gender;

    public Patient(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public Patient(int id, String name, int age, String gender) {
        this(name, age, gender);
        this.id = id;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }

    public String toString() {
        return id + " | " + name + " | Age: " + age + " | Gender: " + gender;
    }
}
