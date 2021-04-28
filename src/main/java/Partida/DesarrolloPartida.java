/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Partida;

import BackendBancopoly.Dado;
import BackendBancopoly.Ranking;
import FrontendBancopoly.Dados;
import FrontendBancopoly.ElGanador;
import FrontendBancopoly.SolicitudDatos;
import FrontendBancopoly.Tablero;
import FrontendBancopoly.TurnoDe;
import Jugadores.Jugador;
//a las 11:30 me puse a sustituir todos los registros por la referencia estática de solicitud de datos, por el hecho de que no pueden serializarse variables estáticas
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author phily
 */
public class DesarrolloPartida {//Con respecto a los jugadores, solamente hice a la var static, y nada más en esta clase con respecto a este tema
    
    public static Jugador jugadores[];//este número tb será útil para desplegar los diálogos donde se solicita el color y además de ello el nombre [solo será 1]
    

    private int sumaDados;//debería reestablecerse en algún lugar
    private int coincidencias=0;
    public int numeroTurno;//por el primer diálogo que no emplea el método sino que usa número de forma directa       
    
    TurnoDe dialogoTurno = new TurnoDe(new javax.swing.JFrame(), true);
    Dado dado1 = new Dado();
    Dado dado2 = new Dado();
    Dado dado3 = new Dado();   
    
    public DesarrolloPartida(){
    }
    
    public DesarrolloPartida(Jugador[] jugadoresRecibidos, int esPartidaComenzada){//como solo se puede guardar después de haber llegado a la pantalla de juego...
        
        estalecerTurnoCorrespondiente();
        if(esPartidaComenzada==0){//si se llega a permitirle guardar en editar deberás cb esto ó hacer que se detecte cuando ha sido guardado en editar y se le vuelva a colocar en esa pantalla ó hacer como que si es una nueva partida
                                //Así para cuando se quiera reanudar el juego irá directiro a traer el valor del turno correspondiente
            establecerOrdenTurnos(jugadoresRecibidos);        
        }else{
            jugadores=jugadoresRecibidos;
        }        
        
    }
    
    public void estalecerTurnoCorrespondiente(){
        numeroTurno=SolicitudDatos.registroDatos.obtenerTurnoActual();
    }
    
    public void establecerOrdenTurnos(Jugador jugadoresPartida[]){
        int jugadoresAsignados=0;        
        int coincidencias=0;
        
        jugadores=new Jugador[jugadoresPartida.length];
        
        if(jugadoresAsignados==0){
            //jugadores[0]=new Jugador();
            jugadores[0]=jugadoresPartida[ordenarTurnoAleatoriamente(jugadoresPartida.length)];
            jugadoresAsignados++;
        }
            while(jugadoresAsignados<jugadoresPartida.length){
                int numeroGenerado=ordenarTurnoAleatoriamente(jugadoresPartida.length);
                
                for (int jugadorRevisar = 0; jugadorRevisar < jugadoresAsignados; jugadorRevisar++) {
                        if(jugadores[jugadorRevisar].equals(jugadoresPartida[numeroGenerado])){    
                            coincidencias++;
                            break;
                        }
                }
                
                if(coincidencias==0){
                    jugadores[jugadoresAsignados]=jugadoresPartida[numeroGenerado];
                    jugadoresAsignados++;
                }
                
                coincidencias=0;
            }
            
            SolicitudDatos.registroDatos.recopilarJUgadores(jugadores);//Esto es para que funcione con los datos de registro y así cuando se reanude el juego pueda funcionar sin problema alguno, 
            //ya que el único que preservará lo que tenía antes de salir y guardar la partida será registro temporal de datos, por ello el objeto que sale de esta clase, deberá 
            //obtener sus datps del registro      
    }
    
    public int ordenarTurnoAleatoriamente(int rango){
        Random aleatorio = new Random();
                
        return  aleatorio.nextInt(rango);
    }

    
    public Jugador[] enviarJugadores(){//realemente este método no debería existir si es que se van a extraer los datos de la partida del registro temporal (lo cual debe ser así)
        return jugadores;
    }//Aunque relamente después de recibir los jugadores de la fuente que corresponda, no habría problema con el hecho de usar o no directamente el arreglo de jugadores que aquí
    //se encuentra o en el de registro puesto que ambos hacen referencia a los mismos datos y por lo tanto se trabajará con normalidada para ambos ya que para el arreglo de aquí 
    //ya se habrá asignado correctametne el listado y al igual para el de registros luego de haber terminado el proceso de aquí
    
    public void determinarTrayecto(){
        lanzarDados();
        //Aqupi debería poner un momento de espera para que no comience encontrarSUma a actuar...
        encontrarSUmaDados();
    }//no llamo al de las coincidencias pues este retorna un valor del cual dependerá el btn de tablero para habi o no
    
