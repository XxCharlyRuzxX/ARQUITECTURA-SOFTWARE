package com.mycompany.servicepdfgenerator.renderPdf;

import java.io.IOException;
import java.util.ArrayList;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import com.mycompany.servicepdfgenerator.CsvScanner.MessageParticipants;
import java.util.regex.Pattern;
/**
 *
 * @author Rey
 */
public class Renderizador {
    private int numPaginas;
    private String contenido;
    private ArrayList<MessageParticipants> participantes;

    public Renderizador(String contenido, ArrayList<MessageParticipants> participantes) {
        this.numPaginas = participantes.size();
        this.contenido = contenido;
        this.participantes = participantes;
    }

    public int getNumPaginas() {
        return numPaginas;
    }

    public void setNumPaginas(int numPaginas) {
        this.numPaginas = numPaginas;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
    private String[] prepararTextos(String contenido, ArrayList<MessageParticipants> participantes){
        String[] textos= new String[numPaginas];
       for(int i = 0; i< numPaginas; i++){
        textos[i]= contenido;
         }
       return textos;
    }
    
    private String[] reemplazarTextos(String[] textos, ArrayList<MessageParticipants> participantes){
    Pattern patternRemitente = Pattern.compile("<\\s*remitente\\s*>");
    Pattern patternDestinatario = Pattern.compile("<\\s*destinatario\\s*>");

    for (int i = 0; i < textos.length; i++) {
        // Reemplazar <remitente> con el remitente correspondiente
        textos[i] = patternRemitente.matcher(textos[i]).replaceAll(participantes.get(i).getSender());
        // Reemplazar <destinatario> con el destinatario correspondiente
        textos[i] = patternDestinatario.matcher(textos[i]).replaceAll(participantes.get(i).getAdresser());
    }

    return textos;
    }
    
    public void generarPDF(String filePath) {
        String[] textos = prepararTextos(contenido, participantes);
        String[] contenidoFinal = reemplazarTextos(textos, participantes);
        try (PDDocument document = new PDDocument()) {
            
            for (int i = 0; i < numPaginas; i++) {
                PDPage page = new PDPage();
                document.addPage(page);

                // Crear un objeto para escribir en la página
                try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                    // Establecer la fuente y el tamaño de la fuente
                    contentStream.setFont(PDType1Font.HELVETICA, 12);
                    //contentStream.setFont(PDType1Font., 12);
                    // Escribir el texto en la página
                    contentStream.beginText();
                    contentStream.newLineAtOffset(100, 700); // Ajustar las coordenadas según sea necesario
                   
                    String[] lines = contenidoFinal[i].split("\n");
                    for(String line : lines){
                        contentStream.showText(line);
                        contentStream.newLineAtOffset(0, -15);
                    }
                    

                    
                    contentStream.endText();
                }
            }
            
            // Guardar el documento en la ruta especificada
            document.save(filePath);
            System.out.println("PDF creado con éxito en: " + filePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
     
}
