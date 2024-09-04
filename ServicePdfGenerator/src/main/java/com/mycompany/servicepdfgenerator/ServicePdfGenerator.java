package com.mycompany.servicepdfgenerator;

import com.mycompany.servicepdfgenerator.controller.Controller;


public class ServicePdfGenerator {
    public static void main(String[] args) {
        final Controller controller = new Controller();
        String templatePath = "ARQUITECTURA-SOFTWARE/src/correo.txt";
        String CsvPath = "ARQUITECTURA-SOFTWARE/src/ada1.csv";
        String outputPath = "ARQUITECTURA-SOFTWARE/src/ADA1_SALIDA.pdf";
        controller.GeneratePdf(templatePath, CsvPath, outputPath);
    }
}