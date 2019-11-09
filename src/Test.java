import java.util.ArrayList;

public class Test {
    public static void main(String[] args){
        JsonObject total = new JsonObject();
        total.put("session-id", "ajfj2930fa-3fh-ajx03hall");
        total.put("code-tag", -1110847);
        total.put("isOnLoad", false);
        JsonObject user = new JsonObject();
        user.put("id", "lastpane");
        user.put("pw", "ari2018!");

        JsonObject data = new JsonObject();
        data.put("uuid", "18047345788");

        Person p1 = new Person("John", 23, false);
        data.put("p1", p1);


        data.put("user", user);
        total.put("data", data);
        System.out.println(total.getString());
        System.out.println(total.getPrettyString());
    }
}
