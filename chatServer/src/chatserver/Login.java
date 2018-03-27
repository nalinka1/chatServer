
package chatserver;

import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Login extends javax.swing.JFrame {
    
    String Uname;           //user name
    String Upassword;       //user password
    String UIpaddress;      //user Ip
    int Uport;              //user port
    
    public static int checkCon=0;
    


    public Login() {
        initComponents();
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        setResizable(false);                //size is fixed
        setBounds(600,100,400,510);         //setting window size and position
        setTitle("                     Login");     //title name
        
        //setting the reading of window
        
    
        
        
    }
    
    void login(String name,String password,String Ip,int port){
        dispose();
        new Client(Uname,UIpaddress,Uport)._main();
       // new Client(Uname,UIpaddress,Uport);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jport = new javax.swing.JLabel();
        jiptxt = new javax.swing.JTextField();
        jname = new javax.swing.JLabel();
        jpasswordtxt = new javax.swing.JPasswordField();
        jpassword = new javax.swing.JLabel();
        jporttxt = new javax.swing.JTextField();
        jnametxt = new javax.swing.JTextField();
        jip1 = new javax.swing.JLabel();
        jsignup = new javax.swing.JButton();
        jloginbt1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jport.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jport.setText("Port");
        getContentPane().add(jport, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, -1, -1));

        jiptxt.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jiptxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jiptxtActionPerformed(evt);
            }
        });
        getContentPane().add(jiptxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 200, 40));

        jname.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jname.setText("Name");
        getContentPane().add(jname, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, -1, -1));

        jpasswordtxt.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jpasswordtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jpasswordtxtActionPerformed(evt);
            }
        });
        getContentPane().add(jpasswordtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 200, 40));

        jpassword.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jpassword.setText("Password");
        getContentPane().add(jpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, -1, -1));

        jporttxt.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jporttxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jporttxtActionPerformed(evt);
            }
        });
        jporttxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jporttxtKeyPressed(evt);
            }
        });
        getContentPane().add(jporttxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 320, 200, 40));

        jnametxt.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jnametxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jnametxtActionPerformed(evt);
            }
        });
        getContentPane().add(jnametxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 200, 40));

        jip1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jip1.setText("IP Address");
        getContentPane().add(jip1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, -1, -1));

        jsignup.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jsignup.setText("Sign up");
        jsignup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jsignupActionPerformed(evt);
            }
        });
        getContentPane().add(jsignup, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 410, 140, 40));

        jloginbt1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jloginbt1.setText("Login");
        jloginbt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jloginbt1ActionPerformed(evt);
            }
        });
        jloginbt1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jloginbt1KeyPressed(evt);
            }
        });
        getContentPane().add(jloginbt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, 150, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jiptxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jiptxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jiptxtActionPerformed

    private void jpasswordtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jpasswordtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jpasswordtxtActionPerformed

    private void jporttxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jporttxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jporttxtActionPerformed

    private void jnametxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jnametxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jnametxtActionPerformed

    private void jsignupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jsignupActionPerformed
        setVisible(false);
        signUp.signupMain();
    }//GEN-LAST:event_jsignupActionPerformed

    private void jloginbt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jloginbt1ActionPerformed
            Uname=jnametxt.getText().toLowerCase();
            //Upassword=jpasswordtxt.getText();
            UIpaddress=jiptxt.getText();
            //Uport = Integer.parseInt(jporttxt.getText());
            //Uname="heshan";
            Upassword="heshan";
            //UIpaddress="localhost";
            Uport=8192;
        
            login(Uname,Upassword,UIpaddress,Uport); //callig login function
    }//GEN-LAST:event_jloginbt1ActionPerformed

    private void jloginbt1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jloginbt1KeyPressed
        // mistakely generated code
    }//GEN-LAST:event_jloginbt1KeyPressed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            Uname=jnametxt.getText().toLowerCase();
            //Upassword=jpasswordtxt.getText();
            UIpaddress=jiptxt.getText();
            //Uport = Integer.parseInt(jporttxt.getText());
            //Uname="heshan";
            Upassword="heshan";
            //UIpaddress="localhost";
            Uport=8192;
        
            login(Uname,Upassword,UIpaddress,Uport); //callig login function
            
        }
    }//GEN-LAST:event_formKeyPressed

    private void jporttxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jporttxtKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            Uname=jnametxt.getText().toLowerCase();
            //Upassword=jpasswordtxt.getText();
            UIpaddress=jiptxt.getText();
            //Uport = Integer.parseInt(jporttxt.getText());
            //Uname="heshan";
            Upassword="heshan";
            //UIpaddress="localhost";
            Uport=8192;
        
            login(Uname,Upassword,UIpaddress,Uport); //callig login function
            
        }
    }//GEN-LAST:event_jporttxtKeyPressed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jip1;
    private javax.swing.JTextField jiptxt;
    private javax.swing.JButton jloginbt1;
    private javax.swing.JLabel jname;
    private javax.swing.JTextField jnametxt;
    private javax.swing.JLabel jpassword;
    private javax.swing.JPasswordField jpasswordtxt;
    private javax.swing.JLabel jport;
    private javax.swing.JTextField jporttxt;
    private javax.swing.JButton jsignup;
    // End of variables declaration//GEN-END:variables
}
