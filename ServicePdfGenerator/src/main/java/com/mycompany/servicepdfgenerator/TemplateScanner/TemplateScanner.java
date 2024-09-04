package com.mycompany.servicepdfgenerator.TemplateScanner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TemplateScanner {

    private String templatePath;
    private ArrayList<String> templateLines;

    public TemplateScanner(String templatePath) {
        this.templatePath = templatePath;
        this.templateLines = new ArrayList<>();
    }

    public ArrayList<String> getTemplateLines() {
        readTemplate();
        return templateLines;
    }
    

    private void readTemplate() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(templatePath));
            String line;
            while ((line = reader.readLine()) != null) {
                templateLines.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    

}
