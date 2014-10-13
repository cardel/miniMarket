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
        System.out.println("Test BD");
        //SELECT
        controladorProducto cp = new controladorProducto();
        Productos producto = cp.getProducto(" Where producto_id = 1 ");
        System.out.println(producto.getNombre());
        //INSERT
        String [] value_for_insert = {"Limon","acido","2","5000"};
        cp.insertProduct(value_for_insert);
        //Update
        String [] selection = {"nombre"};
        String [] value = {"Fresa"};
        String [] type = {"varchar"};
        cp.updateProduct(selection, value, type, "where producto_id = 2");
        //Delete
        //Descomentar luego de probar lo de arriba para ver resultado en la base de datos
        //cp.deleteProduct( " Where producto_id = 2");
        
        InicioAplicacion aplicacion = new InicioAplicacion();
        aplicacion.show();
        /*
        Ejemplo md5 contraseña admin
                GeneradorMD5 generador = new GeneradorMD5();
        System.out.println(generador.getMD5("admin"));
        */
        

        
    }
    
}
