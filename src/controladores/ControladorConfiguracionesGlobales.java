/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import logica.SQLManager;
import entidades.ConfiguracionesGlobales;

/**
 *
 * @author cardel
 */
public class ControladorConfiguracionesGlobales {

    private SQLManager sQLManager;

    public ControladorConfiguracionesGlobales() {
        sQLManager = new SQLManager();
    }

    public ConfiguracionesGlobales obtenerConfiguracionActual() {
        String selection[] = {"RazonSocial", "NIT", "NombreAdministrador"};
        String selection_type[] = {"varchar", "varchar", "varchar"};
        String table = "ConfiguracionesGlobales";
        String restriction = "";
        String resultado[] = sQLManager.select_query(selection, selection_type, table, restriction);

        String razonSocial = resultado[0];
        String NIT = resultado[1];
        String NombreAdministrador = resultado[2];

        ConfiguracionesGlobales configuracionGlobal = new ConfiguracionesGlobales(razonSocial, NIT, NombreAdministrador);
        return configuracionGlobal;
    }

    public boolean cambiarConfiguracionGlobal(String razonSocial, String NIT, String NombreAdministrador) {
        String selection[] = {"RazonSocial", "NIT", "NombreAdministrador"};
        String value[] = {razonSocial, NIT, NombreAdministrador};
        String type_value[] = {"varchar", "varchar", "varchar"};;
        String table = "ConfiguracionesGlobales";
        String condition = "";
        sQLManager.update_query(selection, value, type_value, table, condition);
        return true;
    }

}