    public void lanzarDados(){
        Dado dado1 = new Dado();//relamene solo con 1 instaciación se mostrarían los números en los labeles correspondeintes según el número de dados, pero para asegurar que hayan menos huecos al mostrar dichos números, etonces mejor crear otras instacias xD
        Dado dado2 = new Dado();
        Dado dado3 = new Dado();
    
        switch(SolicitudDatos.registroDatos.obtenerNumeroDados()){
            case 1:
                if(dado1.isAlive()==false){
                    dado1.estblecerNumeroDados(1);
                    dado1.start();
                }                
            break;                
            case 2:                
                if(dado1.isAlive()==false && dado2.isAlive()==false){
                    dado1.estblecerNumeroDados(2);
                    dado2.estblecerNumeroDados(2);
                    dado1.start();
                    dado2.start();
                }            
            break;            
            case 3:
                if(dado1.isAlive()==false && dado2.isAlive()==false && dado3.isAlive()==false){
                    dado1.estblecerNumeroDados(3);
                    dado2.estblecerNumeroDados(3);
                    dado3.estblecerNumeroDados(3);
                    dado1.start();
                    dado2.start();
                    dado3.start();
                }            
            break;        
        }
    }
    
    public void pararDados(){
     switch(SolicitudDatos.registroDatos.obtenerNumeroDados()){
            case 1:
                dado1.pararHIlo();                
            break;
            
            case 2:
                dado1.pararHIlo();
                dado2.pararHIlo();
            break;
            
            case 3:
                dado1.pararHIlo();
                dado2.pararHIlo();
                dado3.pararHIlo();
            break;            
        }
        
    }//creo que no se pueden para hilos
    
    
    public int encontrarSUmaDados(){//tal vez el error surga porque llamo de primero a este método y luego se exe los hilos...o talvez será que es porque como se exe de forma simultánea, de alguna manera afecta a este método y halla la suma antes de tenr algo ahí... ó pueda ser por algo que solo exe 1 vez, tal vez en el ctrctor
        switch(SolicitudDatos.registroDatos.obtenerNumeroDados()){
            case 1:
                sumaDados=Integer.parseInt(Dados.labelDados[0].getText());                 
            break;
            
            case 2:                
                sumaDados =Integer.parseInt(Dados.labelDados[0].getText())+Integer.parseInt(Dados.labelDados[1].getText());                                               
            break;
            
            case 3:                
                sumaDados= Integer.parseInt(Dados.labelDados[0].getText())+Integer.parseInt(Dados.labelDados[1].getText())+Integer.parseInt(Dados.labelDados[2].getText());                 
            break;
        }
        
        return sumaDados;
    }
    
    public int encontrarCoincidencias(){//este será envuelto por un if donde este permita que el método se exe solamente si hay + de 1 dado
        //deberá hacerse en un método a parte [el reestablecer la var] que sea llamado por el btn terminar turno, pues de esa manera podrá actuar como se debe, pues si lo hace aquí, jamás pasará las coincidencias de 1
        int lbl1=Integer.parseInt(Dados.labelDados[0].getText().trim());
        int lbl2;
        int lbl3; 
        
        switch(SolicitudDatos.registroDatos.obtenerNumeroDados()){//la verdad ya no tendría que estar encerrado esto en un if puesto que en el switch no se considera esa opción entonces no sucede nada cuando es así, lo cual es lo que debe suceder
            case 2:
                lbl2=Integer.parseInt(Dados.labelDados[1].getText().trim());
                if(lbl1==lbl2){
                    coincidencias++;
                }
                
            break;
            
            case 3:
                lbl2=Integer.parseInt(Dados.labelDados[1].getText().trim());
                lbl3=Integer.parseInt(Dados.labelDados[2].getText().trim());
                if(lbl1==lbl2 && lbl1==lbl3){
                    coincidencias++;
                }               
            break;
        }
        
        return coincidencias;//pues ambas comparten la var y además solo podrá suceder una de dos de estas cosas en una misma partida, lo cual dependerá de la configuración        
    }
    
    public boolean tieneRestricciones(){//útil para el btn de lanzar dados
        if(jugadores[numeroTurno-1].estaEnBancarrota() || jugadores[numeroTurno-1].obtenerTurnosPerdidos()!=0 || jugadores[numeroTurno-1].obtenerCondena()!=0){
            return true;
        }    
        
        return false;
    }//en esta parte debo contemplar el hecho de que posea deuda monetaria
    
