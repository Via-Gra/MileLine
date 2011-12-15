package jdopack;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public class TimeStoneServlet extends HttpServlet {

	// Pokud se jedna o GET
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		PersistenceManager pm = null;
		List<TimeStone> list=null;
		Gson gson = new Gson();
		try {
			pm = PMF.get().getPersistenceManager();// tohle mi dovoluje pracovat
													// s databazi a pouzivat
													// persistent - jedna se o
													// singleton v souvoru
													// PMF.java
			String kodStonu = req.getParameter("kod");// nactu za otaznikem
														// hodnotu name - tj
														// milelinecz.appspot.com/timeStone?kod=Radek
														// vrati Radek

			if (kodStonu.equals("ALL")) {
				String query = "select from " + TimeStone.class.getName();
				list = (List<TimeStone>) pm.newQuery(query).execute();
				
				resp.getWriter().append(gson.toJson(list));
				
//				for (TimeStone obj : list) {
//					resp.getWriter().append(gson.toJson(obj));
//				}
				
				resp.getWriter().flush();
				resp.getWriter().close();
			} else {
//				Long.parseLong(kodStonu);
//				Key k = TimeStone.creatKey(kodStonu);// vygeneruje kl��
				TimeStone nalezenyTimeStone = pm.getObjectById(TimeStone.class,
						Long.parseLong(kodStonu));// podle kl��e hled� v DB a vr�t� to objekt t��dy
							// TimeStone
//				JSONObject obj = new JSONObject(nalezenyTimeStone);// Objekt
//																	// vhodn�
//																	// pro
//																	// p�enos ze
//																	// serveru,
//																	// jestli
//																	// tomu
//																	// dob�e
//																	// rozumim
				// Na v�stup nap�e hl�ku - d� se tak i debugovat trochu - je to
				// pak vid�t v tom progr�mku .jar - tj nap�e to JSON objekt a
				// pak i MileStone toho nalezen�ho TimeStonu - vlastn� teda jen
				// jeho n�zev
				resp.getWriter().append(gson.toJson(nalezenyTimeStone));
				resp.getWriter().flush();
				resp.getWriter().close();
			}
		} finally {
			pm.close();
		}
	}

	// Pokud se jedna o POST
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// resp.setContentType("text/plain");
		PersistenceManager pm = null;
		try {
			pm = PMF.get().getPersistenceManager();// tohle mi dovoluje pracovat
													// s databazi a pouzivat
													// persistent - jedna se o
													// singleton v souvoru
													// PMF.java
			String nazevStonu = req.getParameter("name");// nactu za otaznikem
															// hodnotu name - tj
															// milelinecz.appspot.com/timeStone?name=Radek
															// vrati Radek
			String kodStonu = req.getParameter("kod");
			TimeStone timeStone = new TimeStone(nazevStonu, kodStonu,
					Typ.PREDMET); // konstruktor, lze zm�nit, lze jich ud�lat
									// vic dle typu

			// vytvo�en� zku�evn�ho data
			Date date = new Date();
			DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
			date = dfm.parse("2011-12-24 20:15:00 +0200");

			// Vytvo�en� zku�ebn�ho Milestonu hned p�i tvorb� TimeStonu - jen
			// uk�zka, pak to tady nem� co d�lat..
			timeStone.addMileStone(new MileStone(timeStone.getNazev(), "Nic nedelani", date,
					"pohoda"));

			// Pokud jsou p�ed�ny i kredity, tak je to settne - nejsou defaultn�
			// hodnota, tak bych to �e�il takhle - nejsou v konstruktoru
			// p�ed�van�
			if (req.getParameter("kredity") != null)
				timeStone.setPocetKreditu((Integer.parseInt(req
						.getParameter("kredity"))));

			// to ulo�� timeStone - jeho hodnoty - tj i ArrayList s MileStonama,
			// tak�e to ulo�� i ty
			pm.makePersistent(timeStone);

			// Na v�stup nap�e hl�ku - d� se tak i debugovat trochu - je to pak
			// vid�t v tom progr�mku .jar
			resp.getWriter().println("Post proceed");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pm.close();
		}

	}
}
