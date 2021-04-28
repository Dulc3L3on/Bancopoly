/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarjetas;

import Casillas.Casilla;
import FrontendBancopoly.tarjetaSalgaDeLaCarcel;
import Jugadores.Jugador;
import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 *
 * @author phily
 */
public class TarjetaVayaACarcel extends Tarjeta implements Serializable{    
    private String nombre="VayaACarcel";
    private Casilla carcelMasCercana;
    private Jugador encarcelado;    
    private int cantidad;
    private String definicion;
    private boolean unica;
   
    transient tarjetaSalgaDeLaCarcel dialogoSalgaCarcel= new tarjetaSalgaDeLaCarcel(new javax.swing.JFrame(), true);
    
     public TarjetaVayaACarcel(int turnosEncarcelado, boolean esLaUnica){//este será para toma una tarjeta puesto que hasta que reciba la cárcel pediré el número de turnos y mandaré la cárcel, esto lo hago así para no hacer doble trabajo        
        super("VayaACarcel");
        cantidad=turnosEncarcelado;
        unica=esLaUnica;
    }
    
    public TarjetaVayaACarcel(String descripcion, int turnosEncarcelado){//este será para toma una tarjeta puesto que hasta que reciba la cárcel pediré el número de turnos y mandaré la cárcel, esto lo hago así para no hacer doble trabajo
        super("VayaACarcel");
        definicion=descripcion;
        cantidad=turnosEncarcelado;
        
        unica= true;//puesto que debe recibir información
    }
    
    public TarjetaVayaACarcel(Casilla casillaEnCuestion, int sentencia){//hasta donde sé este ctrc no debería estar aquí porque la cárcel queda en espera y luego le es asignada por medio delmétodo correspondiente
        super("VayaACarcel");
        carcelMasCercana=casillaEnCuestion;
        cantidad=sentencia;     
        unica=true;
        //ESTE ES EL MÉTODO EMPLEADO PARA LA CASILLA VC!
        
    }//NO DEBERÍA EXISTIR, y si alguna clase que contiene a esta tjt, lo emplea, prococará problemas puesto que intenetrá trabajar con la nada
    
    /**
     * Encargado de establecer el número de turnos fuera del juego en la 
     * var de turnos restringidos del jugador ó de la cárcel, mira en cual de los 
     * dos, pero la cuestión es que media vez no sea 0, NO podrá lanzar los dados
     * antes de que llegue a 0 el contador, a menos que tenga tjt para salir
     */
    @Override
    public void accion(Jugador solicitante){     
        if(!solicitante.obtenerListadoTarjetasSC().estaVacia()){
            
            dialogoSalgaCarcel.setVisible(true);
                if(dialogoSalgaCarcel.iformarDecision()){
                    solicitante.obtenerListadoTarjetasSC().obtnerPrimerNodo().obtenerObjectcEnCasilla().sacarDeLaCarcel();
                    solicitante.reacomodarTarjetas();
                }           
        }else{
            int decision=JOptionPane.showConfirmDialog(null, "Dame $ 500 y te evito entrar a la cárcel\n Aceptas???", "Soborno", JOptionPane.YES_NO_OPTION);
            
            if(decision==0){
                solicitante.recibirLibertad();
                solicitante.pagar(500);
                solicitante.decrementarRIquezas(500);                
            }else{
                if(cantidad==1 && unica){//pue esa cantidad es determinada por el jugo, aunque podría surgir el hecho de que en el otro diálog ya             
                    JOptionPane.showMessageDialog(null, "Dirígase a la cárcel más cercana\n agradezce que solo fue por un turno", "encarcelado", JOptionPane.ERROR_MESSAGE);//recuerda que con ícono se vería mucho mejor xD
                }else if( cantidad!=1 && unica){//pues haré que en las tjts sea de 2 en ad, porque la casilla manda por un turno y nada más, entonces para que volvera repetir ese número
                    JOptionPane.showMessageDialog(null,definicion , "encarcelado", JOptionPane.ERROR_MESSAGE);//recuerda que con ícono se vería mucho mejor xD
                }        
                    definirAccionJugador(solicitante);
            }                                    
        }                
   }
    
    public void definirAccionJugador(Jugador jugadorAccionista){
        jugadorAccionista.recibirCondena(cantidad);
        //jugadorAccionista.obtenerUBicacion().quitarFicha(jugadorAccionista.obtnerColor());
        jugadorAccionista.moverseA(carcelMasCercana.obtnerIndiceRecorrido());//pues debe mandarse el índice de la casilla a la cual se dirigirá, en este caso, la cárcel        
    }
    
    public void recibirCarcelMasCercana(Casilla carcel){//empleado para toma una tarjeta, por el hecho de que muestro un diálogo para solicitar datos auqneu no esté la cárcel, a diferencia de VC donde al tener la cárcel creo de una vez la tjtj pero es porque allí no se requiere que la tjtj esté creada desde el inicio de forma obligatoria como aquí
        carcelMasCercana=carcel;
    }
    
    
    //Recuerda que antes (cuando no habías pensado que realmente lo que se tenía que necesitar era el nodo), lo único que hacías era la acción que se encuentra en el método
    //establecerCasilla de jugador, donde guardabas dicha referencia y luego accionacbas su método acción de tal manera que pudiera hacer lo que en ella se indica, si es que 
    //posee acción sino pues solo llevar la referencia actual allí
    
    //LA SENTENCIA SE SETEA EN EL JUGADOR Y LEUGO SE REVISA DICHA VAR, para saber que hasta que momento liberarlo si estaba cuativo
    
    
    //NO OLVIDES que antes de entrar a hacer el cb de referencia y todo lo concerniente a las acciones del turno, se procederá a revisar si el jugador no teine condena, si sí, entocnes se saltará el turno
    //y se decrementará en uno la var, hasta que la cumpla (llegue a 0) si se declaró en bancarrota entonces se procede a saltarse hasta que termine la partida, todos sus turnos, y al finalizar se procede
    //A revisar si está endeudado o no para que no pase del turno hasta que pague sus deudas, si puede sino hasta que se declare en bancarrota
}
