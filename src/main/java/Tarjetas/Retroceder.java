/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarjetas;

import Jugadores.Jugador;
import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 *
 * @author phily
 */
public class Retroceder extends Tarjeta implements Serializable{
    private String definicion;
    private int cantidad;//cantidad a retroceder
    String nombre="Retroceder";
    private boolean unica;
    
    public Retroceder(int cantidadRetroceder){
    //este dato le es asignado directamente al ser creado por el hecho de que solo se debe evitar la casilla de inicio y en la que está, esto por medio de un Spinner que contiene el rango adecuado, según los índices de las casillas asignadas hasta ese momento        
        super("Retroceder");
        cantidad=cantidadRetroceder;        
        unica=false;
    }
    
    /**
     * empleado cunado las tarjetas actúan de forma independiente, es decir 
     * es usado en todas las tarjetas, menos en la personalizada, puesto que se
     * genera una fusión de las acciones,por lo cual no es correcto mostrar lo que hace cada
     * una por separado, sino que debe hacerse en forma unificada
     * @param descripcion
     * @param cantidadRetroceder
     */
    public Retroceder(String descripcion, int cantidadRetroceder){
    //este dato le es asignado directamente al ser creado por el hecho de que solo se debe evitar la casilla de inicio y en la que está, esto por medio de un Spinner que contiene el rango adecuado, según los índices de las casillas asignadas hasta ese momento
        super("Retroceder");
        definicion=descripcion;
        cantidad=cantidadRetroceder;       
        unica=true;
    }
    
      @Override
    public void accion(Jugador solicitante){
        if(unica){
            JOptionPane.showMessageDialog(null, definicion, "Retroceder", JOptionPane.INFORMATION_MESSAGE);
        }        
        definirAccionJugador(solicitante);
    }
    
    protected void definirAccionJugador(Jugador jugadorAccionista){//recuerda que depende de la situación el mandamiento                
        //Aquí llamarás al método moverseA del jugador [recuerda este una fusión de caminar y retroceder]
        jugadorAccionista.retroceder(cantidad);
        
    }//fin del método que define la acción
    
    
    
}
