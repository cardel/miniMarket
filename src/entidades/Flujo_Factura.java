/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import javax.xml.crypto.Data;

/**
 *
 * @author cardel
 */
public class Flujo_Factura {
    private int flujo_Factura_id;
    private int factura_id;
    String tipo_flujo;
    String fecha;
    double valor;

    public Flujo_Factura(int flujo_Factura_id) {
        this.flujo_Factura_id = flujo_Factura_id;
    }

    public Flujo_Factura(int flujo_Factura_id, int factura_id, String tipo_flujo, String fecha, double valor) {
        this.flujo_Factura_id = flujo_Factura_id;
        this.factura_id = factura_id;
        this.tipo_flujo = tipo_flujo;
        this.fecha = fecha;
        this.valor = valor;
    }

    public int getFlujo_Factura_id() {
        return flujo_Factura_id;
    }

    public void setFlujo_Factura_id(int flujo_Factura_id) {
        this.flujo_Factura_id = flujo_Factura_id;
    }

    public int getFactura_id() {
        return factura_id;
    }

    public void setFactura_id(int factura_id) {
        this.factura_id = factura_id;
    }

    public String getTipo_flujo() {
        return tipo_flujo;
    }

    public void setTipo_flujo(String tipo_flujo) {
        this.tipo_flujo = tipo_flujo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    
}
