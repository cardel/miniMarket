/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

/**
 *
 * @author cardel
 */
public class CompraProveedores {
    private int ID;
    private int IDProveedor;
    private double montoCompra;
    private String fecha;

    public CompraProveedores() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDProveedor() {
        return IDProveedor;
    }

    public void setIDProveedor(int IDProveedor) {
        this.IDProveedor = IDProveedor;
    }

    public double getMontoCompra() {
        return montoCompra;
    }

    public void setMontoCompra(double montoCompra) {
        this.montoCompra = montoCompra;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
    
}
