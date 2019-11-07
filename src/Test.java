import java.util.ArrayList;

public class Test {
    public static void main(String[] args){
        JsonObject total = new JsonObject();
        JsonObject body = new JsonObject();
        JsonObject head = new JsonObject();
        JsonObject data = new JsonObject();
        ArrayList<Person> people = new ArrayList<>();
        Person per = new Person("John", 23, true);
        data.put("pers", per.toJson("a"));
       // people.add(new Person("Terry", 16, false));




        body.put("data", data);
        total.put("head", head);
        total.put("body", body);
        System.out.println(total.getPrettyString());
    }
}
