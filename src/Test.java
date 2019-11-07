import java.util.ArrayList;

public class Test {
    public static void main(String[] args){
        JsonObject total = new JsonObject();
        JsonObject body = new JsonObject();
        JsonObject head = new JsonObject();
        JsonObject data = new JsonObject();
        ArrayList<Person> people = new ArrayList<>();
        people.add(new Person("John", 23, true));
        people.add(new Person("Terry", 16, false));

        JsonArray peopler = new JsonArray("people", people);

        data.put("array", peopler);
        body.put("data", data);
        total.put("head", head);
        total.put("body", body);
        System.out.println(total.getString());
        System.out.println(total.getPrettyString());
    }
}
