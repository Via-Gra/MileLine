package jdopack;

import javax.jdo.annotations.*;
import com.google.appengine.api.datastore.Key;

//import org.hibernate.validator.constraints.NotBlank;

@PersistenceCapable/*(identityType = IdentityType.APPLICATION)*/
public class TimeStone{
	
	//private static final long serialVersionUID = 1728167041538065348L;
	
	//@NotBlank
		 @PrimaryKey
		 @Persistent /*(valueStrategy = IdGeneratorStrategy.IDENTITY)*/
		private Key idStonu;
		 
	/** street and number */
	@Persistent
	private String nazevStonu;
	
	@Persistent
	private String kodStonu;
	
	

	 @Persistent
	private int pocetKreditu;

	public TimeStone (String nazevStonu,String kodStonu, Key idStonu){
		this.nazevStonu = nazevStonu;
		this.idStonu = idStonu;
		this.kodStonu = kodStonu;
	}
	

	public String getNazevPredmetu() {
		return nazevStonu;
	}

	public void setNazevPredmetu(String nazevPredmetu) {
		this.nazevStonu = nazevPredmetu;
	}

	public Key getIdPredmetu() {
		return idStonu;
	}

	public void setIdPredmetu(Key idStonu) {
		this.idStonu = idStonu;
	}

	public int getPocetKreditu() {
		return pocetKreditu;
	}

	public void setPocetKreditu(int pocetKreditu) {
		this.pocetKreditu = pocetKreditu;
	}

	public String getKodStonu() {
		return kodStonu;
	}

	public void setKodStonu(String kodStonu) {
		this.kodStonu = kodStonu;
	}
	
	
}

