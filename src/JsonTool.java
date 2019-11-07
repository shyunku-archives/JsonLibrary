public class JsonTool {
    public String toPrettyFormat(String json){
        String newString = new String("");
        int indent = 0;
        boolean newLine = false;

        for(int i=0;i<json.length();i++){
            char selected = json.charAt(i);
            char next = i==json.length()-1?'\0':json.charAt(i+1);
            //PreInsert
            if(newLine){
                for(int j=0;j<indent;j++)
                    newString += '\t';
            }
            switch (selected){
                case ':':
                    newString += ' ';
                    break;
            }

            //Insert
            newString += selected;

            //PostInsert
            newLine = false;
            switch (selected){
                case '{':
                    indent++;
                    newString += '\n';
                    newLine = true;
                    break;
                case '}':
                    if(next==','){
                        i++;
                        newString += ',';
                    }
                    indent--;
                    newString += '\n';
                    newLine = true;
                    break;
                case ',':
                    if(next=='}'){
                        indent--;
                    }
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

    public String toStringFormat(String str){
        return "\""+str+"\"";
    }
}
