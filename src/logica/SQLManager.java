/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor
 */
package logica;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor
 */
import static logica.Connect.connectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Joshua
 */
public class SQLManager {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    public SQLManager() {
        
    }

    public ArrayList<String[]> select_query(String[] selection, String[] selection_type, String table, String restriction) {
        conn = connectDB();
        ArrayList<String[]> result = new ArrayList<String[]>();
        String query = "SELECT ";

        for (int i = 0; i < selection.length; i++) {
            query += selection[i];
            if (i != selection.length - 1) {
                query += " , ";
            }
        }

        query += " FROM " + table + restriction;

        try {
            System.out.println(query);
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                String[] tempList = new String[selection_type.length];
                for (int i = 0; i < selection_type.length; i++) {
                    if (selection_type[i] == "int") {
                        tempList[i] = rs.getInt(selection[i]) + "";
                    }
                    if (selection_type[i] == "date") {
                        tempList[i] = rs.getDate(selection[i]) + "";
                    }
                    if (selection_type[i] == "varchar") {
                        tempList[i] = rs.getString(selection[i]);
                    }
                    if (selection_type[i] == "double") {
                        tempList[i] = Double.toString(rs.getDouble(selection[i]));
                    }
                }
                /*System.out.println("array");
                 for(int i = 0 ; i < tempList.length ; i++)
                 System.out.println(tempList[i]);
                 */
                result.add(tempList);
            }
        } catch (Exception e) {

        }
        System.out.println("array list");
        System.out.println(result.size());
        for (int i = 0; i < result.size(); i++) {
            String[] abd = (result.get(0));
            for (int j = 0; j < abd.length; j++) {
                System.out.println(abd[j]);
            }
        }
        System.out.println("termina");
        try {
            conn.close();
        } catch (Exception e) {

        }
        return result;
    }

    public ArrayList<String[]> insert_query(String[] selection, String[] value, String[] type_value, String table, String[] table_id, String[] type_table_id) {
        conn = connectDB();
        String query = "INSERT INTO " + table + "( ";
        ArrayList<String[]> return_id = new ArrayList<String[]>();

        for (int i = 0; i < selection.length; i++) {
            query += selection[i];
            if (i != selection.length - 1) {
                query += " , ";
            }
        }
        query += ") VALUES ( ";

        for (int i = 0; i < value.length; i++) {
            if (type_value[i] == "int" || type_value[i] == "double") {
                query += value[i];
            } else {
                query += "'" + value[i] + "'";
            }

            if (i != value.length - 1) {
                query += " , ";
            }
        }

        query += ");";

        try {
            System.out.println(query);

            ps = conn.prepareStatement(query);
            ps.executeUpdate();
            String query_get_producto_id = "select ";

            for (int i = 0; i < table_id.length; i++) {
                query_get_producto_id += table_id[i];
                if (i != table_id.length) {
                    query_get_producto_id += " , ";
                }
            }

            for (int i = 0; i < selection.length; i++) {
                query_get_producto_id += selection[i];
                if (i != selection.length - 1) {
                    query_get_producto_id += " , ";
                }
            }

            query_get_producto_id += " from " + table + " order by ";
            for (int i = 0; i < table_id.length; i++) {
                query_get_producto_id += table_id[i];
                if (i != table_id.length - 1) {
                    query_get_producto_id += " , ";
                }
            }

            query_get_producto_id += " desc limit 1";

            System.out.println(query_get_producto_id);

            ps = conn.prepareStatement(query_get_producto_id);
            rs = ps.executeQuery();

            if (rs.next()) {
                String[] result = new String[table_id.length];
                for (int i = 0; i < table_id.length; i++) {
                    if (type_table_id[i] == "int") {
                        result[i] = rs.getInt(table_id[i]) + "";
                    }
                    if (type_table_id[i] == "date") {
                        result[i] = rs.getDate(table_id[i]) + "";
                    }
                    if (type_table_id[i] == "varchar") {
                        result[i] = rs.getString(table_id[i]);
                    }
                    if (type_table_id[i] == "double") {
                        result[i] = Double.toString(rs.getDouble(table_id[i]));
                    }
                }
                return_id.add(result);
            }

        } catch (Exception e) {

        }
        try {
            conn.close();
        } catch (Exception e) {

        }
        return return_id;
    }

    public void update_query(String[] selection, String[] value, String[] type_value, String table, String condition) {
        conn = connectDB();
        String query = "UPDATE " + table + " ";
        query += " SET ";
        for (int i = 0; i < selection.length; i++) {
            query += selection[i] + " = ";
            if (type_value[i] == "int" || type_value[i] == "double") {
                query += value[i] + " ";
            } else {
                query += "'" + value[i] + "' ";
            }
            if (i != selection.length - 1) {
                query += " , ";
            }
        }
        query += condition;
        System.out.println(query);
        try {
            ps = conn.prepareStatement(query);
            ps.executeUpdate();
        } catch (Exception e) {

        }
        try {
            conn.close();
        } catch (Exception e) {

        }

    }

    public void delete_query(String table, String condition) {
        conn = connectDB();
        String query = "Delete from " + table + " " + condition;
        System.out.println(query);
        try {
            ps = conn.prepareStatement(query);
            ps.executeUpdate();
        } catch (Exception e) {
        }
        try {
            conn.close();
        } catch (Exception e) {

        }
    }

    /*
     Este método es para una consulta que retorna una sola fila.
     */
    public String[] select_unary_custom_query(String[] columnas, String tabla, String restriccion) {
        String resultado[] = new String[columnas.length];
        String query = "SELECT";
        for (int i = 0; i < columnas.length; i++) {
            query += " " + columnas[i] + ",";
        }

        query += "FROM " + tabla + " " + restriccion;

        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                for (int i = 0; i < columnas.length; i++) {
                    resultado[i] = (String) rs.getObject(i);
                }
            }
        } catch (Exception e) {
            System.err.println("Error ejecutando consulta " + query);
        }
        try {
            conn.close();
        } catch (Exception e) {

        }

        return resultado;
    }

}
