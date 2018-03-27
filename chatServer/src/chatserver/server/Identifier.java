
package chatserver.server;

import java.util.ArrayList;
import java.util.Collections;


public class Identifier {
    private static ArrayList<Integer> ids = new ArrayList<>();
    private static final int range=10000;
    private static int index=0;
    
    static{
        for(int i = 0; i<range;i++){
            ids.add(i);
        }
        Collections.shuffle(ids);
    }
    public static int getID(){
        if(index>ids.size()-1)index=0;
        return ids.get(index++);
    }
    
}
