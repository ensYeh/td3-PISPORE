package fr.uvsq.cprog.collex;

public class CommandeInvalide implements Commande {
    private final String message;

    public CommandeInvalide(String message) {
        this.message = message;
    }

    @Override
    public boolean execute(Dns dns, DnsTUI tui) {
        tui.affiche("Commande invalide : " + message);
        return true;
    }
}
