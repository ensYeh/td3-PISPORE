package fr.uvsq.cprog.collex;

import java.util.Scanner;

/**
 * Interface en ligne de commande pour interagir avec le DNS.
 */
public class DnsTUI {
    private final Scanner scanner;

    public DnsTUI() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Lit la ligne suivante saisie par l'utilisateur et construit une Commande.
     * @return une instance de Commande correspondant à la saisie
     */
    public Commande nextCommande() {
        System.out.print("> ");
        String ligne = scanner.nextLine().trim();

        if (ligne.isEmpty()) {
            return new CommandeInvalide("Commande vide !");
        }

        String[] tokens = ligne.split("\\s+");

        // Cas 1 : une seule entrée => soit un nom machine, soit une IP
        if (tokens.length == 1) {
            String arg = tokens[0];
            if (arg.matches("\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b")) {
                return new CommandeRechercheIP(new AdresseIP(arg));
            } else {
                return new CommandeRechercheNom(new NomMachine(arg));
            }
        }

        // Cas 2 : commande "ls"
        if (tokens[0].equals("ls")) {
            boolean parIp = false;
            String domaine;
            if (tokens.length == 2) {
                domaine = tokens[1];
            } else if (tokens.length == 3 && tokens[1].equals("-a")) {
                parIp = true;
                domaine = tokens[2];
            } else {
                return new CommandeInvalide("Usage: ls [-a] domaine");
            }
            return new CommandeLister(domaine, parIp);
        }

        // Cas 3 : commande "add"
        if (tokens[0].equals("add") && tokens.length == 3) {
            try {
                AdresseIP ip = new AdresseIP(tokens[1]);
                NomMachine nom = new NomMachine(tokens[2]);
                return new CommandeAjouter(ip, nom);
            } catch (IllegalArgumentException e) {
                return new CommandeInvalide("Arguments invalides : " + e.getMessage());
            }
        }

        return new CommandeInvalide("Commande non reconnue !");
    }

    /**
     * Affiche le résultat d'une commande.
     * @param resultat résultat à afficher
     */
    public void affiche(Object resultat) {
        if (resultat == null) {
            System.out.println("Aucun résultat.");
        } else if (resultat instanceof Iterable<?>) {
            for (Object o : (Iterable<?>) resultat) {
                System.out.println(o);
            }
        } else {
            System.out.println(resultat);
        }
    }
}
