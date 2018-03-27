
package chatserver.server;

import java.net.InetAddress;


public class serverClient {
    public InetAddress address;
    public int port;
    public String name;
    public final int ID;
    public int attempt=0;
    
    serverClient(InetAddress add,int port,String name,int id){
        this.ID=id;
        this.port= port;
        this.address=add;
        this.name=name;
        
    }
    
    public int getID(){
        return this.ID;  
    }
    
}
