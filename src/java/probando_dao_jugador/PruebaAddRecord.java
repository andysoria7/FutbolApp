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
public class PruebaAddRecord {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // TODO code application logic here
        
        Jugador JugadorEntrada = new Jugador(Long.valueOf(0),"Daniele", "De Rossi","36 Años","Mediocampista","Masculino","Italiano",Long.valueOf(3));
        
        Jugador JugadorSalida = null;
        
        JugadorDAO MyDAO = new JugadorDAO();
        
        try 
        {
            JugadorSalida = MyDAO.AddRecord(JugadorEntrada);
            
            System.out.println("JUGADOR:= " + JugadorSalida.toString());
            
        } catch (ErroresGenerales ex) 
        {
            System.out.println(ex.getMensajeDeErrorSalida());
            System.out.println("HUBO UN ERROR !!! " + ex.getMensajeDeErrorGeneral());
        } 
      
    }
    
}
