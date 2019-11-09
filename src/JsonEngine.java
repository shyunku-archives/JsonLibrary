import java.lang.reflect.Type;
import java.util.Iterator;

public class JsonEngine {
    public static final String stringType = "java.lang.String";
    public static final String intType = "java.lang.Integer";
    public static final String doubleType = "java.lang.Double";
    public static final String boolType = "java.lang.Boolean";
    public static final String JsonObjectType = "JsonObject";
    public static final String JsonArrayType = "JsonArray";


    public boolean isIterableClass(Class c){
        Class class_branch = c;
        do{
            Class[] interfaces = class_branch.getInterfaces();
            for(Class inter : interfaces) {
                if (inter.equals(Iterable.class))
                    return true;
                boolean isChecked = isIterableClass(inter);
                if(isChecked)return true;
            }
            class_branch = class_branch.getSuperclass();
            print("");
        }while(class_branch!=null);
        return false;
    }

    public String toPrettyFormat(String json){
        String newString = new String("");
        int indent = 0;
        boolean newLine = false;

        for(int i=0;i<json.length();i++){
            char selected = json.charAt(i);
            char next = i==json.length()-1?'\0':json.charAt(i+1);
            //PreInsert
            switch (selected){
                case ':':
                    //newString += ' ';
                    break;
                case '}':
                case ']':
                    newString += '\n';
                    newLine = true;
                    indent--;
                    break;
            }

            if(newLine){
                for(int j=0;j<indent;j++)
                    newString += '\t';
            }

            //System.out.println("["+selected+"] : "+indent+", "+newLine);

            //Insert
            newString += selected;

            //PostInsert
            newLine = false;
            switch (selected){
                case '{':
                case '[':
                    indent++;
                    newString += '\n';
                    newLine = true;
                    break;
                case '}':
                case ']':
                    if(next == ','){
                        i++;
                        newString += next;
                        newLine = true;
                        newString += '\n';
                    }
                    break;
                case ',':
                    newString += '\n';
                    newLine = true;
                    break;
                case ':':
                    newString += ' ';
                    break;
            }
        }

        return newString;
    }

    public void print(Object o){
        System.out.println(o);
    }

    public String toStringFormat(String str){
        return "\""+str+"\"";
    }
}
