package fr.uvsq.cprog.collex;

import java.util.List;

public class CommandeLister implements Commande {
    private final String domaine;
    private final boolean trierParIp;

    public CommandeLister(String domaine, boolean trierParIp) {
        this.domaine = domaine;
        this.trierParIp = trierParIp;
    }

    @Override
    public boolean execute(Dns dns, DnsTUI tui) {
        List<DnsItem> items = dns.getItems(domaine);

        if (trierParIp) {
            items.sort((a, b) -> a.getIp().toString().compareTo(b.getIp().toString()));
        }

        tui.affiche(items);
        return true;
    }
}
