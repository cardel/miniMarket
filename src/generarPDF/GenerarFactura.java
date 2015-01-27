/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generarPDF;

import controladores.ControladorCliente;
import controladores.ControladorConfiguraciones;
import controladores.ControladorFactura;
import controladores.ControladorFactura_Productos;
import controladores.ControladorProducto;
import entidades.Cliente;
import entidades.Factura;
import entidades.Factura_Productos;
import entidades.Productos;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 *
 * @author cardel
 */
public class GenerarFactura {

    private ControladorConfiguraciones controladorConfiguraciones;
    String IVA;
    String informacionLegalFactura;
    String NITEmpresa;
    String nombreEmpresa;
    String direccionEmpresa;

    public GenerarFactura() {

        controladorConfiguraciones = new ControladorConfiguraciones();
        String[] datosConfiguracionesFactura = controladorConfiguraciones.obtenerInformacionFactura();
                        System.out.println("llego aqui 1");

        IVA = datosConfiguracionesFactura[0];
        informacionLegalFactura = datosConfiguracionesFactura[1];
        
        String[] configuracionesGlobales = controladorConfiguraciones.obtenerInformacionLegal();
        nombreEmpresa = configuracionesGlobales[0];
        NITEmpresa = configuracionesGlobales[1];
        direccionEmpresa = configuracionesGlobales[2];

    }

