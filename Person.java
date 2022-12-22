public class Person {
    private String name;
    private int age;
    private String activity;
    public Person(String name, int age, String activity) {
        this.name = name;
        this.age = age;
        this.activity = activity;
    }
    public String getName() {
        return this.name;
    }
    public int getAge() {
        return this.age;
    }
    public String getActivity() {
        return this.activity;
    }
    public static void main(String[] args) {
        Person John = new Person("John", 17, "running");
        System.out.println(John.getAge());
    }
}