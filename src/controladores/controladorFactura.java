/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.Factura ;
import logica.SQLManager;

/**
 *
 * @author Joshua
 */

public class controladorFactura {
    
    SQLManager sqlManager;
    
    //Controladores
    public controladorFactura()
    {
        sqlManager = new SQLManager();
    }
    
    public Factura getFactura(String restriction)
    {
        String [] selection = {"factura_id","cliente_id","fecha","estado","valor"};
        String [] selection_type = {"int","int","varchar","char","double"};
        String table = "Factura";
        
        String [] result = sqlManager.select_query(selection, selection_type, table, restriction);
        
        Factura factura = new Factura(Integer.parseInt(result[0]),Integer.parseInt(result[1]),result[2],result[3].charAt(0),Double.parseDouble(result[4]));
        
        return factura;
    }
     //Comentario importante!!!! No olvidar lo del flujo, queda pendiente
     public String [] insertFactura(String [] value)
     {
        String [] selection = {"cliente_id","fecha","estado","valor"};
        String [] type_value = {"int","varchar","char","double"};
        String [] table_id = {"factura_id"};
        String [] type_table_id = {"int"};
        String [] result = sqlManager.insert_query(selection, value,type_value, "Factura" , table_id, type_table_id);
        return result;
     }
     
     public void updateFactura(String [] selection,String [] value,String [] type_value, String condition)
     {
        String table = "Factura";
        sqlManager.update_query(selection , value , type_value , table , condition);
     }
     
     public void deleteFactura( String condition)
     {
         sqlManager.delete_query("Factura" , condition);
     }
    
}
