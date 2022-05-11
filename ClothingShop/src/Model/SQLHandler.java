/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xtir0
 */
public class SQLHandler {

    public ConnectSQL conn = new ConnectSQL();

    public SQLHandler() {
        conn.connect();
    }

    public ResultSet getDataProducts() {
        String query = "select * from products order by id_product";
        return conn.getData(query);
    }

    public ResultSet getDataIdProductId(int id) {
        String query = "select * from products where id_product = '" + id + "'";
        return conn.getData(query);
    }

    public void insertProduct(String name_product, String type_product, String fabric, String madein, Double price, int quantity, String urlimg) {
        String query = "insert into products(name_product, type_product, fabric, madein, price, quantity, urlimg) values(N'" + name_product + "', N'" + type_product + "', N'" + fabric + "', N'" + madein + "', " + price + ", " + quantity + ", '" + urlimg + "')";
        conn.excute(query);
    }

    public void deleteProduct(int id) {
        String query = "delete from products where id_product = '" + id + "'";
        conn.excute(query);
    }

//    public void deleteProductCart(int id_customer, int id_product) {
//        String query = "delete from cart where id_customer = '" + id_customer + "' and id_product = '" + id_product + "'";
//        conn.excute(query);
//    }

    public void deleteProductCart(int id_cart,int id_customer) {
        String query = "delete from cart where id_cart = '" + id_cart + "' and id_customer = '"+id_customer+"'";
        conn.excute(query);
    }
    
    public void deleteAllProductCart(int id_customer) {
        String query = "delete from cart where id_customer = '"+id_customer+"'";
        conn.excute(query);
    }

    public void updateProduct(int id_product, String name_product, String type_product, String fabric, String madein, Double price, int quantity, String urlimg) {
        String query = "update products set name_product=N'" + name_product + "', type_product= N'" + type_product + "', fabric= '" + fabric + "', madein= N'" + madein + "', price= " + price + ", quantity= " + quantity + ", urlimg= '" + urlimg + "' where id_product='" + id_product + "'";
        conn.excute(query);
    }

    public ResultSet getDataCustomer() {
        String query = "select * from customers order by id_customer";
        return conn.getData(query);
    }

    public ResultSet getDataIdCustomer(int id) {
        String query = "select * from customers where id_customer = '" + id + "'";
        return conn.getData(query);
    }

    public ResultSet getDataUsernameCustomer(String username) {
        String query = "select * from customers where username = '" + username + "'";
        return conn.getData(query);
    }
    
