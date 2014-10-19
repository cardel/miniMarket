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
        ArrayList <String []> resultadoSet = sQLManager.select_query(selection, selection_type, table, restriction);
        String [] resultado = resultadoSet.get(0);
        ArrayList<Cliente> listaDeClientes = new ArrayList<>();
        //Pendiente
        int cliente_id = Integer.parseInt(resultado[0]);
        String tipo_cliente_id = resultado[1];
        String nombre = resultado[1];
        String numero_telefono = resultado[1];
        String numero_celular = resultado[1];
        String direccion = resultado[1];
        double monto_prestamo = Double.parseDouble(resultado[1]);
        Cliente cliente = new Cliente(cliente_id, direccion, nombre, numero_telefono, numero_celular, direccion, monto_prestamo);
        listaDeClientes.add(cliente);

        return listaDeClientes;
    }

    public boolean editarCliente(int cliente_id, String tipo_cliente_id, String nombre, String numero_telefono, String numero_celular, String direccion, double monto_prestamo) {

        
        String selection[] = {"cliente_id", "tipo_id_cliente", "nombre", "numero_de_telefono", "numero_celular", "direccion", "monto_prestamo"};
        String value[] = {String.valueOf(cliente_id), tipo_cliente_id, nombre,numero_telefono,numero_celular,direccion,String.valueOf(monto_prestamo)};
           String type_value[] = {"int", "varchar", "varchar", "varchar", "varchar", "varchar", "int"};

        String table = "Cliente";
        String condition = " where cliente_id="+cliente_id;
        sQLManager.update_query(selection, value, type_value, table, condition);
        return true;

    }

}
