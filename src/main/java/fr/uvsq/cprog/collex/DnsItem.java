package fr.uvsq.cprog.collex;

/**
 * Représente une entrée DNS : une adresse IP associée à un nom de machine.
 */
public class DnsItem {
    private final AdresseIP ip;
    private final NomMachine nomMachine;

    public DnsItem(AdresseIP ip, NomMachine nomMachine) {
        this.ip = ip;
        this.nomMachine = nomMachine;
    }

    public AdresseIP getIp() {
        return ip;
    }

    public NomMachine getNomMachine() {
        return nomMachine;
    }

    @Override
    public String toString() {
        return ip + " " + nomMachine;
    }
}
