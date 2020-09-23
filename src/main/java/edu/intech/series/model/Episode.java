package edu.intech.series.model;

public class Episode {

	private int id = -1;
	private final String titre;
	private final int numero;
	private boolean vu = false;

	/**
	 * @param titre
	 * @param numero
	 * @param vu
	 */
	public Episode(final String titre, final int numero) {
		this.titre = titre;
		this.numero = numero;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * @return the titre
	 */
	public final String getTitre() {
		return this.titre;
	}

	/**
	 * @return the numero
	 */
	public final int getNumero() {
		return this.numero;
	}

	/**
	 * @return the vu
	 */
	public final boolean isVu() {
		return this.vu;
	}

	/**
	 * @param vu the vu to set
	 */
	public final void setVu(final boolean vu) {
		this.vu = vu;
	}

	@Override
	public String toString() {
		return getTitre();
	}

	@Override
	public int hashCode() {
		return getId();
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof Episode)) {
			return false;
		}
		return ((Episode) obj).getId() == getId();
	}

}
