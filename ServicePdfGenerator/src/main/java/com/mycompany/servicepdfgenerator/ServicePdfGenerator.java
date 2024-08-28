package com.mycompany.servicepdfgenerator;

import com.mycompany.servicepdfgenerator.controller.Controller;


public class ServicePdfGenerator {
    public static void main(String[] args) {
        final Controller controller = new Controller();
        String templatePath = "/Users/usuario/Downloads/correo.txt";
        String CsvPath = "/Users/usuario/Downloads/ada1.csv";
        String outputPath = "/Users/usuario/Downloads/ADA1_SALIDA.pdf";    
        controller.GeneratePdf(templatePath, CsvPath, outputPath);
    }
}
