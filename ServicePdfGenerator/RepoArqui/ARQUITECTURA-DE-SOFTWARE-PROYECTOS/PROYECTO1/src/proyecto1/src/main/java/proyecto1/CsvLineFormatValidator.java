package proyecto1;

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


    /* Valida que exista una columna destinatario y remitente */
    public boolean checkCsvIdentifiers(String[] identifiers) throws IllegalArgumentException {
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

        return true;
    }

    /* Valida que la fila contenga ambos nombres y un formato valido */
    public boolean checkCsvParticipants(String[] identifiers) {
        if(identifiers.length > 2 || identifiers.length <= 1){
            return false;
        }
        identifiers[0] = identifiers[0].trim();
        identifiers[1] = identifiers[1].trim();
        return !(identifiers[0].isEmpty() || identifiers[1].isEmpty());
    }
}
