/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generarPDF;

import controladores.ControladorFlujoFactura;
import entidades.Flujo_Factura;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 *
 * @author cardel
 */
public class GenerarReporteDiario {

    public void imprimiDiario(Calendar fechaInicial, Calendar fechaFinal, DefaultTableModel modeloTabla, JFrame dialogo) {
        try {
            PDDocument documento = crearDiario(fechaInicial, fechaFinal, modeloTabla, dialogo);
            documento.print();
        } catch (Exception e) {
        }
    }

    public PDDocument crearDiario(Calendar fechaInicial, Calendar fechaFinal, DefaultTableModel modeloTabla, JFrame dialogo) {
        PDDocument document = new PDDocument();

        try {

            PDPage pagina1 = new PDPage();
            document.addPage(pagina1);

            PDFont font = PDType1Font.HELVETICA_BOLD;
            PDFont fontNormal = PDType1Font.HELVETICA;

            PDPageContentStream contenido = new PDPageContentStream(document, pagina1);

            int annioInicial = fechaInicial.get(Calendar.YEAR);
            int mesInicial = fechaInicial.get(Calendar.MONTH) + 1;
            int diaInicial = fechaInicial.get(Calendar.DAY_OF_MONTH);

            String cadenaFechaInicial = annioInicial + "/" + mesInicial + "/" + diaInicial;

            int annioFinal = fechaFinal.get(Calendar.YEAR);
            int mesFinal = fechaFinal.get(Calendar.MONTH) + 1;
            int diaFinal = fechaFinal.get(Calendar.DAY_OF_MONTH);
            String cadenaFechaFinal = annioFinal + "/" + mesFinal + "/" + diaFinal;

            contenido.beginText();
            contenido.setFont(font, 16);
            contenido.moveTextPositionByAmount(30, 730);
            contenido.drawString("Diario, desde: " + cadenaFechaInicial + " hasta: " + cadenaFechaFinal);
            contenido.endText();

            contenido.drawLine(30, 680, 500, 680);
            contenido.beginText();
            contenido.setFont(fontNormal, 12);
            contenido.moveTextPositionByAmount(200, 660);
            contenido.drawString("DETALLE DEL FLUJO");
            contenido.endText();
            contenido.drawLine(30, 640, 500, 640);


            /*
             * Caben en la pagina
             * Primera pagina 14
             * Seguientes paginas 21
             * Footer cuenta como 3 mas
             */
            double totalEspaciosNecesarios = modeloTabla.getRowCount() + 3 + 1;
            double totalPaginas = 1;
            int indiceProductos = 0;

            if (Math.floor(totalEspaciosNecesarios / 20) == 0) {
                totalPaginas = 1;
            } else {
                totalEspaciosNecesarios -= 20;
                totalPaginas += (int) Math.ceil(totalEspaciosNecesarios / 24);
            }

            /*
             Encabezado tabla
             */
            contenido.drawLine(30, 620, 500, 620);

            contenido.beginText();
            contenido.setFont(fontNormal, 12);
            contenido.moveTextPositionByAmount(60, 605);
            contenido.drawString("Número factura");
            contenido.endText();
            contenido.drawLine(30, 600, 30, 620);
            contenido.drawLine(200, 600, 200, 620);

            contenido.beginText();
            contenido.setFont(fontNormal, 12);
            contenido.moveTextPositionByAmount(220, 605);
            contenido.drawString("Fecha");
            contenido.endText();
            contenido.drawLine(300, 600, 300, 620);

            contenido.beginText();
            contenido.setFont(fontNormal, 12);
            contenido.moveTextPositionByAmount(320, 605);
            contenido.drawString("Tipo");
            contenido.endText();
            contenido.drawLine(380, 600, 380, 620);

            contenido.beginText();
            contenido.setFont(fontNormal, 12);
            contenido.moveTextPositionByAmount(400, 605);
            contenido.drawString("Valor");
            contenido.endText();
            contenido.drawLine(500, 600, 500, 620);
            contenido.drawLine(500, 600, 500, 620);

            contenido.drawLine(30, 600, 500, 600);

            int altura = 600;
            /*
             Generar informes
             */
            for (int i = 0; i < modeloTabla.getRowCount() && altura >= 30; i++) {
                //Imprime por paginas

                String numeroFactura = String.valueOf(modeloTabla.getValueAt(i, 1));
                String fecha = String.valueOf(modeloTabla.getValueAt(i, 2));
                fecha = fecha.substring(0, 10);
                String tipo = String.valueOf(modeloTabla.getValueAt(i, 3));
                String valor = String.valueOf(modeloTabla.getValueAt(i, 4));

                contenido.beginText();
                contenido.setFont(fontNormal, 12);
                contenido.moveTextPositionByAmount(40, altura - 15);
                contenido.drawString(numeroFactura);
                contenido.endText();

                contenido.drawLine(30, altura, 30, altura - 30);
                contenido.drawLine(200, altura, 200, altura - 30);

                contenido.beginText();
                contenido.setFont(fontNormal, 12);
                contenido.moveTextPositionByAmount(220, altura - 15);
                contenido.drawString(fecha);
                contenido.endText();

                contenido.drawLine(300, altura, 300, altura - 30);

                contenido.beginText();
                contenido.setFont(fontNormal, 12);
                contenido.moveTextPositionByAmount(330, altura - 15);
                contenido.drawString(tipo);
                contenido.endText();
                contenido.drawLine(380, altura, 380, altura - 30);

                contenido.beginText();
                contenido.setFont(fontNormal, 12);
                contenido.moveTextPositionByAmount(420, altura - 15);
                contenido.drawString(valor);
                contenido.endText();

                contenido.drawLine(500, altura, 500, altura - 30);

                //Linea inferior
                contenido.drawLine(30, altura - 30, 500, altura - 30);
                altura -= 30;
                indiceProductos = i + 1;
            }
            //Escribir paginas siguientes

            for (int j = 1; j < totalPaginas; j++) {
                altura = 600;
                PDPage paginaSiguiente = new PDPage();
                document.addPage(paginaSiguiente);
                PDPageContentStream contenidoSiguiente = new PDPageContentStream(document, paginaSiguiente);

                for (int i = indiceProductos; i < modeloTabla.getRowCount() && altura >= 30; i++) {
                    //Imprime por paginas

                    String numeroFactura = String.valueOf(modeloTabla.getValueAt(i, 1));
                    String fecha = String.valueOf(modeloTabla.getValueAt(i, 2));
                    fecha = fecha.substring(0, 10);
                    String tipo = String.valueOf(modeloTabla.getValueAt(i, 3));
                    String valor = String.valueOf(modeloTabla.getValueAt(i, 4));

                    contenidoSiguiente.beginText();
                    contenidoSiguiente.setFont(fontNormal, 12);
                    contenidoSiguiente.moveTextPositionByAmount(40, altura - 15);
                    contenidoSiguiente.drawString(numeroFactura);
                    contenidoSiguiente.endText();

                    contenidoSiguiente.drawLine(30, altura, 30, altura - 30);
                    contenidoSiguiente.drawLine(200, altura, 200, altura - 30);

                    contenidoSiguiente.beginText();
                    contenidoSiguiente.setFont(fontNormal, 12);
                    contenidoSiguiente.moveTextPositionByAmount(220, altura - 15);
                    contenidoSiguiente.drawString(fecha);
                    contenidoSiguiente.endText();

                    contenidoSiguiente.drawLine(300, altura, 300, altura - 30);

                    contenidoSiguiente.beginText();
                    contenidoSiguiente.setFont(fontNormal, 12);
                    contenidoSiguiente.moveTextPositionByAmount(330, altura - 15);
                    contenidoSiguiente.drawString(tipo);
                    contenidoSiguiente.endText();
                    contenidoSiguiente.drawLine(380, altura, 380, altura - 30);

                    contenidoSiguiente.beginText();
                    contenidoSiguiente.setFont(fontNormal, 12);
                    contenidoSiguiente.moveTextPositionByAmount(420, altura - 15);
                    contenidoSiguiente.drawString(valor);
                    contenidoSiguiente.endText();

                    contenidoSiguiente.drawLine(500, altura, 500, altura - 30);

                    //Linea inferior
                    contenidoSiguiente.drawLine(30, altura - 30, 500, altura - 30);
                    altura -= 30;
                    indiceProductos = i + 1;
                }
                contenidoSiguiente.close();
            }

            contenido.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(dialogo, "Se ha presentado un error al generar el diario\nInformación técnica\n" + e.toString());
        }
        return document;
    }
}
