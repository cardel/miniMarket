/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.Flujo_Factura;
import java.util.ArrayList;
import logica.SQLManager;

/**
 *
 * @author Joshua
 */
public class ControladorFlujoFactura {
    
    SQLManager sqlManager;
    
    //Controladores
    public ControladorFlujoFactura()
    {
        sqlManager = new SQLManager();
    }
    
    public Flujo_Factura getFlujo_Factura(String restriction)
    {
        String [] selection = {"flujo_Factura_id","factura_id","tipo_flujo","fecha","valor"};
        String [] selection_type = {"int","int","char","date","double"};
        String table = "Flujo_Factura";
        
        ArrayList<String[]> resultSet = sqlManager.select_query(selection, selection_type, table, restriction);
        String [] result = resultSet.get(0);
        
        Flujo_Factura flujo_factura = new Flujo_Factura(Integer.parseInt(result[0]),Integer.parseInt(result[1]),result[2].charAt(0),result[3],Double.parseDouble(result[4]));
        
        return flujo_factura;
    }
     
     public ArrayList<String[]> insertFlujo_Factura(String [] value)
     {
        String [] selection = {"factura_id","tipo_flujo","fecha","valor"};
        String [] type_value = {"int","char","date","double"};
        String [] table_id = {"flujo_Factura_id"};
        String [] type_table_id = {"int"};
        ArrayList<String[]> result = sqlManager.insert_query(selection, value,type_value, "Flujo_Factura" , table_id, type_table_id);
        return result;
     }
     
     public void updateFlujo_Factura(String [] selection,String [] value,String [] type_value, String condition)
     {
        String table = "Flujo_Factura";
        sqlManager.update_query(selection , value , type_value , table , condition);
     }
     
     public void deleteFlujo_Factura( String condition)
     {
         sqlManager.delete_query("Flujo_Factura" , condition);
     }
    
}
