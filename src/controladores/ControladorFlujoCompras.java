/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.Flujo_Compra;
import java.util.ArrayList;
import java.util.Calendar;
import logica.SQLManager;

/**
 *
 * @author cardel
 */
public class ControladorFlujoCompras {

    SQLManager sqlManager;

    //Controladores
    public ControladorFlujoCompras() {
        sqlManager = new SQLManager();
    }

    public ArrayList<Flujo_Compra> obtenerFlujosCompras(String restriction) {

        ArrayList<Flujo_Compra> listaCompras = new ArrayList<Flujo_Compra>();
        String[] selection = {"ID_Flujo_Compra", "tipo_flujo", "ID_CompraProveedor", "fecha", "valor"};
        String[] selection_type = {"int", "varchar", "int", "varchar", "double"};
        String table = "Flujo_Compra";

        ArrayList<String[]> resultSet = sqlManager.select_query(selection, selection_type, table, restriction);

        //Pendiente
        for (int i = 0; i < resultSet.size(); i++) {

            String[] dato = resultSet.get(i);
            Flujo_Compra flujo_Compra = new Flujo_Compra();
            flujo_Compra.setID(Integer.parseInt(dato[0]));
            flujo_Compra.setTipo_flujo(dato[1]);
            flujo_Compra.setID_CompraProveedor(Integer.parseInt(dato[2]));
            flujo_Compra.setFecha(dato[3]);
            flujo_Compra.setMonto(Double.parseDouble(dato[4]));

            listaCompras.add(flujo_Compra);

        }
        return listaCompras;
    }

    public void editarValorFlujoCompra(String ID_Flujo_Compra, String valor) {
        String[] selection = {"valor"};
        String[] type_value = {"int"};
        String[] table_id = {"ID_Flujo_Compra"};

        String[] type_table_id = {"int"};
        String value[] = {valor};

        String condition = " where ID_Flujo_Compra=" + ID_Flujo_Compra;

        String table = "Flujo_Compra";
        sqlManager.update_query(selection, value, type_value, table, condition);
    }

    public void registrarFlujoAbono(String ID_Proveedor_Compra, String valor) {
        String[] selection = {"tipo_flujo", "ID_CompraProveedor", "fecha", "valor"};
        String[] type_value = {"varchar", "int", "varchar", "double"};

        Calendar calendario = Calendar.getInstance();
        String dia = Integer.toString(calendario.get(Calendar.DATE));
        String mes = Integer.toString(calendario.get(Calendar.MONTH));
        String annio = Integer.toString(calendario.get(Calendar.YEAR));

        String fecha = annio + "-" + mes + "-" + dia;

        String value[] = {"abono", ID_Proveedor_Compra, fecha, valor};
        String[] table_id = {"ID_Flujo_Compra"};
        String[] type_table_id = {"int"};
        sqlManager.insert_query(selection, value, type_value, "Flujo_Compra", table_id, type_table_id);

    }

    public void registrarFlujoDeuda(String ID_Proveedor_Compra, String valor) {
        String[] selection = {"tipo_flujo", "ID_CompraProveedor", "fecha", "valor"};
        String[] type_value = {"varchar", "int", "varchar", "double"};

        Calendar calendario = Calendar.getInstance();
        String dia = Integer.toString(calendario.get(Calendar.DATE));
        String mes = Integer.toString(calendario.get(Calendar.MONTH));
        String annio = Integer.toString(calendario.get(Calendar.YEAR));

        String fecha = annio + "-" + mes + "-" + dia;

        String value[] = {"deuda", ID_Proveedor_Compra, fecha, valor};
        String[] table_id = {"ID_Flujo_Compra"};
        String[] type_table_id = {"int"};
        sqlManager.insert_query(selection, value, type_value, "Flujo_Compra", table_id, type_table_id);
    }
}
