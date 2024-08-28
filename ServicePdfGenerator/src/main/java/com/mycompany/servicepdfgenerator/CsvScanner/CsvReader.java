package com.mycompany.servicepdfgenerator.CsvScanner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CsvReader {

    /* Atributos */
    private String filePath;
    private ArrayList<MessageParticipants> participants;
    private CsvLineFormatValidator csvLineFormatValidator;

    /* Constructor */
    public CsvReader(String filePath) {
        this.filePath = filePath;
        this.participants = new ArrayList<MessageParticipants>();
        this.csvLineFormatValidator = new CsvLineFormatValidator();
    }

    /* Lee el CSV y devuelve un ArrayList de Objetos "MessagePatricipants" donde hay un remitente y un destinatario */
     public ArrayList<MessageParticipants> getCsvMessageParticipants() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.filePath))){
            String line;
            boolean validLine;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                if(lineNumber == 1){
                    try {
                        csvLineFormatValidator.checkCsvIdentifiers(data);
                        lineNumber++;
                        continue;
                    } catch (IllegalArgumentException e ){
                        System.out.println(e.getMessage());
                        return null;
                    }
                }

                validLine = csvLineFormatValidator.checkCsvParticipants(data);
                
                if (validLine){
                    csvParticipantsToArrayList(data);
                }
            }
            return participants;
        } catch (IOException e){
            System.out.println("El archivo no pudo leerse correctamente");
            return null;
        }
    }

    /* Convierte la linea en un objeto tipo MessageParticipants, asignando los nombres dependiendo de las keys de la primera fila */
    private void csvParticipantsToArrayList(String[] participants){
        String sender = participants[this.csvLineFormatValidator.getSenderIndex()];
        String adresser = participants[this.csvLineFormatValidator.getAdresserIndex()];
        MessageParticipants messageParticipants = new MessageParticipants(sender, adresser);
        this.participants.add(messageParticipants);
    }
    
}
