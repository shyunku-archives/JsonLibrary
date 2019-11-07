import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;

public class JsonArray<type> {
    private final String stringType = "java.lang.String";
    private final String intType = "int";
    private final String doubleType = "double";
    private final String boolType = "boolean";
    private final String listType = "java.util.ArrayList";
    private final String JsonObjectType = "JsonObject";

    private String key = "";
    private ArrayList<type> typeList;

    public JsonArray(String key, ArrayList<type> list){
        this.key = key;
        this.typeList = (ArrayList<type>)list.clone();
    }

    public String getString(){
        String generic = typeList.get(0).getClass().getTypeName();

        String result = "[";
        Iterator<type> iter = typeList.iterator();
        while(iter.hasNext()){
            type t = (type) iter.next();
            switch(generic){
                case stringType:
                    result += "\""+(String)t+"\"";
                    break;
                case intType:
                    result += (Integer)t;
                    break;
                case doubleType:
                    result += (Double)t;
                    break;
                case boolType:
                    result += (Boolean)t;
                    break;
                case listType:
                    ArrayList<?> list__ = (ArrayList<?>) t;
                    if(!list__.isEmpty()) {
                        result += new JsonArray<>("array", list__).getString();
                    }
                    break;
                case JsonObjectType:
                    result += ((JsonObject)t).getString();
                    break;
                default:
                    String className = typeList.get(0).getClass().getTypeName();
                    Class<?> superclass = typeList.get(0).getClass().getSuperclass();

                    if(superclass.getTypeName().equals("JsonFormattable")) {
                        JsonFormattable format = (JsonFormattable) t;
                        result += format.toJson(className).getString();
                    }
            }
            if(iter.hasNext())result+=",";
        }
        result += "]";

        return result;
    }

    private JsonTool tool = new JsonTool();
}
