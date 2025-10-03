package fr.uvsq.cprog.collex;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            // Chemin de votre fichier de base DNS
            String fichier = "src/main/resources/fichier_dns.txt";
            Dns dns = new Dns(fichier);
            DnsTUI tui = new DnsTUI();

            System.out.println("=== Simulateur DNS ===");
            System.out.println("Commandes disponibles :");
            System.out.println("- nom.qualifie.machine  => affiche l'IP");
            System.out.println("- adr.es.se.ip           => affiche le nom qualifié");
            System.out.println("- ls [-a] domaine        => liste les machines d'un domaine");
            System.out.println("- add ip nom.qualifie    => ajoute un nouvel item");
            System.out.println("- quit                   => quitter");
            System.out.println("========================");

            boolean continuer = true;
            while (continuer) {
                Commande cmd = tui.nextCommande();
                continuer = cmd.execute(dns, tui);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
