/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import logica.SQLManager;

/**
 *
 * @author cardel
 */
public class ControladorConfiguraciones {

    SQLManager sqlManager;

    public ControladorConfiguraciones() {
        sqlManager = new SQLManager();
    }

    public String[] obtenerInformacionFactura() {
        String salida[];
        String[] selection = {"porcentajeIVA", "mensajeLegal"};
        String[] selection_type = {"double", "varchar"};
        String table = "ConfiguracionesFactura";
        String restriction = "";

        ArrayList<String[]> resultSet = sqlManager.select_query(selection, selection_type, table, restriction);

        salida = resultSet.get(0);

        return salida;
    }

    public String[] obtenerInformacionLegal() {
        String salida[];

        String[] selection = {"RazonSocial", "NIT", "direccion"};
        String[] selection_type = {"varchar", "varchar", "varchar"};
        String table = "ConfiguracionesGlobales";
        String restriction = "";

        ArrayList<String[]> resultSet = sqlManager.select_query(selection, selection_type, table, restriction);

        salida = resultSet.get(0);
        return salida;
    }

    public void cambiarInformacionLegal(String razonSocial, String NIT, String direccion) {
        String table = "ConfiguracionesGlobales";
        String[] selection = {"RazonSocial", "NIT", "direccion"};
        String[] value = {razonSocial, NIT, direccion};
        String[] type_value = {"varchar", "varchar", "varchar"};
        String condition = "";
        sqlManager.update_query(selection, value, type_value, table, condition);

    }

    public void cambiarInformacioNFactura(String IVA, String informacionLegal) {
        String table = "ConfiguracionesFactura";
        String condition = "";
        String[] selection = {"porcentajeIVA", "mensajeLegal"};
        String[] type_value = {"double", "varchar"};
        String[] value = {IVA, informacionLegal};
        sqlManager.update_query(selection, value, type_value, table, condition);

    }

}
