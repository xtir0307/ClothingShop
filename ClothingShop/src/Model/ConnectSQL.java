/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xtir0
 */
public class ConnectSQL {

    private Connection conn = null;

    public ConnectSQL() {

    }

    public void connect() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://HL-PC\\SQLEXPRESS:1466;databaseName=midterm_shop";
            String username = "sa";
            String password = "1";
            try {
                conn = DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                Logger.getLogger(ConnectSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
        }
    }

    public ResultSet getData(String query) {
        ResultSet rs = null;
        try {
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void excute(String query) {
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        ConnectSQL s = new ConnectSQL();
        s.connect();
        String sql = "select * from products";

        ResultSet rs = s.getData(sql);
        while (rs.next()) {
            System.out.println(rs.getString("name_product"));
        }
    }
}
