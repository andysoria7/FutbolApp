/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.jugador;

import com.google.gson.Gson;
import daogenerico.ErroresGenerales;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.postgresql.util.PSQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ANDRES
 */
public class JugadorDAO extends daogenerico.DAOGenerico implements daogenerico.DAOMetodos<Jugador,JugadorExtendido>
{

    @Override
    public Jugador AddRecord(Jugador Objeto) throws ErroresGenerales // Agrega un registro a la base en formate de objeto de la clase Jugador//
    {
       Jugador Salida = null;
        Long PK = Long.valueOf(0);
        int RESULTADO = 0;
        
        try 
        {
            STATEMENT = CON.getCon().prepareStatement("insert into jugador (jugadornombre,jugadorapellido,jugadoredad,jugadorposicion,jugadorsexo,jugadornacionalidad,clubpk) values (?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
        
            STATEMENT.setString(1,Objeto.jugadornombre);
            STATEMENT.setString(2,Objeto.jugadorapellido);
            STATEMENT.setString(3,Objeto.jugadoredad);
            STATEMENT.setString(4,Objeto.jugadorposicion);
            STATEMENT.setString(5,Objeto.jugadorsexo);
            STATEMENT.setString(6,Objeto.jugadornacionalidad);
            STATEMENT.setLong(7,Objeto.getClubpk());
            
            
             
            
            RESULTADO = STATEMENT.executeUpdate();
            
            if (RESULTADO > 0)
            {
                ResultSet rs = STATEMENT.getGeneratedKeys();             
                if (rs.next())
                { 
                    PK = rs.getLong(1);   
                    Salida = Objeto;
                    Salida.setJugadorpk(PK);
                }           
            }
            
        }
        catch (PSQLException e) 
        {  
            throw new ErroresGenerales(e.getMessage());                
        }
        catch(SQLException e) 
        {              
            throw new ErroresGenerales(e.getMessage());               
        }
        catch(Exception e)
        {
            throw new ErroresGenerales(e.getMessage()); 
        }
        
        return Salida;
        
    }

    @Override
    public boolean UpdateRecord(Jugador Objeto) throws ErroresGenerales // Actualiza un registro de la base que coincida con la clave primaria//
    {
        int RESULTADO = 0;
        
        try 
        {
            STATEMENT = CON.getCon().prepareStatement("update jugador set jugadornombre = ?, jugadorapellido = ?, jugadoredad = ?, jugadorposicion = ?, jugadorsexo = ?, jugadornacionalidad = ?,clubpk = ? where jugadorpk = ?");
        
            STATEMENT.setString(1,Objeto.jugadornombre);
            STATEMENT.setString(2,Objeto.jugadorapellido);
            STATEMENT.setString(3,Objeto.jugadoredad);
            STATEMENT.setString(4,Objeto.jugadorposicion);
            STATEMENT.setString(5,Objeto.jugadorsexo);
            STATEMENT.setString(6,Objeto.jugadornacionalidad);
            STATEMENT.setLong(7,Objeto.getClubpk());
            STATEMENT.setLong(8,Objeto.getJugadorpk());
            
           
            RESULTADO = STATEMENT.executeUpdate();
            
            if (RESULTADO > 0)
            {
                return true;
            }
            
        }catch (PSQLException e) 
        {  
            throw new ErroresGenerales(e.getMessage());                
        }
        catch(SQLException e) 
        {              
            throw new ErroresGenerales(e.getMessage());               
        }
        catch(Exception e)
        {
            throw new ErroresGenerales(e.getMessage()); 
        }     
        
        return false;
    }

    @Override
    public boolean DeleteRecord(Jugador Objeto) throws ErroresGenerales { // Borra un registro de la base de datos//
        int RESULTADO = 0;
        
        try 
        {
            STATEMENT = CON.getCon().prepareStatement("delete from jugador where jugadorpk = ?");
        
            STATEMENT.setLong(1,Objeto.getJugadorpk());
            
            RESULTADO = STATEMENT.executeUpdate();
            
            if (RESULTADO > 0)
            {
                return true;
            }
            
        }catch (PSQLException e) 
        {  
            throw new ErroresGenerales(e.getMessage());                
        }
        catch(SQLException e) 
        {              
            throw new ErroresGenerales(e.getMessage());               
        }
        catch(Exception e)
        {
            throw new ErroresGenerales(e.getMessage()); 
        }    
        
        return false;    
    }

    @Override
    public Jugador LoadRecord(Long PK) throws ErroresGenerales { // recupera un registro de la base mediante la clave primaria
        
        Jugador Salida = null;
      
        int RESULTADO = 0;
        
        ResultSet RS;
        
        try 
        {
            STATEMENT = CON.getCon().prepareStatement("select * from jugador where jugadorpk = ?");        
                     
            STATEMENT.setLong(1,PK);
            
            RS = STATEMENT.executeQuery();           
                       
            while (RS.next())
            {
                Salida = new Jugador();
                
                Salida.setJugadorpk(PK);
                Salida.setJugadornombre(RS.getString("jugadornombre"));
                Salida.setJugadorapellido(RS.getString("jugadorapellido"));
                Salida.setJugadoredad(RS.getString("jugadoredad"));
                Salida.setJugadorposicion(RS.getString("jugadorposicion"));
                Salida.setJugadorsexo(RS.getString("jugadorsexo"));
                Salida.setJugadornacionalidad(RS.getString("jugadornacionalidad"));
                Salida.setClubpk(RS.getLong("clubpk"));
                
                return Salida;            
            }
            
        }catch (PSQLException e) 
        {  
            throw new ErroresGenerales(e.getMessage());                
        }
        catch(SQLException e) 
        {              
            throw new ErroresGenerales(e.getMessage());               
        }
        catch(Exception e)
        {
            throw new ErroresGenerales(e.getMessage()); 
        }
        
        return Salida;  
    }

    @Override
    public String LoadRecordToJSON(Long PK) throws ErroresGenerales { // Hace lo mismo que el loadrecord solo que lo devuelve en formato json(string)//
        Jugador J = new Jugador();
        Gson Conversor = new Gson();
        
        J = this.LoadRecord(PK);
        
        String SalidaDeObjetoEnFormatoJson = Conversor.toJson(J);
        
        return (SalidaDeObjetoEnFormatoJson);
    }

    @Override
    public Jugador LoadRecord(String ComandoSQL) throws ErroresGenerales { //Recupera un registro atraves de un comando SQL
        Jugador Salida = null;
      
        int RESULTADO = 0;
        
        ResultSet RS;
        
        try 
        {
            STATEMENT = CON.getCon().prepareStatement(ComandoSQL);            
            
            RS = STATEMENT.executeQuery();
            
            while (RS.next())
            {
                Salida = new Jugador();
                
                Salida.setJugadorpk(RS.getLong("jugadorpk"));
                Salida.setJugadornombre(RS.getString("jugadornombre"));
                Salida.setJugadorapellido(RS.getString("jugadorapellido"));
                Salida.setJugadoredad(RS.getString("jugadoredad"));
                Salida.setJugadorposicion(RS.getString("jugadorposicion"));
                Salida.setJugadorsexo(RS.getString("jugadorsexo"));
                Salida.setJugadornacionalidad(RS.getString("jugadornacionalidad"));
                Salida.setClubpk(RS.getLong("clubpk"));
                
                return Salida;            
            }
            
          
            
        }catch (PSQLException e) 
        {  
            throw new ErroresGenerales(e.getMessage());                
        }
        catch(SQLException e) 
        {              
            throw new ErroresGenerales(e.getMessage());               
        }
        catch(Exception e)
        {
            throw new ErroresGenerales(e.getMessage()); 
        }
        
        return Salida; 
    }

   
    @Override
    public List<Jugador> getAll(String ComandoSQL) throws ErroresGenerales { // Devuelve el registro de una tabla
        
        List<Jugador> Salida = new ArrayList<Jugador>();      
        
        int RESULTADO = 0;
        
        ResultSet RS;
        
        try 
        {
            STATEMENT = CON.getCon().prepareStatement(ComandoSQL);            
            
            RS = STATEMENT.executeQuery();
            
            while (RS.next())
            {
                Jugador J = new Jugador();
                
                J.setJugadorpk(RS.getLong("jugadorpk"));
                J.setJugadornombre(RS.getString("jugadornombre"));
                J.setJugadorapellido(RS.getString("jugadorapellido"));
                J.setJugadoredad(RS.getString("jugadoredad"));
                J.setJugadorposicion(RS.getString("jugadorposicion"));
                J.setJugadorsexo(RS.getString("jugadorsexo"));
                J.setJugadornacionalidad(RS.getString("jugadornacionalidad"));
                J.setClubpk(RS.getLong("clubpk"));
                  
                
                Salida.add(J);
            }
            
        }catch (PSQLException e) 
        {  
            throw new ErroresGenerales(e.getMessage());                
        }
        catch(SQLException e) 
        {              
            throw new ErroresGenerales(e.getMessage());               
        }
        catch(Exception e)
        {
            throw new ErroresGenerales(e.getMessage()); 
        }
        
        return Salida;
       
    }

    @Override
    public List<JugadorExtendido> getJoinAll(String ComandoSQL) throws ErroresGenerales {
        
         List<JugadorExtendido> Salida = new ArrayList<JugadorExtendido>();      
        
        int RESULTADO = 0;
        
        ResultSet RS;
        
        try 
        {
            STATEMENT = CON.getCon().prepareStatement(ComandoSQL);            
            
            RS = STATEMENT.executeQuery();
            
            while (RS.next())
            {               
                
                JugadorExtendido J = new JugadorExtendido();
                
                J.setJugadorpk(RS.getLong("jugadorpk"));
                J.setJugadornombre(RS.getString("jugadornombre"));
                J.setJugadorapellido(RS.getString("jugadorapellido"));
                J.setJugadoredad(RS.getString("jugadoredad"));
                J.setJugadorposicion(RS.getString("jugadorposicion"));
                J.setJugadorsexo(RS.getString("jugadorsexo"));
                J.setJugadornacionalidad(RS.getString("jugadornacionalidad"));
                J.setClubpk(RS.getLong("clubpk"));
                J.setClubnombre(RS.getString("clubnombre"));
                J.setClubpais(RS.getString("clubpais"));
                J.setClubcategoria(RS.getString("clubcategoria"));
                J.setClubdeporte(RS.getString("clubdeporte"));
                                                  
             
                Salida.add(J);
            }
            
        }catch (PSQLException e) 
        {  
            throw new ErroresGenerales(e.getMessage());                
        }
        catch(SQLException e) 
        {              
            throw new ErroresGenerales(e.getMessage());               
        }
        catch(Exception e)
        {
            throw new ErroresGenerales(e.getMessage()); 
        }
        
        return Salida; 
    }

    @Override
    public String getAllToJSON(String ComandoSQL) throws ErroresGenerales {
        
        Gson Conversor = new Gson();
        
        List<Jugador> Salida = new ArrayList<Jugador>();
        
        Salida = this.getAll(ComandoSQL);
        
        return(Conversor.toJson(Salida));
    }

    @Override
    public String getJoinAllToJSON(String ComandoSQL) throws ErroresGenerales {
        
        Gson Conversor = new Gson();
        
        List<JugadorExtendido> Salida = new ArrayList<JugadorExtendido>();
        
        Salida = this.getJoinAll(ComandoSQL);
        
        return(Conversor.toJson(Salida));
    }
    
     @Override
    public String LoadRecordToJSON(String ComandoSQL) throws ErroresGenerales {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