    public String getNameCustomer(int id) {
        String name = null;
        ResultSet rs = null;
        String query = "select * from customers where id_customer = " + id;
        rs = conn.getData(query);
        try {
            while(rs.next()) {
                name = rs.getString("firstname") +""+  rs.getString("lastname");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name;
    }

    public void insertCustomer(String username, String firstname, String lastname, String password, String phone, String email, int gender) {
        String query = "insert into customers(username, firstname, lastname, passwordu, phone, email, gender) values(N'" + username + "', N'" + firstname + "', N'" + lastname + "', N'" + password + "', '" + phone + "', '" + email + "', " + gender + ")";
        conn.excute(query);
    }

    public void deleteCustomer(int id) {
        String query = "delete from customers where id_customer = '" + id + "'";
        conn.excute(query);
    }

    public void updateCustomer(int id, String username, String firstname, String lastname, String password, String phone, String email, int gender) {
        String query = "update customers set username=N'" + username + "', firstname= N'" + firstname + "', lastname= N'" + lastname + "', passwordu= '" + password + "', phone= '" + phone + "', email='" + email + "', gender= " + gender + " where id_customer='" + id + "'";
        conn.excute(query);
    }

    public void updateProfile(String username, String firstname, String lastname, String password, String phone, String email, int gender) {
        String query = "update customers set firstname= N'" + firstname + "', lastname= N'" + lastname + "', passwordu= '" + password + "', phone= '" + phone + "', email='" + email + "', gender= " + gender + " where username='" + username + "'";
        conn.excute(query);
    }

    // Employee
    public ResultSet getDataEmployee() {
        String query = "select * from employee order by id_employee";
        return conn.getData(query);
    }

    public ResultSet getDataIdEmployee(int id) {
        String query = "select * from employee where id_employee = '" + id + "'";
        return conn.getData(query);
    }

    public void insertEmployee(String username, String firstname, String lastname, String password, String phone, String email, int gender, String position, double salary) {
        String query = "insert into employee(username, firstname, lastname, passwordu, phone, email, gender, position, salary) values(N'" + username + "', N'" + firstname + "', N'" + lastname + "', N'" + password + "', '" + phone + "', '" + email + "', " + gender + ", '" + position + "', " + salary + ")";
        conn.excute(query);
    }

    public void deleteEmployee(int id) {
        String query = "delete from employee where id_employee = '" + id + "'";
        conn.excute(query);
    }

    public void updateEmployee(int id, String username, String firstname, String lastname, String password, String phone, String email, int gender, String position, double salary) {
        String query = "update employee set username=N'" + username + "', firstname= N'" + firstname + "', lastname= N'" + lastname + "', passwordu= '" + password + "', phone= '" + phone + "', email='" + email + "', gender= " + gender + ", position = N'" + position + "' where id_employee='" + id + "'";
        conn.excute(query);
    }

    public boolean checkUser(String username, String password) {

        String query = "select * from customers where username = '" + username + "' and passwordu = '" + password + "'";
        int n = 0;
        ResultSet rs = conn.getData(query);
        try {
            while (rs.next()) {
                n += 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (n == 0) {
            return false;
        }
        return true;
    }

    public int getIdCustomer(String username, String password) {
        String query = "select * from customers where username = '" + username + "' and passwordu = '" + password + "'";
        int id_customer = 0;
        ResultSet rs = conn.getData(query);
        try {
            while (rs.next()) {
                id_customer = rs.getInt("id_customer");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id_customer;
    }

    public int getIdEmployee(String username, String password) {
        String query = "select * from employee where username = '" + username + "' and passwordu = '" + password + "'";
        int id_employee = 0;
        ResultSet rs = conn.getData(query);
        try {
            while (rs.next()) {
                id_employee = rs.getInt("id_employee");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id_employee;
    }
    
    public int checksignin(String username) {
        String querye = "select * from employee where username = '"+username+"'";
        String queryc = "select * from customers where username = '"+username+"'";
        int id= 0;
        ResultSet rse = conn.getData(querye);
        ResultSet rsc = conn.getData(queryc);
        try {
            while (rse.next()) {
                id = rse.getInt(1);
            }
            while (rsc.next()) {
                id = rsc.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id;
    }
    public void signUpCustomer(String username, String password, String firstname, String lastname, String phone, String email, int gender) {
        String query = "insert into customers(username, firstname, lastname, passwordu, phone, email, gender) values(N'" + username + "', N'" + firstname + "', N'" + lastname + "', N'" + password + "', '" + phone + "', '" + email + "', " + gender + ")";
        conn.excute(query);
    }

    public void addProducttoCart(int id_customer, int id_product, int quantity) {
        String query = "insert into cart(id_customer, id_product, quantity_orders) values(" + id_customer + ", " + id_product + ", " + quantity + ")";
        conn.excute(query);
    }

    public ResultSet getDataProductsCart(int id_customer) {
        String query = "select * from products, customers, cart where products.id_product = cart.id_product and cart.id_customer = customers.id_customer and customers.id_customer = " + id_customer;
        return conn.getData(query);
    }

    public ResultSet countCustomer() {
        String query = "select count( *) from customers ";
        return conn.getData(query);
    }

    public ResultSet countEmployee() {
        String query = "select count( *) from employee ";
        return conn.getData(query);
    }

    public ResultSet countProduct() {
        String query = "select count( *) from products ";
        return conn.getData(query);
    }

}
