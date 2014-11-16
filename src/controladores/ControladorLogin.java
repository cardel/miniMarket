/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import logica.SQLManager;
import seguridad.GeneradorMD5;

/**
 *
 * @author cardel
 */
public class ControladorLogin {

    private GeneradorMD5 generadorAplicativo;
    private SQLManager sqlManager;

    public ControladorLogin() {
        generadorAplicativo = new GeneradorMD5();
        sqlManager = new SQLManager();
    }

    public boolean autenticar(String usuario, String password) {
        String md5Prueba = generadorAplicativo.getMD5(password);
        String seleccion[] = {"login", "password"};
        String tipo_seleccion[] = {"varchar", "varchar"};
        String restriccion = " where login = \"" + usuario + "\" and password = \"" + md5Prueba + "\" and status='e'";
        String tabla = "Usuarios";

        ArrayList<String[]> resultSet = sqlManager.select_query(seleccion, tipo_seleccion, tabla, restriccion);
        String [] result = resultSet.get(0);

        if (result[0]!=null) {
            return true;
        }
        return false;

    }

}
