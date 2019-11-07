import javax.lang.model.type.NullType;
import java.util.HashMap;
import java.util.Iterator;

public class JsonObject {
    private HashMap<String, String> stringBundle = new HashMap<>();
    private HashMap<String, Integer> intBundle = new HashMap<>();
    private HashMap<String, Double> doubleBundle = new HashMap<>();
    private HashMap<String, Boolean> boolBundle = new HashMap<>();
    private HashMap<String, JsonArray> arrayBundle = new HashMap<>();
    private HashMap<String, JsonObject> objectBundle = new HashMap<>();

    public void put(String key, String data){
        stringBundle.put(key, data);
    }

    public void put(String key, int data){
        intBundle.put(key, data);
    }

    public void put(String key, double data){
        doubleBundle.put(key, data);
    }

    public void put(String key, boolean data){
        boolBundle.put(key, data);
    }

    public void put(String key, JsonArray data){
        arrayBundle.put(key, data);
    }

    public void put(String key, JsonObject data){
        objectBundle.put(key, data);
    }

    public String getString(){
        String result = "{";
        Iterator<String> keys = stringBundle.keySet().iterator();
        while(keys.hasNext()){
            String key = (String)keys.next();
            result += tool.toStringFormat(key) +":"+tool.toStringFormat(stringBundle.get(key));
            result += ",";
        }

        keys = intBundle.keySet().iterator();
        while(keys.hasNext()){
            String key = (String)keys.next();
            result += tool.toStringFormat(key) +":"+intBundle.get(key);
            result += ",";
        }

        keys = doubleBundle.keySet().iterator();
        while(keys.hasNext()){
            String key = (String)keys.next();
            result += tool.toStringFormat(key) +":"+doubleBundle.get(key);
            result += ",";
        }

        keys = boolBundle.keySet().iterator();
        while(keys.hasNext()){
            String key = (String)keys.next();
            result += tool.toStringFormat(key) +":"+boolBundle.get(key);
            result += ",";
        }

        keys = arrayBundle.keySet().iterator();
        while(keys.hasNext()){
            String key = (String)keys.next();
            result += tool.toStringFormat(key) +":"+arrayBundle.get(key);
            result += ",";
        }

        keys = objectBundle.keySet().iterator();
        while(keys.hasNext()){
            String key = (String)keys.next();
            JsonObject json = objectBundle.get(key);
            result += tool.toStringFormat(key) +":"+json.getString();
            result += ",";
        }
        result += "}";

        return result;
    }

    public String getPrettyString(){
        return tool.toPrettyFormat(getString());
    }

    private JsonTool tool = new JsonTool();
}
