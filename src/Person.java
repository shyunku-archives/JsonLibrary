import java.util.ArrayList;

public class Person extends JsonFormattable{
    //TEST
    public String name;
    public int age;
    public boolean isMarried;
    public ArrayList<Integer> ints;

    public Person(String name, int age, boolean isMarried) {
        this.name = name;
        this.age = age;
        this.isMarried = isMarried;
        ints = new ArrayList<>();
        ints.add(5);
        ints.add(7);
        ints.add(9);
        ints.add(11);
    }
}
