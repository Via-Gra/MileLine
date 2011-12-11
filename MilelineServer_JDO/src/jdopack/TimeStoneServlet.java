package jdopack;


import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.repackaged.org.json.JSONObject;

@SuppressWarnings("serial")
public class TimeStoneServlet extends HttpServlet {
	
	
	//Pokud se jedna o GET 
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		PersistenceManager pm = null;
		try {
			 pm = PMF.get().getPersistenceManager();//tohle mi dovoluje pracovat s databazi a pouzivat persistent - jedna se o singleton v souvoru PMF.java
			 String kodStonu = req.getParameter("kod");//nactu za otaznikem hodnotu name - tj milelinecz.appspot.com/timeStone?kod=Radek vrati Radek
			 Key k = TimeStone.creatKey (kodStonu);//vygeneruje klíè
			 TimeStone nalezenyTimeStone = pm.getObjectById(TimeStone.class,k);//podle klíèe hledá v DB a vrátí to objekt tøídy TimeStone
			 JSONObject obj = new JSONObject(nalezenyTimeStone);//Objekt vhodný pro pøenos ze serveru, jestli tomu dobøe rozumim
			 // Na výstup napíše hlášku - dá se tak i debugovat trochu - je to pak vidìt v tom prográmku .jar - tj napíše to JSON objekt a pak i MileStone toho nalezenýho TimeStonu - vlastnì teda jen jeho název
			 resp.getWriter().println(obj.toString() + ", " + nalezenyTimeStone.getMileStony().get(0).getNazev());
		}finally {
			pm.close();
		}
	}
	
	//Pokud se jedna o POST 
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			//resp.setContentType("text/plain");
		PersistenceManager pm = null;
		 try {
			 pm = PMF.get().getPersistenceManager();//tohle mi dovoluje pracovat s databazi a pouzivat persistent - jedna se o singleton v souvoru PMF.java
			 String nazevStonu = req.getParameter("name");//nactu za otaznikem hodnotu name - tj milelinecz.appspot.com/timeStone?name=Radek vrati Radek
			 String kodStonu = req.getParameter("kod");
			 TimeStone timeStone = new TimeStone(nazevStonu,kodStonu,Typ.PREDMET); // konstruktor, lze zmìnit, lze jich udìlat vic dle typu
			
			 // vytvoøení zkuševního data
			 Date date = new Date ();
			 DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
			 date = dfm.parse("2011-12-24 20:15:00 +0200");
			 
			 // Vytvoøení zkušebního Milestonu hned pøi tvorbì TimeStonu - jen ukázka, pak to tady nemá co dìlat..
			 timeStone.addMileStone(new MileStone (timeStone.getNazev(),timeStone.getPocetMileStonu()+1,"Nic nedelani", date,"pohoda"));
			 
			 //Pokud jsou pøedány i kredity, tak je to settne - nejsou defaultní hodnota, tak bych to øešil takhle - nejsou v konstruktoru pøedávaný
			 if (req.getParameter("kredity") != null) timeStone.setPocetKreditu(( Integer.parseInt(req.getParameter("kredity"))));	
			 
			 // to uloží timeStone - jeho hodnoty - tj i ArrayList s MileStonama, takže to uloží i ty
			 pm.makePersistent(timeStone);
			 
			 // Na výstup napíše hlášku - dá se tak i debugovat trochu - je to pak vidìt v tom prográmku .jar
			 resp.getWriter().println("Post proceed");
		    } catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
		     pm.close();
		    }

	}
}
