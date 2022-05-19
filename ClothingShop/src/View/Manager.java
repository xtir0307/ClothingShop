/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Model.SQLHandler;
import java.awt.Image;
import java.io.File;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author HNhat
 */
public class Manager extends javax.swing.JFrame {
DecimalFormat df = new DecimalFormat("#");
    public final SQLHandler sqlHandler = new SQLHandler();
    public final DefaultTableModel tableModelProducts = new DefaultTableModel();
    public final DefaultTableModel tableModelCustomer = new DefaultTableModel();
    public final DefaultTableModel tableModelEmployee = new DefaultTableModel();
    // Products_44
    private String name_product_44, type_product_44, fabric_44, madein_44, urlimg_44;
    private Double price_44;
    private int id_product_44, quantity_44;

    // Customer
    private String username, firstname, lastname, password, phone, email;
    int id_customer, gender;

    // Employee
    private String username_employee, firstname_employee, lastname_employee, password_employee, phone_employee, email_employee, position_employee;
    private int id_employee, gender_employee, id_gues;
    private double salary_employee;

    /**
     * Creates new form Manager
     */
    public Manager() {
        initComponents();
        createList();
        setTableProduct();
        setTableCustomer();
        setTableEmployee();
        setStatistical();
        if (id_employee == 0) {
            btn_logout_in.setText("Đăng nhập");
            ImageIcon icon = new ImageIcon("D:\\MyCode\\Java\\Advanced\\Theory\\ClothingShop\\src\\Image\\enter.png");
            btn_logout_in.setIcon(icon);
        } else {
            btn_logout_in.setText("Đăng xuất");
            ImageIcon icon = new ImageIcon("D:\\MyCode\\Java\\Advanced\\Theory\\ClothingShop\\src\\Image\\logout.png");
            btn_logout_in.setIcon(icon);
        }
    }
    
    public Manager(int id_gues) {
        this.id_gues = id_gues;
        initComponents();
        createList();
        setTableProduct();
        setTableCustomer();
        setTableEmployee();
        setStatistical();
        if (id_employee == 0) {
            btn_logout_in.setText("Đăng nhập");
            ImageIcon icon = new ImageIcon("D:\\MyCode\\Java\\Advanced\\Theory\\ClothingShop\\src\\Image\\enter.png");
            btn_logout_in.setIcon(icon);
        } else {
            btn_logout_in.setText("Đăng xuất");
            ImageIcon icon = new ImageIcon("D:\\MyCode\\Java\\Advanced\\Theory\\ClothingShop\\src\\Image\\logout.png");
            btn_logout_in.setIcon(icon);
        }
    }
    

    public void setTableProduct() {
        String columns[] = {
            "STT", "Tên sản phẩm", "Loại", "Giá", "Số lượng còn", "id"
        };
        tableModelProducts.setColumnIdentifiers(columns);
        table_products.setModel(tableModelProducts);
        hideColumn(table_products, 5);
        showDataProducts();
    }

