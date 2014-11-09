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

    public ArrayList<Factura> getFactura(String restriction) {
        String[] selection = {"factura_id", "cliente_id", "fecha", "estado", "valor"};
        String[] selection_type = {"int", "int", "varchar", "varchar", "double"};
        String table = "Factura";

        ArrayList<String[]> resultSet = sqlManager.select_query(selection, selection_type, table, restriction);
        
        ArrayList<Factura> listaDeFactura = new ArrayList<>();
        //Pendiente

        for (int i = 0; i < resultSet.size(); i++) {
            String[] resultado = resultSet.get(i);
            int factura_id = Integer.parseInt(resultado[0]);
            int cliente_id = Integer.parseInt(resultado[1]);
            String fecha = resultado[2];
            String estado = resultado[3];
            Double valor = Double.parseDouble(resultado[4]);
           
            Factura factura = new Factura(factura_id, cliente_id, fecha, estado, valor);
            listaDeFactura.add(factura);

        }
        return listaDeFactura;
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
