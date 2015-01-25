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
public class Flujo_Compra {
    private int ID;
    private int ID_CompraProveedor;
    private String fecha;
    private String tipo_flujo;
    private double monto;

    public Flujo_Compra() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID_CompraProveedor() {
        return ID_CompraProveedor;
    }

    public void setID_CompraProveedor(int ID_CompraProveedor) {
        this.ID_CompraProveedor = ID_CompraProveedor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipo_flujo() {
        return tipo_flujo;
    }

    public void setTipo_flujo(String tipo_flujo) {
        this.tipo_flujo = tipo_flujo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
    
    
}
