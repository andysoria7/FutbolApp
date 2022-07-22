/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package probando_dao_jugador;

import com.google.gson.Gson;
import entidades.jugador.Jugador;

/**
 *
 * @author ANDRES
 */
public class ProbandoCrearJugadorAJson {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here
        
         Gson Conversor = new Gson();
        
        /*(01) de OBJETO JAVA (Java Object) a OBJETO JSON (Json Object)*/
        
        
            Jugador P1 = new Jugador(Long.valueOf(0),"Ramon","Abila","30 Años","Centro Delantero","Masculino","Argentino",Long.valueOf(3));           
        
            String SalidaDeObjetoEnFormatoJson = Conversor.toJson(P1);
        
            System.out.println(SalidaDeObjetoEnFormatoJson);
        
        
    }
    
}
