/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserver.server;

/**
 *
 * @author Nalinka Heshan
 */
public class serverMain {
    
    private int port;
    public Server server;
    
    serverMain(int port){
        this.server=new Server(port);
    }
    public static void main(String[] args) {
        int port;
        if(args.length!=1){
            System.out.println("we are here");
            
        }
        port=Integer.parseInt(args[0]);
        new serverMain(port);
    }
}
