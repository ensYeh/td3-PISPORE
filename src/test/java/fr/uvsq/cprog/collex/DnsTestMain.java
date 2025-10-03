package fr.uvsq.cprog.collex;

import java.io.IOException;
import java.util.List;

public class DnsTestMain {
    public static void main(String[] args) {
        try {
            // Remplacez par le chemin réel de votre fichier de base
            String fichier = "src/main/java/fr/uvsq/cprog/collex/fichier_dns.txt";
            Dns dns = new Dns(fichier);

            // Test getItem par nom
            NomMachine nom = new NomMachine("www.uvsq.fr");
            DnsItem itemNom = dns.getItem(nom);
            System.out.println("IP de " + nom + " : " + (itemNom != null ? itemNom.getIp() : "introuvable"));

            // Test getItem par IP
            AdresseIP ip = new AdresseIP("193.51.31.90");
            DnsItem itemIp = dns.getItem(ip);
            System.out.println("Nom de " + ip + " : " + (itemIp != null ? itemIp.getNomMachine() : "introuvable"));

            // Test getItems par domaine
            List<DnsItem> items = dns.getItems("uvsq.fr");
            System.out.println("Liste des machines dans uvsq.fr :");
            for (DnsItem di : items) {
                System.out.println(di);
            }

            // Test addItem
            AdresseIP nouvelleIp = new AdresseIP("193.51.25.24");
            NomMachine nouveauNom = new NomMachine("pikachu.uvsq.fr");
            dns.addItem(nouvelleIp, nouveauNom);
            System.out.println("Ajout effectué : " + nouveauNom + " -> " + nouvelleIp);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
