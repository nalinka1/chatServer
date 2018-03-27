/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserver;

import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Nalinka Heshan
 */
public class Client extends javax.swing.JFrame implements Runnable{

    private static String name;
    private static String IP;
    private static int port;
    
    
    private String Msg;
    
    DatagramSocket socket;
    InetAddress ip;
    
    private Thread send,listen,run;
    
    private boolean running =false;
    
    private String myID;
    
    public Client(String name,String address,int Port) {
        initComponents();
        this.name=name;
        this.IP=address;
        this.port=Port;
        setBounds(550,70,620,880);         //setting window size and position
        setTitle("user : "+name);     //title name
        addWindowListener(new java.awt.event.WindowAdapter() {                                                                                                                 
            public void windowClosing(java.awt.event.WindowEvent evt) {
                send(("/d/"+myID).getBytes());
            }
        });
        boolean connection = openConnection(address);
        if(!connection){
            JOptionPane.showMessageDialog(null, "Connection lost..!!!");
        }
        if(Login.checkCon==1){
            Login.checkCon=0;
            System.out.println("attempt to connection "+address+" : "+port+" User : "+name);
            String connect="/c/"+name;
            send(connect.getBytes());
            run= new Thread(this,"running");
            run.start();
            
            
        }
        
        System.out.println("checkCOn : "+Login.checkCon);
        Login.checkCon++;
    }

    public void run(){
        running=true;
        Listen();   
    }
    private void Listen(){
        listen= new Thread("Listen"){
            public void run(){
                while(running){
                    String receiveMsg=receive();
                    if(receiveMsg.startsWith("/c/")){
                        myID= receiveMsg.substring(3, receiveMsg.length());
                        System.out.println("id is received : "+myID);
                    }
                    else if(receiveMsg.startsWith("/m/")){
                        System.out.println("we are above console");
                        console(receiveMsg.substring(3,receiveMsg.indexOf("/e/")));
                        System.out.println("client msg is recieved "+receiveMsg);
                    }
                    else{
                        console("some data has come");
                        System.out.println(receiveMsg);
                    }
                }
            }
        };
        listen.start();
    }
    private boolean openConnection(String address){
        try {
            socket = new DatagramSocket();
            ip = InetAddress.getByName(address);
            
        }
        catch (SocketException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }catch (UnknownHostException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        
        return true;
    }
    private boolean console(String print){
        if(functions.checkWhitespace(print)){
            return false;
        }
        else{
            System.out.println("im here");
            jmsgshow.setText(jmsgshow.getText()+print+"\n");
            return true;
        }
    }
    private String receive(){
        byte[] data = new byte[1024];
        DatagramPacket packet = new DatagramPacket(data,data.length); 
        try {
            socket.receive(packet);
            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        String msg2 = new String(packet.getData());
        return msg2;
    }
    private void send(final byte[] data){
        send = new Thread("send"){
            public void run(){
                try {
                    DatagramPacket packet = new DatagramPacket(data,data.length,ip ,port);
                    socket.send(packet);
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        send.start();
        
    }


 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jsendbt = new javax.swing.JButton();
        jsendmsg = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jmsgshow = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jsendbt.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jsendbt.setText("Send");
        jsendbt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jsendbtActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        getContentPane().add(jsendbt, gridBagConstraints);

        jsendmsg.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jsendmsg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jsendmsgKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jsendmsg, gridBagConstraints);

        jmsgshow.setEditable(false);
        jmsgshow.setColumns(20);
        jmsgshow.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jmsgshow.setRows(5);
        jScrollPane1.setViewportView(jmsgshow);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 550;
        gridBagConstraints.ipady = 750;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        getContentPane().add(jScrollPane1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jsendbtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jsendbtActionPerformed
        Msg=name+" : "+jsendmsg.getText();
        String msgs= jsendmsg.getText();
        jsendmsg.setText("");
        if (!functions.checkWhitespace(msgs)){
            //console(Msg);
            Msg="/m/"+Msg+"/e/";
            send(Msg.getBytes());
            }
            else{
                return;
            }
    }//GEN-LAST:event_jsendbtActionPerformed

    private void jsendmsgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jsendmsgKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            Msg=name+" : "+jsendmsg.getText();
            String msgs=jsendmsg.getText();
            jsendmsg.setText("");
            if (!functions.checkWhitespace(msgs)){
                //console(Msg);
                Msg="/m/"+Msg+"/e/";
                send(Msg.getBytes());
            }  
            else{             
                return;
            }
          
            
        }
    }//GEN-LAST:event_jsendmsgKeyPressed
    public static void _main(){
        
         try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
         java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {  
                new Client(name,IP,port).setVisible(true);
                
            }
        });
    }
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Client(name,IP,port).setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jmsgshow;
    private javax.swing.JButton jsendbt;
    private javax.swing.JTextField jsendmsg;
    // End of variables declaration//GEN-END:variables
}
