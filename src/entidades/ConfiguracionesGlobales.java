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
public class ConfiguracionesGlobales {
    private String razonSocial;
    private String NIT;
    private String NombreAdministrador;

    public ConfiguracionesGlobales(String razonSocial, String NIT, String NombreAdministrador) {
        this.razonSocial = razonSocial;
        this.NIT = NIT;
        this.NombreAdministrador = NombreAdministrador;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNIT() {
        return NIT;
    }

    public void setNIT(String NIT) {
        this.NIT = NIT;
    }

    public String getNombreAdministrador() {
        return NombreAdministrador;
    }

    public void setNombreAdministrador(String NombreAdministrador) {
        this.NombreAdministrador = NombreAdministrador;
    }
    
    
}
