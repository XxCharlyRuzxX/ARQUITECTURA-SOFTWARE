package com.mycompany.servicepdfgenerator.CsvScanner;


public class CsvLineFormatValidator {

    public CsvLineFormatValidator(){
    }

    public void checkCsvKeys(String[] keys) throws IllegalArgumentException {
        if(keys == null || (keys.length == 1 && keys[0].isEmpty())){
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
