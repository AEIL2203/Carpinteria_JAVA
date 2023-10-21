package Inicio;

public class POO {
	private String Nom;
	private int Cant;

	public POO(String Nom, int Cant) {
		this.Nom = Nom;
		this.Cant = Cant;

	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public int getCant() {
		return Cant;
	}

	public void setCant(int cant) {
		Cant = cant;
	}

}
