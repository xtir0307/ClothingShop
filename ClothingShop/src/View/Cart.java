/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Model.SQLHandler;
import java.awt.Image;
import java.sql.*;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author xtir0
 */
public class Cart extends javax.swing.JFrame {

    DecimalFormat df55 = new DecimalFormat("#");
    public final SQLHandler sqlHandler55 = new SQLHandler();
    public DefaultTableModel tableModelProducts55
            = new DefaultTableModel();
    public int id_customer55;
    private String name_product55, type_product55, fabric55, madein55, urlimg55;
    private int quantity55, id_product55, id_cart55;
    private Double price55;

    /**
     * Creates new form Cart1
     */
    public Cart() {
        initComponents();
        setTableProduct();
    }

    public Cart(int id_customer55) {
        this.id_customer55 = id_customer55;
        initComponents();
        setTableProduct();
//        lab_username.setText(username);
    }

    public void setTableProduct() {
        String columns[] = {
            "STT", "Tên sản phẩm", "Loại", "Giá", "Số lượng còn", "id", "Số lượng đặt", "id"
        };
        tableModelProducts55
                .setColumnIdentifiers(columns);
        table_products55.setModel(tableModelProducts55
        );
        hideColumn(table_products55, 5);
        hideColumn(table_products55, 7);
        showDataProducts();
    }

