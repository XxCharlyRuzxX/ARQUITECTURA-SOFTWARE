package com.mycompany.servicepdfgenerator.CsvScanner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CsvReader {

    /* Atributos */
    private String filePath;
    private ArrayList<HashMap<String, String>> lines;
    private CsvLineFormatValidator csvLineFormatValidator;

    /* Constructor */
    public CsvReader(String filePath) {
        this.filePath = filePath;
        this.lines = new ArrayList<HashMap<String, String>>();
        this.csvLineFormatValidator = new CsvLineFormatValidator();
    }

    /* Lee el CSV y devuelve un ArrayList de Objetos "MessagePatricipants" donde hay un remitente y un destinatario */
     public ArrayList<HashMap<String, String>> getCsvMessageParticipants() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.filePath))){
            String line;
            boolean validLine;

            String[] keys = reader.readLine().split(",");

            csvLineFormatValidator.checkCsvKeys(keys);
            csvLineFormatValidator.formatCsvStrings(keys);

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                validLine = csvLineFormatValidator.checkValidLengthLine(data, keys.length);
                
                if (validLine){
                    csvLineFormatValidator.formatCsvStrings(data);
                    HashMap<String, String> dataLine = csvDataLineToHashMap(keys, data);
                    this.lines.add(dataLine);
                }
            }
            return this.lines;
        } catch (IOException e){
            System.out.println("El archivo no pudo leerse correctamente");
            return null;
        }
     }

    /* Convierte la linea en un objeto tipo MessageParticipants, asignando los nombres dependiendo de las keys de la primera fila */

    private HashMap<String, String> csvDataLineToHashMap(String[] keys, String[] data){
        HashMap<String, String> line = new HashMap<>();
        for (int i = 0; i < data.length; i++) {
            line.put(keys[i], data[i]);
        }
        return line;
    }
    
}
