/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Casillas;

import BackendBancopoly.ListaEnlazada;
import Jugadores.Jugador;
import Tarjetas.Tarjeta;
import java.awt.Color;
import java.io.Serializable;
import javax.swing.*;

/**
 *
 * @author phily
 */
public class Casilla implements Serializable{
    final Color Beigh = new Color(219,213,203);
    private ListaEnlazada<Jugador> listaJugadores;//recibido en el momento en que hace referencia a dicha casilla
    String nombreCasilla;
     JPanel casillaFisica;//Si te da mucha complicación el hecho de obtener los componentes de aquí, entonces recibe los
    private Tarjeta tarjeta;         // lbls que necesitar modificar aquí como el del nombre del dueño y el de la breve descripción donde en lugar del precio al comprarla mostrarás el numero de ctrcs que posea la propiedad    
     int cantidad;
    private Color colorMezclado = new Color(255, 255, 255);
    
    
    
    public Casilla(JPanel casilla, String nombre){//si pues las demás variables deben ser usadas por las hijas y por lo tanto deben ellas solicitar los datos, para que cada una pueda emplearlo
        casillaFisica=casilla;
        nombreCasilla=nombre;
    }          
    /**
     * Aqupi se hace la respectiva creación de la tarjeta la cual depende del tipo de casilla, de tal forma que auí ya se
     * tenga la hija que se debe crear, a excepción de toma un tarjeta que si dependerá de las escojencias que tenga y 
     * número de repeticiones
     */
    public void crearTarjeta(){//será sobreescrito
        //en esta parte se crea a la tarjeta lógica y luego ella se encarga de crear la logica que requiera, solo que
        //en el caso de propiedad habrán dos tarjetas físicas una para cuando le pertenezca al banco y otra cuando al jugador
        
    }
    
    /**
     * aquí manda a llamar a su tarjeta respectiva quien en realidad será la encargada de 
     * mandar a exe las accione del jugador, haciendo las respectivas llamadas a los métodos
     * que quiera él exe [esto por medio del recibimeinto del jugador en cuestión
     * @param jugadorLlegado
     */
    public void accion(Jugador jugadorLlegado){
    //Aqupi se llamará a la tarjeta para que exe su acción, la cual dependerá de la situación, comprar, cobrar, no mostrar nada (si es propiedad y es suya)
    //mandar a hacer algo, nformar... cosas así, pero eso se decide en la tarjeta
       colocarFicha(jugadorLlegado.obtnerColor()) ;
    }
      
     //manda los datos respectivos a la forma física según haya sido la acción, es decir serpa empleado solo por acción
    //por tal motivo debe ser sobreescrito
      private void actualizarFormaFisica(){//recuerda que estos datos lo podrá enviar por medio del acceso a los componentes del panel que el es enviado a la hora de crear la casilla(esto por medio del componentsIn(y aquí el indice que ya
          //sabes que debe ir xD
          
          
      
      }
      
    private void colorear(Color color){                        
        
        casillaFisica.setBackground(new Color ((casillaFisica.getBackground().getRed() + color.getRed())/2, (casillaFisica.getBackground().getGreen() + color.getGreen())/2, 
                (casillaFisica.getBackground().getBlue() + color.getBlue())/2));//esto para fusionar
    }
    
    public Tarjeta obtenerTarjeta(){//lo cual entregará difrentes forma al app el jugador al obtner la tarjeta, el método "entregarte" que las tarjetas sobreescribirán, según lo que deban dar al jugador
        return tarjeta;
    }
    
    public void establecerJugador(Jugador jugadorEnPosicion){
         listaJugadores.anadirAlFinal(jugadorEnPosicion);
    }
    
    /**
     *Método empleado para dar el índice al que será mandado y así se pueda buscar el contenido
     * de forma más rápida, sabiendo si debe ir hacia adelante o hacia atrás para obtenerlo
     * @return
     */
    public int obtnerIndiceRecorrido(){//empleado más que hacen que el jugador se vaya a otra parte para exe su acción, pues 
        //al mandar la casilla al jugador, no hay problema con la acción, pero si con el recorrido, pues no se tendrá el nodo actual
        //donde se encuentra, y avanzará como si siguiera en la casilla mandona xD
        JLabel lbl_IndiceRecorrido=(JLabel) casillaFisica.getComponent(1);//creo que el índice está mal...pero dudo mucho eso
        System.out.println("indice de casilla a la que fue mandado:" + lbl_IndiceRecorrido.getText());
              return Integer.parseInt(lbl_IndiceRecorrido.getText());
    }
    
    /**
     * Se encuentra en el método acción de la casilla para que cuando el juador caiga, es decir, haga referencia a 
     * la casilla, entonces se coloree de forma correcta, este método es llamado por la casilla, a dif de quitar ficha
     * @param color
     */
    public void colocarFicha(Color color){//va ir metido en el método de actualizar forma física, de tal manera que el jugador solo deba enrtegar el color
        int espacioFisicoFicha=3;
        JLabel fichaFisica = new JLabel();
        fichaFisica= (JLabel) casillaFisica.getComponent(espacioFisicoFicha);
        
        while(!fichaFisica.getBackground().equals(Beigh)){//esto es para saber si está o no ocupado el espacio correspondiente
            espacioFisicoFicha++;
            
            System.out.println(casillaFisica.getComponent(espacioFisicoFicha));
            fichaFisica = (JLabel) casillaFisica.getComponent(espacioFisicoFicha);          
            
            System.out.println(fichaFisica);
            /*if(){
            
            }*/
        }
        
        fichaFisica.setBackground(color);        
        fichaFisica.setOpaque(true);
        
    }
    
    /**
     * Es llamado en el método jugar, ante de avanzar, por el hecho de que 
     * para caminar de primero se debe quitar un pie para mover el otro xD
     * @param color
     */
    public void quitarFicha(Color color){
        int espacioFisicoFicha=3;//pues los lbl de las fichas comienzan en este índice
        JLabel fichaFisica = new JLabel();
                fichaFisica=(JLabel) casillaFisica.getComponent(espacioFisicoFicha);
        
        while(!fichaFisica.getBackground().equals(color) /*&& espacioFisicoFicha<3+registro.obtenerNumeroJugadores()*/){//esto es para saber si está o no ocupado el espacio correspondiente
            espacioFisicoFicha++;
            
            fichaFisica = (JLabel) casillaFisica.getComponent(espacioFisicoFicha);            
        }
        
        fichaFisica.setBackground(Beigh);        
        fichaFisica.setOpaque(true);//sería bueno quitarle lo opaco al desplegarlos al principio
    }
    
    public JPanel obtenerCasillaFisica(){
        return casillaFisica;
    }
    
    public String obtenerNombre(){
        return nombreCasilla;
    }
    
    
}
