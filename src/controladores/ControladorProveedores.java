/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.Proveedores;
import java.util.ArrayList;
import logica.SQLManager;

/**
 *
 * @author Joshua
 */
public class ControladorProveedores {

    private SQLManager sQLManager;

    public ControladorProveedores() {
        sQLManager = new SQLManager();
    }
    
    public ArrayList<Proveedores> obtenerProveedores(String Identificacion , String nombreIn) {
        String selection[] = {"Identificacion", "nombre"};
        String selection_type[] = {"varchar", "varchar"};
        String table = "Proveedores";

        boolean nombreVacio = !nombreIn.equals("");
        boolean identificacionVacia = !Identificacion.equals("");

        String restriction = "";
        if (nombreVacio && identificacionVacia) {
            restriction = " where nombre like \"%" + nombreIn + "%\" and Identificacion like \"%" + Identificacion + "%\"";
        }
        if (!nombreVacio && identificacionVacia) {
            restriction = " where Identificacion like \"%" + Identificacion + "%\"";
        }
        if (nombreVacio && !identificacionVacia) {
            restriction = " where nombre like \"%" + nombreIn + "%\"";
        }
        
        ArrayList<String[]> resultadoSet = sQLManager.select_query(selection, selection_type, table, restriction);

        ArrayList<Proveedores> listaDeProveedores = new ArrayList<>();

        for (int i = 0; i < resultadoSet.size(); i++) {
            String[] resultado = resultadoSet.get(i);
            String ident = resultado[0];            
            String nombre = resultado[1];            
            Proveedores proveedor = new Proveedores(ident, nombre);
            listaDeProveedores.add(proveedor);

        }

        return listaDeProveedores;
    }
        
        public boolean editarProveedores(String Identificacion, String nombre) {

        //cliente_id Es la identificación.
        String selection[] = { "nombre"};
        String value[] = { nombre};
        String type_value[] = {"varchar", "varchar"};

        String table = "Proveedores";
        String condition = " where Identificacion=" + Identificacion;
        sQLManager.update_query(selection, value, type_value, table, condition);
        return true;

    }
    
    public boolean agregarProveedores( String Identificacion ,  String nombre) {
        //cliente_id Es la identificación.
        String selection[] = { "Identificacion", "nombre"};
        String value[] = {  Identificacion, nombre};
        String type_value[] = {"varchar", "varchar"};

        String table = "Proveedores";
        //String condition = " where cliente_id=" + cliente_id;
        String table_id[] = {"ID"};
        String type_table_id[] = {"int"};
        sQLManager.insert_query(selection, value, type_value, table, table_id, type_table_id);
        return true;

    }
    
}

