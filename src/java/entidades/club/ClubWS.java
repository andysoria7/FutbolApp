/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.club;

import com.google.gson.Gson;
import daogenerico.ErroresGenerales;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ANDRES
 */
@WebServlet(name = "ClubWS", urlPatterns = {"/ClubWS"})
public class ClubWS extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
         String MensajeADevolverEnFormatoJSON = "";
        
         
         
         int TipoProceso = 0;
         
         TipoProceso = Integer.valueOf(request.getParameter("TipoProceso"));
         
         if (TipoProceso == 4) // Es una Consulta
        {
            String NombreClub = "";    
            String ComandoSQL = ""; 
            NombreClub = request.getParameter("ClubNombreABuscar");
            
            ClubDAO MyClubDao = new ClubDAO();
                                
            if (NombreClub.isEmpty())
            {
                ComandoSQL = "Select * from club";   
            }
            else
            {
                ComandoSQL = "select * from club where club.clubnombre like '%" + NombreClub.trim() + "%'";
            }  
            
            try 
            {
                MensajeADevolverEnFormatoJSON = MyClubDao.getAllToJSON(ComandoSQL);
            } catch (ErroresGenerales ex) 
            {
                MensajeADevolverEnFormatoJSON = ex.getMensajeDeErrorGeneral();
            }
        }
        else
        {            
              // Es 1 = Alta, 2 = Modificacion, 3 = Eliminacion
            if ((TipoProceso == 1) || (TipoProceso == 2) || (TipoProceso==3)) 
            {
                String ParametroEntrada = "";
                ParametroEntrada = request.getParameter("ParametroJSON");
                
                MensajeADevolverEnFormatoJSON = ProcesarAltaBajaModificacion(TipoProceso,ParametroEntrada);                
            } 
            
        }
         
        
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) 
        {
            /* TODO output your page here. You may use following sample code. */
            out.println(MensajeADevolverEnFormatoJSON);
          
        }
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
    
     private String ProcesarAltaBajaModificacion(int TipoProceso,String ParametroEntrada)
    {
        String SalidaJSON = "";
        if (!ParametroEntrada.isEmpty())
        {           
            Club C = new Club();  
            Gson Conversor = new Gson();                    
            C = Conversor.fromJson(ParametroEntrada,Club.class);   
            
            ClubDAO DAO = new ClubDAO();
            
            if (TipoProceso == 1) // es ALTA
            {     
                try 
                {
                    Club ClubAgregado = DAO.AddRecord(C);
                    SalidaJSON = "Club Agregado " + ClubAgregado.toString();
                } 
                catch (ErroresGenerales ex)
                {
                    SalidaJSON = ex.getMensajeDeErrorGeneral();
                }
            }
            
            
            // MensajeADevolverEnFormatoJSON = ProcesarAltaBajaModificacion(TipoProceso,ParametroEntrada);
            if (TipoProceso == 2) // es MODIFICACION
            {   
                try 
                {
                    boolean PudimosActualizar = DAO.UpdateRecord(C);
                    if (PudimosActualizar)
                    {
                        SalidaJSON = "Club Actualizado";
                    }   
                    else
                    {
                        SalidaJSON = "Club No Actualizado";
                    }
                } 
                catch (ErroresGenerales ex)
                {
                    SalidaJSON = ex.getMensajeDeErrorGeneral();
                }
            }        
            if (TipoProceso == 3) // es BAJA
            {      
               try 
                {
                    boolean PudimosEliminar = DAO.DeleteRecord(C);
                    if (PudimosEliminar)
                    {
                        SalidaJSON = "Club Eliminado";
                    }      
                    else
                    {
                        SalidaJSON = "El Club No pudo Ser Eliminado";
                    }
                } 
                catch (ErroresGenerales ex)
                {
                    SalidaJSON = ex.getMensajeDeErrorGeneral();
                }
            }
        }
      
        return SalidaJSON;        
    }

}
