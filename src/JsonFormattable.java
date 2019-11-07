import com.sun.org.apache.xpath.internal.operations.Bool;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;

public abstract class JsonFormattable<gene> {
    private final String stringType = "java.lang.String";
    private final String intType = "int";
    private final String doubleType = "double";
    private final String boolType = "boolean";
    private final String listType = "java.util.ArrayList";

    public JsonObject toJson(String key){
        JsonObject object = new JsonObject();
        Field[] fields = this.getClass().getFields();
        try {
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                Type type = field.getGenericType();
                String typeName = type.getTypeName();
                String valueName = field.getName();

                typeName = getTypeWithoutGeneric(typeName);

                switch(typeName){
                    case stringType:
                        String string__ = (String) field.get(this);
                        object.put(valueName, string__);
                        break;
                    case intType:
                        int integer__ = (int) field.get(this);
                        object.put(valueName, integer__);
                        break;
                    case doubleType:
                        double double__ = (double) field.get(this);
                        object.put(valueName, double__);
                        break;
                    case boolType:
                        boolean bool__ = (boolean) field.get(this);
                        object.put(valueName, bool__);
                        break;
                    case listType:
                        ArrayList<?> list__ = (ArrayList<?>) field.get(this);
                        if(!list__.isEmpty()) {
                            String arrayType = list__.get(0).getClass().getTypeName();
                            System.out.println(arrayType);
                        }
                        break;
                }

            }
        }catch(IllegalAccessException e){
            e.printStackTrace();
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
}
