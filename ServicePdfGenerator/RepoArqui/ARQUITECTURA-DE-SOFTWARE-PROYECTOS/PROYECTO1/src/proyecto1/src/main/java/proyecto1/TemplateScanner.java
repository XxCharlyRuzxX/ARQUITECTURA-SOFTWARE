package proyecto1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TemplateScanner {

    private String templatePath;
    private String templateContent;

    public TemplateScanner(String templatePath) {
        this.templatePath = templatePath;
        this.templateContent = "";
    }

    public void readTemplate() {
        StringBuilder contentBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(templatePath));
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.templateContent = contentBuilder.toString();
    }

    public void printTemplate() {
        System.out.println(templateContent);
    }

    public String getTemplateContent() {
        return templateContent;
    }

    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

}
