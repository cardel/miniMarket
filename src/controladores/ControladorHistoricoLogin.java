/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.HistoricoSesiones;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import logica.SQLManager;

/**
 *
 * @author cardel
 */
public class ControladorHistoricoLogin {

    private SQLManager sqlManager;

    public ControladorHistoricoLogin() {
        sqlManager = new SQLManager();
    }

    public boolean registrarInicioSession(int user_id) {
        Calendar calendario = Calendar.getInstance();
        String dia = Integer.toString(calendario.get(Calendar.DATE));
        String mes = Integer.toString(calendario.get(Calendar.MONTH));
        String annio = Integer.toString(calendario.get(Calendar.YEAR));
        Date date = new Date();
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        String hora = hourFormat.format(date);

        String fecha = annio + "-" + mes + "-" + dia+" "+hora;
        String selection[] = {"user_id", "fecha"};
        String value[] = {String.valueOf(user_id), fecha};
        String type_value[] = {"varchar", "varchar"};
        String table = "HistoricoSesiones";
        String table_id[] = {"sesion_id"};
        String type_table_id[] = {"int"};
        sqlManager.insert_query(selection, value, type_value, table, table_id, type_table_id);
        return true;
    }

    public ArrayList<HistoricoSesiones> obtenerHistoricoSession(int user_id) {
        ArrayList<HistoricoSesiones> retorno = new ArrayList<>();

        String selection[] = {"sesion_id", "user_id", "fecha"};
        String selection_type[] = {"int", "int", "varchar"};
        String table = "HistoricoSesiones";
        String restriction = " where user_id=" + user_id;
        ArrayList<String[]> resultadoSet = sqlManager.select_query(selection, selection_type, table, restriction);
        String[] resultado = resultadoSet.get(0);
        for (int i = 0; i < restriction.length(); i++) {
            HistoricoSesiones historicoConsultado = new HistoricoSesiones(Integer.parseInt(resultado[0]), Integer.parseInt(resultado[1]), resultado[2]);
            retorno.add(historicoConsultado);

        }
        return retorno;

    }

}
