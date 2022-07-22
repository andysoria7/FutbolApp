/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package probando_dao_jugador;

import daogenerico.ErroresGenerales;
import entidades.jugador.Jugador;
import entidades.jugador.JugadorDAO;

/**
 *
 * @author ANDRES
 */
public class PruebaLoadRecord {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Jugador J = null;
        
        JugadorDAO MyDAO = new JugadorDAO();
        
        try 
        {
            J = MyDAO.LoadRecord(Long.valueOf(1));
            
            if (J!= null)
            {
                System.out.println(J.toString());
            }
            else
            {
                System.out.println("NO LO ENCONTRAMOS");
            }
            
        } catch (ErroresGenerales ex) 
        {
            System.out.println(ex.getMensajeDeErrorSalida());
            System.out.println("HUBO UN ERROR !!! " + ex.getMensajeDeErrorGeneral());
        } 
    }
    
}
