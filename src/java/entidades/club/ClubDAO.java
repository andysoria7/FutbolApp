/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.club;

import com.google.gson.Gson;
import daogenerico.ErroresGenerales;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.postgresql.util.PSQLException;

/**
 *
 * @author ANDRES
 */
public class ClubDAO extends daogenerico.DAOGenerico implements daogenerico.DAOMetodos<Club,Club> 
{

    @Override
    public Club AddRecord(Club Objeto) throws ErroresGenerales 
    {
         Club Salida = null;
        Long PK = Long.valueOf(0);
        int RESULTADO = 0;
        
        try 
        {
            STATEMENT = CON.getCon().prepareStatement("INSERT INTO club(clubnombre, clubpais, clubcategoria, clubdeporte) values (?, ?, ?, ?)",PreparedStatement.RETURN_GENERATED_KEYS);
        
            STATEMENT.setString(1,Objeto.clubnombre);
            STATEMENT.setString(2,Objeto.clubpais);
            STATEMENT.setString(3,Objeto.clubcategoria); 
            STATEMENT.setString(4,Objeto.clubdeporte); 
            
            RESULTADO = STATEMENT.executeUpdate();
            
            if (RESULTADO > 0)
            {
                ResultSet rs = STATEMENT.getGeneratedKeys();             
                if (rs.next())
                { 
                    PK = rs.getLong(1);   
                    Salida = Objeto;
                    Salida.setClubpk(PK);
                }           
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
    public boolean UpdateRecord(Club Objeto) throws ErroresGenerales {
        
        int RESULTADO = 0;
        
        try 
        {
            STATEMENT = CON.getCon().prepareStatement("update club set clubnombre = ?,clubpais = ?, clubcategoria = ?, clubdeporte = ? where clubpk = ?");
        
            STATEMENT.setString(1,Objeto.clubnombre);
            STATEMENT.setString(2,Objeto.clubpais);
            STATEMENT.setString(3,Objeto.clubcategoria);
            STATEMENT.setString(4,Objeto.clubdeporte);
            STATEMENT.setLong(5,Objeto.getClubpk());
            
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
    public boolean DeleteRecord(Club Objeto) throws ErroresGenerales {
        
        int RESULTADO = 0;
        
        try 
        {
            STATEMENT = CON.getCon().prepareStatement("delete from club where clubpk = ?");
        
            STATEMENT.setLong(1,Objeto.getClubpk());
            
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
    public Club LoadRecord(Long PK) throws ErroresGenerales {
        
        Club Salida = null;
      
        int RESULTADO = 0;
        
        ResultSet RS;
        
        try 
        {
            STATEMENT = CON.getCon().prepareStatement("select * from club where clubpk = ?");        
                     
            STATEMENT.setLong(1,PK);
            
            RS = STATEMENT.executeQuery();           
                       
            while (RS.next())
            {
                Salida = new Club();
                
                
                Salida.setClubpk(RS.getLong("clubpk"));
                Salida.setClubnombre("clubnombre");
                Salida.setClubnombre("clubpais");
                Salida.setClubnombre("clubcategoria");
                Salida.setClubnombre("clubdeporte");
                
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
    public String LoadRecordToJSON(Long PK) throws ErroresGenerales {
        
        Club C = new Club();
        Gson Conversor = new Gson();
        
        C = this.LoadRecord(PK);
        
        String SalidaDeObjetoEnFormatoJson = Conversor.toJson(C);
        
        return (SalidaDeObjetoEnFormatoJson);
    }

    @Override
    public Club LoadRecord(String ComandoSQL) throws ErroresGenerales {
        
        Club Salida = null;
      
        int RESULTADO = 0;
        
        ResultSet RS;
        
        try 
        {
            STATEMENT = CON.getCon().prepareStatement(ComandoSQL);            
            
            RS = STATEMENT.executeQuery();
            
            while (RS.next())
            {
                Salida = new Club();
                
                Salida.setClubpk(RS.getLong("clubpk"));
                Salida.setClubnombre("clubnombre");
                Salida.setClubpais("clubpais");  
                Salida.setClubcategoria("clubcategoria");
                Salida.setClubdeporte("clubdeporte");
                
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
    public String LoadRecordToJSON(String ComandoSQL) throws ErroresGenerales 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Club> getAll(String ComandoSQL) throws ErroresGenerales {
       
         List<Club> Salida = new ArrayList<Club>();      
        
        int RESULTADO = 0;
        
        ResultSet RS;
        
        try 
        {
            STATEMENT = CON.getCon().prepareStatement(ComandoSQL);            
            
            RS = STATEMENT.executeQuery();
            
            while (RS.next())
            {
                Club C = new Club();
                
                
                C.setClubpk(RS.getLong("clubpk"));
                C.setClubnombre(RS.getString("clubnombre"));
                C.setClubpais(RS.getString("clubpais"));
                C.setClubcategoria(RS.getString("clubcategoria"));
                C.setClubdeporte(RS.getString("clubdeporte"));
                         
                Salida.add(C);
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
    public List<Club> getJoinAll(String ComandoSQL) throws ErroresGenerales {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAllToJSON(String ComandoSQL) throws ErroresGenerales {
       
        Gson Conversor = new Gson();
        
        List<Club> Salida = new ArrayList<Club>();
        
        Salida = this.getAll(ComandoSQL);
        
        return(Conversor.toJson(Salida));
    }

    @Override
    public String getJoinAllToJSON(String ComandoSQL) throws ErroresGenerales {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
