package proyecto1;

public class Main {
    public static void main(String[] args) {
        TemplateScanner scanner = new TemplateScanner(
                "ARQUITECTURA-DE-SOFTWARE-PROYECTOS\\PROYECTO1\\src\\proyecto1\\src\\main\\java\\proyecto1\\Estimado destinatario.txt");
        scanner.readTemplate();
        scanner.printTemplate();
    }
}