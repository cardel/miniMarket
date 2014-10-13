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
    
    
    public Productos(int productoId)
    {
        this.productoId=productoId;
    }
    
    public Productos(int productoId, String nombre, String descripcion, int unidadesDisponibles, double precio)
    {
        this.productoId=productoId;
        this.nombre=nombre;
        this.unidadesDisponibles=unidadesDisponibles;
        this.precio=precio;
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
    
    
}
