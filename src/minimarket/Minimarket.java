/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimarket;

import controladores.controladorProducto;
import entidades.Productos;
import interfaces.InicioAplicacion;

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
        
        InicioAplicacion aplicacion = new InicioAplicacion();
        test t = new test();
        //t.test4Producto();
        t.test4Factura();
        //aplicacion.show();
        /*
        Ejemplo md5 contraseña admin
                GeneradorMD5 generador = new GeneradorMD5();
        System.out.println(generador.getMD5("admin"));
        */
        

        
    }
    
}
