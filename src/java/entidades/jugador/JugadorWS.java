/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.jugador;

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
@WebServlet(name = "JugadorWS", urlPatterns = {"/JugadorWS"})
public class JugadorWS extends HttpServlet {

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
            String NombreJugador = "";    
            String ComandoSQL = ""; 
            NombreJugador = request.getParameter("JugadorNombreABuscar");
            
            JugadorDAO MyJugadorDAO = new JugadorDAO();
                                
            if (NombreJugador.isEmpty())
            {
                ComandoSQL = "select * from jugador inner join club on jugador.clubpk = club.clubpk";   
            }
            else
            {
                ComandoSQL = "select jugador.*,club.clubnombre,clubpais,clubcategoria,clubdeporte from jugador inner join club on jugador.clubpk = club.clubpk where jugador.jugadornombre like '%" + NombreJugador.trim() + "%'";
            }  
            
            try 
            {
                MensajeADevolverEnFormatoJSON = MyJugadorDAO.getJoinAllToJSON(ComandoSQL);
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
            Jugador J = new Jugador();  
            Gson Conversor = new Gson();                    
            J = Conversor.fromJson(ParametroEntrada,Jugador.class);   
            
            JugadorDAO DAO = new JugadorDAO();
            
            if (TipoProceso == 1) // es ALTA
            {     
                try 
                {
                    Jugador JugadorAgregado = DAO.AddRecord(J);
                    SalidaJSON = "Jugador Agregado " + JugadorAgregado.toString();
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
                    boolean PudimosActualizar = DAO.UpdateRecord(J);
                    if (PudimosActualizar)
                    {
                        SalidaJSON = "Jugador Actualizado !!!";
                    }   
                    else
                    {
                        SalidaJSON = "El Jugador no pudo ser Actualizado !!!";
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
                    boolean PudimosEliminar = DAO.DeleteRecord(J);
                    if (PudimosEliminar)
                    {
                        SalidaJSON = "Jugador Eliminado !!!";
                    }      
                    else
                    {
                        SalidaJSON = "El Jugador no pudo ser Eliminado";
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
