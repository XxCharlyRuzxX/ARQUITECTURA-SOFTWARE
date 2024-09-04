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
    private final ArrayList<HashMap<String, String>> dataLines;
    private final HashSet<String> identifiers;
    private boolean isCsvRead = false;
    private CsvFormatValidator csvFormatValidator;


    /* Constructor */
    public CsvReader(String filePath) {
        this.filePath = filePath;
        this.dataLines = new ArrayList<>();
        this.identifiers = new HashSet<>();
        this.csvFormatValidator = new CsvFormatValidator();
    }

    private void initializeReader() throws IOException, IllegalArgumentException {
        if(!this.isCsvRead){
            readCsv();
            this.isCsvRead = true;
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

    private void readCsv() throws IOException, IllegalArgumentException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(this.filePath));
        ArrayList<String[]> rows = new ArrayList<>();
        String line;
        while((line = bufferedReader.readLine()) != null) {
            String[] formatLine = line.split(",");
            formatRow(formatLine);
            rows.add(formatLine);
        }
        bufferedReader.close();
        this.csvFormatValidator.validateEmptyCsv(rows);
        String[] identifiers = rows.getFirst();
        parseIdentifiersToSet(identifiers);
        addDataRows(rows, identifiers);
    }

    private void parseIdentifiersToSet(String[] identifiers) throws IllegalArgumentException {
        this.csvFormatValidator.validateIdentifiers(identifiers);
        for (String identifier : identifiers){
            if(!this.identifiers.add(identifier)){
                throw new IllegalArgumentException("Identificadores redundantes en el CSV");
            }
        }
    }

    private void addDataRows(ArrayList<String[]> rows, String[] identifiers) {
        for(String[] row : rows){
            if(this.csvFormatValidator.validateRowData(row) && row != identifiers){
                this.dataLines.add(parseDataRowToMap(row, identifiers));
            }
        }
        this.csvFormatValidator.validateArrayEmptyData(this.dataLines);
    }

    private HashMap<String, String> parseDataRowToMap(String[] row, String[] identifiers){
        HashMap<String, String> rowMap = new HashMap<>();
        for (int position = 0; position < identifiers.length; position++){
            rowMap.put(identifiers[position], row[position]);
        }
        return rowMap;
    }

    private void formatRow(String[] row) {
        for (int position = 0; position < row.length; position++) {
            row[position] = row[position].trim();
        }
    }

}
