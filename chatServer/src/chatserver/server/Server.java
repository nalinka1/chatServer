/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserver.server;

import chatserver.functions;
import java.awt.List;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;

/**
 *
 * @author Nalinka Heshan
 */
public class Server implements Runnable {
    
    private int  port;
    DatagramSocket socket;
    boolean running =false;
    Thread run,send,manage,receive,serverListening,append,read;
    
    File f = new File("F:\\java\\projects\\chat server\\textFile\\chat.txt");
    
    private ArrayList<serverClient> clients = new ArrayList<serverClient>();
    
    Server(int port){
        this.port = port;
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        serverWindow sr = new serverWindow();
        sr._main();
        run = new Thread(this,"Server");
        run.start();
    }

    @Override
    public void run() {
        running =true;
        System.out.println("user is connected in port :"+port);
        ManageClient();
        receive();
        serverListening();
        
        
    }

    private void ManageClient(){
        manage= new Thread(){
             public void run(){
                 while(running){
                     
                 }
             }
        };
        manage.start();
    }
    private void serverListening(){
        serverListening= new Thread(){
            public void run(){
                while(running){
                    try {
                        String msg= serverSend();
                        sendToAll(msg);
                    } catch (IOException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        serverListening.start();
    }
    private String serverSend() throws IOException{
        Scanner ser= new Scanner(System.in);
        String servermsg= ser.nextLine();
        setValue("\nServer : "+servermsg);
        servermsg="/m/Server : "+servermsg+"/e/";
        append(servermsg.substring(3,servermsg.indexOf("/e/"))+"\r\n",f);
        return servermsg;
    }
    private void setValue(String message){
        serverWindow.serverDisplay.setText(serverWindow.serverDisplay.getText()+message);
    }

    private void receive(){
        receive = new Thread(){
          public void run(){
              while(running){
                  byte[] data = new byte[1024];
                  DatagramPacket packet = new DatagramPacket(data,data.length);
                  try {
                      
                      socket.receive(packet);
                      process(packet);   
                  } catch (IOException ex) {
                      Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }
          }  
        };
        receive.start();
        
    }
    private void send(final byte[] data,final InetAddress address,final int port){
        send = new Thread("send"){
            public void run(){
                 DatagramPacket packet = new DatagramPacket(data,data.length,address,port);
                try {
                    socket.send(packet);
                    
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
                 
            }
        };
        send.start();
        
        
    }
    private void process(DatagramPacket packet) throws IOException{
        String serverMsg = new String(packet.getData());
        if(serverMsg.startsWith("/c/")){
            int id = Identifier.getID();
            String userName=serverMsg.substring(3,serverMsg.length());
            setValue("\n"+userName+" connected");
            
            clients.add(new  serverClient(packet.getAddress(),packet.getPort(),userName,id));
            int clientIndex=clients.size()-1;
            String show="sevever : "+serverMsg.substring(3,serverMsg.length())+" from : "+clients.get(clientIndex).address+" : "+clients.get(clientIndex).port;
            send(("/c/"+Integer.toString(id)).getBytes(),clients.get(clientIndex).address,clients.get(clientIndex).port);
            read(packet,10,f);
            
            
        }
        else if(serverMsg.startsWith("/m/")){
            setValue("\n"+serverMsg.substring(3,serverMsg.length()));
            sendToAll(serverMsg);
            append(serverMsg.substring(3, serverMsg.indexOf("/e/"))+"\r\n",f);
            System.out.println(serverMsg.substring(3,serverMsg.length()));
            
        }
        else if(serverMsg.startsWith("/d/")){
            int a=(int)Integer.parseInt(String.join("", functions.removeWhitespace(serverMsg.substring(3, 7))));
            int removeMe=-1;
            for(int i=0;i<clients.size();i++){
                if(clients.get(i).ID==a){
                    serverClient c = clients.get(i);
                    removeMe=i;
                    break;
                }
            }
            String msgss="/m/Offline..... ";
            sendToAll(msgss);
            msgss="/m/"+String.join("",clients.get(removeMe).name);
            sendToAll(msgss);
            String m =msgss.substring(3,msgss.length())+" offline.....";
            System.out.println(m);
            append(m,f);
            setValue("\n"+m);
            clients.remove(removeMe);

        }
        else{
            System.out.println("error : ");
            
        }
    }
    public void sendToAll(String st){
        for(int i = 0;i<clients.size();i++){
            serverClient client = clients.get(i);
            send(st.getBytes(),client.address,client.port);
        }
    }
        void append(String s,File f) throws IOException{
            append = new Thread(){
            public void run(){          
                try {             
                    byte[] bt= s.getBytes();
                    FileOutputStream fos=new FileOutputStream(f,true);
                    fos.write(bt);
                    fos.close();
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    
                } finally {
                    
                }
            }
        };
        append.start();
        
    }
     public void read(DatagramPacket packet,int threshold,File f) throws FileNotFoundException, IOException{
        
        read = new Thread(){
            public void run(){
                RandomAccessFile raf = null;
                FileInputStream fis =null;
                try {
                    int line =10;
                    
                    try {
                        raf = new RandomAccessFile(f, "r");
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    fis = new FileInputStream(f);
                    byte buf[] = null;
                    try {
                        buf = new byte[((int)raf.length())];
                    } catch (IOException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    raf.seek(raf.length());
                    int character = fis.read(buf);
                    int newLinePosition = 0;
                    while (character != -1) {
                        for (int i = 0; i < character; i++) {
                            if (buf[i] == '\n')
                                newLinePosition = i;
                        }
                        character = raf.read(buf);
                    }
                    System.out.println("last new line position: " + newLinePosition);
                    raf.seek((newLinePosition+1));
                    String st = new String(buf);
                    send(("/m/"+st+"/e/").getBytes(),packet.getAddress(),packet.getPort());
                    
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        fis.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        read.start();
        
        
        
    }
    
}
