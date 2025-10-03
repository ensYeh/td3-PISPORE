package fr.uvsq.cprog.collex;

public class CommandeQuitter implements Commande {
    @Override
    public boolean execute(Dns dns, DnsTUI tui) {
        tui.affiche("Au revoir !");
        return false; // signal pour arrêter la boucle
    }
}
