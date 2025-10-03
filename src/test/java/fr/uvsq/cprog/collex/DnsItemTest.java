package fr.uvsq.cprog.collex;

import org.junit.Test;
import static org.junit.Assert.*;

public class DnsItemTest {

    @Test
    public void testDnsItemCreation() {
        AdresseIP ip = new AdresseIP("192.168.1.10");
        NomMachine nom = new NomMachine("poste.uvsq.fr");
        DnsItem item = new DnsItem(ip, nom);

        assertEquals(ip, item.getIp());
        assertEquals(nom, item.getNomMachine());
        assertEquals("192.168.1.10 poste.uvsq.fr", item.toString());
    }
}
