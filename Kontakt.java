/**
 * Eine Klasse, die einen Kontakt im Adressbuch repraesentiert.
 * 
 * @author Jeff Benad
 */
public class Kontakt {
    
    private String vorname;
    private String nachname;
    private String telefonnummer;
    private String geburtstag;
    
    /**
     * Konstruktor fuer die Initialisierung eines Kontakts mit Vorname, Nachname, Telefonnummer und Geburtstag.
     * 
     * @param vorname Der Vorname des Kontakts.
     * @param nachname Der Nachname des Kontakts.
     * @param telefonnummer Die Telefonnummer des Kontakts.
     * @param geburtstag Der Geburtstag des Kontakts im Format "dd.MM.yyyy".
     */
    public Kontakt(String vorname, String nachname, String telefonnummer, String geburtstag) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.telefonnummer = telefonnummer;
        this.geburtstag = geburtstag;     
    }

    /**
     * Setzt den Vorname des Kontakts.
     * 
     * @param vorname Der neue Vorname des Kontakts.
     */
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    /**
     * Setzt den Nachname des Kontakts.
     * 
     * @param nachname Der neue Nachname des Kontakts.
     */
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    /**
     * Setzt die Telefonnummer des Kontakts.
     * 
     * @param telefonnummer Die neue Telefonnummer des Kontakts.
     */
    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    /**
     * Gibt den Vorname des Kontakts zurueck.
     * 
     * @return Der Vorname des Kontakts.
     */
    public String getVorname() {
        return this.vorname;
    }
    
    /**
     * Gibt den Nachname des Kontakts zurueck.
     * 
     * @return Der Nachname des Kontakts.
     */
    public String getNachname() {
        return this.nachname;
    }

    /**
     * Gibt die Telefonnummer des Kontakts zurueck.
     * 
     * @return Die Telefonnummer des Kontakts.
     */
    public String getTelefonnummer() {
        return this.telefonnummer;
    }

    /**
     * Gibt den Geburtstag des Kontakts zurueck.
     * 
     * @return Der Geburtstag des Kontakts im Format "dd.MM.yyyy".
     */
    public String getGeburtstag() {
        return this.geburtstag;
    }
}