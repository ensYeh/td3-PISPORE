package fr.uvsq.cprog.collex;

import org.junit.Test;
import static org.junit.Assert.*;

public class NomMachineTest {

    @Test
    public void testValidNomMachine() {
        NomMachine nom = new NomMachine("www.uvsq.fr");
        assertEquals("www", nom.getNom());
        assertEquals("uvsq.fr", nom.getDomaine());
        assertEquals("www.uvsq.fr", nom.getNomQualifie());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidNomMachine() {
        new NomMachine("invalidname"); // Nom invalide
    }

    @Test
    public void testEqualsAndHashCode() {
        NomMachine n1 = new NomMachine("ecampus.uvsq.fr");
        NomMachine n2 = new NomMachine("ecampus.uvsq.fr");
        NomMachine n3 = new NomMachine("poste.uvsq.fr");

        assertTrue(n1.equals(n2));
        assertFalse(n1.equals(n3));
        assertEquals(n1.hashCode(), n2.hashCode());
        assertNotEquals(n1.hashCode(), n3.hashCode());
    }
}
