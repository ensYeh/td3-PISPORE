package fr.uvsq.cprog.collex;

public class CommandeRechercheNom implements Commande {
    private final NomMachine nom;

    public CommandeRechercheNom(NomMachine nom) {
        this.nom = nom;
    }

    @Override
    public boolean execute(Dns dns, DnsTUI tui) {
        DnsItem item = dns.getItem(nom);
        tui.affiche(item != null ? item.getIp() : "Non trouvé !");
        return true;
    }
}
