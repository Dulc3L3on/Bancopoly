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
public class Caminar extends Tarjeta implements Serializable{//podría fusionarse con mover a casilla    
    String nombre="Caminar";
    private String definicion;
    private int cantidad;//aún no sé exactamente como evitar que caiga en la casilla tomaTjt de forma seguida... aunque con esta casilla no hay tanto problema, por el hecho de que no solo existe
    private boolean unica;
//esta tjt sino tb otras, pero esto no podría ser el caso todas las veces, esto podría provocar un bucle infinito, deplano que al igual que con moverA, tendrá que solicitarse el dato al finalizar 
    //la revisión, lo cual hará que se tengan que estar almacenando las casillas lógicas en un arreglo o lista enlazada para que se sepa cuantas casillas requieren que se les asigne el dato, ya sea 
    //con varias ventanas o varios lbl con su respectivo txt para escribir el dato que corresponda a cada casilla y así pueda generar bien sus acciones, recuerda que la cantidad estará dada por un intervalo
    //de tal manera que no pueda llegar a la casilla física
    
    public Caminar(boolean esUnica){
        super("Caminar");
        unica=esUnica;
        //no recibirá nada pues, recuerda que al igual que mover a, mostrará su diálog hasta el final, de la customización, antes de jugar,pues hasta es momento posee lo datos necesarios
        //para evitar que se genere una excepción, en este caso por el hecho de caer en esta casilla y solamente hayan casillas cmaniar, y en el caso de mover a por el hecho de que no exista la casilla    
    }  
    
    /**
     * Hace avanzar al usuario la cantidad de casillas especificadas en la customización
     * @param solicitante
     */
    @Override
    public void accion(Jugador solicitante){
        //Se mandará a jugador la cantidad a overse, para que pueda cb su referencia en el recorrido
        if(unica){
            JOptionPane.showMessageDialog(null, definicion , "Avanzar", JOptionPane.INFORMATION_MESSAGE);//siempre tendrá definición así que no hay necesidad de poner una condición para mostrar este JOP
        }        
        definirAccionJugador(solicitante);        
    }
    
    protected void definirAccionJugador(Jugador jugadorAccionista){
        jugadorAccionista.avanzar(cantidad);
    }
    
    /**
     *  Empleado por la casilla luego de haber mostrado el diálog respectivo, el cual es invocado por
     * la casilla, talvez no la que se encuentra en el listado,pero sí una referencia a ella misma, esto por
     * medio del alamcenamiento en la lista de la customización principal, que será llamada por última 
     * vez antes de jugar al aprobar el diseño, solo que esta vez, directamente con el método que establezca
     * los datos faltantes de las casillas en espera
     * @param definicion
     * @param casillasACaminar
     */
    public void establecerDatosFaltantes(String definicion, int casillasACaminar){
            establecerDefinicion(definicion);
            establecerCantidadCaminar(casillasACaminar);            
    }
    
    private  void establecerDefinicion(String descripcion){
        definicion=descripcion;
    }
    
    /**
     * Método llamado desde casilla en el momento de ser aprobado el diseño
     * es decir antes de empezar a jugar y por ello haber mostrado el diálogo y haber recibido el 
     * dato ingresado por el usuario
     * @param cantidadACaminar
     */
    private void establecerCantidadCaminar(int cantidadACaminar){
        cantidad=cantidadACaminar;
    }//esta cantidad será = al npumero de casillas creadas totales, puesto que inicia en 0 lo cual evita que se le reste 1
    
}
