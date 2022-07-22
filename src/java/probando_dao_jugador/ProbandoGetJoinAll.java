/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package probando_dao_jugador;

import daogenerico.ErroresGenerales;
import entidades.jugador.JugadorDAO;
import entidades.jugador.JugadorExtendido;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ANDRES
 */
public class ProbandoGetJoinAll {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
                List<JugadorExtendido> ListaDevuelta = new ArrayList<JugadorExtendido>();        
        
                JugadorDAO MyDAO = new JugadorDAO();
        
        try 
        {
            ListaDevuelta = MyDAO.getJoinAll("select jugador.*,club.clubnombre,clubpais,clubcategoria,clubdeporte from jugador inner join club on jugador.clubpk = club.clubpk");
            
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
