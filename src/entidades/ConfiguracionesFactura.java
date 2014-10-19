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
public class ConfiguracionesFactura {
    private double porcentajeIVA;
    private String RangoInicialFactura;
    private String RangoFinalFacturaDIAN;
    private String mensajeLegal;

    public ConfiguracionesFactura(double porcentajeIVA, String RangoInicialFactura, String RangoFinalFacturaDIAN, String mensajeLegal) {
        this.porcentajeIVA = porcentajeIVA;
        this.RangoInicialFactura = RangoInicialFactura;
        this.RangoFinalFacturaDIAN = RangoFinalFacturaDIAN;
        this.mensajeLegal = mensajeLegal;
    }

    public double getPorcentajeIVA() {
        return porcentajeIVA;
    }

    public void setPorcentajeIVA(double porcentajeIVA) {
        this.porcentajeIVA = porcentajeIVA;
    }

    public String getRangoInicialFactura() {
        return RangoInicialFactura;
    }

    public void setRangoInicialFactura(String RangoInicialFactura) {
        this.RangoInicialFactura = RangoInicialFactura;
    }

    public String getRangoFinalFacturaDIAN() {
        return RangoFinalFacturaDIAN;
    }

    public void setRangoFinalFacturaDIAN(String RangoFinalFacturaDIAN) {
        this.RangoFinalFacturaDIAN = RangoFinalFacturaDIAN;
    }

    public String getMensajeLegal() {
        return mensajeLegal;
    }

    public void setMensajeLegal(String mensajeLegal) {
        this.mensajeLegal = mensajeLegal;
    }

    
    
    
}
