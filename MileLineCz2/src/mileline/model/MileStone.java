package mileline.model;

import java.io.Serializable;
import java.util.Date;

public class MileStone implements Serializable { /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Long id;
	
	private String nazev;
	
	private Date datum;

	private String poznamka;
	
	private TimeStone timeStone;
	
	public MileStone(String nazev, Date datum, String poznamka) {
		this.nazev = nazev;
		this.datum = datum;
		this.poznamka = poznamka;
	}


	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNazev() {
		return nazev;
	}



	public void setNazev(String nazev) {
		this.nazev = nazev;
	}



	public Date getDatum() {
		return datum;
	}



	public void setDatum(Date datum) {
		this.datum = datum;
	}



	public String getPoznamka() {
		return poznamka;
	}



	public void setPoznamka(String poznamka) {
		this.poznamka = poznamka;
	}



	public TimeStone getTimeStone() {
		return timeStone;
	}



	public void setTimeStone(TimeStone timeStone) {
		this.timeStone = timeStone;
	}



	@Override
	public String toString() {
		return "MileStone [nazev=" + nazev + ", datum=" + datum + ", poznamka="
				+ poznamka + ", timeStone=" + timeStone + "]";
	}

	
	

	
	


//	public static Key creatKey(String timeStone, int id) {
//		return KeyFactory.createKey(MileStone.class.getSimpleName(), timeStone+id);
//	}
	
	
}
