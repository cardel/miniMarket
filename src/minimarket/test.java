/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimarket;

import controladores.controladorFactura;
import controladores.controladorProducto;
import entidades.Productos;

/**
 *
 * @author Joshua
 */
public class test {
    
    public test()
    {
    
    }
    
    public void test4Producto()
    {
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
        
    }
    
    public void test4Factura()
    {
        //SELECT
        controladorFactura cf = new controladorFactura();
        /*Productos producto = cf.getProducto(" Where producto_id = 1 ");
        System.out.println(producto.getNombre());*/
        //INSERT
        String [] value_for_insert = {"1","2013-05-05","1","5000"};
        cf.insertFactura(value_for_insert);
        //Update
        String [] selection = {"estado"};
        String [] value = {"2"};
        String [] type = {"char"};
        cf.updateFactura(selection, value, type, "where factura_id = 1");
        
        //Delete
        //Descomentar luego de probar lo de arriba para ver resultado en la base de datos
        //cp.deleteProduct( " Where producto_id = 2");
        
    }
}
