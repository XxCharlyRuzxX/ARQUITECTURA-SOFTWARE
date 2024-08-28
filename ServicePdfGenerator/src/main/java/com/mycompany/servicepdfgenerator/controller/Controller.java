
package com.mycompany.servicepdfgenerator.controller;

import com.mycompany.servicepdfgenerator.CsvScanner.CsvReader;
import com.mycompany.servicepdfgenerator.TemplateScanner.TemplateScanner;
import com.mycompany.servicepdfgenerator.renderPdf.Renderizador;
import java.util.ArrayList;

public class Controller {

    
    private TemplateScanner templateScanner;
    private CsvReader csvReader;
    private Renderizador render;


    public Controller() {
    }
    
    public void GeneratePdf(String templatePath, String CsvPath , String outputPath){
        initialComponents(templatePath , CsvPath );
        render.generarPDF(outputPath);
    }
    
    private void initialComponents(String templatePath, String CsvPath){
        this.templateScanner = new TemplateScanner(templatePath);
        this.csvReader = new CsvReader(CsvPath);
        this.render = new Renderizador(convertArrayToString(templateScanner.getTemplateLines()),csvReader.getCsvMessageParticipants());
    }
    
    private String convertArrayToString(ArrayList<String> arrayLines) {
        // Usa StringBuilder para concatenar los elementos del ArrayList
        StringBuilder stringBuilder = new StringBuilder();
        for (String line : arrayLines) {
            stringBuilder.append(line).append("\n");
        }
        return stringBuilder.toString();
    }
    
    

}
