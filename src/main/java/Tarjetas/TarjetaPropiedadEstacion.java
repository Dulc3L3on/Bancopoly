/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarjetas;

import Casillas.Casilla;
import FrontendBancopoly.Tablero;
import FrontendBancopoly.tarjetaCompraServicioEstacion;
import Jugadores.Jugador;
import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 *
 * @author phily
 */
public class TarjetaPropiedadEstacion extends TarjetaPropiedad implements Serializable{
    private int pagoTotal;//lo recibe de jugador, pues es una acción mediata y las tjt's se encargan de que se realicen las acciones inmediatas
    private boolean estadoPropiedad;    
    
    
    transient tarjetaCompraServicioEstacion dialogoCompraSE;
            
    public TarjetaPropiedadEstacion(String nombrePropiedad,int precioLugar, int pagoPorEstancia, Casilla casilla){
        super( nombrePropiedad, precioLugar, pagoPorEstancia);                      
              cantidad=precioLugar;
              nombre=nombrePropiedad;
              casillaPropiedad=casilla;
        
    }
    
     @Override
     public void accion(Jugador solicitante){
         mostrarDialogoDePartida();            
         definirAccionJugador(solicitante);//no habrá problema con esto, pues el valor no se cambirá de ese pto en adelante y otro objeto no será instanciado, es decir seguirá siendo el mismo en se estableción dicho valor, si no sucediera eso
                //entonces solo deberá guardar dicho valor en una var de aquí para esarla revisando cada vez, aunque media vez se vuleve true (según las reglas dadas, no cambiará de valor)                                        
    }
         
    private void mostrarDialogoDePartida(){//varía según la casilla y por ello la tarjeta, de tal forma que deba ser implementado el método en las hijas, está en el cuerpo de acción donde se escoge cual de los 2 mostrar
         if(!estadoPropiedad){ //pues por el momento nadie ha comprado                                    
            dialogoCompraSE = new tarjetaCompraServicioEstacion(new javax.swing.JFrame(), true);
            dialogoCompraSE.establecerDatos(nombre, cantidad, pagoEstancia,"<<ESTACIÓN>>");
            dialogoCompraSE.setVisible(true);
        }        
    }
           
    @Override
     protected void definirAccionJugador(Jugador jugadorAccionista){//recuerda que depende de la situación el mandamiento
           if(estadoPropiedad){//pues solo deberá suceder si no es el dueño, duh! xD
                if(dueno!=null && !jugadorAccionista.obtnerNombre().equalsIgnoreCase(dueno.obtnerNombre())){
                    //Aquí se establecería el pago total, lo cual se determina con costo*(2*numeroEstaciones)
                    //Aquí itía el JOP para avisar lo que se hizo al caer en esta casilla
                    dueno.recibirDInero(jugadorAccionista.pagar(dueno.darDetalleCobroEstaciones(casillaPropiedad)));//aquí ya se tiene contempladas las dos posibles situaciones, luego de ser comprada
                    dueno.incrementarRiquezas(cantidad);
                    jugadorAccionista.decrementarRIquezas(cantidad);
                    
                    Tablero.actualizarDatosTablaTurno();
                    //1 comprada y 2. hipotecada
                }
            }else if(!estadoPropiedad && dialogoCompraSE.retornarDecision()){
               if(jugadorAccionista.obtenerDinero()>=cantidad){               
                    super.definirAccionJugador(jugadorAccionista);
                    entregarPropiedad(jugadorAccionista);//aquí adquiere la propiedad en cuestión
                    dueno.incrementarPropiedades();//pues recuerda que sus riquezas siguen estando ahí pero de otra forma en este caso, en la forma de propiedad lugar
                    estadoPropiedad=true;
                    Tablero.actualizarDatosTablaTurno();
               }else{
                   JOptionPane.showMessageDialog(null, "Una compra requiere de dos cosas fundamentales, persona y dinero\n le hace falta lo segundo", "Consiga dinero", JOptionPane.INFORMATION_MESSAGE);
                   estadoPropiedad=false;
               }
            }
     }          
    //para el caso de estación, este costo total será igual al valor del costo por 2 cada propiedad que de estaciones posea... es decir costo=40, numero de estaciones 3 -> 40*(2*numeroEstaciones)
     
     @Override
       public void entregarPropiedad(Jugador propietario){
        propietario.addEstaciones(casillaPropiedad);//aquí el método para recibir su propiedad comprada, recuerda que el primer número es para saber a que tipo de propiedad general se está refiriendo
     }   
       
       public void reestablecerEstadoOriginal(){
           dueno=null;
           estadoPropiedad=false;
           
       }
       
    
}
