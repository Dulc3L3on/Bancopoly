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
public class TarjetaPropiedadServicio extends TarjetaPropiedad implements Serializable{ 
    int pagoTotal;//lo recibe de jugador, pues es una acción mediata y las tjt's se encargan de que se realicen las acciones inmediatas
    private boolean estadoPropiedad;   
    private int sumaDeDados;
    
    //transient Dados dialogoDados;
    transient tarjetaCompraServicioEstacion dialogoCompraSE;
            
    public TarjetaPropiedadServicio(String nombrePropiedad,int precioLugar, int pagoPorEstancia, Casilla casilla){
        super( nombrePropiedad, precioLugar, pagoPorEstancia);                      
              cantidad=precioLugar;
              nombre=nombrePropiedad;
              casillaPropiedad=casilla;
        
    }
    
     @Override
         public void accion(Jugador solicitante){
            mostrarDialogoDePartida();
            //como media vez se presione aceptar, nunca más se volverá a crear este tipo de diálogo para la casilla en cuestión, entonces, supongo que
            definirAccionJugador(solicitante);//no habrá problema con esto, pues el valor no se cambirá de ese pto en adelante y otro objeto no será instanciado, es decir seguirá siendo el mismo en se estableción dicho valor, si no sucediera eso
            //entonces solo deberá guardar dicho valor en una var de aquí para esarla revisando cada vez, aunque media vez se vuleve true (según las reglas dadas, no cambiará de valor)                                            
         }
         
           private void mostrarDialogoDePartida(){//varía según la casilla y por ello la tarjeta, de tal forma que deba ser implementado el método en las hijas, está en el cuerpo de acción donde se escoge cual de los 2 mostrar
        if(!estadoPropiedad){ //pues por el momento nadie ha comprado                                    
                dialogoCompraSE = new tarjetaCompraServicioEstacion(new javax.swing.JFrame(), true);
                dialogoCompraSE.establecerDatos(nombre, cantidad, pagoEstancia, "<<SERVICIO>>");
                dialogoCompraSE.setLocationRelativeTo(null);
                dialogoCompraSE.setVisible(true);
        }        
    }
           
    @Override
     protected void definirAccionJugador(Jugador jugadorAccionista){//recuerda que depende de la situación el mandamiento
           if(estadoPropiedad){//pues solo deberá suceder si no es el dueño, duh! xD
                if(dueno!=null && !jugadorAccionista.obtnerNombre().equalsIgnoreCase(dueno.obtnerNombre())){
                    //aquí se llama al método que halla el pago total, solo que habrá que encontrar la manera de mandar a llamar a los métodos de desarrollo de la partida, que corresponden a las acciones del dado, más que todo el lanzarlos con el 
                    //btn del tablero y por medio de la suma, hallar el valor total a parti de la primera parte del cálculo encontrado, siendo el cálculo completo -> (costo*numeroServiciosBasicosPoseidos) * sumaDados                                       
                    //dialogoDados = new Dados(new javax.swing.JFrame(), true);
                    Tablero.dialogoLanzaDados.reestablecerDialogo();
                    Tablero.dialogoLanzaDados.setLocationRelativeTo(null);
                    Tablero.dialogoLanzaDados.setVisible(true);
                    /*dialogoDados.recibirDesarrolloPartida(new DesarrolloPartida());//de aquí solamente requieres al método de lanzar dados y suma de dados, nada más, pero si en dado xD caso necestiaras de alguno que requiera de los jug puesde enviárselos haciendo DP.jugadores y ya xD
                    dialogoDados.setVisible(true);*/
                    dueno.recibirDInero(jugadorAccionista.pagar( dueno.darDetalleCostoServicios(casillaPropiedad, Tablero.dialogoLanzaDados.entregarSumaDados())));                
                    dueno.incrementarRiquezas(cantidad);       
                    jugadorAccionista.decrementarRIquezas(cantidad);
                    
                    Tablero.actualizarDatosTablaTurno();
                }
            }else if(!estadoPropiedad && dialogoCompraSE.retornarDecision()){
               if(jugadorAccionista.obtenerDinero()>=cantidad){
                    super.definirAccionJugador(jugadorAccionista);
                    entregarPropiedad(jugadorAccionista);//aquí adquiere la propiedad en cuestión
                    dueno.incrementarPropiedades();
                    estadoPropiedad=true;
                    Tablero.actualizarDatosTablaTurno();                    
               }else{
                   JOptionPane.showMessageDialog(null, "Los servicios son cobrados, también al ser comprados", "Sin dinero sufficiente", JOptionPane.INFORMATION_MESSAGE);
                   estadoPropiedad=false;
               }               
            }
     }
           
    @Override
     public void establecerPagoTotal(int datoTotal){//esto es así por el hecho de que la propiedad con el tiempo y las mejoras, va adquiriendo más valor y por lo tanto se le debe ir pasando el valor total, esto al evaluar el dato directamente en la clase jugador
         
         pagoTotal = datoTotal;
    }//este valor será igual al costo por el número de servicios poseídos por el número salido en los dados, es decir (costo*numeroServiciosBasicosPoseidos) * sumaDados
     
     @Override
       public void entregarPropiedad(Jugador propietario){
        propietario.addServicios(casillaPropiedad);//aquí el método para recibir su propiedad comprada, recuerda que el primer número es para saber a que tipo de propiedad general se está refiriendo
     }//esto va a cambiar junto con los de servicio, porque no tienen grupos 
       
       public void estabelcerSumaDeDados(int sumaDados){
           sumaDeDados=sumaDados;
       }
       
       public int entregarSUmaDados(){
            return sumaDeDados;
       }
       
       public void reestablecerValoresOriginales(){
           dueno=null;
           estadoPropiedad=false;
           sumaDeDados=0;
       }
    
}
