/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Casillas;
import Jugadores.Jugador;
import Tarjetas.Tarjeta;
import java.io.Serializable;
import javax.swing.*;

/**
 *
 * @author phily
 */
public class Propiedad extends Casilla implements Serializable{
    JPanel casillaFisica;       
    boolean grupoCompleto;//se cb al poseer todos, tampoco debería estar aquí puesto que solo le interesa a lugar
    String nombreCasilla;
    int cantidad;//pago por estancia
    int precioPropiedad;//útil para comprar y vender
    int precioHipoteca;
    private boolean estadoPropiedad;//comprada o no
    private boolean hipotecada;
    int interesPorHipoteca;    
    Tarjeta tarjeta; 
    Jugador dueno;     
    
    public Propiedad(int interes, JPanel casillaFisica, String nombre, int pagoEstancia, int precioDePropiedad){
        super(casillaFisica, nombre);
        nombreCasilla=nombre;        
        interesPorHipoteca=interes;//la única solución para agarrar el valor del interés, sería el tomar el dato del registro temporal, esto al momento de estar creando la casilla, lo cual te evitará problemas, no solo con estas casillas
        cantidad=pagoEstancia;
        precioPropiedad=precioDePropiedad;
        //Sino con las demás
    }
    
    @Override
    public void crearTarjeta(){//completamente sobreescrito por sus hijas, por el hecho de que todas las cdades a mostrar varían, a excepción del interés de hipoteca y el estado: comprada, hipotecada
        //recuerda la tarjeta luego de haber adquirido la propiedad, será la que muestre solo el monto total y al jugador que debe pagar
        //esto se decidirá en la tarjeta lógica por medio del valor de "comprada", es decir que un método para esto el cual será invocado 
        //al comprarla y hará que cambie el diálogo a mostrar de ahí en adelante
    }
    
    @Override
    public void accion(Jugador jugadorLlegado){
        super.accion(jugadorLlegado);
    }
    
    private void actualizarFormaFisica(){

    }  
    
    /**
     *Empleado por jugador luego de presionar hipotecar en el dialogo Mis Posesiones, será útil para saltarse los
     * comportamientos que esta casilla exe por medio de su tjt lógica...Para hipotecar solo deberá cb el valor de 
     * esta vari para saber como rxn aquí con respecto a la forma física
     */
    public void cambiarEstadoHIpoteca(){//llamado al hipotecar la propiedad, donde depende totalmente del jugador, de igual forma no recibe argus puesto que solo para esa ocasión s empleará
        if(hipotecada){
            hipotecada=false;
        }else{
            hipotecada=true;
        }        
    }  //esto para que se haga el cab respectivo,pues que si está solicitando la hipoteca y está en treu es porque querra falsearl, verdad? xD
    
    public void establecerGrupoCompleto(){
        grupoCompleto=true;
    }
    
    public boolean estaCompletoELGrupo(){
     return grupoCompleto;   
    }
    
    public boolean estaHIpotecada(){
        return hipotecada;
    }
    
    public int obtenerPagoPorEstancia(){
        return cantidad;
    }    
    
    public int obtenerPrecioPropiedad(){
        return precioPropiedad;
    }
    
  
    
  
    
    
}
