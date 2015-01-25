/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import java.util.Calendar;
import logica.SQLManager;

/**
 *
 * @author cardel
 */
public class ControladorCompraProveedor {

    private SQLManager sQLManager;

    public ControladorCompraProveedor() {
        sQLManager = new SQLManager();
    }

    public void crearNuevaCompra(String IDproveedor, String monto) {
        Calendar calendario = Calendar.getInstance();
        String dia = Integer.toString(calendario.get(Calendar.DATE));
        String mes = Integer.toString(calendario.get(Calendar.MONTH));
        String annio = Integer.toString(calendario.get(Calendar.YEAR));
        //Date date = new Date();
        //DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        //String hora = hourFormat.format(date);

        String fecha = annio + "-" + mes + "-" + dia;

        String selection[] = {"IDProveedor", "MontoCompra", "fecha"};
        String value[] = {IDproveedor, monto, fecha};
        String type_value[] = {"int", "int", "varchar"};
        String table = "Compra_Proveedores";

        String table_id[] = {"ID_Compra_Proveedor"};
        String type_table_id[] = {"int"};
        sQLManager.insert_query(selection, value, type_value, table, table_id, type_table_id);
    }

    public ArrayList<CompraProveedores> obtenerCompraProveedoresPorRestriccion(String restriccion) {
        ArrayList<CompraProveedores> listaCompraproveedores = new ArrayList<CompraProveedores>();
        String selection[] = {"ID_Compra_Proveedor", "IDProveedor", "MontoCompra", "fecha"};
        String selection_type[] = {"int", "int", "int", "varchar"};
        String table = "Compra_Proveedores";
        String restriction = restriccion;
        ArrayList<String[]> resultadoSet = sQLManager.select_query(selection, selection_type, table, restriction);

        for (int i = 0; i < resultadoSet.size(); i++) {
            String[] dato = resultadoSet.get(i);
            CompraProveedores compraProveedores = new CompraProveedores();
            compraProveedores.setID(Integer.parseInt(dato[0]));
            compraProveedores.setIDProveedor(Integer.parseInt(dato[1]));
            compraProveedores.setMontoCompra(Double.parseDouble(dato[2]));
            compraProveedores.setFecha(dato[3]);

            listaCompraproveedores.add(compraProveedores);
        }

        return listaCompraproveedores;
    }

    public void editarCompraProveedor(String ID, String monto) {
        String[] selection = {"MontoCompra"};
        String[] type_value = {"int"};
        String[] table_id = {"ID_Compra_Proveedor"};

        String[] type_table_id = {"int"};
        String value[] = {monto};

        String condition = " where ID_Compra_Proveedor=" + ID;

        String table = "Compra_Proveedores";
        sQLManager.update_query(selection, value, type_value, table, condition);

    }

    public void eliminarCompraProveedor(String condition) {
        sQLManager.delete_query("Compra_Proveedores", condition);
    }
}