    public void imprimirFactura(int facturaID, JFrame dialogo) {
        try {
            PDDocument document = crearFactura(facturaID, dialogo);
            document.print();
            document.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(dialogo, "Error al crear la factura", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public PDDocument crearFactura(int facturaID, JFrame dialogo) {

        try {

            ControladorFactura controladorFactura = new ControladorFactura();
            Factura facturaActual = controladorFactura.getFactura(" where factura_id=" + facturaID).get(0);

            ControladorCliente controladorCliente = new ControladorCliente();
            Cliente cliente = controladorCliente.obtenerClientePorID(facturaActual.getCliente_id());
            
            System.out.println(cliente.getCliente_id());
            
            PDDocument document = new PDDocument();

            PDPage pagina1 = new PDPage();
            document.addPage(pagina1);

            PDFont font = PDType1Font.HELVETICA_BOLD;
            PDFont fontNormal = PDType1Font.HELVETICA;

            PDPageContentStream contenido = new PDPageContentStream(document, pagina1);

            contenido.beginText();
            contenido.setFont(font, 16);
            contenido.moveTextPositionByAmount(30, 730);
                                System.out.println("llego aqui -10");

            contenido.drawString("Factura #" + facturaActual.getFactura_id());
            contenido.endText();
                                System.out.println("llego aqui -11");

            contenido.beginText();
            contenido.setFont(font, 12);
            contenido.moveTextPositionByAmount(30, 700);
            contenido.drawString(nombreEmpresa+" NIT: "+NITEmpresa);
            contenido.endText();

            contenido.beginText();
            contenido.setFont(font, 12);
            contenido.moveTextPositionByAmount(30, 680);
            contenido.drawString(direccionEmpresa);
            contenido.endText();

            contenido.beginText();
            contenido.setFont(fontNormal, 11);
            contenido.moveTextPositionByAmount(30, 650);
            contenido.drawString("Fecha:" + facturaActual.getFecha());
            contenido.endText();

            contenido.beginText();
            contenido.setFont(fontNormal, 11);
            contenido.moveTextPositionByAmount(30, 635);
            contenido.drawString("Nombre: " + cliente.getNombre());
            contenido.endText();

            contenido.beginText();
            contenido.setFont(fontNormal, 11);
            contenido.moveTextPositionByAmount(30, 620);
            contenido.drawString("Dirección: " + cliente.getDireccion());
            contenido.endText();

            contenido.beginText();
            contenido.setFont(fontNormal, 12);
            contenido.moveTextPositionByAmount(200, 590);
            contenido.drawString("DETALLE DE LA FACTURA");
            contenido.endText();

            //Dibujar lineas
            contenido.drawLine(30, 600, 500, 600);
            contenido.drawLine(30, 585, 500, 585);

            //Dibujar tabla de productos
            ControladorFactura_Productos controladorFactura_Productos = new ControladorFactura_Productos();
            ArrayList<Factura_Productos> listaProductosFactura = controladorFactura_Productos.getTodosFactura_Productos(" where factura_id=" + facturaID);

            contenido.drawLine(30, 570, 500, 570);

            contenido.beginText();
            contenido.setFont(fontNormal, 12);
            contenido.moveTextPositionByAmount(60, 560);
            contenido.drawString("Producto");
            contenido.endText();
            contenido.drawLine(30, 550, 30, 570);
            contenido.drawLine(200, 550, 200, 570);

            contenido.beginText();
            contenido.setFont(fontNormal, 12);
            contenido.moveTextPositionByAmount(220, 560);
            contenido.drawString("Valor unitario");
            contenido.endText();
            contenido.drawLine(300, 550, 300, 570);

            contenido.beginText();
            contenido.setFont(fontNormal, 12);
            contenido.moveTextPositionByAmount(320, 560);
            contenido.drawString("Unidades");
            contenido.endText();
            contenido.drawLine(380, 550, 380, 570);

            contenido.beginText();
            contenido.setFont(fontNormal, 12);
            contenido.moveTextPositionByAmount(400, 560);
            contenido.drawString("Valor total");
            contenido.endText();
            contenido.drawLine(500, 550, 500, 570);
            contenido.drawLine(500, 550, 500, 570);

            contenido.drawLine(30, 550, 500, 550);
                                System.out.println("llego aqui 1");

            int altura = 550;
            ControladorProducto controladorProducto = new ControladorProducto();
                                System.out.println("llego aqui 2");

            /*
             * Caben en la pagina
             * Primera pagina 14
             * Seguientes paginas 21
             * Footer cuenta como 5 mas
             */
            int indiceProductos = 0;
            double totalEspaciosNecesarios = listaProductosFactura.size() + 5 + 1;
            double totalPaginas = 1;
                                System.out.println("llego aqui 3");

            if (Math.floor(totalEspaciosNecesarios / 17) == 0) {
                totalPaginas = 1;
            } else {
                totalEspaciosNecesarios -= 17;
                totalPaginas += (int) Math.ceil(totalEspaciosNecesarios / 21);
            }

            //Primer pagina
            for (int i = 0; i < listaProductosFactura.size() && altura >= 30; i++) {
                //Imprime por paginas
                Factura_Productos facturaProducto = listaProductosFactura.get(i);
                Productos productoActual = controladorProducto.getProducto(" where producto_id=" + facturaProducto.getProducto_id()).get(0);

                String nombreProducto = productoActual.getNombre();

                if (nombreProducto.length() > 25) {
                    nombreProducto = nombreProducto.substring(0, 26);
                }

                String valorUnitario = String.valueOf(productoActual.getPrecio());
                String unidades = String.valueOf(facturaProducto.getUnidades());
                String valorTotal = String.valueOf(productoActual.getPrecio() * facturaProducto.getUnidades());

                contenido.beginText();
                contenido.setFont(fontNormal, 12);
                contenido.moveTextPositionByAmount(40, altura - 15);
                contenido.drawString(String.valueOf(i + 1));
                contenido.endText();
                contenido.drawLine(30, altura, 30, altura - 30);
                contenido.drawLine(200, altura, 200, altura - 30);

                contenido.beginText();
                contenido.setFont(fontNormal, 12);
                contenido.moveTextPositionByAmount(70, altura - 15);
                contenido.drawString(nombreProducto);
                contenido.endText();
                contenido.drawLine(70, altura, 70, altura - 30);
                contenido.drawLine(200, altura, 200, altura - 30);

                contenido.beginText();
                contenido.setFont(fontNormal, 12);
                contenido.moveTextPositionByAmount(220, altura - 15);
                contenido.drawString(valorUnitario);
                contenido.endText();
                contenido.drawLine(300, altura, 300, altura - 30);

                contenido.beginText();
                contenido.setFont(fontNormal, 12);
                contenido.moveTextPositionByAmount(320, altura - 15);
                contenido.drawString(unidades);
                contenido.endText();
                contenido.drawLine(380, altura, 380, altura - 30);

                contenido.beginText();
                contenido.setFont(fontNormal, 12);
                contenido.moveTextPositionByAmount(400, altura - 15);
                contenido.drawString(valorTotal);
                contenido.endText();
                contenido.drawLine(500, altura, 500, altura - 30);
                //Linea inferior
                contenido.drawLine(30, altura - 30, 500, altura - 30);
                altura -= 30;
                indiceProductos = i + 1;
            }
            //Escribir footer si paginas es igual a 1
            if (totalPaginas == 1) {
                Double valor = facturaActual.getValor();
                Double ivaCalculado = Math.ceil(valor * Double.parseDouble(IVA) / 100);
                    Double subtotal = Math.floor(valor * (1 - Double.parseDouble(IVA)/100));
                contenido.beginText();
                contenido.setFont(fontNormal, 12);
                contenido.moveTextPositionByAmount(320, altura - 15);
                contenido.drawString("Subtotal");
                contenido.endText();
                contenido.drawLine(380, altura, 380, altura - 30);

                contenido.beginText();
                contenido.setFont(fontNormal, 12);
                contenido.moveTextPositionByAmount(400, altura - 15);
                contenido.drawString(String.valueOf(subtotal));
                contenido.endText();
                contenido.drawLine(500, altura, 500, altura - 30);

                //Linea inferior
                contenido.drawLine(320, altura, 320, altura - 30);
                contenido.drawLine(320, altura - 30, 500, altura - 30);

                altura -= 30;
                contenido.beginText();
                contenido.setFont(fontNormal, 12);
                contenido.moveTextPositionByAmount(320, altura - 15);
                contenido.drawString("IVA " + IVA + "%");
                contenido.endText();
                contenido.drawLine(380, altura, 380, altura - 30);

                contenido.beginText();
                contenido.setFont(fontNormal, 12);
                contenido.moveTextPositionByAmount(400, altura - 15);
                contenido.drawString(String.valueOf(ivaCalculado));
                contenido.endText();
                contenido.drawLine(500, altura, 500, altura - 30);
                //Linea inferior
                contenido.drawLine(320, altura, 320, altura - 30);
                contenido.drawLine(320, altura - 30, 500, altura - 30);
                altura -= 30;

                contenido.beginText();
                contenido.setFont(fontNormal, 12);
                contenido.moveTextPositionByAmount(320, altura - 15);
                contenido.drawString("Total");
                contenido.endText();
                contenido.drawLine(380, altura, 380, altura - 30);

                contenido.beginText();
                contenido.setFont(font, 12);
                contenido.moveTextPositionByAmount(400, altura - 15);
                contenido.drawString(String.valueOf(valor));
                contenido.endText();
                contenido.drawLine(500, altura, 500, altura - 30);
                //Linea inferior
                contenido.drawLine(320, altura - 30, 500, altura - 30);
                contenido.drawLine(320, altura, 320, altura - 30);

                //Informacion legal
                altura -= 40;
                contenido.beginText();
                contenido.setFont(fontNormal, 10);
                contenido.moveTextPositionByAmount(50, altura);
                contenido.drawString(informacionLegalFactura);
                contenido.endText();

            }

            //Siguientes paginas
            for (int j = 1; j < totalPaginas; j++) {
                altura = 650;
                PDPage paginaSiguiente = new PDPage();
                document.addPage(paginaSiguiente);

                PDPageContentStream contenidoSiguiente = new PDPageContentStream(document, paginaSiguiente);
                //Escribir paginas
                for (int i = indiceProductos; i < listaProductosFactura.size() && altura >= 30; i++) {
                    //Imprime por paginas
                    Factura_Productos facturaProducto = listaProductosFactura.get(i);
                    Productos productoActual = controladorProducto.getProducto(" where producto_id=" + facturaProducto.getProducto_id()).get(0);

                    String nombreProducto = productoActual.getNombre();

                    if (nombreProducto.length() > 25) {
                        nombreProducto = nombreProducto.substring(0, 26);
                    }

                    String valorUnitario = String.valueOf(productoActual.getPrecio());
                    String unidades = String.valueOf(facturaProducto.getUnidades());
                    String valorTotal = String.valueOf(productoActual.getPrecio() * facturaProducto.getUnidades());

                    contenidoSiguiente.beginText();
                    contenidoSiguiente.setFont(fontNormal, 12);
                    contenidoSiguiente.moveTextPositionByAmount(40, altura - 15);
                    contenidoSiguiente.drawString(String.valueOf(i + 1));
                    contenidoSiguiente.endText();
                    contenidoSiguiente.drawLine(30, altura, 30, altura - 30);
                    contenidoSiguiente.drawLine(200, altura, 200, altura - 30);

                    contenidoSiguiente.beginText();
                    contenidoSiguiente.setFont(fontNormal, 12);
                    contenidoSiguiente.moveTextPositionByAmount(70, altura - 15);
                    contenidoSiguiente.drawString(nombreProducto);
                    contenidoSiguiente.endText();
                    contenidoSiguiente.drawLine(70, altura, 70, altura - 30);
                    contenidoSiguiente.drawLine(200, altura, 200, altura - 30);

                    contenidoSiguiente.beginText();
                    contenidoSiguiente.setFont(fontNormal, 12);
                    contenidoSiguiente.moveTextPositionByAmount(320, altura - 15);
                    contenidoSiguiente.drawString(unidades);
                    contenidoSiguiente.endText();
                    contenidoSiguiente.drawLine(380, altura, 380, altura - 30);

                    contenidoSiguiente.beginText();
                    contenidoSiguiente.setFont(font, 12);
                    contenidoSiguiente.moveTextPositionByAmount(400, altura - 15);
                    contenidoSiguiente.drawString(valorTotal);
                    contenidoSiguiente.endText();
                    contenidoSiguiente.drawLine(500, altura, 500, altura - 30);
                    //Linea inferior
                    contenidoSiguiente.drawLine(30, altura - 30, 500, altura - 30);
                    indiceProductos = i + 1;
                    altura -= 30;
                }
                //Si no cabe mas cierre el flujo.
                if (indiceProductos < listaProductosFactura.size()) {
                    contenidoSiguiente.close();
                }
                //En ultima pagina escribir footer
                if (j == totalPaginas - 1 && altura >= 40) {
                    Double valor = facturaActual.getValor();
                    Double ivaCalculado = Math.ceil(valor * Double.parseDouble(IVA) / 100);
                    Double subtotal = Math.floor(valor * (1 - Double.parseDouble(IVA)/100));
                    
                    contenidoSiguiente.beginText();
                    contenidoSiguiente.setFont(fontNormal, 12);
                    contenidoSiguiente.moveTextPositionByAmount(320, altura - 15);
                    contenidoSiguiente.drawString("Subtotal");
                    contenidoSiguiente.endText();
                    contenidoSiguiente.drawLine(380, altura, 380, altura - 30);

                    contenidoSiguiente.beginText();
                    contenidoSiguiente.setFont(fontNormal, 12);
                    contenidoSiguiente.moveTextPositionByAmount(400, altura - 15);
                    contenidoSiguiente.drawString(String.valueOf(subtotal));
                    contenidoSiguiente.endText();
                    contenidoSiguiente.drawLine(500, altura, 500, altura - 30);

                    //Linea inferior
                    contenidoSiguiente.drawLine(320, altura, 320, altura - 30);
                    contenidoSiguiente.drawLine(320, altura - 30, 500, altura - 30);

                    altura -= 30;
                    contenidoSiguiente.beginText();
                    contenidoSiguiente.setFont(fontNormal, 12);
                    contenidoSiguiente.moveTextPositionByAmount(320, altura - 15);
                    contenidoSiguiente.drawString("IVA " + IVA + "%");
                    contenidoSiguiente.endText();
                    contenidoSiguiente.drawLine(380, altura, 380, altura - 30);

                    contenidoSiguiente.beginText();
                    contenidoSiguiente.setFont(fontNormal, 12);
                    contenidoSiguiente.moveTextPositionByAmount(400, altura - 15);
                    contenidoSiguiente.drawString(String.valueOf(ivaCalculado));
                    contenidoSiguiente.endText();
                    contenidoSiguiente.drawLine(500, altura, 500, altura - 30);
                    //Linea inferior
                    contenidoSiguiente.drawLine(320, altura, 320, altura - 30);
                    contenidoSiguiente.drawLine(320, altura - 30, 500, altura - 30);
                    altura -= 30;

                    contenidoSiguiente.beginText();
                    contenidoSiguiente.setFont(fontNormal, 12);
                    contenidoSiguiente.moveTextPositionByAmount(320, altura - 15);
                    contenidoSiguiente.drawString("Total");
                    contenidoSiguiente.endText();
                    contenidoSiguiente.drawLine(380, altura, 380, altura - 30);

                    contenidoSiguiente.beginText();
                    contenidoSiguiente.setFont(fontNormal, 12);
                    contenidoSiguiente.moveTextPositionByAmount(400, altura - 15);
                    contenidoSiguiente.drawString(String.valueOf(valor));
                    contenidoSiguiente.endText();
                    contenidoSiguiente.drawLine(500, altura, 500, altura - 30);
                    //Linea inferior
                    contenidoSiguiente.drawLine(320, altura - 30, 500, altura - 30);
                    contenidoSiguiente.drawLine(320, altura, 320, altura - 30);

                    //Informacion legal
                    altura -= 40;
                    contenidoSiguiente.beginText();
                    contenidoSiguiente.setFont(fontNormal, 10);
                    contenidoSiguiente.moveTextPositionByAmount(50, altura);
                    contenidoSiguiente.drawString(informacionLegalFactura);
                    contenidoSiguiente.endText();

                    contenidoSiguiente.close();
                } else {
                    contenidoSiguiente.close();
                }

                System.out.println("Pagina numero: " + j + " De  " + totalPaginas);

            }

            contenido.close();
            return document;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(dialogo, "Error al crear la factura\nInformación Técnica\n" + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

    }
}
