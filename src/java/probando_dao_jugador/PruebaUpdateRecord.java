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
public class PruebaUpdateRecord {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        Jugador JugadorModificar = new Jugador(Long.valueOf(2),"El Oscar","Romero","27 Años","Volante Ofensivo","Masculino","Paraguayo",Long.valueOf(1));      
        
        JugadorDAO MyDAO = new JugadorDAO();
        
        try 
        {
            if (MyDAO.UpdateRecord(JugadorModificar))
            {
                System.out.println("MODIFICADO !!!");
            }
            else
            {
                System.out.println("NO MODIFICADO");
            }
            
        } catch (ErroresGenerales ex) 
        {
            System.out.println(ex.getMensajeDeErrorSalida());
            System.out.println("HUBO UN ERROR !!! " + ex.getMensajeDeErrorGeneral());
        }
    }
    
}
