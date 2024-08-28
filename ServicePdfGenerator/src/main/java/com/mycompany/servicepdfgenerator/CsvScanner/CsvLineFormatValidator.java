package com.mycompany.servicepdfgenerator.CsvScanner;


public class CsvLineFormatValidator {

    private int adresserIndex;
    private int senderIndex;

    public CsvLineFormatValidator(){
        this.adresserIndex = -1;
        this.senderIndex = -1;
    }


    public int getAdresserIndex(){
        return this.adresserIndex;
    }

    public int getSenderIndex(){
        return this.senderIndex;
    }

    /*
    public void checkCsvIdentifiers(String[] identifiers) throws IllegalArgumentException {
        for(int i = 0; i < identifiers.length; i++){
            if (identifiers[i].trim().equalsIgnoreCase("destinatario")){
                this.adresserIndex = i;
            } else if (identifiers[i].trim().equalsIgnoreCase("remitente")) {
                this.senderIndex = i;
            }
        }

        if (this.adresserIndex == -1 | this.senderIndex == -1){
            throw new IllegalArgumentException("Las palabras claves destinatario o remitente no fueron declaradas en el CSV");
        }

    }*/

    public void checkCsvKeys(String[] keys) throws IllegalArgumentException {
        if(keys.length < 1){
            throw new IllegalArgumentException("No se encontraron identificadores en el csv");
        }
    }

    public void formatCsvStrings(String[] keys){
        for(int keyposition = 0; keyposition < keys.length; keyposition++){
            keys[keyposition] = keys[keyposition].trim();
        }
    }

    public boolean checkValidLengthLine(String[] data, int numberOfKeys){
        return data.length == numberOfKeys;
    }
}