    public void showDataProducts() {
        try {
            ResultSet rs = sqlHandler55.getDataProductsCart(id_customer55);
            int i = 1;
            while (rs.next()) {
                Object[] rows = {
                    i, rs.getString("name_product"), rs.getString("type_product"), df55.format(rs.getDouble("price")), rs.getInt("quantity"), rs.getInt("id_product"), rs.getInt("quantity_orders"), rs.getInt("id_cart")
                };
                tableModelProducts55
                        .addRow(rows);
                i++;
            }
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
            ResultSet rs = sqlHandler55.getDataIdProductId(id_product55);
            while (rs.next()) {

                name_product55 = rs.getString("name_product");
                type_product55 = rs.getString("type_product");
                fabric55 = rs.getString("fabric");
                madein55 = rs.getString("madein");
                quantity55 = rs.getInt("quantity");
                price55 = rs.getDouble("price");
                urlimg55 = rs.getString("urlimg");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getDataProduct() {
        name_product55 = lab_nameproduct55.getText();
        type_product55 = lab_type55.getText();
        fabric55 = lab_fabric55.getText();
        madein55 = lab_madein55.getText();
        quantity55 = Integer.valueOf(lab_quantity55.getText());
        price55 = Double.valueOf(lab_price55.getText());
    }

    public void setDataProduct() {
        lab_nameproduct55.setText(name_product55);
        lab_type55.setText(type_product55);
        lab_madein55.setText(madein55);
        lab_fabric55.setText(fabric55);
        lab_quantity55.setText(String.valueOf(quantity55));
        lab_price55.setText(df55.format(price55) + " VNĐ");
        lab_img55.setIcon(ResizeImage(urlimg55));
    }

    public ImageIcon ResizeImage(String ImagePath) {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(lab_img55.getWidth(), lab_img55.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }

    public void clearData(DefaultTableModel dftableModel) {
        int n = dftableModel.getRowCount() - 1;
        for (int i = n; i >= 0; i--) {
            dftableModel.removeRow(i);
        }
    }

    public void clearDataInput() {
        lab_nameproduct55.setText("");
        lab_type55.setText("");
        lab_madein55.setText("");
        lab_fabric55.setText("");
        lab_quantity55.setText("");
        lab_price55.setText("");
        lab_img55.setIcon(null);
    }

    public int checkTable() {
        int count = 0;
        count = table_products55.getRowCount();
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
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_products55 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lab_nameproduct55 = new javax.swing.JLabel();
        lab_type55 = new javax.swing.JLabel();
        lab_fabric55 = new javax.swing.JLabel();
        lab_quantity55 = new javax.swing.JLabel();
        lab_price55 = new javax.swing.JLabel();
        lab_madein55 = new javax.swing.JLabel();
        lab_img55 = new javax.swing.JLabel();
        btn_delete55 = new javax.swing.JButton();
        btn_deleteall = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btn_pay55 = new javax.swing.JButton();
        btn_back55 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lab_username = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Giỏ hàng");

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Sản phẩm");

        table_products55.setBackground(new java.awt.Color(255, 255, 153));
        table_products55.setModel(new javax.swing.table.DefaultTableModel(
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
        table_products55.setSelectionBackground(new java.awt.Color(0, 153, 102));
        table_products55.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_products55MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_products55);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Chi tiết sản phẩm");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Tên sản phẩm: ");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Loại: ");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Chất liệu: ");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Số lượng: ");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Giá: ");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Hình ảnh sản phẩm: ");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Nhập từ: ");

        lab_nameproduct55.setDisplayedMnemonic('G');
        lab_nameproduct55.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lab_nameproduct55.setText(" ");

        lab_type55.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lab_type55.setText(" ");

        lab_fabric55.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lab_fabric55.setText(" ");

        lab_quantity55.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lab_quantity55.setText(" ");

        lab_price55.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lab_price55.setText(" ");

        lab_madein55.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lab_madein55.setText(" ");

        lab_img55.setText(" ");

        btn_delete55.setBackground(new java.awt.Color(255, 102, 51));
        btn_delete55.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_delete55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/bin.png"))); // NOI18N
        btn_delete55.setText("Xóa");
        btn_delete55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delete55ActionPerformed(evt);
            }
        });

        btn_deleteall.setBackground(new java.awt.Color(255, 0, 51));
        btn_deleteall.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_deleteall.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/bin.png"))); // NOI18N
        btn_deleteall.setText("Xóa tất cả");
        btn_deleteall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteallActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(12, 12, 12)
                        .addComponent(lab_type55, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lab_madein55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lab_nameproduct55, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(7, 7, 7)
                                .addComponent(lab_fabric55, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(7, 7, 7)
                                .addComponent(lab_quantity55, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(lab_price55, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lab_img55, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(75, 75, 75)
                                .addComponent(btn_delete55)
                                .addGap(36, 36, 36)
                                .addComponent(btn_deleteall)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lab_nameproduct55)
                    .addComponent(jLabel4))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(lab_type55)
                    .addComponent(jLabel10)
                    .addComponent(lab_madein55))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(lab_fabric55))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(lab_quantity55))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(lab_price55))
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(lab_img55, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_deleteall)
                            .addComponent(btn_delete55))))
                .addGap(13, 13, 13))
        );

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));

        btn_pay55.setBackground(new java.awt.Color(0, 255, 0));
        btn_pay55.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_pay55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/credit-card.png"))); // NOI18N
        btn_pay55.setText("Thanh toán");
        btn_pay55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pay55ActionPerformed(evt);
            }
        });

        btn_back55.setBackground(new java.awt.Color(255, 255, 51));
        btn_back55.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_back55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/back-button.png"))); // NOI18N
        btn_back55.setText("Quay lại");
        btn_back55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_back55ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Giỏ hàng");

        lab_username.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lab_username.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lab_username.setText(" ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(btn_back55)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lab_username, javax.swing.GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_pay55))
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_pay55)
                    .addComponent(btn_back55)
                    .addComponent(lab_username)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_back55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_back55ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new Home(id_customer55).setVisible(true);
    }//GEN-LAST:event_btn_back55ActionPerformed

    private void table_products55MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_products55MouseClicked
        // TODO add your handling code here:
        int row = table_products55.getSelectedRow();
        id_product55 = Integer.valueOf(table_products55.getModel().getValueAt(row, 5).toString());
        id_cart55 = Integer.valueOf(table_products55.getModel().getValueAt(row, 7).toString());
        getDataSQLProduct();
        setDataProduct();
    }//GEN-LAST:event_table_products55MouseClicked

    private void btn_delete55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delete55ActionPerformed
        // TODO add your handling code here:
        try {
            if (id_cart55 == 0) {
                JOptionPane.showMessageDialog(rootPane, "Chưa chọn sản phẩm!!!");
            } else {
                sqlHandler55.deleteProductCart(id_cart55, id_customer55);
                JOptionPane.showMessageDialog(rootPane, "Đã xóa sản phẩm!!!");
                clearData(tableModelProducts55
                );
                clearDataInput();
                showDataProducts();
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_delete55ActionPerformed

    private void btn_pay55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pay55ActionPerformed
        // TODO add your handling code here:
        if (checkTable() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Chưa có sản phẩm nào!!!");
        } else {
            this.dispose();
            new Pay(id_customer55).setVisible(true);
        }

    }//GEN-LAST:event_btn_pay55ActionPerformed

    private void btn_deleteallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteallActionPerformed
        // TODO add your handling code here:
        if (checkTable() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Không có sản phẩm nào");
        } else {
            try {
                sqlHandler55.deleteAllProductCart(id_customer55);
                JOptionPane.showMessageDialog(rootPane, "Đã xóa tất cả sản phẩm!!!");
                clearData(tableModelProducts55
                );
                clearDataInput();
                showDataProducts();
            } catch (Exception e) {
            }
        }

    }//GEN-LAST:event_btn_deleteallActionPerformed

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
            java.util.logging.Logger.getLogger(Cart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cart().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_back55;
    private javax.swing.JButton btn_delete55;
    private javax.swing.JButton btn_deleteall;
    private javax.swing.JButton btn_pay55;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lab_fabric55;
    private javax.swing.JLabel lab_img55;
    private javax.swing.JLabel lab_madein55;
    private javax.swing.JLabel lab_nameproduct55;
    private javax.swing.JLabel lab_price55;
    private javax.swing.JLabel lab_quantity55;
    private javax.swing.JLabel lab_type55;
    private javax.swing.JLabel lab_username;
    private javax.swing.JTable table_products55;
    // End of variables declaration//GEN-END:variables
}
