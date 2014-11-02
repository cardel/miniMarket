/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import logica.SQLManager;
import entidades.Cliente;
import java.util.ArrayList;

/**
 *
 * @author cardel
 */
public class ControladorCliente {

    private SQLManager sQLManager;

    public ControladorCliente() {
        sQLManager = new SQLManager();
    }

    public ArrayList<Cliente> obtenerClientes() {
        String selection[] = {"cliente_id", "tipo_id_cliente", "nombre", "numero_de_telefono", "numero_celular", "direccion", "monto_prestamo"};
        String selection_type[] = {"int", "varchar", "varchar", "varchar", "varchar", "varchar", "int"};
        String table = "Cliente";
        String restriction = "";
        ArrayList<String[]> resultadoSet = sQLManager.select_query(selection, selection_type, table, restriction);

        ArrayList<Cliente> listaDeClientes = new ArrayList<>();
        //Pendiente

        for (int i = 0; i < resultadoSet.size(); i++) {
            String[] resultado = resultadoSet.get(i);
            int cliente_id = Integer.parseInt(resultado[0]);
            String tipo_cliente_id = resultado[1];
            String nombre = resultado[2];
            String numero_telefono = resultado[3];
            String numero_celular = resultado[4];
            String direccion = resultado[5];
            double monto_prestamo = Double.parseDouble(resultado[6]);
            Cliente cliente = new Cliente(cliente_id, direccion, nombre, numero_telefono, numero_celular, direccion, monto_prestamo);
            listaDeClientes.add(cliente);

        }

        return listaDeClientes;
    }

    public ArrayList<Cliente> obtenerClientes(String nombreIn, int identificacion) {
        String selection[] = {"cliente_id", "tipo_id_cliente", "nombre", "numero_de_telefono", "numero_celular", "direccion", "monto_prestamo"};
        String selection_type[] = {"int", "varchar", "varchar", "varchar", "varchar", "varchar", "int"};
        String table = "Cliente";

        boolean nombreVacio = !nombreIn.equals("");
        boolean identificacionVacia = identificacion != 0;

        String restriction = "";
        if (nombreVacio && identificacionVacia) {
            restriction = " where nombre like \"%" + nombreIn + "%\" and cliente_id="+identificacion;
        }
        if (!nombreVacio && identificacionVacia) {
            restriction = " where cliente_id like "+identificacion;
        }
        if (nombreVacio && !identificacionVacia) {
            restriction = " where nombre like \"%" + nombreIn+ "%\"";
        }

        ArrayList<String[]> resultadoSet = sQLManager.select_query(selection, selection_type, table, restriction);

        ArrayList<Cliente> listaDeClientes = new ArrayList<>();

        for (int i = 0; i < resultadoSet.size(); i++) {
            String[] resultado = resultadoSet.get(i);
            int cliente_id = Integer.parseInt(resultado[0]);
            String tipo_cliente_id = resultado[1];
            String nombre = resultado[2];
            String numero_telefono = resultado[3];
            String numero_celular = resultado[4];
            String direccion = resultado[5];
            double monto_prestamo = Double.parseDouble(resultado[6]);
            Cliente cliente = new Cliente(cliente_id, tipo_cliente_id, nombre, numero_telefono, numero_celular, direccion, monto_prestamo);
            listaDeClientes.add(cliente);

        }

        return listaDeClientes;
    }

    public boolean editarCliente(int cliente_id, String tipo_cliente_id, String nombre, String numero_telefono, String numero_celular, String direccion, double monto_prestamo) {

        //cliente_id Es la identificación.
        String selection[] = {"cliente_id", "tipo_id_cliente", "nombre", "numero_de_telefono", "numero_celular", "direccion", "monto_prestamo"};
        String value[] = {String.valueOf(cliente_id), tipo_cliente_id, nombre, numero_telefono, numero_celular, direccion, String.valueOf(monto_prestamo)};
        String type_value[] = {"int", "varchar", "varchar", "varchar", "varchar", "varchar", "int"};

        String table = "Cliente";
        String condition = " where cliente_id=" + cliente_id;
        sQLManager.update_query(selection, value, type_value, table, condition);
        return true;

    }
      public boolean editarCliente(Cliente cliente) {
        int cliente_id = cliente.getCliente_id();
        String tipo_cliente_id = cliente.getTipo_cliente_id();
        String nombre = cliente.getNombre();
        String numero_telefono = cliente.getNumero_telefono();
        String numero_celular = cliente.getNumero_celular();
        String direccion = cliente.getDireccion();
        double monto_prestamo = cliente.getMonto_prestamo();
        //cliente_id Es la identificación.
        String selection[] = {"cliente_id", "tipo_id_cliente", "nombre", "numero_de_telefono", "numero_celular", "direccion", "monto_prestamo"};
        String value[] = {String.valueOf(cliente_id), tipo_cliente_id, nombre, numero_telefono, numero_celular, direccion, String.valueOf(monto_prestamo)};
        String type_value[] = {"int", "varchar", "varchar", "varchar", "varchar", "varchar", "int"};

        String table = "Cliente";
        String condition = " where cliente_id=" + cliente_id;
        sQLManager.update_query(selection, value, type_value, table, condition);
        return true;

    }  
    public boolean agregarCliente(int cliente_id, String tipo_cliente_id, String nombre, String numero_telefono, String numero_celular, String direccion, double monto_prestamo) {
        //cliente_id Es la identificación.
        String selection[] = {"cliente_id", "tipo_id_cliente", "nombre", "numero_de_telefono", "numero_celular", "direccion", "monto_prestamo"};
        String value[] = {String.valueOf(cliente_id), tipo_cliente_id, nombre, numero_telefono, numero_celular, direccion, String.valueOf(monto_prestamo)};
        String type_value[] = {"int", "varchar", "varchar", "varchar", "varchar", "varchar", "int"};

        String table = "Cliente";
        String condition = " where cliente_id=" + cliente_id;
        String table_id[] = {""};
        String type_table_id[] = {""};
        sQLManager.insert_query(selection, value, type_value, table, table_id, type_table_id);
        return true;

    }
}