    public void reducirRestricciones(){//empelado en el método de tetminar turno para saber si debe app esto o simplemente dejar cb el número de turno al correspondiente
        if(!jugadores[numeroTurno-1].estaEnBancarrota()){//y si si, pues no hace nada xD
            if(jugadores[numeroTurno-1].obtenerCondena()!=0){
                jugadores[numeroTurno-1].reducirCondena();
                JOptionPane.showMessageDialog(null, jugadores[numeroTurno-1].obtnerNombre()+" tu condena ahora es de "+jugadores[numeroTurno-1].obtenerCondena(), "", JOptionPane.INFORMATION_MESSAGE);
            }
            
            if(jugadores[numeroTurno-1].obtenerTurnosPerdidos()!=0){
                jugadores[numeroTurno-1].reducirTurnosPerdidos();
                JOptionPane.showMessageDialog(null, jugadores[numeroTurno-1].obtnerNombre()+" solo te restan "+ jugadores[numeroTurno-1].obtenerTurnosPerdidos() + " de descanso", "aprovéchalos xD", JOptionPane.INFORMATION_MESSAGE);                
            }
        }
    }
    
    /*public void corroborarPermisoParaJUgar(){//recuerda que estos son revisados de forma oficial en el método de terminar turno, aquí es solo cuando hayn excepciones, es decir cuando existan coincidencias y en esa parte, solo le importará saber si cumple o no, no el detalle o que debe hacer con ello
        //establecerTurno();
        if(!jugadores[numeroTurno-1].estaEnBancarrota()){
            jugadores[numeroTurno-1]
        }
    }*///YA NO, ha sido reemplazado por reducir restricciones...
    
    public void jugar(int sumaDados){//no debe recibir parám sino trabajar con el número que se encuentre aquí en la clase, porque el método de terminar turno ya ha colocado las debidas actualizaciones a los valores para poder trabajar
        /*dialogoTurno.recibirDatosParaIndicarAvance(String.valueOf(numeroTurno), jugadores[numeroTurno-1], String.valueOf(sumaDados));//tal vez esto no debería mstrarlo,porque se vileven demasiados diálogos
        dialogoTurno.setVisible(true);*/
        //aquí se llama a la función que se encarga de quitar la ficha de la ubicación ant para que luego en jugar se le coloque en su nueva posición
        //jugadores[numeroTurno-1].obtenerUBicacion().quitarFicha(jugadores[numeroTurno-1].obtnerColor());
        //Aquí ya se procede a avanzar y a poner la ficha en la nueva ubic, antes de exe la acción
        jugadores[numeroTurno-1].avanzar(sumaDados);//ya se mueve y hace la llamada al método acción UwU
    }
    
    /*
        Debe recibir a los jugadores por el hecho de que existen 4 formas para que pueda declararse a un jugador como ganador siendo estas
        separadas en 2 grupos, uno donde se desconoce quien es el ganador, otro cuando se sabe exactamente quien es el que ganó, entonces las 
        situaciones en las que se genera esto es: 1.1 al salir sin guardar, 1.2 al terminarse el tiempo establecido. 2.1 al adquirir todas las
        propiedades un solo jugador, 2.2 al quedar únicamente un solo jugador debido a que los demás quedaron en quiebra.
    */
    public static void encontrarGanador(Jugador jugador){//esto por riquezas, pusto que el porceso para propiedades, es realizado en el momento que se agregan las propiedades, sin importar cual sea, ya que este método revisa a los 3 en general
        int elQueTieneLaMayor=0;//Aquí se almacena el índice del jugadore que posea mayor riqueza, para luego acceder a él por medio de este número        
        ElGanador dialogoFelicitaciones = new ElGanador(new javax.swing.JFrame(), true);
        
        
        if(jugador==null){
            for (int numeroJugador = 1; numeroJugador < SolicitudDatos.registroDatos.darJugadoresEnPartida().length; numeroJugador++) {            
                SolicitudDatos.registroDatos.darJugadoresEnPartida()[numeroJugador].darRiquezasActuales();
            
                if(SolicitudDatos.registroDatos.darJugadoresEnPartida()[numeroJugador].darRiquezasActuales()>SolicitudDatos.registroDatos.darJugadoresEnPartida()[elQueTieneLaMayor].darRiquezasActuales()){//no hay problema con tomar en cuenta a los de bancarrota, porque se estableció su dinero final en 0
                    elQueTieneLaMayor=numeroJugador;//y así se obtiene al jugador en cuestión
                }            
            }//fin del for que se encarga de encontrar al ganador por riquezas debido a que no surgió una situación inesperada
                dialogoFelicitaciones.recibirNombreJUgador(SolicitudDatos.registroDatos.darJugadoresEnPartida()[elQueTieneLaMayor].obtnerNombre());
                dialogoFelicitaciones.setLocationRelativeTo(null);
                dialogoFelicitaciones.setVisible(true);
                SolicitudDatos.registroDatos.darJugadoresEnPartida()[elQueTieneLaMayor].declaraseGanador();
        }else{
            jugador.declaraseGanador();
            
            for (int numeroBoton = 0; numeroBoton < 10; numeroBoton++) {
                Tablero.botonesEnPartida[numeroBoton].setEnabled(false);
            }
            
            dialogoFelicitaciones.recibirNombreJUgador(SolicitudDatos.registroDatos.darJugadoresEnPartida()[elQueTieneLaMayor].obtnerNombre());
            dialogoFelicitaciones.setLocationRelativeTo(null);
            dialogoFelicitaciones.setVisible(true);
        }                                                     
        
        //aquí el diálogo que pregunta si desean jugar de nuevo o no y si ahora jugarán con tienpo o no
        
        //Aqupi el diálogo que muestra la felicitación al jugador, su hay empate... bueno, el jugador ganador sería el úlimo a quien reviso...pues puede que exista un empate, triple, cuadruple...de tal manera que todos puedan llegar a tener la misma cdad
       //resulta posible mostrar de una vez al ganador por el hecho de que en estas formas de ganar ya se sabe que si o si se deben inhabi los btn por ello no hay necesidad de tener que estar revisando la var y luego habi o no dependiendo de la situación
       //como sería para el caso de cuando se gana por ser el único ó porque acaparó todas las propiedades..     
    }//solo te haría falta llamarlo cuando se acabe el tiempo, pero aún no hay lugar especificado
    
    
    
