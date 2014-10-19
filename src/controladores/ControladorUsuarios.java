/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.Usuarios;
import java.util.ArrayList;
import java.util.Calendar;
import logica.SQLManager;
import seguridad.GeneradorMD5;

/**
 *
 * @author cardel
 */
public class ControladorUsuarios {

    private SQLManager sqlManager;
    private GeneradorMD5 generador;

    public ControladorUsuarios() {
        sqlManager = new SQLManager();
        generador = new GeneradorMD5();
    }

    public boolean CrearUsuario(String login, String password) {

        password = generador.getMD5(password);
        Calendar calendario = Calendar.getInstance();
        String dia = Integer.toString(calendario.get(Calendar.DATE));
        String mes = Integer.toString(calendario.get(Calendar.MONTH));
        String annio = Integer.toString(calendario.get(Calendar.YEAR));

        String fecha = annio + "-" + mes + "-" + dia;

        String selection[] = {"login", "password", "creation_date", "status"};
        String value[] = {login, password, fecha, "e"};
        String type_value[] = {"int", "varchar", "varchar", "varchar", "varchar"};
        String table = "Usuarios";
        String table_id[] = {"user_id"};
        String type_table_id[] = {"int"};
        sqlManager.insert_query(selection, value, type_value, table, table_id, type_table_id);

        return true;
    }

    public Usuarios obtenerUsuario(String login) {

        String selection[] = {"login"};
        String selection_type[] = {"varchar"};
        String table = "Usuarios";
        String restriction = " where login=\"" + login + "\"";
        ArrayList<String[]> resultadoSet = sqlManager.select_query(selection, selection_type, table, restriction);
        String [] resultado = resultadoSet.get(0);
        Usuarios usuario = new Usuarios(Integer.parseInt(resultado[0]), resultado[1], resultado[2], resultado[3], resultado[4].toCharArray()[0]);

        return usuario;
    }

    public boolean modificarUsuarioPassword(int user_id, String password) {
        
        password = generador.getMD5(password);
        String selection[] = {"password"};
        String table = "Usuarios";
        String value[] = {password};
        String type_value[] = {"varchar"};
        String restriction = " where user_id=" + user_id;

        sqlManager.update_query(selection, value, type_value, table, restriction);
        return false;
    }
}
