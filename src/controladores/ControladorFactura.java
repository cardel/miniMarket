/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.Factura;
import java.util.ArrayList;
import logica.SQLManager;

/**
 *
 * @author Joshua
 */
public class ControladorFactura {

    SQLManager sqlManager;

    //Controladores
    public ControladorFactura() {
        sqlManager = new SQLManager();
    }

    public Factura getFactura(String restriction) {
        String[] selection = {"factura_id", "cliente_id", "fecha", "estado", "valor"};
        String[] selection_type = {"int", "int", "varchar", "char", "double"};
        String table = "Factura";

        ArrayList<String[]> resultSet = sqlManager.select_query(selection, selection_type, table, restriction);
        String[] result = resultSet.get(0);

        Factura factura = new Factura(Integer.parseInt(result[0]), Integer.parseInt(result[1]), result[2], result[3].charAt(0), Double.parseDouble(result[4]));

        return factura;
    }

    //Comentario importante!!!! No olvidar lo del flujo, queda pendiente

    public ArrayList<String[]> insertFactura(String[] value) {
        String[] selection = {"cliente_id", "fecha", "estado", "valor"};
        String[] type_value = {"int", "varchar", "char", "double"};
        String[] table_id = {"factura_id"};
        String[] type_table_id = {"int"};
        ArrayList<String[]> resultSet = sqlManager.insert_query(selection, value, type_value, "Factura", table_id, type_table_id);

        return resultSet;
    }

    public void updateFactura(String[] selection, String[] value, String[] type_value, String condition) {
        String table = "Factura";
        sqlManager.update_query(selection, value, type_value, table, condition);
    }

    public void deleteFactura(String condition) {
        sqlManager.delete_query("Factura", condition);
    }

    public void cambiarEstadoFactura(String factura_id, String estado) {
        String[] selection = {"estado"};
        String[] type_value = { "varchar"};
        String[] table_id = {"factura_id"};
        
        String[] type_table_id = {"int"};
        String value[]={estado};
        
        String condition = " where factura_id="+factura_id;
        
        String table = "Factura";
        sqlManager.update_query(selection, value, type_value, table, condition);
    }

}
