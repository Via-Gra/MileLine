package jdopack;


import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.repackaged.org.json.JSONObject;

@SuppressWarnings("serial")
public class TimeStoneServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//resp.setContentType("text/plain");
		//resp.getWriter().println("GET");
		
		PersistenceManager pm = null;
		try {
			 pm = PMF.get().getPersistenceManager();
			 String kodStonu = req.getParameter("kod");
			 Key k = KeyFactory.createKey(TimeStone.class.getSimpleName(), kodStonu);
			 TimeStone nalezenyTimeStone = pm.getObjectById(TimeStone.class,k);
			 JSONObject obj = new JSONObject(nalezenyTimeStone);
			 resp.getWriter().println(obj.toString());
		}finally {
			pm.close();
		}
	}
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			//resp.setContentType("text/plain");
		PersistenceManager pm = null;
		 try {
			 pm = PMF.get().getPersistenceManager();
			 String nazevStonu = req.getParameter("name");
			 String kodStonu = req.getParameter("kod");
			 Key k = KeyFactory.createKey(TimeStone.class.getSimpleName(), kodStonu);
			 TimeStone timeStone = new TimeStone(nazevStonu,kodStonu,k);
			 if (req.getParameter("kredity") != null) timeStone.setPocetKreditu(( Integer.parseInt(req.getParameter("kredity"))));	
			 pm.makePersistent(timeStone);
			 resp.getWriter().println("Post proceed");
		    } finally {
		     pm.close();
		    }

	}
}
