/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package probando_dao_club;

import daogenerico.ErroresGenerales;
import entidades.club.Club;
import entidades.club.ClubDAO;
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
    public static void main(String[] args) 
    {
         List<Club> ListaDevuelta = new ArrayList<Club>();        
        
        ClubDAO MyDAO = new ClubDAO();
        
        try 
        {
            ListaDevuelta = MyDAO.getAll("select * from club");
            
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
