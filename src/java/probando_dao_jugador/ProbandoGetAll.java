/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package probando_dao_jugador;

import daogenerico.ErroresGenerales;
import entidades.jugador.Jugador;
import entidades.jugador.JugadorDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ANDRES
 */
public class ProbandoGetAll {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
         List<Jugador> ListaDevuelta = new ArrayList<Jugador>();        
        
        JugadorDAO MyDAO = new JugadorDAO();
        
        try 
        {
            ListaDevuelta = MyDAO.getAll("select * from jugador");
            
            if (ListaDevuelta != null)
            {
                for (int i = 0; i < ListaDevuelta.size();i++)
                {
                    System.out.println(ListaDevuelta.get(i).toString());
                }
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
