package jdopack;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable(detachable="true")
public class MileStone implements Serializable { /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@PrimaryKey
	@Persistent /*(valueStrategy = IdGeneratorStrategy.IDENTITY)*/
	private Key id;
	
	@Persistent
	private String nazev;
	
	@Persistent
	private Date datum;

	@Persistent
	private String poznamka;
	
	@Persistent
	private TimeStone timeStone;
	
	public MileStone(String timeStoneKod, int poradoveCisloMileStonu, String nazev, Date datum, String poznamka) {
		this.id = creatKey(timeStoneKod, poradoveCisloMileStonu);
		this.nazev = nazev;
		this.datum = datum;
		this.poznamka = poznamka;
	}


	
	public Key getId() {
		return id;
	}



	public void setId(Key id) {
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



	public static Key creatKey(String timeStone, int id) {
		return KeyFactory.createKey(MileStone.class.getSimpleName(), timeStone+id);
	}
	
	
}
