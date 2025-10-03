package fr.uvsq.cprog.collex;

import java.util.Objects;

/**
 * Représente une adresse IP.
 */
public class AdresseIP {
    private final String ip;

    public AdresseIP(String ip) {
        String[] octets = ip.split("\\.");
        if (octets.length != 4) {
            throw new IllegalArgumentException("Adresse IP invalide : " + ip);
        }
        for (String o : octets) {
            int n;
            try {
                n = Integer.parseInt(o);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Adresse IP invalide : " + ip);
            }
            if (n < 0 || n > 255) {
                throw new IllegalArgumentException("Adresse IP invalide : " + ip);
            }
        }
        this.ip = ip;
    }


    public String getIp() {
        return ip;
    }

    @Override
    public String toString() {
        return ip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdresseIP)) return false;
        AdresseIP adresseIP = (AdresseIP) o;
        return ip.equals(adresseIP.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip);
    }
}
