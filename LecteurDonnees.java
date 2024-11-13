
import java.io.*;
import java.util.*;
import java.util.zip.DataFormatException;

import robots.*;
import map.Carte;
import map.Case;
import map.Incendie;
import map.NatureTerrain;
import simulation.*;


/**
 * Lecteur de cartes au format spectifié dans le sujet.
 * Les données sur les cases, robots puis incendies sont lues dans le fichier,
 * puis simplement affichées.
 * A noter: pas de vérification sémantique sur les valeurs numériques lues.
 */
public class LecteurDonnees {
    /**
     * Lit et affiche le contenu d'un fichier de donnees (cases,
     * robots et incendies).
     * Ceci est méthode de classe; utilisation:
     * LecteurDonnees.lire(fichierDonnees)
     * @param fichierDonnees nom du fichier à lire
     */
    public static DonneesSimulation lire(String fichierDonnees)throws FileNotFoundException, DataFormatException {

        LecteurDonnees lecteur = new LecteurDonnees(fichierDonnees);
        DonneesSimulation donnees = new DonneesSimulation(
                lecteur.lireCarte(),
                lecteur.lireIncendies(),
                lecteur.lireRobots()
        );
        scanner.close();  // Ferme le scanner après lecture
        return donnees;   // Renvoie l'instance de DonneesSimulation
    }

    // Tout le reste de la classe est prive!

    private static Scanner scanner;

    /**
     * Constructeur prive; impossible d'instancier la classe depuis l'exterieur
     * @param fichierDonnees nom du fichier a lire
     */
    private LecteurDonnees(String fichierDonnees)
        throws FileNotFoundException {
        scanner = new Scanner(new File(fichierDonnees));
        scanner.useLocale(Locale.US);
    }

    /**
     * Lit et affiche les donnees de la carte.
     * @throws ExceptionFormatDonnees
     */
    private Carte lireCarte() throws DataFormatException {
        ignorerCommentaires();
        try {
            int nbLignes = scanner.nextInt();
            int nbColonnes = scanner.nextInt();
            int tailleCases = scanner.nextInt();
            
            Case[][] cases = new Case[nbLignes][nbColonnes]; // Initialisation du tableau
    
            for (int lig = 0; lig < nbLignes; lig++) {
                for (int col = 0; col < nbColonnes; col++) {
                    cases[lig][col] = lireCase(lig, col); // Remplissage du tableau
                }
            }
    
            return new Carte(cases, tailleCases); // Création de la carte avec cases et taille
    
        } catch (NoSuchElementException e) {
            throw new DataFormatException("Format invalide. Attendu: nbLignes nbColonnes tailleCases");
        }
    }


    /**
     * Lit et affiche les donnees d'une case.
     */
    private Case lireCase(int lig, int col) throws DataFormatException {
        ignorerCommentaires();
        try {
            String chaineNature = scanner.next();
            NatureTerrain nature = NatureTerrain.valueOf(chaineNature); // Conversion en NatureTerrain
            Case case1 = new Case(lig, col, nature); // Manque ; ajouté ici
            verifieLigneTerminee();
    
            return case1;
    
        } catch (NoSuchElementException e) {
            throw new DataFormatException("Format de case invalide. Attendu: nature altitude [valeur_specifique]");
        }
    }


    /**
     * Lit et affiche les donnees des incendies.
     */
    private HashMap<Case, Incendie> lireIncendies() throws DataFormatException {
        ignorerCommentaires();
        try {
            int nbIncendies = scanner.nextInt();
            HashMap<Case, Incendie> incendies = new HashMap<Case, Incendie>();
            for (int i = 0; i < nbIncendies; i++) {
                Incendie incendie = lireIncendie(i);
                incendies.put(incendie.getPosition(), incendie);
            }
            return incendies;
        } catch (NoSuchElementException e) {
            throw new DataFormatException("Format invalide. " + "Attendu: nbIncendies");
        }
    }


    /**
     * Lit et affiche les donnees du i-eme incendie.
     * @param i
     */
    private Incendie lireIncendie(int i) throws DataFormatException {
        ignorerCommentaires();

        try {
            int lig = scanner.nextInt();
            int col = scanner.nextInt();
            int intensite = scanner.nextInt();
            if (intensite <= 0) {
                throw new DataFormatException("incendie " + i
                        + "nb litres pour eteindre doit etre > 0");
            }
            verifieLigneTerminee();
            Case position = new Case(lig, col, null);
            Incendie feu = new Incendie(position, intensite);
            return feu;

        } catch (NoSuchElementException e) {
            throw new DataFormatException("format d'incendie invalide. "
                    + "Attendu: ligne colonne intensite");
        }
    }


    /**
     * Lit et affiche les donnees des robots.
     */
    private ArrayList<Robot> lireRobots() throws DataFormatException {
        ignorerCommentaires();
        try {
            int nbRobots = scanner.nextInt();
            ArrayList<Robot> robots = new ArrayList<>();
            for (int i = 0; i < nbRobots; i++) {
                robots.add(lireRobot(i));
            }
            return robots;

        } catch (NoSuchElementException e) {
            throw new DataFormatException("Format invalide. "
                    + "Attendu: nbRobots");
        }
    }


    /**
     * Lit et affiche les donnees du i-eme robot.
     * @param i
     */
    private Robot lireRobot(int i) throws DataFormatException {
        ignorerCommentaires();
        try {
            int lig = scanner.nextInt();
            int col = scanner.nextInt();
            String type = scanner.next();
    
            // lecture éventuelle d'une vitesse du robot (entier)
            String s = scanner.findInLine("(\\d+)");
            double vitesse;
            
            verifieLigneTerminee();
            Case position = new Case(lig, col, null);
    
            switch (type) {
                case "DRONE": 
                    vitesse = (s == null) ? 100/3.6 : Double.parseDouble(s);
                    return new Drone(vitesse, position);  
    
                case "PATTES": 
                    return new Pattes(position);   
    
                case "CHENILLES": 
                    vitesse = (s == null) ? 60/3.6 : Double.parseDouble(s);
                    return new Chenilles(vitesse, position);
    
                case "ROUES": 
                    vitesse = (s == null) ? 80/3.6 : Double.parseDouble(s);
                    return new Roues(vitesse, position);
    
                default: 
                    throw new DataFormatException("Type de robot inconnu : " + type);
            }
    
        } catch (NoSuchElementException e) {
            throw new DataFormatException("Format de robot invalide. Attendu : ligne colonne type [valeur_specifique]");
        }
    }

    /** Ignore toute (fin de) ligne commencant par '#' */
    private void ignorerCommentaires() {
        while(scanner.hasNext("#.*")) {
            scanner.nextLine();
        }
    }

    /**
     * Verifie qu'il n'y a plus rien a lire sur cette ligne (int ou float).
     * @throws ExceptionFormatDonnees
     */
    private void verifieLigneTerminee() throws DataFormatException {
        if (scanner.findInLine("(\\d+)") != null) {
            throw new DataFormatException("format invalide, donnees en trop.");
        }
    }
}
