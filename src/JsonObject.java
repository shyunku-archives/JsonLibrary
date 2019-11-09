import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class JsonObject {
    private LinkedHashMap<String, Object> dataBundle = new LinkedHashMap<>();

    public void put(String key, Object data){
        dataBundle.put(key, data);
    }

    public String getString(){
        StringBuilder result = new StringBuilder("{");
        Iterator<String> keys = dataBundle.keySet().iterator();
        while(keys.hasNext()){
            Object object = keys.next();
            String key = (String)object;
            Object val = dataBundle.get(key);
            Class class_ = val.getClass();
            String typeName = class_.getTypeName();

            //System.out.println("key : "+key+", type : "+typeName);

            switch(typeName){
                case JsonEngine.stringType:
                    result.append(e.toStringFormat(key))
                            .append(":")
                            .append(e.toStringFormat(
                                    getObjectString(String.class, val)
                            ));
                    break;
                case JsonEngine.intType:
                    result.append(e.toStringFormat(key))
                            .append(":")
                            .append(
                                    getObjectString(Integer.class, val)
                            );
                    break;
                case JsonEngine.doubleType:
                    result.append(e.toStringFormat(key))
                            .append(":")
                            .append(
                                    getObjectString(Double.class, val)
                            );
                    break;
                case JsonEngine.boolType:
                    result.append(e.toStringFormat(key))
                            .append(":")
                            .append(
                                    getObjectString(Boolean.class, val)
                            );
                    break;
                case JsonEngine.JsonArrayType:
                    e.print("aagh");
                    result.append(e.toStringFormat(key))
                            .append(":")
                            .append(((JsonArray)val).getString());
                    break;
                case JsonEngine.JsonObjectType:
                    result.append(e.toStringFormat(key))
                            .append(":")
                            .append(((JsonObject) val).getString());
                    break;
                default:
                    e.print("Default type : "+typeName);
                    Class<?> superclass = class_.getSuperclass();
                    if(superclass.getTypeName().equals("JsonFormattable")) {
                        JsonFormattable format = (JsonFormattable) val;
                        result.append(e.toStringFormat(key))
                                .append(":")
                                .append(format.toJson().getString());
                    }else{
                        if(e.isIterableClass(class_)){
                            JsonArray array = new JsonArray(key, (Iterable)val);
                            result.append(e.toStringFormat(key))
                                    .append(":")
                                    .append(array.getString());
                        }else{
                            //can't handle this (not supported)
                        }
                    }
            }
            if(keys.hasNext()) result.append(",");
        }
        result.append("}");

        return result.toString();
    }

    public <type> String getObjectString(Class<type> typeClass, Object object){
        type obj = typeClass.cast(object);
        return obj.toString();
    }

    public String getPrettyString(){
        return e.toPrettyFormat(getString());
    }

    private JsonEngine e = new JsonEngine();
}
