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
public class Productos {
    
    private int productoId;
    private String nombre;
    private String descripcion;
    private int unidadesDisponibles;
    private double precio;
    private String codigoBarras;
    
    

    
    public Productos(int productoId, String nombre, String descripcion, int unidadesDisponibles, double precio,String codigoBarras)
    {
        this.productoId=productoId;
        this.nombre=nombre;
        this.descripcion = descripcion;
        this.unidadesDisponibles=unidadesDisponibles;
        this.precio=precio;
        this.codigoBarras = codigoBarras;
    }

    public int getProductoId() {
        return productoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getUnidadesDisponibles() {
        return unidadesDisponibles;
    }

    public void setUnidadesDisponibles(int unidadesDisponibles) {
        this.unidadesDisponibles = unidadesDisponibles;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }
    
    
}
