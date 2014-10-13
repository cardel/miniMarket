/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author cardel
 */
public class Factura_Productos {
    private int factura_id;
    private int producto_id;
    private int unidades;
    private double precio;

    public Factura_Productos(int factura_id) {
        this.factura_id = factura_id;
    }

    public Factura_Productos(int factura_id, int producto_id, int unidades, double precio) {
        this.factura_id = factura_id;
        this.producto_id = producto_id;
        this.unidades = unidades;
        this.precio = precio;
    }

    public int getFactura_id() {
        return factura_id;
    }


    public int getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(int producto_id) {
        this.producto_id = producto_id;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    
}
