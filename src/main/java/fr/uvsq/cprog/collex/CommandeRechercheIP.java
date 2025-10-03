package fr.uvsq.cprog.collex;

public class CommandeRechercheIP implements Commande {
    private final AdresseIP ip;

    public CommandeRechercheIP(AdresseIP ip) {
        this.ip = ip;
    }

    @Override
    public boolean execute(Dns dns, DnsTUI tui) {
        DnsItem item = dns.getItem(ip);
        tui.affiche(item != null ? item.getNomMachine() : "Non trouvé !");
        return true;
    }
}
