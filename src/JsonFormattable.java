import com.sun.org.apache.xpath.internal.operations.Bool;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;

public abstract class JsonFormattable {
    public JsonObject toJson(){
        JsonObject object = new JsonObject();
        Field[] fields = this.getClass().getFields();
        try {
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                Type type = field.getGenericType();
                String valueName = field.getName();
                object.put(valueName, field.get(this));
            }
        }catch(IllegalAccessException ex){
            ex.printStackTrace();
        }

        return object;
    }

    private String getTypeWithoutGeneric(String typeName){
        int ind = typeName.indexOf("<");
        if(ind!=-1){
            return typeName.substring(0, ind);
        }
        return typeName;
    }

    private JsonEngine e = new JsonEngine();
}
