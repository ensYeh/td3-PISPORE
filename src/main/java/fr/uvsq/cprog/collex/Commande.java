package fr.uvsq.cprog.collex;

/**
 * Interface Commande : chaque action doit pouvoir être exécutée.
 */
public interface Commande {
    /**
     * Exécute la commande.
     *
     * @param dns le serveur DNS
     * @param tui l'interface utilisateur pour afficher le résultat
     * @return true si l'application doit continuer, false pour quitter
     */
    boolean execute(Dns dns, DnsTUI tui);
}
