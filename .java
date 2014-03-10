package projet.gui.atelier;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import projet.personnage.Elfe;
import projet.personnage.Humain;
import projet.personnage.Ogre;
import projet.personnage.Personnage;

public class Atelier {

	private static ArrayList<Personnage> personnages;

	public Atelier() {
		personnages = new ArrayList<Personnage>();
	}

	public boolean ajouterPersonnage(String race, String nom, int vitesse,
			int force) {
		Personnage ajout;
		if (race.equals("Humain")) {
			ajout = new Humain(nom, vitesse, force);
		} else if (race.equals("Ogre")) {
			ajout = new Ogre(nom, vitesse, force);
		} else {
			ajout = new Elfe(nom, vitesse, force);
		}
		boolean existe = false;
		for (Personnage personnage : personnages) {
			if (ajout.equals(personnage)) {
				existe = true;
			}
		}
		if (!existe) {
			personnages.add(ajout);
		} else {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		String res = new String();
		for (int i = 1; i <= personnages.size(); i++) {
			res += i + ". " + personnages.get(i - 1) + "\n";
		}
		return res;
	}

	public Personnage createPersonnageFromLine(String ligne) {
		String[] tab = ligne.split("\t");
		String nom = tab[1];
		int vitesse = Integer.parseInt(tab[2]);
		int force = Integer.parseInt(tab[3]);
		if (tab[0].equals("Hero")) {
			return new Ogre(nom, vitesse, force);
		} else if (tab[0].equals("Humain")) {
			return new Humain(nom, vitesse, force);
		} else {
			return new Elfe(nom, vitesse, force);
		}

	}

	public Personnage creerPersonnage(String race, String nom, int vitesse,
			int force) {
		if (race.equals("Ogre")) {
			return new Ogre(nom, vitesse, force);
		} else if (race.equals("Humain")) {
			return new Humain(nom, vitesse, force);
		} else {
			return new Elfe(nom, vitesse, force);
		}
	}

	public void addToFile(ArrayList<Personnage> personnages) throws Exception {
		BufferedWriter buffer = new BufferedWriter(new FileWriter(
				"personnages.txt"));
		for (Personnage l : personnages) {
			buffer.write(l.toBase() + "\n");
		}
		buffer.close();
	}

	public void chargerPersonnage(String file_name) throws Exception {
		BufferedReader buff = new BufferedReader(new FileReader(file_name));
		String line = buff.readLine();
		while (line != null) {
			Personnage new_personnage = createPersonnageFromLine(line);
			personnages.add(new_personnage);
			line = buff.readLine();
		}
		buff.close();
	}

	public ArrayList<Personnage> getPersonnages() {
		return new ArrayList<Personnage>(this.personnages);
	}

}
