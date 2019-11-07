import java.util.ArrayList;
import java.util.Iterator;

public class JsonArray {
    private String key = "";
    private ArrayList<JsonObject> typeList;

    public JsonArray(String key, ArrayList<JsonObject> list){
        this.key = key;
        this.typeList = list;
    }

    public String getString(){
        String result = "{";
        for(int i=0;i<typeList.size();i++){
//            result += tool.toStringFormat(key) +":"+toStringFormat(stringBundle.get(key));
//            result += ",";
        }
        result += "}";

        return result;
    }

    private JsonTool tool = new JsonTool();
}
