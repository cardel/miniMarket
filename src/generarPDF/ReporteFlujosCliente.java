/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generarPDF;

import controladores.ControladorCliente;
import controladores.ControladorFlujoFactura;
import entidades.Cliente;
import entidades.Flujo_Factura;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 *
 * @author cardel
 */
public class ReporteFlujosCliente {

    public ReporteFlujosCliente() {

    }

    public void guardarDocumentoDialogo(JDialog dialogo, ArrayList<Integer> flujosID, int cliente_id) {
        PDDocument documento = crearInformeMovimientosCliente(flujosID, cliente_id);
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo PDF", "pdf", "text");

        fc.setFileFilter(filter);

        fc.showSaveDialog(dialogo);
        if (fc.getSelectedFile() != null) {
            File selectedFile = fc.getSelectedFile();
            try {

                documento.save(selectedFile + ".pdf");
                JOptionPane.showMessageDialog(dialogo, "El archivo ha sido guardado en disco");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialogo, "El archivo no se puede leer!");
            }
        }

    }

    public void imprimirFlujo(ArrayList<Integer> flujosID, int cliente_id) {
        try {
            PDDocument document = crearInformeMovimientosCliente(flujosID, cliente_id);
            document.print();
            document.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear la factura", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private PDDocument crearInformeMovimientosCliente(ArrayList<Integer> flujosID, int cliente_id) {

        try {

            ControladorCliente controladorCliente = new ControladorCliente();
            Cliente cliente = controladorCliente.obtenerClientePorID(cliente_id);

            PDDocument document = new PDDocument();

            PDPage pagina1 = new PDPage();
            document.addPage(pagina1);

            PDFont font = PDType1Font.HELVETICA_BOLD;
            PDFont fontNormal = PDType1Font.HELVETICA;

            PDPageContentStream contenido = new PDPageContentStream(document, pagina1);

            contenido.beginText();
            contenido.setFont(font, 16);
            contenido.moveTextPositionByAmount(30, 730);
            contenido.drawString("Reporte de movimientos del cliente");
            contenido.endText();

            contenido.beginText();
            contenido.setFont(font, 12);
            contenido.moveTextPositionByAmount(30, 700);
            contenido.drawString("Minimarket Barrio Nuevo.       NIT: 1234567898-9");
            contenido.endText();

            contenido.beginText();
            contenido.setFont(font, 12);
            contenido.moveTextPositionByAmount(30, 680);
            contenido.drawString("Calle Falsa 1 2 3");
            contenido.endText();

            Calendar fecha = new GregorianCalendar();
            //Obtenemos el valor del año, mes, día,
            //hora, minuto y segundo del sistema
            //usando el método get y el parámetro correspondiente
            int annio = fecha.get(Calendar.YEAR);
            int mes = fecha.get(Calendar.MONTH);
            int dia = fecha.get(Calendar.DAY_OF_MONTH);

            contenido.beginText();
            contenido.setFont(fontNormal, 11);
            contenido.moveTextPositionByAmount(30, 650);
            contenido.drawString("Fecha: " + annio + "/" + (mes + 1) + "/" + dia);
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
            contenido.drawString("DETALLE DE LOS MOVIMIENTOS");
            contenido.endText();

            //Dibujar lineas
            contenido.drawLine(30, 600, 500, 600);
            contenido.drawLine(30, 585, 500, 585);

            contenido.drawLine(30, 570, 500, 570);

            contenido.beginText();
            contenido.setFont(fontNormal, 12);
            contenido.moveTextPositionByAmount(60, 560);
            contenido.drawString("Factura");
            contenido.endText();
            contenido.drawLine(30, 550, 30, 570);
            contenido.drawLine(200, 550, 200, 570);

            contenido.beginText();
            contenido.setFont(fontNormal, 12);
            contenido.moveTextPositionByAmount(220, 560);
            contenido.drawString("Tipo flujo");
            contenido.endText();
            contenido.drawLine(300, 550, 300, 570);

            contenido.beginText();
            contenido.setFont(fontNormal, 12);
            contenido.moveTextPositionByAmount(320, 560);
            contenido.drawString("Fecha");
            contenido.endText();
            contenido.drawLine(380, 550, 380, 570);

            contenido.beginText();
            contenido.setFont(fontNormal, 12);
            contenido.moveTextPositionByAmount(400, 560);
            contenido.drawString("Valor");
            contenido.endText();
            contenido.drawLine(500, 550, 500, 570);
            contenido.drawLine(500, 550, 500, 570);

            contenido.drawLine(30, 550, 500, 550);

            int altura = 550;
            /*
             * Caben en la pagina
             * Primera pagina 14
             * Seguientes paginas 21
             * Footer cuenta como 3 mas
             */
            int indiceProductos = 0;
            double totalEspaciosNecesarios = flujosID.size() + 3 + 1;
            double totalPaginas = 1;

            if (Math.floor(totalEspaciosNecesarios / 17) == 0) {
                totalPaginas = 1;
            } else {
                totalEspaciosNecesarios -= 17;
                totalPaginas += (int) Math.ceil(totalEspaciosNecesarios / 21);
            }
            double abonos = 0.0;
            double deudas = 0.0;
            for (int i = 0; i < flujosID.size(); i++) {
                ControladorFlujoFactura controladorFlujoFactura = new ControladorFlujoFactura();
                Flujo_Factura flujoFactura = controladorFlujoFactura.getFlujo_Factura(" where flujo_id=" + flujosID.get(i));

                if (flujoFactura.getTipo_flujo().equals("abono")) {
                    abonos += flujoFactura.getValor();
                } else {
                    deudas += flujoFactura.getValor();
                }
            }
            //Primer pagina
            for (int i = 0; i < flujosID.size() && altura >= 30; i++) {
                //Imprime por paginas
                ControladorFlujoFactura controladorFlujoFactura = new ControladorFlujoFactura();
                Flujo_Factura flujoFactura = controladorFlujoFactura.getFlujo_Factura(" where flujo_id=" + flujosID.get(i));

                String facturaID = String.valueOf(flujoFactura.getFactura_id());

                if (facturaID.length() > 25) {
                    facturaID = facturaID.substring(0, 26);
                }

                String tipoFlujo = flujoFactura.getTipo_flujo();

                String fechaFlujo = flujoFactura.getFecha();
                fechaFlujo = fechaFlujo.substring(0, 10);
                String valorTotal = String.valueOf(flujoFactura.getValor());

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
                contenido.drawString(facturaID);
                contenido.endText();
                contenido.drawLine(70, altura, 70, altura - 30);
                contenido.drawLine(200, altura, 200, altura - 30);

                contenido.beginText();
                contenido.setFont(fontNormal, 12);
                contenido.moveTextPositionByAmount(220, altura - 15);
                contenido.drawString(tipoFlujo);
                contenido.endText();
                contenido.drawLine(300, altura, 300, altura - 30);

                contenido.beginText();
                contenido.setFont(fontNormal, 12);
                contenido.moveTextPositionByAmount(310, altura - 15);
                contenido.drawString(fechaFlujo);
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
                Double valor = deudas - abonos;

                contenido.beginText();
                contenido.setFont(fontNormal, 12);
                contenido.moveTextPositionByAmount(300, altura - 15);
                contenido.drawString("Total abonos");
                contenido.endText();
                contenido.drawLine(380, altura, 380, altura - 30);

                contenido.beginText();
                contenido.setFont(fontNormal, 12);
                contenido.moveTextPositionByAmount(400, altura - 15);
                contenido.drawString(String.valueOf(abonos));
                contenido.endText();
                contenido.drawLine(500, altura, 500, altura - 30);

                //Linea inferior
                contenido.drawLine(300, altura, 300, altura - 30);
                contenido.drawLine(300, altura - 30, 500, altura - 30);

                altura -= 30;
                contenido.beginText();
                contenido.setFont(fontNormal, 12);
                contenido.moveTextPositionByAmount(300, altura - 15);
                contenido.drawString("Total deuda");
                contenido.endText();
                contenido.drawLine(380, altura, 380, altura - 30);

                contenido.beginText();
                contenido.setFont(fontNormal, 12);
                contenido.moveTextPositionByAmount(400, altura - 15);
                contenido.drawString(String.valueOf(deudas));
                contenido.endText();
                contenido.drawLine(500, altura, 500, altura - 30);
                //Linea inferior
                contenido.drawLine(300, altura, 300, altura - 30);
                contenido.drawLine(300, altura - 30, 500, altura - 30);
                altura -= 30;

                contenido.beginText();
                contenido.setFont(fontNormal, 12);
                contenido.moveTextPositionByAmount(300, altura - 15);
                contenido.drawString("Total debido");
                contenido.endText();
                contenido.drawLine(380, altura, 380, altura - 30);

                contenido.beginText();
                contenido.setFont(font, 12);
                contenido.moveTextPositionByAmount(400, altura - 15);
                contenido.drawString(String.valueOf(valor));
                contenido.endText();
                contenido.drawLine(500, altura, 500, altura - 30);
                //Linea inferior
                contenido.drawLine(300, altura - 30, 500, altura - 30);
                contenido.drawLine(300, altura, 300, altura - 30);

            }

            //Siguientes paginas
            for (int j = 1; j < totalPaginas; j++) {
                altura = 650;
                PDPage paginaSiguiente = new PDPage();
                document.addPage(paginaSiguiente);

                PDPageContentStream contenidoSiguiente = new PDPageContentStream(document, paginaSiguiente);
                //Escribir paginas
                for (int i = indiceProductos; i < flujosID.size() && altura >= 30; i++) {
                    //Imprime por paginas
                    //Imprime por paginas
                    ControladorFlujoFactura controladorFlujoFactura = new ControladorFlujoFactura();
                    Flujo_Factura flujoFactura = controladorFlujoFactura.getFlujo_Factura(" where flujo_id=" + flujosID.get(i));

                    String facturaID = String.valueOf(flujoFactura.getFactura_id());

                    if (facturaID.length() > 25) {
                        facturaID = facturaID.substring(0, 26);
                    }

                    String tipoFlujo = flujoFactura.getTipo_flujo();

                    String fechaFlujo = flujoFactura.getFecha();
                    fechaFlujo = fechaFlujo.substring(0, 10);
                    String valorTotal = String.valueOf(flujoFactura.getValor());

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
                    contenido.drawString(facturaID);
                    contenido.endText();
                    contenido.drawLine(70, altura, 70, altura - 30);
                    contenido.drawLine(200, altura, 200, altura - 30);

                    contenido.beginText();
                    contenido.setFont(fontNormal, 12);
                    contenido.moveTextPositionByAmount(220, altura - 15);
                    contenido.drawString(tipoFlujo);
                    contenido.endText();
                    contenido.drawLine(300, altura, 300, altura - 30);

                    contenido.beginText();
                    contenido.setFont(fontNormal, 12);
                    contenido.moveTextPositionByAmount(310, altura - 15);
                    contenido.drawString(fechaFlujo);
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
                //Si no cabe mas cierre el flujo.
                if (indiceProductos < flujosID.size()) {
                    contenidoSiguiente.close();
                }
                //En ultima pagina escribir footer
                if (j == totalPaginas - 1 && altura >= 40) {
                    Double valor = deudas - abonos;

                    contenido.beginText();
                    contenido.setFont(fontNormal, 12);
                    contenido.moveTextPositionByAmount(300, altura - 15);
                    contenido.drawString("Total abonos");
                    contenido.endText();
                    contenido.drawLine(380, altura, 380, altura - 30);

                    contenido.beginText();
                    contenido.setFont(fontNormal, 12);
                    contenido.moveTextPositionByAmount(400, altura - 15);
                    contenido.drawString(String.valueOf(abonos));
                    contenido.endText();
                    contenido.drawLine(500, altura, 500, altura - 30);

                    //Linea inferior
                    contenido.drawLine(300, altura, 300, altura - 30);
                    contenido.drawLine(300, altura - 30, 500, altura - 30);

                    altura -= 30;
                    contenido.beginText();
                    contenido.setFont(fontNormal, 12);
                    contenido.moveTextPositionByAmount(300, altura - 15);
                    contenido.drawString("Total deuda");
                    contenido.endText();
                    contenido.drawLine(380, altura, 380, altura - 30);

                    contenido.beginText();
                    contenido.setFont(fontNormal, 12);
                    contenido.moveTextPositionByAmount(400, altura - 15);
                    contenido.drawString(String.valueOf(deudas));
                    contenido.endText();
                    contenido.drawLine(500, altura, 500, altura - 30);
                    //Linea inferior
                    contenido.drawLine(300, altura, 300, altura - 30);
                    contenido.drawLine(300, altura - 30, 500, altura - 30);
                    altura -= 30;

                    contenido.beginText();
                    contenido.setFont(fontNormal, 12);
                    contenido.moveTextPositionByAmount(300, altura - 15);
                    contenido.drawString("Total debido");
                    contenido.endText();
                    contenido.drawLine(380, altura, 380, altura - 30);

                    contenido.beginText();
                    contenido.setFont(font, 12);
                    contenido.moveTextPositionByAmount(400, altura - 15);
                    contenido.drawString(String.valueOf(valor));
                    contenido.endText();
                    contenido.drawLine(500, altura, 500, altura - 30);
                    //Linea inferior
                    contenido.drawLine(300, altura - 30, 500, altura - 30);
                    contenido.drawLine(300, altura, 300, altura - 30);

                    contenidoSiguiente.close();
                } else {
                    contenidoSiguiente.close();
                }

                System.out.println("Pagina numero: " + j + " De  " + totalPaginas);

            }

            contenido.close();
            return document;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear la factura ", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

    }

}
