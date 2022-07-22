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
public class PruebaDeleteRecord {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Jugador JugadorEliminar = new Jugador(Long.valueOf(5),"El Oscar","Romero","27 Años","Volante Ofensivo","Masculino","Paraguayo",Long.valueOf(8));      
        
        JugadorDAO MyDAO = new JugadorDAO();
        
        try 
        {
            if (MyDAO.DeleteRecord(JugadorEliminar))
            {
                System.out.println("ELIMINADO !!!");
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
