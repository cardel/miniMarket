/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.Productos;
import java.util.ArrayList;
import logica.SQLManager;

/**
 *
 * @author Joshua
 */
public class ControladorProducto {
    
    SQLManager sqlManager;
    
    //Controladores
    public ControladorProducto()
    {
        sqlManager = new SQLManager();
    }
    
    public ArrayList<Productos> getProducto(String restriction)
    {
        String [] selection = {"producto_id","nombre","descripcion","unidades","precio"};
        String [] selection_type = {"int","varchar","varchar","int","double"};
        String table = "Producto";
        
        ArrayList<String[]> resultSet = sqlManager.select_query(selection, selection_type, table, restriction);
        
        ArrayList<Productos> listaDeProductos = new ArrayList<>();
        //Pendiente

        for (int i = 0; i < resultSet.size(); i++) {
            String[] resultado = resultSet.get(i);
            int producto_id = Integer.parseInt(resultado[0]);
            String nombre = resultado[1];
            String descripcion = resultado[2];
            int unidades = Integer.parseInt(resultado[3]);
            double precio = Double.parseDouble(resultado[4]);
            Productos producto = new Productos(producto_id, nombre, descripcion, unidades, precio);
            listaDeProductos.add(producto);

        }
        
        return listaDeProductos;
    }
     public boolean insertProduct(String [] value)
     {
        String [] selection = {"nombre","descripcion","unidades","precio"};
        String [] type_value = {"varchar","varchar","int","double"};
        String [] table_id = {"producto_id"};
        String [] type_table_id = {"int"};
        sqlManager.insert_query(selection, value,type_value, "Producto" , table_id, type_table_id);
        return true;
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
