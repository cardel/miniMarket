/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.Factura_Productos;
import java.util.ArrayList;
import logica.SQLManager;

/**
 *
 * @author Joshua
 */
public class ControladorFactura_Productos {
    
    SQLManager sqlManager;
    
    //Controladores
    public ControladorFactura_Productos()
    {
        sqlManager = new SQLManager();
    }
    
    /*
    private int factura_id;
    private int producto_id;
    private int unidades;
    private double precio;
    */
    public Factura_Productos getFactura_Productos(String restriction)
    {
        String [] selection = {"factura_id","producto_id","unidades","precio"};
        String [] selection_type = {"int","int","int","double"};
        String table = "Factura_Productos";
        
        ArrayList<String[]> resultSet = sqlManager.select_query(selection, selection_type, table, restriction);
        String [] result = resultSet.get(0);
        Factura_Productos factura_productos = new Factura_Productos(Integer.parseInt(result[0]),Integer.parseInt(result[1]),Integer.parseInt(result[2]),Double.parseDouble(result[3]));
        
        return factura_productos;
    }
     //Comentario importante!!!! No olvidar lo del flujo, queda pendiente
     public ArrayList<String[]> insertFactura_Productos(String [] value)
     {
        String [] selection = {"factura_id","producto_id","unidades","precio"};
        String [] type_value = {"int","int","int","double"};
        String [] table_id = {"factura_id","producto_id"};
        String [] type_table_id = {"int","int"};
        ArrayList<String[]> result = sqlManager.insert_query(selection, value,type_value, "Factura_Productos" , table_id, type_table_id);
        return result;
     }
     
     public void updateFactura_Productos(String [] selection,String [] value,String [] type_value, String condition)
     {
        String table = "Factura_Productos";
        sqlManager.update_query(selection , value , type_value , table , condition);
     }
     
     public void deleteFactura_Productos( String condition)
     {
         sqlManager.delete_query("Factura_Productos" , condition);
     }
    
}
