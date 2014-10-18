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

/**
 *
 * @author Joshua
 */
public class SQLManager {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    public SQLManager(){
        conn = connectDB();
    }
    
    public String[] select_query(String [] selection , String [] selection_type , String table , String restriction )
    {
        String [] result = new String [selection.length];
        String query = "SELECT ";
        
        for(int i = 0 ; i < selection.length ; i++)
        {
            query += selection[i];
            if(i!=selection.length - 1)
                query += " , ";
        }
            
        query += " FROM " + table + restriction;
        
        try{
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            int contador = 0;
            while(rs.next()){
                for(int i = 0 ; i < selection_type.length ; i++)
                {
                    if(selection_type[i] == "int")
                        result[i]= rs.getInt(selection[i])+"";
                    if(selection_type[i] == "date")
                        result[i]= rs.getDate(selection[i])+"";
                    if(selection_type[i] == "varchar")
                        result[i]= rs.getString(selection[i]);
                    if(selection_type[i] == "double")
                        result[i]= Double.toString(rs.getDouble(selection[i]));
                }
            }
        }
        catch (Exception e)
        {
            
        }
        return result;
    }
    
    public String [] insert_query(String [] selection , String [] value , String [] type_value, String table , String [] table_id , String [] type_table_id )
    {
        String query = "INSERT INTO "+table+"( ";
        String return_id[] = new String [table_id.length];
        
        for(int i = 0 ; i < selection.length ; i++)
        {
            query += selection[i];
            if(i!=selection.length - 1)
                query += " , ";
        }
        query += ") VALUES ( ";
        
        for(int i = 0 ; i < value.length ; i++)
        {
            if(type_value[i]=="int" || type_value[i]=="double")
                query += value[i];
            else
                query += "'"+value[i]+"'";
            
            if(i!=value.length - 1)
                query += " , ";
        }
        
        query += ");";
        
        try{
            System.out.println(query);
            ps = conn.prepareStatement(query);
            ps.executeUpdate();
            String query_get_producto_id = "select producto_id,nombre,descripcion,unidades,precio from Producto order by producto_id desc limit 1";
            ps = conn.prepareStatement(query_get_producto_id);
            rs = ps.executeQuery();
            
            if(rs.next())
            {
                for(int i = 0 ; i < table_id.length ; i++)
                {
                    if(type_table_id[i] == "int")
                        return_id[i]= rs.getInt(table_id[i])+"";
                    if(type_table_id[i] == "date")
                        return_id[i]= rs.getDate(table_id[i])+"";
                    if(type_table_id[i] == "varchar")
                        return_id[i]= rs.getString(table_id[i]);
                    if(type_table_id[i] == "double")
                        return_id[i]= Double.toString(rs.getDouble(table_id[i]));
                }
                
            }
        }
        catch(Exception e)
        {
        
        }
        return return_id;
    }
    
    public void update_query(String [] selection , String [] value ,String [] type_value , String table , String condition)
    {
        String query = "UPDATE " + table + " ";
        for(int i = 0 ; i < selection.length ; i++)
        {
            query += " SET " + selection[i]+ " = ";
            if(type_value[i]=="int" || type_value[i]=="double")
                query += value[i]+" ";
            else
                query += "'"+value[i]+"' ";
            if(i!=selection.length - 1)
                query += " , ";
        }
        query += condition;
        try{
            ps = conn.prepareStatement(query);
            ps.executeUpdate();
        }
        catch(Exception e)
        {
        
        }
        
    }
    
    public void delete_query (String table , String condition)
    {
        String query = "Delete from "+table+" "+condition;
        System.out.println(query);
        try{
            ps = conn.prepareStatement(query);
            ps.executeUpdate();
        }
        catch(Exception e)
        {
        }
    }
    
}
