/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarjetas;

import FrontendBancopoly.SolicitudDatos;
import FrontendBancopoly.Tablero;
import Jugadores.Jugador;
import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 *
 * @author phily
 */
public class TarjetaPagoTodos extends TarjetaPago implements Serializable{
    String nombre="PagoATodos";
    private String definicion;//creo que para sobreescribirlas debes ser publicas o default, creo 
    //int cantidad;    
     //int cantidadPagos;
    //int total;
    private boolean unica;
    private Jugador jugadores[];//este si será un arreglo, porque no debo eliminar a los jugadores,pues deben aparecer en los diferentes listados
    //clasificados por categorías, debido a que en el momento en que se definen todas las cosas generales, aún no se han creado a los jugadores
    //como tal entonces, no deberá tenerse en el ctctor a los jugadores, sino en otro método, de forma similar a lo que sucede con la cárcel solo
    //Que esta vez, un poco más alejado al proceso de cosumización
           
    public TarjetaPagoTodos(int pago, int numeroJugadoresPagar, boolean esLaUnica){//puede ser true o flase, pues existe una casilla de esta [que no necestia de definiicón por el hecho de que yo soy quien se la define xD] y además la de personaliada, donde NUNCA se necesitará una definición, puesto que debe ser una descripción unificada, y no individual
        super(pago, ((numeroJugadoresPagar-1)*pago));
        //cantidadPagos=numeroJugadoresPagar-1;//pues 1 le deberá pagar a los demás
        //total=cantidadPagos*pago;
        unica=esLaUnica;
    }
    
     public TarjetaPagoTodos(int pago, int numeroJugadoresPagar, String definicionTarjeta){//el número de jugadores se va a traer desde registro, por el hecho de que en ese pto ya ha recogido ese y los demás datos principales
        super(pago, ((numeroJugadoresPagar-1)*pago));
//        cantidadPagos=numeroJugadoresPagar-1;//pues 1 le deberá pagar a los demás
//        total=cantidadPagos*pago;
        definicion=definicionTarjeta;
        unica=true;
    }//Este es el cnstrc para casilla
    
    public void establecerJugadores(Jugador[] todosLosJugadores){//esti ya no es necesario por el hecho de que ahora es un arreglo estático
        jugadores=todosLosJugadores;
    }
    
      @Override
    public void accion(Jugador solicitante){
        if(definicion==null && unica){
            JOptionPane.showMessageDialog(null,solicitante.obtnerNombre()+ ", a pagarle a todos $ " + cantidad +" se ha dicho", "Pago a todos", JOptionPane.WARNING_MESSAGE);
        }else if(definicion!=null && unica){//cada vez que se emplee el cntstrc que reciba una definición es porque la tjt es única, es decir no hay fusión, sin importar que exista más de otro o del mismo tipo en la casilla, solo importa que no está fusionada con otra funcionalidad como en personalizada
             JOptionPane.showMessageDialog(null, definicion,  "Pago a todos", JOptionPane.WARNING_MESSAGE);
        }
        
        definirAccionJugador(solicitante);
        Tablero.actualizarDatosTablaTurno();
    }
    
        @Override
        protected void definirAccionJugador(Jugador jugadorAccionista){//recuerda que depende de la situación el mandamiento         
            super.definirAccionJugador(jugadorAccionista);
            if(jugadores==null){                
                jugadores= SolicitudDatos.registroDatos.darJugadoresEnPartida();
            }
            
        
            for (int jugador=0; jugador<SolicitudDatos.registroDatos.darJugadoresEnPartida().length; jugador++) {                
                
                if(jugadores[jugador] != jugadorAccionista){
                    jugadores[jugador].recibirDInero(cantidad);
                    SolicitudDatos.registroDatos.darJugadoresEnPartida()[jugador].incrementarRiquezas(cantidad);
                }
            }     
            
            
            
        }//fin del método que define la acción
    
    
     
    
    //Aquí no se velará el hecho de que quede una cantidad negativa en la var de dinero del jugador, puesto que lo que se hará será que no se le permitirá ceder el turno
    //hasta que pague su deuda, si no puede porque ya no tiene nada entonces se le obliga práticamente a declararse en bancarrota, y hasta que haga algo para saldar
    //su deuda, se continuará con el flujo de juego xD... entonces aquí solo resta, lo demás se debe controlar en un método diferente... ad diferencia de las tjt's propiedades
    //Donde se tendrá que evaluar antes de exe la acción del jugador, aunque ... esto podría resolverse viendo antes de mostrar el diálogo, si el jugador es capaz de pagar
    //y si no lo es, entonces solo tener habilitado el botón de dejar ir xD
    
}
