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

    public boolean CrearUsuario(String login, String password, String estado) {

        password = generador.getMD5(password);
        Calendar calendario = Calendar.getInstance();
        String dia = Integer.toString(calendario.get(Calendar.DATE));
        String mes = Integer.toString(calendario.get(Calendar.MONTH));
        String annio = Integer.toString(calendario.get(Calendar.YEAR));

        String fecha = annio + "-" + mes + "-" + dia;

        String selection[] = {"login", "password", "creation_date", "status"};
        String value[] = {login, password, fecha, estado};
        String type_value[] = {"varchar", "varchar", "varchar", "varchar", "varchar"};
        String table = "Usuarios";
        String table_id[] = {"user_id"};
        String type_table_id[] = {"int"};
        sqlManager.insert_query(selection, value, type_value, table, table_id, type_table_id);

        return true;
    }

    public Usuarios obtenerUsuario(String login) {

        String selection[] = {"user_id", "login", "password", "creation_date", "status"};
        String selection_type[] = {"int", "varchar", "varchar", "varchar", "varchar"};
        String table = "Usuarios";
        String restriction = " where login=\"" + login + "\"";
        ArrayList<String[]> resultadoSet = sqlManager.select_query(selection, selection_type, table, restriction);

        Usuarios usuario = null;
        if (resultadoSet.size() > 0) {
            String[] resultado = resultadoSet.get(0);
            usuario = new Usuarios(Integer.parseInt(resultado[0]), resultado[1], resultado[2], resultado[3], resultado[4].toCharArray()[0]);
        }

        return usuario;
    }

    public ArrayList<Usuarios> obtenerTodosUsuarios() {

        ArrayList<Usuarios> listaUsuarios = new ArrayList<>();
        String selection[] = {"user_id", "login", "password", "creation_date", "status"};
        String selection_type[] = {"int", "varchar", "varchar", "varchar", "varchar"};
        String table = "Usuarios";
        String restriction = "";
        ArrayList<String[]> resultadoSet = sqlManager.select_query(selection, selection_type, table, restriction);

        for (int i = 0; i < resultadoSet.size(); i++) {
            String[] resultado = resultadoSet.get(i);
            Usuarios usuario = new Usuarios(Integer.parseInt(resultado[0]), resultado[1], resultado[2], resultado[3], resultado[4].toCharArray()[0]);
            listaUsuarios.add(usuario);
        }

        return listaUsuarios;
    }

    public boolean modificarUsuario(int user_id, String login, String password) {

        password = generador.getMD5(password);
        String selection[] = {"login","password"};
        String table = "Usuarios";
        String value[] = {login,password};
        String type_value[] = {"varchar","varchar"};
        String restriction = " where user_id=" + user_id;

        sqlManager.update_query(selection, value, type_value, table, restriction);
        return false;
    }

    public void modificarEstadoUsuario(Usuarios usuario) {
        
        String selection[] = {"status"};;
        String table = "Usuarios";
        String value[] = {""+usuario.getStatus()};
        String type_value[] = {"varchar"};
        String restriction = " where user_id=" + usuario.getUser_id();

        sqlManager.update_query(selection, value, type_value, table, restriction);
    }
}
