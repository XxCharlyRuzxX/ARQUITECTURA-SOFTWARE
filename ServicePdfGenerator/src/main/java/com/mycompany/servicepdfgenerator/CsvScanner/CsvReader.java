package com.mycompany.servicepdfgenerator.CsvScanner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class CsvReader {

    /* Atributos */
    private final String filePath;
    private ArrayList<HashMap<String, String>> dataLines;
    private HashSet<String> identifiers
            ;
    private boolean isCsvRead = false;
    private ArrayList<String[]> csvLines;
    private int columns;

    /* Constructor */
    public CsvReader(String filePath) {
        this.filePath = filePath;
        this.dataLines = new ArrayList<>();
        this.csvLines = new ArrayList<>();
        this.identifiers = new HashSet<>();
    }



    /* Convierte la línea en un objeto tipo MessageParticipants, asignando los nombres dependiendo de las keys de la primera fila */

    private HashMap<String, String> csvDataLineToHashMap(String[] keys, String[] data){
        HashMap<String, String> line = new HashMap<>();
        for (int i = 0; i < data.length; i++) {
            line.put(keys[i], data[i]);
        }
        return line;
    }

    private void initializeReader() throws IOException, IllegalArgumentException {
        if(!this.isCsvRead){
            readCsv();
            parseIdentifiersToSet();

            this.isCsvRead = true;
        }
    }

    private void readCsv() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(this.filePath));
        String line;
        while((line = bufferedReader.readLine()) != null) {
            this.csvLines.add(line.split(","));
        }
        bufferedReader.close();
    }
    private void parseIdentifiersToSet() throws IllegalArgumentException {
        ArrayList<String[]> identifiers = new ArrayList<>(this.csvLines);
        formatRow(identifiers.get(0));
        validateIdentifiers(identifiers.get(0));
        if(!this.identifiers.addAll(Arrays.asList(identifiers.get(0)))){
            throw new IllegalArgumentException("Identificadores redundantes en el CSV");
        }
        this.columns = this.identifiers.size();
    }

    /*
    private HashMap<String, String> parseDataRowToMap(String[] row){
        String[] temporalRow = row.clone();
        formatRow(temporalRow);
        if(validateRowData(temporalRow)){
            HashMap<String, String> rowMap = new HashMap<>();
            for (int position = 0; position < this.columns; position++){
                // rowMap.put(this.identifiers.)
            }
        }
    }
*/
    private void validateIdentifiers(String[] identifiers) throws IllegalArgumentException {
        if (identifiers == null || (identifiers.length == 1 && identifiers[0].isEmpty())){
            throw new IllegalArgumentException("No existen identificadores en el CSV");
        }
        for (String identifier: identifiers) {
            if(identifier.isEmpty()){
                throw new IllegalArgumentException("Los identificadores están incompletos");
            }
        }
    }

    private boolean validateRowData(String[] row) {
        if (row.length != this.columns){
            return false;
        }
        for (String data: row) {
            if(data.isEmpty()){
                return false;
            }
        }
        return true;
    }

    private void formatRow(String[] row) {
        for (int position = 0; position < row.length; position++) {
            row[position] = row[position].trim();
        }
    }
    public ArrayList<HashMap<String, String>> getDataLines() throws IOException, IllegalArgumentException{
        initializeReader();
        return this.dataLines;
    }

    public HashSet<String> getIdentifiers() throws IOException, IllegalArgumentException {
        initializeReader();
        return this.identifiers;
    }
}
