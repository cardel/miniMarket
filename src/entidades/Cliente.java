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
public class Cliente {
    private int cliente_id;
    private String tipo_cliente_id;
    private String nombre;
    private String numero_telefono;
    private String numero_celular;
    private String direccion;
    private double monto_prestamo;

    public Cliente(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public Cliente(int cliente_id, String tipo_cliente_id, String nombre, String numero_telefono, String numero_celular, String direccion, double monto_prestamo) {
        this.cliente_id = cliente_id;
        this.tipo_cliente_id = tipo_cliente_id;
        this.nombre = nombre;
        this.numero_telefono = numero_telefono;
        this.numero_celular = numero_celular;
        this.direccion = direccion;
        this.monto_prestamo = monto_prestamo;
    }

    public int getCliente_id() {
        return cliente_id;
    }


    public String getTipo_cliente_id() {
        return tipo_cliente_id;
    }

    public void setTipo_cliente_id(String tipo_cliente_id) {
        this.tipo_cliente_id = tipo_cliente_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumero_telefono() {
        return numero_telefono;
    }

    public void setNumero_telefono(String numero_telefono) {
        this.numero_telefono = numero_telefono;
    }

    public String getNumero_celular() {
        return numero_celular;
    }

    public void setNumero_celular(String numero_celular) {
        this.numero_celular = numero_celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getMonto_prestamo() {
        return monto_prestamo;
    }

    public void setMonto_prestamo(double monto_prestamo) {
        this.monto_prestamo = monto_prestamo;
    }
    
    
    
}
