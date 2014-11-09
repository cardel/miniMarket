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
public class Factura {
    private int factura_id;
    private int cliente_id;
    private String fecha;
    private String estado;
    private double valor;
    

    public Factura(int factura_id) {
        this.factura_id = factura_id;
    }

    public Factura(int factura_id, int cliente_id, String fecha, String estado, double valor) {
        this.factura_id = factura_id;
        this.cliente_id = cliente_id;
        this.fecha = fecha;
        this.estado = estado;
        this.valor = valor;
       
    }

    public int getFactura_id() {
        return factura_id;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
