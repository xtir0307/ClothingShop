/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Model.SQLHandler;
import javax.swing.JOptionPane;

/**
 *
 * @author xtir0
 */
public class Login extends javax.swing.JFrame {

    public final SQLHandler sqlHandler = new SQLHandler();

    /**
     * Creates new form Login1
     */
    public Login() {
        initComponents();
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

        jPanel1 = new javax.swing.JPanel();
        lb_login_26 = new javax.swing.JLabel();
        lb_pass_26 = new javax.swing.JLabel();
        textf_user_26 = new javax.swing.JTextField();
        textf_pass_26 = new javax.swing.JTextField();
        btn_login_26 = new javax.swing.JButton();
        lb_note_26 = new javax.swing.JLabel();
        btn_signup_26 = new javax.swing.JButton();
        lb_user_26 = new javax.swing.JLabel();
        btn_home_26 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Đăng nhập");

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        lb_login_26.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lb_login_26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_login_26.setText("Đăng nhập");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 11;
        gridBagConstraints.ipadx = 365;
        gridBagConstraints.ipady = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(26, 27, 0, 27);
        jPanel1.add(lb_login_26, gridBagConstraints);

        lb_pass_26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lb_pass_26.setText("Mật khẩu: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(29, 113, 0, 0);
        jPanel1.add(lb_pass_26, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 261;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 5, 0, 0);
        jPanel1.add(textf_user_26, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 261;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(29, 5, 0, 0);
        jPanel1.add(textf_pass_26, gridBagConstraints);

        btn_login_26.setBackground(new java.awt.Color(51, 255, 0));
        btn_login_26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_login_26.setText("Đăng nhập");
        btn_login_26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_login_26ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(28, 23, 0, 0);
        jPanel1.add(btn_login_26, gridBagConstraints);

        lb_note_26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lb_note_26.setText("Bạn chưa có tài khoản?");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(17, 163, 0, 0);
        jPanel1.add(lb_note_26, gridBagConstraints);

        btn_signup_26.setBackground(new java.awt.Color(255, 0, 0));
        btn_signup_26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_signup_26.setText("Đăng ký");
        btn_signup_26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_signup_26ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(13, 5, 0, 0);
        jPanel1.add(btn_signup_26, gridBagConstraints);

        lb_user_26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lb_user_26.setText("Tên người dùng: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 69, 0, 0);
        jPanel1.add(lb_user_26, gridBagConstraints);

        btn_home_26.setBackground(new java.awt.Color(255, 255, 51));
        btn_home_26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_home_26.setText("Trang chủ");
        btn_home_26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_home_26ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(13, 38, 22, 0);
        jPanel1.add(btn_home_26, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_login_26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_login_26ActionPerformed
        // TODO add your handling code here:
        try {
            if (sqlHandler.getIdCustomer(textf_user_26.getText(), textf_pass_26.getText()) != 0) {
                this.dispose();
                new Home(sqlHandler.getIdCustomer(textf_user_26.getText(), textf_pass_26.getText())).setVisible(true);
            } else if (sqlHandler.getIdEmployee(textf_user_26.getText(), textf_pass_26.getText()) != 0) {
                this.dispose();
                new Manager(sqlHandler.getIdEmployee(textf_user_26.getText(), textf_pass_26.getText())).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Đăng nhập thất bại");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_login_26ActionPerformed

    private void btn_signup_26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_signup_26ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new SignUp().setVisible(true);
    }//GEN-LAST:event_btn_signup_26ActionPerformed

    private void btn_home_26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_home_26ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new Home().setVisible(true);
    }//GEN-LAST:event_btn_home_26ActionPerformed

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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_home_26;
    private javax.swing.JButton btn_login_26;
    private javax.swing.JButton btn_signup_26;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lb_login_26;
    private javax.swing.JLabel lb_note_26;
    private javax.swing.JLabel lb_pass_26;
    private javax.swing.JLabel lb_user_26;
    private javax.swing.JTextField textf_pass_26;
    private javax.swing.JTextField textf_user_26;
    // End of variables declaration//GEN-END:variables
}