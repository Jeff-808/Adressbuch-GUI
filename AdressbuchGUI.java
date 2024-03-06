import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class AdressbuchGUI extends JFrame {
    private JTextArea textArea;
    private List<Kontakt> kontakte;

    public AdressbuchGUI(List<Kontakt> kontakte) {
        this.kontakte = kontakte;
        setTitle("Adressbuch");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JButton anzeigenButton = new JButton("Kontakte anzeigen");
        JButton hinzufuegenButton = new JButton("Kontakt hinzufuegen");
        JButton beendenButton = new JButton("Beenden");

        anzeigenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anzeigenKontakte();
            }
        });

        hinzufuegenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kontaktHinzufuegen();
            }
        });

        beendenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                speichernUndBeenden();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(anzeigenButton);
        buttonPanel.add(hinzufuegenButton);
        buttonPanel.add(beendenButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void anzeigenKontakte() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < kontakte.size(); i++) {
            Kontakt kontakt = kontakte.get(i);
            sb.append("Kontakt ").append(i + 1).append(":\n");
            sb.append("Vorname: ").append(kontakt.getVorname()).append("\n");
            sb.append("Nachname: ").append(kontakt.getNachname()).append("\n");
            sb.append("Telefonnummer: ").append(kontakt.getTelefonnummer()).append("\n");
            sb.append("Geburtstag: ").append(kontakt.getGeburtstag()).append("\n\n");
        }
        textArea.setText(sb.toString());
    }

    private void kontaktHinzufuegen() {
        JFrame frame = new JFrame("Kontakt hinzufuegen");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2));
        JTextField vornameField = new JTextField();
        JTextField nachnameField = new JTextField();
        JTextField telefonnummerField = new JTextField();
        JTextField geburtstagField = new JTextField();
        panel.add(new JLabel("Vorname:"));
        panel.add(vornameField);
        panel.add(new JLabel("Nachname:"));
        panel.add(nachnameField);
        panel.add(new JLabel("Telefonnummer:"));
        panel.add(telefonnummerField);
        panel.add(new JLabel("Geburtstag (dd.MM.yyyy):"));
        panel.add(geburtstagField);

        JButton addButton = new JButton("Hinzufuegen");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String vorname = vornameField.getText();
                String nachname = nachnameField.getText();
                String telefonnummer = telefonnummerField.getText();
                String geburtstag = geburtstagField.getText();
                Kontakt kontakt = new Kontakt(vorname, nachname, telefonnummer, geburtstag);
                kontakte.add(kontakt);
                anzeigenKontakte();
                frame.dispose();
            }
        });

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.getContentPane().add(addButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void speichernUndBeenden() {
        try (PrintWriter writer = new PrintWriter("kontakte.txt", "UTF-8")) {
            for (Kontakt kontakt : kontakte) {
                writer.println(kontakt.getVorname() + ";" + kontakt.getNachname() + ";" +
                        kontakt.getTelefonnummer() + ";" + kontakt.getGeburtstag());
            }
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            JOptionPane.showMessageDialog(this, "Fehler beim Speichern der Kontakte: " + e.getMessage(),
                    "Fehler", JOptionPane.ERROR_MESSAGE);
        }
        dispose(); // Schließt das Fenster und beendet die Anwendung
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                List<Kontakt> kontakte = ladeKontakte();
                new AdressbuchGUI(kontakte).setVisible(true);
            }
        });
    }

    private static List<Kontakt> ladeKontakte() {
        try (Scanner filescanner = new Scanner(new File("kontakte.txt"))) {
            List<Kontakt> kontakte = new ArrayList<>();
            while (filescanner.hasNextLine()) {
                String line = filescanner.nextLine();
                String[] values = line.split(";");
                Kontakt kontakt = new Kontakt(values[0], values[1], values[2], values[3]);
                kontakte.add(kontakt);
            }
            return kontakte;
        } catch (FileNotFoundException e) {
            System.out.println("Fehler beim Laden der Kontakte: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
