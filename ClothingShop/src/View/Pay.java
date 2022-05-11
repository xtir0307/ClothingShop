/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Model.SQLHandler;
import java.awt.Image;
import java.beans.Customizer;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author xtir0
 */
public class Pay extends javax.swing.JFrame {

        public final SQLHandler sqlHandler = new SQLHandler();
    public DefaultTableModel tableModelProducts = new DefaultTableModel();
//    public String username;
    public int id_customer;
    private String name_product, type_product, fabric, madein, urlimg;
    private int quantity, id_product, id_cart, home = 0;
    private Double price, payment = 0.0;
    DecimalFormat df = new DecimalFormat("#");

    /**
     * Creates new form Pay
     */
    public Pay() {
        initComponents();
        setTableProduct();
    }
    
    public Pay(int id_customer) {
        this.id_customer = id_customer;
        initComponents();
        setTableProduct();
    }
    
    public Pay(int id_customer, int id_product, int quantity) {
        home = 1;
        initComponents();
        this.id_customer = id_customer;
        setTableProduct(id_product, quantity);
    }
    
    
    public void setTableProduct() {
        String columns[] = {
            "STT", "Tên sản phẩm", "Loại", "Giá", "id", "Số lượng đặt", "id"
        };
        tableModelProducts.setColumnIdentifiers(columns);
        table_products.setModel(tableModelProducts);
        hideColumn(table_products, 4);
        hideColumn(table_products, 6);
        showDataProducts();
    }

    public void showDataProducts() {
        try {
            ResultSet rs = sqlHandler.getDataProductsCart(id_customer);
            int i = 1;
            while (rs.next()) {
                Object[] rows = {
                    i, rs.getString("name_product"), rs.getString("type_product"),df.format(rs.getDouble("price")) , rs.getInt("id_product"), rs.getInt("quantity_orders"),  rs.getInt("id_cart")
                };
                payment += rs.getDouble("price") * rs.getInt("quantity_orders");
                tableModelProducts.addRow(rows);
                i++;
            }
            lab_payment.setText("" + df.format(payment) + " VND");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void setTableProduct(int id_product, int quantity) {
        String columns[] = {
            "STT", "Tên sản phẩm", "Loại", "Giá", "id", "Số lượng đặt"
        };
        tableModelProducts.setColumnIdentifiers(columns);
        table_products.setModel(tableModelProducts);
        hideColumn(table_products, 4);
        showDataProducts(id_product, quantity);
    }
    
    public void showDataProducts(int id_product, int quantity) {
        try {
            ResultSet rs = sqlHandler.getDataIdProductId(id_product);
            int i = 1;
            while (rs.next()) {
                Object[] rows = {
                    i, rs.getString("name_product"), rs.getString("type_product"),df.format(rs.getDouble("price")) , rs.getInt("id_product"), quantity
                };
                payment += rs.getDouble("price") * quantity;
                tableModelProducts.addRow(rows);
                i++;
            }
            lab_payment.setText("" + df.format(payment) + " VND");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hideColumn(JTable table_products, int index) {
        table_products.getColumnModel().getColumn(index).setMinWidth(0);
        table_products.getColumnModel().getColumn(index).setMaxWidth(0);
        table_products.getColumnModel().getColumn(0).setMinWidth(40);
        table_products.getColumnModel().getColumn(0).setMaxWidth(40);
        table_products.getColumnModel().getColumn(1).setMinWidth(180);
    }

    public void getDataSQLProduct() {
        try {
            ResultSet rs = sqlHandler.getDataIdProductId(id_product);
            while (rs.next()) {

                name_product = rs.getString("name_product");
                type_product = rs.getString("type_product");
                fabric = rs.getString("fabric");
                madein = rs.getString("madein");
                quantity = rs.getInt("quantity");
                price = rs.getDouble("price");
                urlimg = rs.getString("urlimg");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public void clearData(DefaultTableModel dftableModel) {
        int n = dftableModel.getRowCount() - 1;
        for (int i = n; i >= 0; i--) {
            dftableModel.removeRow(i);
        }
    }
    
    public int checkTable() {
        int count = 0;
        count = table_products.getRowCount();
        return count;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_products = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lab_payment = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Thanh toán");

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sản phẩm");

        table_products.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table_products.setSelectionBackground(new java.awt.Color(0, 153, 102));
        jScrollPane1.setViewportView(table_products);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Thanh toán");

        jButton2.setBackground(new java.awt.Color(255, 51, 51));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/pay-per-click.png"))); // NOI18N
        jButton2.setText("Đặt hàng");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 255, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/back-button.png"))); // NOI18N
        jButton1.setText("Quay lại");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/dollar.png"))); // NOI18N
        jLabel3.setText("Tổng tiền: ");

        lab_payment.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lab_payment.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lab_payment.setText(" ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lab_payment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lab_payment))
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 570, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(home == 0) {
            this.dispose();
            new Cart(id_customer).setVisible(true);
        } else {
            this.dispose();
            new Home(id_customer).setVisible(true);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(checkTable() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Chưa có sản phẩm!!!");
        } else {
            JOptionPane.showMessageDialog(rootPane, "Đặt hàng thành công!!!");
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Pay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pay().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lab_payment;
    private javax.swing.JTable table_products;
    // End of variables declaration//GEN-END:variables
}