    /**
     *Este método será empleado en la parte en la que se adquieren las propiedades para saber si todas ya poseen dueño 
     * donde no importa si estan hipotecadas porque siguen teniendo dueño... de esta manera se procederá a llamar al método
     * buscar el ganador porque todas
     */
    public void averiguarEstadoPropiedadesTotales(){
        SolicitudDatos.registroDatos.ObtenerRecorridoLogico();
        
    }
    
    public void mostrarTurno(){
        dialogoTurno.recibirDatosParaMostrarTurno(String.valueOf(numeroTurno), jugadores[numeroTurno-1]);
        dialogoTurno.setLocationRelativeTo(null);
        dialogoTurno.setVisible(true);
    }
    
    public void reestablecerCoincidencias(){//usado por finalizar turno
        coincidencias=0;
    }
    
    public void reestablecerSumaDados(){
        sumaDados=0;
    }
    
    public int establecerTurno(){
        numeroTurno++;
        
        if(numeroTurno==(SolicitudDatos.registroDatos.obtenerNumeroJugadores()+1)){
            numeroTurno=1;//si debe ser 1 porque siempre tendrá el valor a mostrar por lo cual es el valor +1 con el que se trabaja para desarrollar la partida
        }
        
        SolicitudDatos.registroDatos.recopilarTurnoActual(numeroTurno);
        return numeroTurno;
    }//recuerd, este método es para el diálogo y el método que se emplea para que el juegador juegue su repectivo turno xD, obtendrá
    //el número de jugador del diálogo, porque si lo hace por medio de este método estaría haciendo referencia al jugador siguiente
    
    public int obtenerTurnoActual(){//este será para el diálogo de las poseciones, puesto que si se usa el establecer turno se habrá distorsionado el orden ya que se slatará 1
        return numeroTurno;
    }
    
    public int obtenerSUmaDados(){
        return sumaDados;
    }
    
    public void crearJugadores(){
      //aquí irá el método que revisa si el ibjeto no tiene nada es porque debe soliciar los datos [es decir que aquí debe ponerse el bloque que en solicitud de datos se exe para los jugaores, pero 
      //debido a que este bloque se exe cuando se juega una partida recién creada o editada, ó cuando se acaba de decidir cb de jugadores entonces debe ser un método que no se emplea en el ctrctor
      //si es que al decidir jugar de nuevo se rxn borrando lo que en el tablero se hallaba y no creando otro obtjeto tablero [lo cual creo que sería mejor]
    }
    
    
    //RECUERDA, EL MÉTODO  DE LANZAR DADOS TRABAJA SOLAMENTE CON EL JUGADOR CON EL QUE 
    //COMENZÓ, AL MOMENTO DE YA NO PODER TRABAJR CON ÉL, ES CUANDO OTORGA LA BATUTA XD, AL
    //MÉTODO QUE TRABAJE CON LOS JUGADORES SIGUIENTES AL ACTUAL (en plural porque puede que el
    //siguiente al actual no cumpla condiciones y por ello se deba trabajar con su siguiente, y así...
    
}
