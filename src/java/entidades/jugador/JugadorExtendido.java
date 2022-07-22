/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.jugador;

/**
 *
 * @author ANDRES
 */
public class JugadorExtendido 
{
    Long jugadorpk;
    String jugadornombre;
    String jugadorapellido;
    String jugadoredad;
    String jugadorposicion;
    String jugadorsexo;
    String jugadornacionalidad;
    Long clubpk;
    String clubnombre;
    String clubpais;
    String clubcategoria;
    String clubdeporte;

    public JugadorExtendido(Long jugadorpk, String jugadornombre, String jugadorapellido, String jugadoredad, String jugadorposicion, String jugadorsexo,String jugadornacionalidad, Long clubpk, String clubnombre, String clubpais, String clubcategoria, String clubdeporte) 
    {
        this.jugadorpk = jugadorpk;
        this.jugadornombre = jugadornombre;
        this.jugadorapellido = jugadorapellido;
        this.jugadoredad = jugadoredad;
        this.jugadorposicion = jugadorposicion;
        this.jugadorsexo = jugadorsexo;
        this.jugadornacionalidad = jugadornacionalidad;
        this.clubpk = clubpk;
        this.clubnombre = clubnombre;
        this.clubpais = clubpais;
        this.clubcategoria = clubcategoria;
        this.clubdeporte = clubdeporte;
    }

    public JugadorExtendido() 
    {
        this.jugadorpk = Long.valueOf(0);
        this.jugadornombre = "";
        this.jugadorapellido= "";
        this.jugadoredad = "";
        this.jugadorposicion = "";
        this.jugadorsexo= "";
        this.jugadornacionalidad = "";
        this.clubpk = Long.valueOf(0);
        this.clubnombre = "";
        this.clubpais = "";
        this.clubcategoria = "";
        this.clubdeporte = "";
        
    }

    public Long getJugadorpk() 
    {
        return jugadorpk;
    }

    public void setJugadorpk(Long jugadorpk) 
    {
        this.jugadorpk = jugadorpk;
    }

    public String getJugadornombre() 
    {
        return jugadornombre;
    }

    public void setJugadornombre(String jugadornombre) 
    {
        this.jugadornombre = jugadornombre;
    }

    public String getJugadorapellido() 
    {
        return jugadorapellido;
    }

    public void setJugadorapellido(String jugadorapellido) 
    {
        this.jugadorapellido = jugadorapellido;
    }

    public String getJugadoredad() 
    {
        return jugadoredad;
    }

    public void setJugadoredad(String jugadoredad) 
    {
        this.jugadoredad = jugadoredad;
    }

    public String getJugadorposicion() 
    {
        return jugadorposicion;
    }

    public void setJugadorposicion(String jugadorposicion) 
    {
        this.jugadorposicion = jugadorposicion;
    }

    public String getJugadorsexo() 
    {
        return jugadorsexo;
    }

    public void setJugadorsexo(String jugadorsexo) 
    {
        this.jugadorsexo = jugadorsexo;
    }

    public Long getClubpk() 
    {
        return clubpk;
    }

    public void setClubpk(Long clubpk) 
    {
        this.clubpk = clubpk;
    }
    
     public String getJugadornacionalidad() 
    {
        return jugadornacionalidad;
    }

    public void setJugadornacionalidad(String jugadornacionalidad) 
    {
        this.jugadornacionalidad = jugadornacionalidad;
    }

    public String getClubnombre() 
    {
        return clubnombre;
    }

    public void setClubnombre(String clubnombre) 
    {
        this.clubnombre = clubnombre;
    }

    public String getClubpais() 
    {
        return clubpais;
    }

    public void setClubpais(String clubpais) 
    {
        this.clubpais = clubpais;
    }

    public String getClubcategoria() 
    {
        return clubcategoria;
    }

    public void setClubcategoria(String clubcategoria) 
    {
        this.clubcategoria = clubcategoria;
    }

    public String getClubdeporte() 
    {
        return clubdeporte;
    }

    public void setClubdeporte(String clubdeporte) 
    {
        this.clubdeporte = clubdeporte;
    }

    @Override
    public String toString() 
    {
        return "JugadorExtendido{" + "jugadorpk=" + jugadorpk + ", jugadornombre=" + jugadornombre + ", jugadorapellido=" + jugadorapellido + ", jugadoredad=" + jugadoredad + ", jugadorposicion=" + jugadorposicion + ", jugadorsexo=" + jugadorsexo + ", jugadornacionalidad=" + jugadornacionalidad + ", clubpk=" + clubpk + ", clubnombre=" + clubnombre + ", clubpais=" + clubpais + ", clubcategoria=" + clubcategoria + ", clubdeporte=" + clubdeporte + '}';
    }
    
    

   
    
    
    
    

  
    
    
    
    
    
    
    
    
    
    
    
}
