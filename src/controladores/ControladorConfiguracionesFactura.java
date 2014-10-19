/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import logica.SQLManager;
import entidades.ConfiguracionesFactura;
import java.util.ArrayList;

/**
 *
 * @author cardel
 */
public class ControladorConfiguracionesFactura {

    private SQLManager sqlManager;

    public ControladorConfiguracionesFactura() {
        sqlManager = new SQLManager();
    }

    public ConfiguracionesFactura obtenerConfiguracionActual() {
        String selection[] = {"PorcentajeIVA", "rangoFacturaIncial", "rangoFacturaFinal", "mensajeLegal"};
        String selection_type[] = {"double", "varchar", "varchar", "varchar"};
        String table = "ConfiguracionesFactura";
        String restriction = "";
        ArrayList <String[]> resultadoSet = sqlManager.select_query(selection, selection_type, table, restriction);
        String [] resultado = resultadoSet.get(0);
        
        double porcentajeIVA = Double.parseDouble(resultado[0]);
        String rangoInicialFactura = resultado[1];
        String rangoFinalFactura = resultado[2];
        String mensajeLegal = resultado[3];

        ConfiguracionesFactura configuracionFactura = new ConfiguracionesFactura(porcentajeIVA, rangoInicialFactura, rangoFinalFactura, mensajeLegal);
        return configuracionFactura;
    }

    public boolean cambiarConfiguracionActual(Double porcentaje, String rangoInicialFactura, String rangoFinalFactura, String mensajeLegal) {
        String selection[] = {"PorcentajeIVA", "rangoFacturaIncial", "rangoFacturaFinal", "mensajeLegal"};
        String value[] = {String.valueOf(porcentaje), rangoInicialFactura, rangoFinalFactura,mensajeLegal};
        String type_value[] = {"double", "varchar", "varchar", "varchar"};
        String table = "ConfiguracionesFactura";
        String condition = "";
        sqlManager.update_query(selection, value, type_value, table, condition);
        return true;
    }

}
