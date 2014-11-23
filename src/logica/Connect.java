/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor
 */
package logica;

/**
 *
 * @author Joshua
 */
import java.sql.*;

public class Connect {

    static Connection conn = null;

    public static Connection connectDB() {
        try {
            String sDriverName = "org.sqlite.JDBC";
            Class.forName(sDriverName);
            Connection conn = DriverManager.getConnection("jdbc:sqlite:database1.sqlite");
            //JOptionPane.showMessageDialog(null, "Connect");
            return conn;
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
            System.out.println(e.toString());
        }
        return null;
    }
}
