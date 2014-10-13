/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.Productos;
import logica.SQLManager;

/**
 *
 * @author Joshua
 */
public class controladorProducto {
    
    SQLManager sqlManager;
    
    //Controladores
    public controladorProducto()
    {
        sqlManager = new SQLManager();
    }
    
    public Productos getProducto(String restriction)
    {
        String [] selection = {"producto_id","nombre","descripcion","unidades","precio"};
        String [] selection_type = {"int","varchar","varchar","int","double"};
        String table = "Producto";
        
        String [] result = sqlManager.select_query(selection, selection_type, table, restriction);
        
        Productos producto = new Productos(Integer.parseInt(result[0]),result[1],result[2],Integer.parseInt(result[3]),Double.parseDouble(result[4]));
        
        return producto;
    }
     public String [] insertProduct(String [] value)
     {
        String [] selection = {"nombre","descripcion","unidades","precio"};
        String [] type_value = {"varchar","varchar","int","double"};
        String [] table_id = {"producto_id"};
        String [] type_table_id = {"int"};
        String [] result = sqlManager.insert_query(selection, value,type_value, "Producto" , table_id, type_table_id);
        return result;
     }
     
     public void updateProduct(String [] selection,String [] value,String [] type_value, String condition)
     {
        String table = "Producto";
        sqlManager.update_query(selection , value , type_value , table , condition);
     }
     
     public void deleteProduct( String condition)
     {
         sqlManager.delete_query("Producto" , condition);
     }
    
}
