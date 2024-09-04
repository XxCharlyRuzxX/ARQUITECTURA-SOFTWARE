package com.mycompany.servicepdfgenerator.CsvScanner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CsvFormatValidator {
    private int csvColumns;

    public CsvFormatValidator(){

    }

    public void setCsvColumns(int csvColumns){
        this.csvColumns = csvColumns;
    }

    public void validateIdentifiers(String[] identifiers) throws IllegalArgumentException {
        if (identifiers == null || (identifiers.length == 1 && identifiers[0].isEmpty())){
            throw new IllegalArgumentException("No existen identificadores en el CSV");
        }
        for (String identifier: identifiers) {
            if(identifier.isEmpty()){
                throw new IllegalArgumentException("Los identificadores están incompletos");
            }
        }
        setCsvColumns(identifiers.length);
    }

    public boolean validateRowData(String[] row) {
        if (row.length != this.csvColumns){
            return false;
        }
        for (String data: row) {
            if(data.isEmpty()){
                return false;
            }
        }
        return true;
    }

    public void validateArrayEmptyData(ArrayList<HashMap<String, String>> data) {
        if(data.isEmpty()){
            throw new IllegalArgumentException("No existe información válida");
        }
    }

    public void validateEmptyCsv(ArrayList<String[]> rows) throws IOException {
        if(rows.isEmpty()){
            throw new IOException("El archivo CSV esta vacío");
        }
    }
}
