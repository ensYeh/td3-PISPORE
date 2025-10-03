package fr.uvsq.cprog.collex;

import java.io.IOException;

public class DnsApp {
    private final Dns dns;
    private final DnsTUI tui;

    /**
     * Constructeur : initialise la base DNS et l'interface utilisateur.
     *
     * @param fichier chemin vers le fichier de base DNS
     * @throws IOException si le fichier ne peut pas être lu
     */
    public DnsApp(String fichier) throws IOException {
        this.dns = new Dns(fichier);
        this.tui = new DnsTUI();
    }

    /**
     * Boucle principale : récupère les commandes, exécute et affiche le résultat.
     */
    public void run() {
        boolean continuer = true;
        while (continuer) {
            // Récupérer la prochaine commande
            Commande cmd = tui.nextCommande();
            // Exécuter la commande et continuer ou non
            continuer = cmd.execute(dns, tui);
        }
    }

    /**
     * Méthode main pour lancer l'application.
     */
    public static void main(String[] args) {
        try {
            String fichier = "src/main/java/fr/uvsq/cprog/collex/fichier_dns.txt";
            DnsApp app = new DnsApp(fichier);
            app.run();
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de la base DNS : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
