package jdopack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

//import org.hibernate.validator.constraints.NotBlank;

@PersistenceCapable(detachable="true")
/* (identityType = IdentityType.APPLICATION) */
public class TimeStone implements Serializable {

	// private static final long serialVersionUID = 1728167041538065348L;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// @NotBlank
	@PrimaryKey
	@Persistent /*(valueStrategy = IdGeneratorStrategy.IDENTITY)*/
	private Key idStonu;

	/** street and number */
	@Persistent
	private String nazev;

	@Persistent
	private int pocetKreditu;

	@Persistent
	private String kod;
	@Persistent
	private Date zacatek;
	@Persistent
	private Date konec;
	@Persistent
	private boolean sudost;
	@Persistent
	private Typ typ;
	@Persistent
	private int pocetMileStonu;
	
	@Persistent(mappedBy = "timeStone")
	private ArrayList<MileStone> mileStony;


	public TimeStone(String nazevStonu, String kodStonu, Typ typ) {
		this.nazev = nazevStonu;
		this.kod = kodStonu;
		this.idStonu = creatKey(kodStonu);
		this.typ = typ;
		mileStony = new ArrayList<MileStone>();
	}

	

	public Key getIdStonu() {
		return idStonu;
	}



	public void setIdStonu(Key idStonu) {
		this.idStonu = idStonu;
	}



	public String getNazev() {
		return nazev;
	}



	public void setNazev(String nazev) {
		this.nazev = nazev;
	}



	public int getPocetKreditu() {
		return pocetKreditu;
	}



	public void setPocetKreditu(int pocetKreditu) {
		this.pocetKreditu = pocetKreditu;
	}



	public String getKod() {
		return kod;
	}



	public void setKod(String kod) {
		this.kod = kod;
	}



	public Date getZacatek() {
		return zacatek;
	}



	public void setZacatek(Date zacatek) {
		this.zacatek = zacatek;
	}



	public Date getKonec() {
		return konec;
	}



	public void setKonec(Date konec) {
		this.konec = konec;
	}



	public boolean isSudost() {
		return sudost;
	}



	public void setSudost(boolean sudost) {
		this.sudost = sudost;
	}



	public Typ getTyp() {
		return typ;
	}



	public void setTyp(Typ typ) {
		this.typ = typ;
	}



	public int getPocetMileStonu() {
		return pocetMileStonu;
	}



	public void setPocetMileStonu(int pocetMileStonu) {
		this.pocetMileStonu = pocetMileStonu;
		pocetMileStonu = mileStony.size();
	}



	public ArrayList<MileStone> getMileStony() {
		return mileStony;
	}



	public void setMileStony(ArrayList<MileStone> mileStony) {
		this.mileStony = mileStony;
	}

	public void addMileStone (MileStone ms){
		mileStony.add(ms);
		pocetMileStonu = mileStony.size();
	}


	public static Key creatKey(String kodStonu) {
		return KeyFactory.createKey(TimeStone.class.getSimpleName(), kodStonu);
	}

}
