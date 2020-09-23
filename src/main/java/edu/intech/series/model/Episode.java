package edu.intech.series.model;

public class Episode {

	private int id = -1;
	private final String title;
	private final int number;
	private boolean seen = false;

	/**
	 * @param title
	 * @param number
	 */
	public Episode(final String title, final int number) {
		this.title = title;
		this.number = number;
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
	 * @return the title
	 */
	public final String getTitle() {
		return this.title;
	}

	/**
	 * @return the number
	 */
	public final int getNumber() {
		return this.number;
	}

	/**
	 * @return the seen
	 */
	public final boolean isSeen() {
		return this.seen;
	}

	/**
	 * @param seen the seen to set
	 */
	public final void setSeen(final boolean seen) {
		this.seen = seen;
	}

	@Override
	public String toString() {
		return getTitle();
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
