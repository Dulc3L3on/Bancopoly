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
public class SalgaCarcel extends Tarjeta implements Serializable{
    Jugador condenado;
    String nombre="SalgaCárcel";
       
    public SalgaCarcel(){
        //no debe recibir nada en particular, ni siquiera la referncia a la cárcel, porque puede sacar de cualquiera ya que lo que hace su método es reducir la condena a 0
        super("SalgaCárcel");
    }   
        
    /**
     *Se encargará de volver el contador a 0 de turnos restantes de la cárcel y con ello se permite el acceso al método
     * del jugador que le permite lanzar los dados
     * @param jugadorSolicitante
     */
    @Override
    public void accion(Jugador jugadorSolicitante){
        //muestra el diálogo, ahora sí diálog de la tjt y pasa a entregarse al jugador
        JOptionPane.showMessageDialog(null, "A continuación pasará a tus manos la tarjeta más deseada... usala con inteligencia", "Tarjeta salga caŕcel", JOptionPane.INFORMATION_MESSAGE);//cuando revises y esté bine, mejor muestra un diaologo así se ve más bonito xD, NO ES POR DISCRI AL JOP, me ha sido muy útil, él entiende xD
        jugadorSolicitante.recibirTarjetaParaSalirDeCarcel(this);//se va a su lista enlazada, aunque podría ser arreglo, pero con la lista se puede sacar, 
        //lo malo es que al sacarla implicaría que deba crear nuevamente el objt tjtSC, lo cual no se si aplique a todos, cuando quiera reiniciar el juego ya sea en el mismo periodo en que lo creo o luego de haberlo cargado
    }
    
    /**
     * Método llamado directamente por el jugador cuando quiera salir de la cárcel, a diferencia de acción que como todas las demás por el método de casilla que se llama al hacerle referencia
     */
    public void sacarDeLaCarcel(){//ni siquiera será necesario mandar la var de la condena sino que el jugador iguale la ponga ahí [es decir al momento de obtenerla] para que se la haga 0, pero para segui el estándard... se guardará al jugador aquí de forma "permanetente" y se empleará el método solicitarSC
        condenado.recibirLibertad();//xD y así es libre como el viento xD
    }
    
    
    //DEBES revisar que es lo que sucede al usar salir de la cárcel, pues al menos por la forma en que trabajamos, lo que sucede es que solo podrá emplear
    //la tarjeta y en el próximo turno comenzar a moverse, ya sea que emplee inmediatamente la tjt al entrar a la cárcel, o lo haga turnos después, ya que después 
    //al no cumplr con las condi, no podrá emplear los dados... auqnue creo que lo que sucede es que después de pasados algunos turnos y luego de usar la tjt el     
    //puede lanzar los dados
}
