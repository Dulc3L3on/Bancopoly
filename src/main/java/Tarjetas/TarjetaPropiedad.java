/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarjetas;

import Casillas.Casilla;
import Jugadores.Jugador;
import java.io.Serializable;


/**
 *
 * @author phily
 */
public class TarjetaPropiedad extends Tarjeta implements Serializable{//compartida por los 3 tipos, puesto que solo varía en el dato enviado, el cual se sabrá si se tiene que enviar o no según lo que se le envíe desde la casilla, es decir que ctrctor se emplee
    Casilla casillaPropiedad;
    protected Jugador dueno;
    String nombre;
    int cantidad;//Precio
    int pagoEstancia;    
    int pagoTotal;//lo recibe de jugador, pues es una acción mediata y las tjt's se encargan de que se realicen las acciones inmediatas
    private boolean estadoPropiedad;//ta vez el que sea privada esta var es la casusa de que vuleva a pintar la casilla como cuando teine dueño solamente
    
      public TarjetaPropiedad(String nombrePropiedad,int precioLugar, int pagoPorEstancia){//para servicio/estación
          super(nombrePropiedad);
          cantidad=precioLugar;
          pagoEstancia=pagoPorEstancia;
          nombre=nombrePropiedad;
      }
      
    /**
     * Llamado desde el método de acción de la casilla, este es el método que se encarga de la interacción
     * del jugador(o jugadores en el caso de pago) con el tablero
     * @param solicitante
     */
    @Override
    public void accion(Jugador solicitante){//ese tipo de jugaodr no es necesario almacenarlo, pues es un dato efímero
      
        //definirAccionJugador(solicitante);//creo que no debe ir aquí xD es que este método es cb por cada hija
    }
    
    private void mostrarDialogoDePartida(){//varía según la casilla y por ello la tarjeta, de tal forma que deba ser implementado el método en las hijas, está en el cuerpo de acción donde se escoge cual de los 2 mostrar
      
    }
    
    protected void definirAccionJugador(Jugador jugadorAccionista){//recuerda que depende de la situación el mandamiento            
                 jugadorAccionista.pagar(cantidad);
                 establecerDueno(jugadorAccionista);
                 estadoPropiedad=true;            
    }//esto no cambia en ninguna de las hijas, a la hora de comprar, pero lo que sucede después de, si cambia
    
    public void establecerDueno(Jugador jugadorDueno){        
        dueno=jugadorDueno;        
    }
    
    public Jugador retornarDueno(){//empleado por casilla para definir a su dueño
        return dueno; 
    }
    
    /**
     * empleado por el jugador, donde le envía el dato total de pago, puesto que él se encarga de hacer las ctas
     * según sea su situación
     */
    public void establecerPagoTotal(int datoTotal){
        pagoTotal = datoTotal;
    }//este será llamado por el jugador para que pueda cobrar, aunque tb podría hacerse de una vez aquí, por el hecho de que la tjt determina la acción, peroo ella solo manda a que lo haga, así que sí, jugador debe exe, esot al mandarle 
    //desde aquí a la casilla en cuestión
    
    public void reestablecerEstadoPropiedad(){//no recibe valores, puesto que este solamente será empleado al declararse en quiebra lo cual indica que su estado es no comprado
        estadoPropiedad=false;
    }
    
      public void entregarPropiedad(Jugador propietario){
      
     }
    
      public int entregarPagoEstancia(){
          return pagoEstancia;
      }
      
      public Casilla obtnerCasilla(){
          return casillaPropiedad;
      }
    
}

//te quedaste terminando de definir la acción de esta tjta, y terminar de llenar los datos en la casilla lugar, para luego proceder con los demás tipos de lugar, mientras tanto no has definido las acciones que se harán con el jugador
//esto podría hacerse luego de verificar la correcta asignación a la lista circular para ver si se debe arreglar o no, para luego seguir con la clase partida y el traspaso de la lista de casillas al mapa

//RECUERDA, cuando el jugador apunte a un nodo de la lista circular, hará referencia de una vez al método "acción", de la casilla, el cual está conectado con el método de acción de la tjta (si posee) la cual mandará
//(en casi todas las casillas, a excep de tramap y las demás que solo empleen un JOP) las acciones que el jugador deba realizar.

