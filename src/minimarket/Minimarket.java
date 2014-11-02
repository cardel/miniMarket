/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimarket;

import controladores.ControladorProducto;
import entidades.Productos;
import interfaces.InicioAplicacion;
import javax.swing.UIManager;

/**
 *
 * @author cardel
 */
public class Minimarket {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        try {

            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            
        } catch (Exception e) {
        }
        InicioAplicacion aplicacion = new InicioAplicacion();
        aplicacion.setVisible(true);
        
    }

}
