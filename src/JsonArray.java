import java.util.ArrayList;
import java.util.Iterator;

public class JsonArray<type> {
    private String key = "";
    private Iterable<type> typeList;

    public JsonArray(String key, Iterable<type> list){
        this.key = key;
        this.typeList = (Iterable<type>)list;
    }

    public String getString(){
        Iterator<type> iter_temp = typeList.iterator();
        String generic = null;
        if(iter_temp.hasNext())generic = iter_temp.next().getClass().getTypeName();

        String result = "[";
        Iterator<type> iter = typeList.iterator();
        while(iter.hasNext()){
            type t = (type) iter.next();
            switch(generic){
                case JsonEngine.stringType:
                    result += "\""+(String)t+"\"";
                    break;
                case JsonEngine.intType:
                    result += (Integer)t;
                    break;
                case JsonEngine.doubleType:
                    result += (Double)t;
                    break;
                case JsonEngine.boolType:
                    result += (Boolean)t;
                    break;
                case JsonEngine.JsonObjectType:
                    result += ((JsonObject)t).getString();
                    break;
                default:
                    break;
            }
            if(iter.hasNext())result+=",";
        }
        result += "]";

        return result;
    }

    private JsonEngine e = new JsonEngine();
}
