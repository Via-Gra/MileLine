package mileline.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

//import org.hibernate.validator.constraints.NotBlank;

/* (identityType = IdentityType.APPLICATION) */
public class TimeStone implements Serializable {

	// private static final long serialVersionUID = 1728167041538065348L;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// @NotBlank
	private Long idStonu;

	/** street and number */
	private String nazev;

	private int pocetKreditu;

	private String kod;
	private Date zacatek;
	private Date konec;
	private boolean sudost;
	private Typ typ;
	private int pocetMileStonu;
	
	private ArrayList<MileStone> mileStony;


	public TimeStone(String nazevStonu, String kodStonu, Typ typ) {
		this.nazev = nazevStonu;
		this.kod = kodStonu;
//		this.idStonu = creatKey(kodStonu);
		this.typ = typ;
		mileStony = new ArrayList<MileStone>();
	}

	

	public Long getIdStonu() {
		return idStonu;
	}



	public void setIdStonu(Long idStonu) {
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



	@Override
	public String toString() {
		return "TimeStone [idStonu=" + idStonu + ", nazev=" + nazev
				+ ", pocetKreditu=" + pocetKreditu + ", kod=" + kod
				+ ", zacatek=" + zacatek + ", konec=" + konec + ", sudost="
				+ sudost + ", typ=" + typ + ", pocetMileStonu="
				+ pocetMileStonu + ", mileStony=" + mileStony + "]";
	}
	
	
//	public static Key creatKey(String kodStonu) {
//		return KeyFactory.createKey(TimeStone.class.getSimpleName(), kodStonu);
//	}

}
