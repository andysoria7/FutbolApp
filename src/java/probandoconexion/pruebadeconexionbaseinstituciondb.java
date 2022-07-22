/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package probandoconexion;

import conectividad.Conexion;

/**
 *
 * @author ANDRES
 */
public class pruebadeconexionbaseinstituciondb {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Conexion Con = Conexion.OpenConnection();
    }
    
}
