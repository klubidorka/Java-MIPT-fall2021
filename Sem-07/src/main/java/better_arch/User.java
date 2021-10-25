package better_arch;

public class User {
    private static int nextPossibleId = 0;
    private Integer id;
    private String name;
    private Integer age;

    User() {
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
        this.id = nextPossibleId++;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
