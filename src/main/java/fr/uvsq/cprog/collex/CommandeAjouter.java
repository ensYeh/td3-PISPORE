package fr.uvsq.cprog.collex;

import java.io.IOException;

public class CommandeAjouter implements Commande {
    private final AdresseIP ip;
    private final NomMachine nom;

    public CommandeAjouter(AdresseIP ip, NomMachine nom) {
        this.ip = ip;
        this.nom = nom;
    }

    @Override
    public boolean execute(Dns dns, DnsTUI tui) {
        try {
            dns.addItem(ip, nom);
            tui.affiche("Ajout réussi : " + ip + " " + nom);
        } catch (IllegalArgumentException | IOException e) {
            tui.affiche("ERREUR : " + e.getMessage());
        }
        return true;
    }
}