    public void showDataProducts() {
        try {
            ResultSet rs = sqlHandler.getDataProducts();
            int i = 1;
            while (rs.next()) {
                Object[] rows = {
                    i, rs.getString("name_product"), rs.getString("type_product"), df.format(rs.getDouble("price")), rs.getInt("quantity"), rs.getInt("id_product")
                };
                tableModelProducts.addRow(rows);
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
            ResultSet rs = sqlHandler.getDataIdProductId(id_product_44);
            while (rs.next()) {
                name_product_44 = rs.getString("name_product");
                type_product_44 = rs.getString("type_product");
                fabric_44 = rs.getString("fabric");
                madein_44 = rs.getString("madein");
                quantity_44 = rs.getInt("quantity");
                price_44 = rs.getDouble("price");
                urlimg_44 = rs.getString("urlimg");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getDataProduct() {
        name_product_44 = textf_nameproduct.getText();
        type_product_44 = textf_types.getText();
        fabric_44 = textf_fabric.getText();
        madein_44 = textf_madein.getText();
        quantity_44 = Integer.valueOf(textf_quantity.getText());
        price_44 = Double.valueOf(textf_price.getText());
    }

    public void setDataProduct() {
        textf_nameproduct.setText(name_product_44);
        textf_types.setText(type_product_44);
        textf_madein.setText(madein_44);
        textf_fabric.setText(fabric_44);
        textf_quantity.setText(String.valueOf(quantity_44));
        textf_price.setText(String.valueOf(df.format(price_44)));
        lab_img.setIcon(ResizeImage(urlimg_44));
    }

    public void refreshProduct() {
        clearData(tableModelProducts);
        showDataProducts();

    }

    public void clearData(DefaultTableModel dftableModel) {
        int n = dftableModel.getRowCount() - 1;
        for (int i = n; i >= 0; i--) {
            dftableModel.removeRow(i);
        }
    }

    public void renewProduct() {
        textf_nameproduct.setText("");
        textf_types.setText("");
        textf_madein.setText("");
        textf_fabric.setText("");
        textf_quantity.setText("");
        textf_price.setText("");
        lab_img.setIcon(null);
    }

    //------------------------------------------------------------------ Customer ------------------------------------------------------------------\\
    public void setTableCustomer() {
        String columns[] = {
            "STT", "Họ", "Tên", "Giới tính", "id"
        };
        tableModelCustomer.setColumnIdentifiers(columns);
        table_customer.setModel(tableModelCustomer);
        hideColumn(table_customer, 4);
        showDataCustomer();
    }

    public void showDataCustomer() {
        try {
            ResultSet rs = sqlHandler.getDataCustomer();
            int i = 1;
            while (rs.next()) {
                String gender;
                gender = (rs.getInt("gender") == 1) ? "Nam" : "Nữ";
                Object[] rows = {
                    i, rs.getString("firstname"), rs.getString("lastname"), gender, rs.getInt("id_customer")
                };
                tableModelCustomer.addRow(rows);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getDataCustomer() {
        try {
            username = textf_username.getText();
            firstname = textf_firstname.getText();
            lastname = textf_lastname.getText();
            password = textf_password.getText();
            phone = textf_phone.getText();
            email = textf_email.getText();
            if (rbtn_male.isSelected()) {
                gender = 1;
            }
            if (rbtn_female.isSelected()) {
                gender = 0;
            }
        } catch (Exception e) {
        }

    }

    public void getDataSQLCustomer() {
        try {
            ResultSet rs = sqlHandler.getDataIdCustomer(id_customer);
            while (rs.next()) {
                username = rs.getString("username");
                firstname = rs.getString("firstname");
                lastname = rs.getString("lastname");
                password = rs.getString("passwordu");
                email = rs.getString("email");
                phone = rs.getString("phone");
                gender = rs.getInt("gender");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDataCustomer() {
        try {
            textf_username.setText(username);
            textf_firstname.setText(firstname);
            textf_lastname.setText(lastname);
            textf_password.setText(password);
            textf_phone.setText(phone);
            textf_email.setText(email);
            if (gender == 1) {
                rbtn_male.setSelected(true);
            }
            if (gender == 0) {
                rbtn_female.setSelected(true);
            }

        } catch (Exception e) {
        }
    }

    public void renewCustomer() {
        try {
            textf_username.setText("");
            textf_firstname.setText("");
            textf_lastname.setText("");
            textf_password.setText("");
            textf_phone.setText("");
            textf_email.setText("");
            if (gender == 1) {
                rbtn_male.setSelected(false);
            }
            if (gender == 0) {
                rbtn_female.setSelected(false);
            }
        } catch (Exception e) {
        }
    }

    //------------------------------------------------------------------ Employee ------------------------------------------------------------------\\
    public void setTableEmployee() {
        String columns[] = {
            "STT", "Họ", "Tên", "Giới tính", "Chức vụ", "id"
        };
        tableModelEmployee.setColumnIdentifiers(columns);
        table_employee.setModel(tableModelEmployee);
        hideColumn(table_employee, 5);
        showDataEmployee();
    }

    public void showDataEmployee() {
        try {
            ResultSet rs = sqlHandler.getDataEmployee();
            int i = 1;
            while (rs.next()) {
                String gender;
                gender = (rs.getInt("gender") == 1) ? "Nam" : "Nữ";
                Object[] rows = {
                    i, rs.getString("firstname"), rs.getString("lastname"), gender, rs.getString("position"), rs.getInt("id_employee")
                };
                tableModelEmployee.addRow(rows);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getDataEmployee() {
        try {
            username_employee = textf_username_employee.getText();
            firstname_employee = textf_firstname_employee.getText();
            lastname_employee = textf_lastname_employee.getText();
            password_employee = textf_password_employee.getText();
            phone_employee = textf_phone_employee.getText();
            email_employee = textf_email_employee.getText();
            position_employee = textf_position_employee.getText();
            salary_employee = Double.valueOf(textf_email_employee.getText());
            if (rbtn_male_employee.isSelected()) {
                gender_employee = 1;
            }
            if (rbtn_female_employee.isSelected()) {
                gender_employee = 0;
            }
        } catch (Exception e) {
        }

    }

    public void getDataSQLEmployee() {
        try {
            ResultSet rs = sqlHandler.getDataIdEmployee(id_employee);
            while (rs.next()) {
                username_employee = rs.getString("username");
                firstname_employee = rs.getString("firstname");
                lastname_employee = rs.getString("lastname");
                password_employee = rs.getString("passwordu");
                email_employee = rs.getString("email");
                phone_employee = rs.getString("phone");
                gender_employee = rs.getInt("gender");
                position_employee = rs.getString("position");
                salary_employee = rs.getDouble("salary");
                if(gender == 1) {
                    rbtn_male_employee.setSelected(true);
                }
                if(gender == 0) {
                    rbtn_female_employee.setSelected(true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDataEmployee() {
        try {
            textf_username_employee.setText(username_employee);
            textf_firstname_employee.setText(firstname_employee);
            textf_lastname_employee.setText(lastname_employee);
            textf_password_employee.setText(password_employee);
            textf_position_employee.setText(position_employee);
            textf_phone_employee.setText(phone_employee);
            textf_email_employee.setText(email_employee);
            textf_salary_employee.setText(String.valueOf(salary_employee));
            if (gender_employee == 1) {
                rbtn_male_employee.setSelected(true);
            }
            if (gender_employee == 0) {
                rbtn_female_employee.setSelected(true);
            }

        } catch (Exception e) {
        }
    }

    public void renewEmployee() {
        try {
            textf_username_employee.setText("");
            textf_firstname_employee.setText("");
            textf_lastname_employee.setText("");
            textf_password_employee.setText("");
            textf_phone_employee.setText("");
            textf_email_employee.setText("");
            textf_position_employee.setText("");
            textf_salary_employee.setText("");
            if (gender_employee == 1) {
                rbtn_male_employee.setSelected(false);
            }
            if (gender_employee == 0) {
                rbtn_female_employee.setSelected(false);
            }
        } catch (Exception e) {
        }
    }

    public void refreshEmployee() {
        clearDatalEmployee(tableModelEmployee);
        showDataEmployee();

    }

    public void clearDatalEmployee(DefaultTableModel dftableModel) {
        int n = tableModelEmployee.getRowCount() - 1;
        for (int i = n; i >= 0; i--) {
            dftableModel.removeRow(i);
        }
    }

    public void renewlEmployee() {
        textf_nameproduct.setText("");
        textf_types.setText("");
        textf_madein.setText("");
        textf_fabric.setText("");
        textf_quantity.setText("");
        textf_price.setText("");
        lab_img.setIcon(null);
    }

    //------------------------------------------------------------------ Statistical ------------------------------------------------------------------\\
    
    
    public void setStatistical() {
        try {
            ResultSet rs1 =  sqlHandler.countCustomer();
            while(rs1.next()) {
                lab_countcustomer.setText(String.valueOf(rs1.getInt(1)));
            }
            
            ResultSet rs2 =  sqlHandler.countEmployee();
            while(rs2.next()) {
                lab_countemployee.setText(String.valueOf(rs2.getInt(1)));
            }
            
            ResultSet rs3 =  sqlHandler.countProduct();
            while(rs3.next()) {
                lab_countproduct.setText(String.valueOf(rs3.getInt(1)));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    
    //------------------------------------------------------------------ Statistical ------------------------------------------------------------------\\
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grouprbtn_gender = new javax.swing.ButtonGroup();
        grouprbtn_genderemployee = new javax.swing.ButtonGroup();
        panel_menu = new javax.swing.JPanel();
        panel_title = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btn_products = new javax.swing.JButton();
        btn_employee = new javax.swing.JButton();
        btn_customer = new javax.swing.JButton();
        btn_statistical = new javax.swing.JButton();
        btn_logout_in = new javax.swing.JButton();
        panel_main = new javax.swing.JPanel();
        panel_products = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_products = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        textf_nameproduct = new javax.swing.JTextField();
        textf_types = new javax.swing.JTextField();
        textf_madein = new javax.swing.JTextField();
        textf_fabric = new javax.swing.JTextField();
        textf_quantity = new javax.swing.JTextField();
        textf_price = new javax.swing.JTextField();
        btn_chosefile = new javax.swing.JButton();
        lab_img = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btn_add = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_renew = new javax.swing.JButton();
        panel_customer = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_customer = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        textf_username = new javax.swing.JTextField();
        textf_password = new javax.swing.JTextField();
        textf_firstname = new javax.swing.JTextField();
        textf_lastname = new javax.swing.JTextField();
        textf_phone = new javax.swing.JTextField();
        textf_email = new javax.swing.JTextField();
        rbtn_male = new javax.swing.JRadioButton();
        rbtn_female = new javax.swing.JRadioButton();
        jPanel6 = new javax.swing.JPanel();
        btn_addcustomer = new javax.swing.JButton();
        btn_editcustomer = new javax.swing.JButton();
        btn_deletecustomer = new javax.swing.JButton();
        btn_renewcustomer = new javax.swing.JButton();
        panel_employee = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_employee = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        textf_firstname_employee = new javax.swing.JTextField();
        textf_lastname_employee = new javax.swing.JTextField();
        rbtn_male_employee = new javax.swing.JRadioButton();
        rbtn_female_employee = new javax.swing.JRadioButton();
        textf_position_employee = new javax.swing.JTextField();
        textf_salary_employee = new javax.swing.JTextField();
        textf_username_employee = new javax.swing.JTextField();
        textf_password_employee = new javax.swing.JTextField();
        textf_phone_employee = new javax.swing.JTextField();
        textf_email_employee = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        btn_addemployee = new javax.swing.JButton();
        btn_editemployee = new javax.swing.JButton();
        btn_renewemployee = new javax.swing.JButton();
        btn_delelteemplyee = new javax.swing.JButton();
        panel_statistical = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        lab_countproduct = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel381 = new javax.swing.JLabel();
        lab_countcustomer = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        lab_countemployee = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý chung");
        setSize(new java.awt.Dimension(930, 600));

        panel_menu.setBackground(new java.awt.Color(0, 51, 51));

        panel_title.setBackground(new java.awt.Color(0, 204, 204));

        jLabel1.setBackground(new java.awt.Color(255, 51, 204));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Quản lý");

        javax.swing.GroupLayout panel_titleLayout = new javax.swing.GroupLayout(panel_title);
        panel_title.setLayout(panel_titleLayout);
        panel_titleLayout.setHorizontalGroup(
            panel_titleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
        );
        panel_titleLayout.setVerticalGroup(
            panel_titleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
        );

        btn_products.setBackground(new java.awt.Color(255, 102, 102));
        btn_products.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_products.setText("Sản phẩm");
        btn_products.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_productsActionPerformed(evt);
            }
        });

        btn_employee.setBackground(new java.awt.Color(153, 153, 255));
        btn_employee.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_employee.setText("Nhân viên");
        btn_employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_employeeActionPerformed(evt);
            }
        });

        btn_customer.setBackground(new java.awt.Color(102, 255, 102));
        btn_customer.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_customer.setText("Khách hàng");
        btn_customer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_customerActionPerformed(evt);
            }
        });

        btn_statistical.setBackground(new java.awt.Color(204, 204, 0));
        btn_statistical.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_statistical.setText("Thống kê");
        btn_statistical.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_statisticalActionPerformed(evt);
            }
        });

        btn_logout_in.setBackground(new java.awt.Color(255, 0, 51));
        btn_logout_in.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_logout_in.setText("Đăng xuất");
        btn_logout_in.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_logout_inActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_menuLayout = new javax.swing.GroupLayout(panel_menu);
        panel_menu.setLayout(panel_menuLayout);
        panel_menuLayout.setHorizontalGroup(
            panel_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_employee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_products, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_statistical, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_customer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_logout_in, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel_menuLayout.setVerticalGroup(
            panel_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_menuLayout.createSequentialGroup()
                .addComponent(panel_title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_products, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_customer, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_statistical, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_logout_in))
        );

        panel_products.setBackground(new java.awt.Color(255, 153, 153));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Quản lý sản phẩm");

        jPanel1.setBackground(new java.awt.Color(153, 255, 204));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Danh sách sản phẩm");

        table_products.setBackground(new java.awt.Color(153, 255, 204));
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
        table_products.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_productsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_products);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1))
        );

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Chi tiết sản phẩm");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Tên sản phẩm: ");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Loại: ");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Chất liệu: ");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Số lượng: ");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Giá: ");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Hình ảnh sản phẩm: ");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Nhập từ: ");

        btn_chosefile.setText("Chọn file");
        btn_chosefile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_chosefileActionPerformed(evt);
            }
        });

        lab_img.setText(" ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textf_nameproduct))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel9)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(textf_quantity))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel8)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(textf_fabric, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel10)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(textf_price, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(btn_chosefile))
                                            .addComponent(jLabel11))
                                        .addGap(50, 50, 50)
                                        .addComponent(lab_img, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(12, 12, 12))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textf_types)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textf_madein)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textf_nameproduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textf_madein, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textf_types)))
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textf_fabric, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textf_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textf_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_chosefile)
                        .addGap(62, 62, 62))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lab_img, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 204));

        btn_add.setBackground(new java.awt.Color(102, 255, 102));
        btn_add.setText("Thêm");
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });

        btn_edit.setBackground(new java.awt.Color(255, 204, 102));
        btn_edit.setText("Sửa");
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });

        btn_delete.setBackground(new java.awt.Color(255, 102, 102));
        btn_delete.setText("Xóa");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        btn_renew.setBackground(new java.awt.Color(102, 255, 204));
        btn_renew.setText("Làm mới");
        btn_renew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_renewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btn_add, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                .addGap(26, 26, 26)
                .addComponent(btn_edit, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                .addGap(26, 26, 26)
                .addComponent(btn_renew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(27, 27, 27)
                .addComponent(btn_delete, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addGap(25, 25, 25))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_renew)
                    .addComponent(btn_edit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout panel_productsLayout = new javax.swing.GroupLayout(panel_products);
        panel_products.setLayout(panel_productsLayout);
        panel_productsLayout.setHorizontalGroup(
            panel_productsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panel_productsLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_productsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        panel_productsLayout.setVerticalGroup(
            panel_productsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_productsLayout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_productsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_productsLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        panel_customer.setBackground(new java.awt.Color(153, 255, 153));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Quản lý khách hàng");

        jPanel4.setBackground(new java.awt.Color(204, 255, 204));

        table_customer.setBackground(new java.awt.Color(204, 255, 204));
        table_customer.setModel(new javax.swing.table.DefaultTableModel(
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
        table_customer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_customerMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table_customer);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Danh sách người dùng");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2))
        );

        jPanel5.setBackground(new java.awt.Color(153, 255, 204));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Chi tiết người dùng");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Tên đăng nhập: ");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("Mật khẩu: ");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setText("Họ: ");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setText("Tên: ");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setText("Giới tính: ");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setText("Số điện thoại: ");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setText("Email: ");

        grouprbtn_gender.add(rbtn_male);
        rbtn_male.setText("Nam");

        grouprbtn_gender.add(rbtn_female);
        rbtn_female.setText("Nữ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(jLabel18)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textf_email)
                    .addComponent(textf_lastname)
                    .addComponent(textf_username)
                    .addComponent(textf_phone)
                    .addComponent(textf_firstname)
                    .addComponent(textf_password)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(rbtn_male, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(102, 102, 102)
                        .addComponent(rbtn_female, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(177, 177, 177))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textf_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textf_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textf_firstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textf_lastname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rbtn_male)
                    .addComponent(rbtn_female))
                .addGap(43, 43, 43)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textf_phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textf_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 204));

        btn_addcustomer.setBackground(new java.awt.Color(153, 255, 153));
        btn_addcustomer.setText("Thêm");
        btn_addcustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addcustomerActionPerformed(evt);
            }
        });

        btn_editcustomer.setBackground(new java.awt.Color(255, 153, 51));
        btn_editcustomer.setText("Sửa");
        btn_editcustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editcustomerActionPerformed(evt);
            }
        });

        btn_deletecustomer.setBackground(new java.awt.Color(255, 51, 51));
        btn_deletecustomer.setText("Xóa");
        btn_deletecustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deletecustomerActionPerformed(evt);
            }
        });

        btn_renewcustomer.setBackground(new java.awt.Color(51, 255, 204));
        btn_renewcustomer.setText("Làm mới");
        btn_renewcustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_renewcustomerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(btn_addcustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(56, 56, 56)
                .addComponent(btn_editcustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(48, 48, 48)
                .addComponent(btn_renewcustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(39, 39, 39)
                .addComponent(btn_deletecustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(44, 44, 44))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_addcustomer)
                    .addComponent(btn_editcustomer)
                    .addComponent(btn_deletecustomer)
                    .addComponent(btn_renewcustomer))
                .addContainerGap())
        );

        javax.swing.GroupLayout panel_customerLayout = new javax.swing.GroupLayout(panel_customer);
        panel_customer.setLayout(panel_customerLayout);
        panel_customerLayout.setHorizontalGroup(
            panel_customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panel_customerLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        panel_customerLayout.setVerticalGroup(
            panel_customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_customerLayout.createSequentialGroup()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel_customerLayout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        panel_employee.setBackground(new java.awt.Color(204, 204, 255));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Quản lý nhân viên");

        jPanel7.setBackground(new java.awt.Color(153, 255, 153));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Danh sách nhân viên");

        table_employee.setBackground(new java.awt.Color(204, 255, 204));
        table_employee.setModel(new javax.swing.table.DefaultTableModel(
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
        table_employee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_employeeMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(table_employee);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3))
        );

        jPanel8.setBackground(new java.awt.Color(204, 255, 255));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Chi tiết nhân viên");

        jLabel25.setText("Họ: ");

        jLabel26.setText("Tên: ");

        jLabel27.setText("Giới tính: ");

        jLabel28.setText("Chức vụ: ");

        jLabel29.setText("Tên tài khoản: ");

        jLabel30.setText("Mật khẩu: ");

        jLabel31.setText("Số điện thoại: ");

        jLabel32.setText("Email: ");

        jLabel33.setText("Lương: ");

        grouprbtn_genderemployee.add(rbtn_male_employee);
        rbtn_male_employee.setText("Nam");

        grouprbtn_genderemployee.add(rbtn_female_employee);
        rbtn_female_employee.setText("Nữ");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textf_firstname_employee))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textf_lastname_employee))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addGap(12, 12, 12)
                        .addComponent(rbtn_male_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(rbtn_female_employee)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textf_position_employee))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textf_salary_employee)
                        .addGap(110, 110, 110))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textf_username_employee)
                            .addComponent(textf_phone_employee)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textf_password_employee))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textf_email_employee)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel24)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(textf_firstname_employee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(textf_lastname_employee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28)
                    .addComponent(rbtn_male_employee)
                    .addComponent(rbtn_female_employee)
                    .addComponent(textf_position_employee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(textf_salary_employee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(textf_username_employee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(textf_password_employee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(textf_phone_employee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(textf_email_employee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 50, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 204));

        btn_addemployee.setBackground(new java.awt.Color(153, 255, 153));
        btn_addemployee.setText("Thêm");
        btn_addemployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addemployeeActionPerformed(evt);
            }
        });

        btn_editemployee.setBackground(new java.awt.Color(255, 204, 51));
        btn_editemployee.setText("Sửa");
        btn_editemployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editemployeeActionPerformed(evt);
            }
        });

        btn_renewemployee.setBackground(new java.awt.Color(51, 255, 204));
        btn_renewemployee.setText("Làm mới");
        btn_renewemployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_renewemployeeActionPerformed(evt);
            }
        });

        btn_delelteemplyee.setBackground(new java.awt.Color(255, 51, 51));
        btn_delelteemplyee.setText("Xóa");
        btn_delelteemplyee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delelteemplyeeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btn_addemployee)
                .addGap(46, 46, 46)
                .addComponent(btn_editemployee)
                .addGap(38, 38, 38)
                .addComponent(btn_renewemployee)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(btn_delelteemplyee)
                .addGap(23, 23, 23))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_addemployee)
                    .addComponent(btn_editemployee)
                    .addComponent(btn_renewemployee)
                    .addComponent(btn_delelteemplyee))
                .addContainerGap())
        );

        javax.swing.GroupLayout panel_employeeLayout = new javax.swing.GroupLayout(panel_employee);
        panel_employee.setLayout(panel_employeeLayout);
        panel_employeeLayout.setHorizontalGroup(
            panel_employeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panel_employeeLayout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_employeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        panel_employeeLayout.setVerticalGroup(
            panel_employeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_employeeLayout.createSequentialGroup()
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_employeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panel_employeeLayout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        panel_statistical.setBackground(new java.awt.Color(255, 255, 204));

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("Thống kê");

        jPanel10.setLayout(new java.awt.GridLayout(1, 0));

        jPanel11.setBackground(new java.awt.Color(255, 204, 204));

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("Sản phẩm");

        jLabel40.setBackground(new java.awt.Color(204, 255, 255));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/package.png"))); // NOI18N

        lab_countproduct.setBackground(new java.awt.Color(204, 255, 255));
        lab_countproduct.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lab_countproduct.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lab_countproduct.setText("50");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(lab_countproduct, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                    .addComponent(lab_countproduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel10.add(jPanel11);

        jPanel12.setBackground(new java.awt.Color(204, 255, 204));

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("Khách hàng");

        jLabel381.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel381.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/user.png"))); // NOI18N

        lab_countcustomer.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lab_countcustomer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lab_countcustomer.setText("50");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel381, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(lab_countcustomer, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel381, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lab_countcustomer, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel10.add(jPanel12);

        jPanel13.setBackground(new java.awt.Color(153, 255, 255));

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Nhân viên");

        lab_countemployee.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lab_countemployee.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lab_countemployee.setText("50");

        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/user.png"))); // NOI18N

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(lab_countemployee, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lab_countemployee, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
                .addGap(11, 11, 11))
        );

        jPanel10.add(jPanel13);

        javax.swing.GroupLayout panel_statisticalLayout = new javax.swing.GroupLayout(panel_statistical);
        panel_statistical.setLayout(panel_statisticalLayout);
        panel_statisticalLayout.setHorizontalGroup(
            panel_statisticalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel_statisticalLayout.setVerticalGroup(
            panel_statisticalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_statisticalLayout.createSequentialGroup()
                .addComponent(jLabel34)
                .addGap(18, 18, 18)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 389, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel_mainLayout = new javax.swing.GroupLayout(panel_main);
        panel_main.setLayout(panel_mainLayout);
        panel_mainLayout.setHorizontalGroup(
            panel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_products, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panel_customer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panel_employee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panel_statistical, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_mainLayout.setVerticalGroup(
            panel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_products, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panel_customer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panel_employee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panel_statistical, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel_menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_main, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel_menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    public static ArrayList<JPanel> listPanel = new ArrayList<>();

    public void createList() {
        listPanel.add(panel_products);
        listPanel.add(panel_customer);
        listPanel.add(panel_employee);
        listPanel.add(panel_statistical);
        clickBtn(0);
    }

    public void clickBtn(int x) {
        for (int i = 0; i < listPanel.size(); i++) {
            if (i == x) {
                listPanel.get(i).setVisible(true);
            } else {
                listPanel.get(i).setVisible(false);
            }
        }
    }
    private void btn_productsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_productsActionPerformed
        // TODO add your handling code here:
        clickBtn(0);

    }//GEN-LAST:event_btn_productsActionPerformed

    private void btn_employeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_employeeActionPerformed
        // TODO add your handling code here:
        clickBtn(2);
    }//GEN-LAST:event_btn_employeeActionPerformed

    private void btn_customerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_customerActionPerformed
        // TODO add your handling code here:
        clickBtn(1);
    }//GEN-LAST:event_btn_customerActionPerformed

    private void btn_statisticalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_statisticalActionPerformed
        // TODO add your handling code here:
        clickBtn(3);
        setStatistical();
    }//GEN-LAST:event_btn_statisticalActionPerformed

    private void table_productsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_productsMouseClicked
        // TODO add your handling code here:
        int row = table_products.getSelectedRow();
        id_product_44 = Integer.valueOf(table_products.getModel().getValueAt(row, 5).toString());
        getDataSQLProduct();
        setDataProduct();
    }//GEN-LAST:event_table_productsMouseClicked

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        // TODO add your handling code here:
        //sua sp_44
        try {
            getDataProduct();
            refreshProduct();
            sqlHandler.updateProduct(id_product_44, name_product_44, type_product_44, fabric_44, madein_44, price_44, quantity_44, urlimg_44);
        } catch (Exception e) {
        }


    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_renewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_renewActionPerformed
        // TODO add your handling code here:
        //lammoi sp44
        renewProduct();

    }//GEN-LAST:event_btn_renewActionPerformed

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        // TODO add your handling code here:
        //thêm sp444
        try {
            getDataProduct();
            sqlHandler.insertProduct(name_product_44, type_product_44, fabric_44, madein_44, price_44, quantity_44, urlimg_44);
            refreshProduct();
            renewProduct();

        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_addActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:
        //xóa sp44
        try {
            sqlHandler.deleteProduct(id_product_44);
            refreshProduct();
            renewProduct();
        } catch (Exception e) {
        }

//System.out.println(id);
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_chosefileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chosefileActionPerformed
        // TODO add your handling code here:
        choseImg();
    }//GEN-LAST:event_btn_chosefileActionPerformed

    private void btn_addcustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addcustomerActionPerformed
        // TODO add your handling code here:
        try {
            getDataCustomer();
            sqlHandler.insertCustomer(username, firstname, lastname, password, phone, email, gender);
            clearData(tableModelCustomer);
            showDataCustomer();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_addcustomerActionPerformed

    private void btn_deletecustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deletecustomerActionPerformed
        // TODO add your handling code here:
        sqlHandler.deleteCustomer(id_customer);
        clearData(tableModelCustomer);
        showDataCustomer();
    }//GEN-LAST:event_btn_deletecustomerActionPerformed

    private void btn_editcustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editcustomerActionPerformed
        // TODO add your handling code here:
        getDataCustomer();
        sqlHandler.updateCustomer(id_customer, username, firstname, lastname, password, phone, email, gender);
        clearData(tableModelCustomer);
        showDataCustomer();
    }//GEN-LAST:event_btn_editcustomerActionPerformed

    private void btn_renewcustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_renewcustomerActionPerformed
        // TODO add your handling code here:
        renewCustomer();
    }//GEN-LAST:event_btn_renewcustomerActionPerformed

    private void table_customerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_customerMouseClicked
        // TODO add your handling code here:
        int row = table_customer.getSelectedRow();
        id_customer = Integer.valueOf(table_customer.getModel().getValueAt(row, 4).toString());
        getDataSQLCustomer();
        setDataCustomer();
    }//GEN-LAST:event_table_customerMouseClicked

    // ------------------------------------------------------ Employee ---------------------------------------------------------------\\

    private void table_employeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_employeeMouseClicked
        // TODO add your handling code here:
        int row = table_employee.getSelectedRow();
        id_employee = Integer.valueOf(table_employee.getModel().getValueAt(row, 5).toString());
        sqlHandler.getDataIdEmployee(id_employee);
        getDataSQLEmployee();
        setDataEmployee();
    }//GEN-LAST:event_table_employeeMouseClicked

    private void btn_addemployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addemployeeActionPerformed
        // TODO add your handling code here:
        try {
            getDataEmployee();
            sqlHandler.insertEmployee(username_employee, firstname_employee, lastname_employee, password_employee, phone_employee, email_employee, gender_employee, position_employee, salary_employee);
            refreshEmployee();
            renewProduct();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_addemployeeActionPerformed

    private void btn_editemployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editemployeeActionPerformed
        // TODO add your handling code here:
        getDataEmployee();
        sqlHandler.updateEmployee(id_employee, username_employee, firstname_employee, lastname_employee, password_employee, phone_employee, email_employee, gender_employee, position_employee, salary_employee);
        refreshEmployee();
    }//GEN-LAST:event_btn_editemployeeActionPerformed

    private void btn_renewemployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_renewemployeeActionPerformed
        // TODO add your handling code here:
        renewEmployee();
    }//GEN-LAST:event_btn_renewemployeeActionPerformed

    private void btn_delelteemplyeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delelteemplyeeActionPerformed
        // TODO add your handling code here:
        try {
            sqlHandler.deleteEmployee(id_employee);
            refreshEmployee();
            renewEmployee();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_delelteemplyeeActionPerformed

    private void btn_logout_inActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_logout_inActionPerformed
        // TODO add your handling code here:
        if(id_employee != 0) {
            id_employee = 0;
            this.dispose();
            new Login().setVisible(true);
        } else {
            this.dispose();
            new Login().setVisible(true);
        }
        
    }//GEN-LAST:event_btn_logout_inActionPerformed

    public void choseImg() {
        JFileChooser fileChose = new JFileChooser();
        FileNameExtensionFilter extendsImg = new FileNameExtensionFilter("Image", "png", "jpg");
        fileChose.setFileFilter(extendsImg);
        fileChose.setMultiSelectionEnabled(true);
        int x = fileChose.showDialog(this, "Open");
        if (x == JFileChooser.APPROVE_OPTION) {
            File file = fileChose.getSelectedFile();
            File o = new File(file.getPath());
            StringTokenizer strto = new StringTokenizer(file.getPath(), "\\");
            String name = null;
            while (strto.hasMoreTokens()) {
                name = strto.nextToken();
            }
            File n = new File("D:\\MyCode\\Java\\Advanced\\Theory\\ClothingShop\\src\\Image\\" + name);
            try {
                Files.copy(o.toPath(), n.toPath());
            } catch (Exception ex) {
            }
            lab_img.setIcon(ResizeImage("D:\\MyCode\\Java\\Advanced\\Theory\\ClothingShop\\src\\Image\\" + name));
            urlimg_44 = "D:\\MyCode\\Java\\Advanced\\Theory\\ClothingShop\\src\\Image\\" + name;
        }
    }

    public ImageIcon ResizeImage(String ImagePath) {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(lab_img.getWidth(), lab_img.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }

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
            java.util.logging.Logger.getLogger(Manager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Manager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Manager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Manager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Manager().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_addcustomer;
    private javax.swing.JButton btn_addemployee;
    private javax.swing.JButton btn_chosefile;
    private javax.swing.JButton btn_customer;
    private javax.swing.JButton btn_delelteemplyee;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_deletecustomer;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_editcustomer;
    private javax.swing.JButton btn_editemployee;
    private javax.swing.JButton btn_employee;
    private javax.swing.JButton btn_logout_in;
    private javax.swing.JButton btn_products;
    private javax.swing.JButton btn_renew;
    private javax.swing.JButton btn_renewcustomer;
    private javax.swing.JButton btn_renewemployee;
    private javax.swing.JButton btn_statistical;
    private javax.swing.ButtonGroup grouprbtn_gender;
    private javax.swing.ButtonGroup grouprbtn_genderemployee;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel381;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lab_countcustomer;
    private javax.swing.JLabel lab_countemployee;
    private javax.swing.JLabel lab_countproduct;
    private javax.swing.JLabel lab_img;
    private javax.swing.JPanel panel_customer;
    private javax.swing.JPanel panel_employee;
    private javax.swing.JPanel panel_main;
    private javax.swing.JPanel panel_menu;
    private javax.swing.JPanel panel_products;
    private javax.swing.JPanel panel_statistical;
    private javax.swing.JPanel panel_title;
    private javax.swing.JRadioButton rbtn_female;
    private javax.swing.JRadioButton rbtn_female_employee;
    private javax.swing.JRadioButton rbtn_male;
    private javax.swing.JRadioButton rbtn_male_employee;
    private javax.swing.JTable table_customer;
    private javax.swing.JTable table_employee;
    private javax.swing.JTable table_products;
    private javax.swing.JTextField textf_email;
    private javax.swing.JTextField textf_email_employee;
    private javax.swing.JTextField textf_fabric;
    private javax.swing.JTextField textf_firstname;
    private javax.swing.JTextField textf_firstname_employee;
    private javax.swing.JTextField textf_lastname;
    private javax.swing.JTextField textf_lastname_employee;
    private javax.swing.JTextField textf_madein;
    private javax.swing.JTextField textf_nameproduct;
    private javax.swing.JTextField textf_password;
    private javax.swing.JTextField textf_password_employee;
    private javax.swing.JTextField textf_phone;
    private javax.swing.JTextField textf_phone_employee;
    private javax.swing.JTextField textf_position_employee;
    private javax.swing.JTextField textf_price;
    private javax.swing.JTextField textf_quantity;
    private javax.swing.JTextField textf_salary_employee;
    private javax.swing.JTextField textf_types;
    private javax.swing.JTextField textf_username;
    private javax.swing.JTextField textf_username_employee;
    // End of variables declaration//GEN-END:variables
}
