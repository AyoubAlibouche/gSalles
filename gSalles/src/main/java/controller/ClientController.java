package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.Client;
import service.ClientService;


/**
 * Servlet implementation class ClientController
 */
@WebServlet("/ClientController")
public class ClientController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClientService cm=new ClientService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientController() {
        super();
        // TODO Auto-generated constructor stub
    }
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("op") != null) {
            if (request.getParameter("op").equals("load")) {
                response.setContentType("application/json");
                List<Client> clients = cm.findAll();
                Gson json = new Gson();
                response.getWriter().write(json.toJson(clients));
            }else if(request.getParameter("op").equals("delete")){
            	System.out.println("d5uul l delete");
                int id = Integer.parseInt(request.getParameter("id"));
                cm.delete(cm.findById(id));
                response.setContentType("application/json");
                List<Client> clients = cm.findAll();
                Gson json = new Gson();
                response.getWriter().write(json.toJson(clients));
                
            }else if(request.getParameter("op").equals("update")){
            	System.out.println("d5uul l update");
            	System.out.println(request.getParameter("id"));
            	System.out.println(request.getParameter("nom"));
            	System.out.println(request.getParameter("prenom"));
            	System.out.println(request.getParameter("login"));
            	System.out.println(request.getParameter("mdp"));
                int id = Integer.parseInt(request.getParameter("id"));
                String nom =request.getParameter("nom");
                String prenom = request.getParameter("prenom");
                String login =request.getParameter("login");
                String mdp =request.getParameter("mdp");
                System.out.println(id);
                System.out.println(nom);
                System.out.println(prenom);
                System.out.println(login);
                System.out.println(mdp);
                cm.update(new Client(id,nom, prenom, login,mdp));
              //  Salle salle=ss.findById(id);
               // ss.update(salle);
                response.setContentType("application/json");
                List<Client> clients = cm.findAll();
                Gson json = new Gson();
                response.getWriter().write(json.toJson(clients));
                
            }
        } else {
        	System.out.println("d5ul l create");
            String nom = request.getParameter("nom");
            System.out.println(nom);
            String prenom = request.getParameter("prenom");
            String login= request.getParameter("login");
            String mdp= request.getParameter("mdp");
          //  Date dateAchat = new Date(request.getParameter("dateAchat").replace("-", "/"));
            cm.create(new Client(nom, prenom, login,mdp));
            System.out.println("avant json");
            response.setContentType("application/json");
            System.out.println("apres json");
            List<Client> clients = cm.findAll();
            System.out.println("avant Gson");
            Gson json = new Gson();
            response.getWriter().write(json.toJson(clients));
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
