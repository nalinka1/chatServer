
package chatserver;

import java.util.ArrayList;
public class functions {
    
    public static boolean checkWhitespace(String msg){
        int length = msg.length();
        boolean value =true ;
        for(int i = 0;i<length;i++ ){
            if(!msg.substring(i, i+1).equalsIgnoreCase(" ")){
                value=false;
                return value;
                
            }
            else{
                value=true;
            }
        }
        return value;
        
    }
    public static String removeWhitespace(String input){
        ArrayList<String>cha = new ArrayList<>();
        for(int i=0;i<input.length();i++){
            String sub= input.substring(i,i+1);
            if(!(sub.equalsIgnoreCase(" "))){
                cha.add(sub);
            }
            
        }
        return String.join("", cha);
    }
    
}
