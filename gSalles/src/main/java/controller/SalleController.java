package controller;


import java.io.Console;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.Salle;
import service.SalleService;

@WebServlet(urlPatterns = {"/SalleController"})
public class SalleController extends HttpServlet {
	private SalleService ss=new SalleService();
	
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("op") != null) {
            if (request.getParameter("op").equals("load")) {
                response.setContentType("application/json");
                List<Salle> salles = ss.findAll();
                Gson json = new Gson();
                response.getWriter().write(json.toJson(salles));
            }else if(request.getParameter("op").equals("delete")){
            	System.out.println("d5uul l delete");
                int id = Integer.parseInt(request.getParameter("id"));
                ss.delete(ss.findById(id));
                response.setContentType("application/json");
                List<Salle> salles = ss.findAll();
                Gson json = new Gson();
                response.getWriter().write(json.toJson(salles));
                
            }else if(request.getParameter("op").equals("update")){
            	System.out.println("d5uul l update");
            	System.out.println(request.getParameter("id"));
            	System.out.println(request.getParameter("code"));
            	System.out.println(request.getParameter("capacite"));
            	System.out.println(request.getParameter("type"));
                int id = Integer.parseInt(request.getParameter("id"));
                String code =request.getParameter("code");
                int capacite = Integer.parseInt(request.getParameter("capacite"));
                String type =request.getParameter("type");
                System.out.println(id);
                System.out.println(code);
                System.out.println(capacite);
                System.out.println(type);
                ss.update(new Salle(id,code, capacite, type));
              //  Salle salle=ss.findById(id);
               // ss.update(salle);
                response.setContentType("application/json");
                List<Salle> salles = ss.findAll();
                Gson json = new Gson();
                response.getWriter().write(json.toJson(salles));
                
            }
        } else {
        	System.out.println("d5ul l create");
            String code = request.getParameter("code");
            System.out.println(code);
            int capacite=Integer.parseInt(request.getParameter("capacite"));
            String type= request.getParameter("type");
          //  Date dateAchat = new Date(request.getParameter("dateAchat").replace("-", "/"));
            ss.create(new Salle(code, capacite, type));
            System.out.println("avant json");
            response.setContentType("application/json");
            System.out.println("apres json");
            List<Salle> salles = ss.findAll();
            System.out.println("avant Gson");
            Gson json = new Gson();
            response.getWriter().write(json.toJson(salles));
            System.out.println("apres Gson");
            System.out.println("5ruuuuj");
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
