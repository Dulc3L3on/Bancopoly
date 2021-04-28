/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarjetas;

import Casillas.Casilla;
import FrontendBancopoly.Tablero;
import FrontendBancopoly.tarjetaCompraPropiedad;
import Jugadores.Jugador;
import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 *
 * @author phily
 */
public class TarjetaPropiedadLugar extends TarjetaPropiedad implements Serializable{
    int pagoEstancia;
    int precioPorSimples, precioPorSofisticadas=-1;    //no tendría que haber problemas por el hecho de que te encargarás de que no se puedan recibir números negativos
    int pagoTotal;//lo recibe de jugador, pues es una acción mediata y las tjt's se encargan de que se realicen las acciones inmediatas
    private boolean estadoPropiedad;
    transient tarjetaCompraPropiedad dialogoCompra;
    
         public TarjetaPropiedadLugar(String nombrePropiedad,int precioLugar, int pagoPorEstancia, int precioSImples, int precioSofisticadas, Casilla casilla){//para lugar
             super(nombrePropiedad,precioLugar,pagoPorEstancia);        
              precioPorSimples=precioSImples;
              precioPorSofisticadas=precioSofisticadas;                 
              cantidad=precioLugar;
              nombre=nombrePropiedad;
              casillaPropiedad=casilla;
    }        
         
         
    @Override
         public void accion(Jugador solicitante){
            mostrarDialogoDePartida();            
            //como media vez se presione aceptar, nunca más se volverá a crear este tipo de diálogo para la casilla en cuestión, entonces, supongo que
            definirAccionJugador(solicitante);//no habrá problema con esto, pues el valor no se cambirá de ese pto en adelante y otro objeto no será instanciado, es decir seguirá siendo el mismo en se estableción dicho valor, si no sucediera eso
            //si nunca estableces la var de estado de propiedad, entonces debes hacerlo y en este lugar               
            //para el caso de que no pueda comprar, sería una llamada en vano, pero lo hago así para no estar repitiendo l
            //entonces solo deberá guardar dicho valor en una var de aquí para esarla revisando cada vez, aunque media vez se vuleve true (según las reglas dadas, no cambiará de valor)            
         }
         
           private void mostrarDialogoDePartida(){//varía según la casilla y por ello la tarjeta, de tal forma que deba ser implementado el método en las hijas, está en el cuerpo de acción donde se escoge cual de los 2 mostrar
        if(!estadoPropiedad){ //pues por el momento nadie ha comprado                                    
                dialogoCompra = new tarjetaCompraPropiedad(new javax.swing.JFrame(), true);
                dialogoCompra.establecerDatosPropiedad(nombre, cantidad, precioPorSimples, precioPorSofisticadas);
                dialogoCompra.setVisible(true);
                
        }                
               
    }
           
    @Override
     protected void definirAccionJugador(Jugador jugadorAccionista){//recuerda que depende de la situación el mandamiento
            if(estadoPropiedad){//pues solo deberá suceder si no es el dueño, duh! xD
                if(dueno!=null && !jugadorAccionista.obtnerNombre().equalsIgnoreCase(dueno.obtnerNombre())){
                    //cuando ya posea todo el grupo, puedo incluir de una vez el pago de las construcciones, esto noafectará por el hecho de que si no tiene ninguna. entonces el término se hará 0 y por lo tanto no habrá pena
                    //pago total - > if(numeroELementos= tamanioLimite) -> pagoUso*2 
                    //sino -> pagoUso                                     
                    dueno.recibirDInero(jugadorAccionista.pagar(dueno.darDatoCobroPropiedad(casillaPropiedad)));//Aqui se hacen de una vez los cálculos dependiendo de cual de las 3 situaciones se prtn, es decir                                        
                    dueno.incrementarRiquezas(cantidad);
                    jugadorAccionista.decrementarRIquezas(cantidad);
                    
                    Tablero.actualizarDatosTablaTurno();
                    //1.comprada sin grupo completo, 2. comprada con grupo completo, 3. comprada pero hipotecada
                }
            }else if(!estadoPropiedad && dialogoCompra.retornarDecision()){//creo que hubiera podido omitirse lo del estado de propiedad... pero me da desconfianza xD
                if(jugadorAccionista.obtenerDinero()>=cantidad){
                    super.definirAccionJugador(jugadorAccionista);
                    entregarPropiedad(jugadorAccionista);//aquí adquiere la propiedad en cuestión                    
                    dueno.incrementarPropiedades();
                    estadoPropiedad=true;//esto ni siquiera tendría que hacerlo porque se hace en el padre
                    
                    Tablero.actualizarDatosTablaTurno();
                }else{
                    JOptionPane.showMessageDialog(null, "Lastimosamente las propiedades no pueden darse fiado...\nCuando tenga el dinero vuelva", "Si la quiere, consiga dinero", JOptionPane.INFORMATION_MESSAGE);
                    estadoPropiedad=false;
                }                
            }
         
     }
           
    
     
    @Override
     public void entregarPropiedad(Jugador propietario){
        propietario.addPropiedades(casillaPropiedad);//aquí el método para recibir su propiedad comprada, recuerda que el primer número es para saber a que tipo de propiedad general se está refiriendo
     }
     
     public void reestablecerEstadoOriginal(){
         estadoPropiedad=false;
         dueno=null;         
     }
         
         
}
