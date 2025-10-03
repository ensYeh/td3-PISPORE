package fr.uvsq.cprog.collex;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe Dns : gère la base d'adresses IP et noms de machines.
 */
public class Dns {
    private final Map<NomMachine, AdresseIP> nomToIp = new HashMap<>();
    private final Map<AdresseIP, NomMachine> ipToNom = new HashMap<>();
    private final Path fichierDb;

    /**
     * Constructeur : charge la base de données depuis un fichier.
     *
     * @param fichier chemin du fichier texte contenant la base DNS
     * @throws IOException si le fichier ne peut pas être lu
     */
    public Dns(String fichier) throws IOException {
        this.fichierDb = Paths.get(fichier);
        chargerBase();
    }

    private void chargerBase() throws IOException {
        if (!Files.exists(fichierDb)) {
            Files.createFile(fichierDb); // crée un fichier vide si inexistant
        }
        List<String> lignes = Files.readAllLines(fichierDb);
        for (String ligne : lignes) {
            String[] parts = ligne.trim().split("\\s+");
            if (parts.length == 2) {
                NomMachine nom = new NomMachine(parts[0]);
                AdresseIP ip = new AdresseIP(parts[1]);
                nomToIp.put(nom, ip);
                ipToNom.put(ip, nom);
            }
        }
    }

    /**
     * Retourne le DnsItem correspondant à un NomMachine.
     */
    public DnsItem getItem(NomMachine nom) {
        AdresseIP ip = nomToIp.get(nom);
        if (ip == null) return null;
        return new DnsItem(ip, nom);
    }

    /**
     * Retourne le DnsItem correspondant à une AdresseIP.
     */
    public DnsItem getItem(AdresseIP ip) {
        NomMachine nom = ipToNom.get(ip);
        if (nom == null) return null;
        return new DnsItem(ip, nom);
    }

    /**
     * Retourne la liste des DnsItem d'un domaine donné.
     * Tri par nom de machine.
     */
    public List<DnsItem> getItems(String domaine) {
        return nomToIp.entrySet().stream()
                .filter(e -> e.getKey().getDomaine().equals(domaine))
                .map(e -> new DnsItem(e.getValue(), e.getKey()))
                .sorted(Comparator.comparing(item -> item.getNomMachine().getNom()))
                .collect(Collectors.toList());
    }

    /**
     * Ajoute un nouvel item à la base.
     * @throws IllegalArgumentException si le nom ou l'adresse existe déjà
     * @throws IOException si le fichier ne peut pas être mis à jour
     */
    public void addItem(AdresseIP ip, NomMachine nom) throws IOException {
        if (nomToIp.containsKey(nom)) {
            throw new IllegalArgumentException("ERREUR : Le nom de machine existe déjà !");
        }
        if (ipToNom.containsKey(ip)) {
            throw new IllegalArgumentException("ERREUR : L'adresse IP existe déjà !");
        }
        nomToIp.put(nom, ip);
        ipToNom.put(ip, nom);
        sauvegarderBase();
    }

    /**
     * Sauvegarde la base de données dans le fichier.
     */
    private void sauvegarderBase() throws IOException {
        List<String> lignes = nomToIp.entrySet().stream()
                .map(e -> e.getKey().getNomQualifie() + " " + e.getValue().getIp())
                .collect(Collectors.toList());
        Files.write(fichierDb, lignes, StandardOpenOption.TRUNCATE_EXISTING);
    }
}
