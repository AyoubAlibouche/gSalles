package controller;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Console;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.google.gson.Gson;

import beans.Creneau;
import service.CreneauService;

/**
 * Servlet implementation class CreneauController
 */
@WebServlet("/CreneauController")
public class CreneauController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CreneauService cs=new CreneauService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreneauController() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("op") != null) {
            if (request.getParameter("op").equals("load")) {
                response.setContentType("application/json");
                List<Creneau> creneaus = cs.findAll();
                Gson json = new Gson();
                response.getWriter().write(json.toJson(creneaus));
            }else if(request.getParameter("op").equals("delete")){
            	System.out.println("d5uul l delete");
                int id = Integer.parseInt(request.getParameter("id"));
                cs.delete(cs.findById(id));
                response.setContentType("application/json");
                List<Creneau> creneaus = cs.findAll();
                Gson json = new Gson();
                response.getWriter().write(json.toJson(creneaus));
                
            }else if(request.getParameter("op").equals("update")){
            	System.out.println("d5uul l update");
            	System.out.println(request.getParameter("heureDebut"));
            	System.out.println(request.getParameter("heureFin"));
            	 String heureDebut =request.getParameter("heureDebut");
                 String heureFin = request.getParameter("heureFin");
                 
                 
                 
                 
                 Time heureDeb = Time.valueOf(request.getParameter("heureDebut") + ":00");
                 Time heureFi = Time.valueOf(request.getParameter("heureFin") + ":00");
            	 DateFormat formatter = new SimpleDateFormat("hh:mm:ss");
            	 Time heuredeb=null;
            	 Time heurefi = null;
				try {
					heuredeb = (Time) formatter.parse(heureDebut);
					 heurefi = (Time) formatter.parse(heureFin);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	 
                int id = Integer.parseInt(request.getParameter("id"));
               
              
                System.out.println(id);
                
                cs.update(new Creneau(id,heureDeb, heureFi));
              //  Salle salle=ss.findById(id);
               // ss.update(salle);
                response.setContentType("application/json");
                List<Creneau> creneaus = cs.findAll();
                Gson json = new Gson();
                response.getWriter().write(json.toJson(creneaus));
                
            }
        } else {
        	System.out.println("d5ul l create");
            String heureDebut = request.getParameter("heureDebut");
            System.out.println(heureDebut);
            String heureFin=request.getParameter("heureFin");
            System.out.println(heureFin);
           // Time heureDeb = Time.valueOf(request.getParameter("heureDebut")+ ":00");
           // Time heureFi = Time.valueOf(request.getParameter("heureFin")+ ":00");
            Time heureDeb = Time.valueOf(request.getParameter("heureDebut") + ":00");
            Time heureFi = Time.valueOf(request.getParameter("heureFin") + ":00");
            //  Date dateAchat = new Date(request.getParameter("dateAchat").replace("-", "/"));
           // DateFormat formatter = new SimpleDateFormat("hh:mm:ss");
       //	 Time heureDeb=null;
      // 	Time heurefi=null;
	//	try {
	//		heureDeb = (Time) formatter.parse(heureDebut);
	//		heurefi = (Time) formatter.parse(heureFin);
	//	} catch (ParseException e) {
		//	// TODO Auto-generated catch block
	//		e.printStackTrace();
	//	}
       	  
            System.out.println("avant creat");
            cs.create(new Creneau(heureDeb, heureFi));
            System.out.println("apres json");
            System.out.println("avant json");
            response.setContentType("application/json");
            System.out.println("apres json");
            List<Creneau> creneaus = cs.findAll();
            System.out.println("avant Gson");
            Gson json = new Gson();
            response.getWriter().write(json.toJson(creneaus));
            System.out.println("apres Gson");
            System.out.println("5ruuuuj");
        }

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}