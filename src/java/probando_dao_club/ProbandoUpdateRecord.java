/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package probando_dao_club;

import daogenerico.ErroresGenerales;
import entidades.club.Club;
import entidades.club.ClubDAO;

/**
 *
 * @author ANDRES
 */
public class ProbandoUpdateRecord {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        Club ClubModificar = new Club(Long.valueOf(3),"Independiente","Argentina","Primera Division","Futbol Profesional Masculino");      
        
        ClubDAO MyDAO = new ClubDAO();
        
        try 
        {
            if (MyDAO.UpdateRecord(ClubModificar))
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
