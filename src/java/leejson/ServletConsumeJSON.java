package leejson;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import conexion.ConexionURL;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 *
 * @author luis
 */
@WebServlet(urlPatterns = {"/ServletConsumeJSON"})
public class ServletConsumeJSON extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url="http://10.2.130.28:8080/AgendaJSON/PideJSON";
        String json=ConexionURL.peticionWeb(url);
        ArrayList<Contacto> lista_contactos=tratarJSON(json);
        request.setAttribute("lista", lista_contactos);
        request.getRequestDispatcher("vercontactos.jsp").forward(request, response);
        }

    private ArrayList<Contacto> tratarJSON(String json) {
        ArrayList<Contacto> lista=new ArrayList<>();
        try {
            JSONParser j=new JSONParser();
            JSONObject objeto_json=(JSONObject)j.parse(json);
            /*{"agenda":[{"nombre":"Nerea","telefono":"655043843"},{"nombre":"Silvia","telefono":"678455323"},
            {"nombre":"Pedro","telefono":"655674553"},
            {"nombre":"Ana","telefono":"689734441"},
            {"nombre":"Jorge","telefono":"698788546"}]}*/
            JSONArray lista_contactos=(JSONArray)objeto_json.get("agenda");
            for (int i=0; i<lista_contactos.size(); i++)
            {
                JSONObject objeto_contacto=(JSONObject)lista_contactos.get(i);
                String nombre=(String)objeto_contacto.get("nombre");
                String telefono=(String)objeto_contacto.get("telefono");
                Contacto c=new Contacto(nombre, telefono);
                lista.add(c);
            }
            
        } catch (ParseException ex) {
            Logger.getLogger(ServletConsumeJSON.class.getName()).log(Level.SEVERE, null, ex);
        }
    return lista;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
