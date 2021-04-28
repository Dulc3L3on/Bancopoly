/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RegistroYManejoTemporalDatos;

import BackendBancopoly.Cola;
import BackendBancopoly.ListaCircular;
import BackendBancopoly.ListaEnlazada;
import Casillas.Casilla;
import Jugadores.Jugador;
import java.io.Serializable;
import javax.swing.JPanel;

/**
 *Es creada con el fin de que las clases no vayan a traer directamente los datos del frontend
 * sino que acudan a esta que se encarga de agarrar los datos del forntend donde fueron
 * ingresados, así es más sencilla la asignación puesto que se tendrá que importar tanta clase
 * en cada una de las demás del backend sino esta(al menos para pedir datos).
 * @author phily
 */
public class RegistroTemporalDatosPartida implements Serializable{//DEBE SER ESTÁTICO TODO LO DE REGISTRO!!!
    //datos del tablero
     private String nombreDelJuego="Aquí el nombre";
     private int numColumnas=5, numFilas=5;   
     private int numeroCasillasAsignadas=-1;//este es el índice que se mira y es igual al número de casillas con jugabilidad creadas
     private int ultimoNumeroPanelAsignado;//este valor será el índice el panel tiene con respecto a toda la cuadrícula generada, este campo será reemplazado al momento de editar por la creación y eliminación de columnas
    
    //datos de jugadores
     private int numeroJugadores=2;    
     private int dineroInicial=1000;
     private Jugador jugadores[];
    
    //especificaciones de la partida
     private int turnoActual=1;
     private int estadoBotonDados;
     private int dineroPorVuelta=500;
     private int cantidadDados=1;
     private int limiteEdificaciones[]= new int[2];
     private int valorHIpotecaGeneral;
     private int interesHIpoteca;
     public boolean tiempoLimite;
     private int horasIniciales, minutosIniciales, segundosIniciales, tipoTiempo;//1 para temporizador 2 para reloj
     private int horasRestantes, minutosRestantes, segundosRestantes;
     private boolean modoRestringido;
     private int propiedadesTotales;
    /*I*/ private ListaEnlazada<JPanel> recorridoFIsico;//esta no requiere de ser guardada al momento de decidir salir porque así es "inmutable" ya que solo cambiará cuando decidan editar dicho recorrido
    /*No I*/ private ListaCircular<Casilla> recorrido;//Recuerda que como en este caso la lista circular es guardada y se queda de la manera en que fue creada entonces es necesario que se guarde al momento de terminar la partida por el hecho de que esta no contiene los datos de ese momento
    
    
    
    
    static private int numeroOrdinarioCAsilla;//si vas a crear en el momento los obj este número será útil para reemplazar el nodo correpondiente por esta nueva info, al igual que los datos mandados anteriormente por esta clase
    //recuerda que esta mandará de una vez la info al archivo y no al final, el archivo se cerrará hasta finalizar la customización del mapa completo.
    
    //datos de propiedades... pero para saber a que grupo pertenecen las propiedaes, como estas no se encuentran
    //de forma explícita, será necesario app al nombre del lbl el nombre de su grupo
     private Cola<String> pilaGruposLugar= new Cola();
     private Cola<String> pilaGruposServicio= new Cola();
     private Cola<String> pilaGruposEstacion= new Cola();//están en desuso, por el hecho de que fueron susti por las listas de acá abajito
    
     ListaEnlazada<String> listaGruposLugar = new BackendBancopoly.ListaEnlazada();
     ListaEnlazada<String> listaElementosServicio = new BackendBancopoly.ListaEnlazada();//este cb se dio por el hecho de que no tienen grupos sino mas bien solo elementos y de a montón
     ListaEnlazada<String> listaElementosEstacion = new BackendBancopoly.ListaEnlazada();
    
    
    //datos de propiedades en general
     private String grupoPropiedad;//Recuerda este será el nombre no el texto del label, pues solo le es útil a la clase casilla mas no al mapa,pues por medio de este atrib en la lista de las casillas en funcionamiento se sabrá si posee todos los integrantes del grupo o no
     private String nombrePropiedad;    
     private int cobroPorEstanciaUso;
    
    //datos de los lugares
     private int valorCtrccSimple;
     private int valorCtrccSofisticada;
    
    //Datos de las casillas trampa
     private String tipoTrampa;//para saber a que hija pertenece de las TARJETAS (no me confundí) pertenece, puesto que aquí se trabaja con una funcionalidad similar... bueno aunque pensandolo bien,e sto complicaría la herencia...
     private int cantidaTrampa;
     private String breveDescripcion;//Esto solo le será util a la casila y al registro no a la clase casilla en particular    
    
    public RegistroTemporalDatosPartida(){//Aun no sé que recibir aquí, probablemente aquellos datos que no variarán en la customización en cuestión.. como filas, cols
        
    }
    
    /**
     *Registra los datos compartidos por los 3 tipos de propiedades
     * @param grupoDePropiedad
     * @param nombreDePropiedad
     * @param cobroEstanciaUso
     */
    public void recopilarDatosCasillasPropiedad(String grupoDePropiedad, String nombreDePropiedad, int cobroEstanciaUso){
        grupoPropiedad=grupoDePropiedad;
        nombrePropiedad=nombreDePropiedad;
        cobroPorEstanciaUso=cobroEstanciaUso;
    }
    
    public void incrementarNumeroPropiedadesTotales(){
        propiedadesTotales++;
    }
    
    public int obtenerTotalPropiedades(){
        return propiedadesTotales;
    }
    
    public String obtenerGrupoPropiedad(){
        return grupoPropiedad;
    }
    
    public String obtenerNombrePropiedad(){
        return nombrePropiedad;
    }
    
    public int obtenerCobroPorEstanciaUso(){
        return cobroPorEstanciaUso;
    }
    
    public void recopilarEspecificacionesPartida(String nombreJUego, int filas, int columnas, 
            int numeroJUgadores, int dineroIni, int dineroPorCadaVuelta, int cdadDados, int limiteSimples, 
            int limiteSofisticadas,  int interesPOrHIpoteca, int hipotecaGeneral, boolean hayTiempoLimite, boolean esRestringido){
        
        recopilarDatosJuego(nombreJUego, filas, columnas);
        recopilarDatosJUgadores(numeroJUgadores, dineroIni);
        recopilarDatosGeneralesPartida(dineroPorCadaVuelta, cdadDados, limiteSimples, limiteSofisticadas, hipotecaGeneral, interesPOrHIpoteca, hayTiempoLimite, esRestringido);
    }
    
    public void recopilarDatosJuego(String nombreJUego, int filas, int columnas){
        nombreDelJuego=nombreJUego;        
        numFilas=filas;
        numColumnas=columnas;        
    }
    
    public String obtenerNombreJuego(){
        return nombreDelJuego;
    }
    
    public int obtenerNumeroDados(){
        return cantidadDados;
    }
    
    public void recopilarDatosJUgadores(int numeroJUgadores, int dineroIni){
        numeroJugadores= numeroJUgadores;
        dineroInicial=dineroIni;        
    }
    
    public void recopilarJUgadores(Jugador jugadoresPartida[]){//iba a decir que deberá ser recopilado al principio y al final, perooo recordando que esta es una referencia entonces quiere decir que como va a estar apuntando al listado de jugadores que se emplea en la partida entonces media vez se guarde, poseerá los datos del momento actual..¿por qué? pues porque refiere al objeto que cada vez cambia y por ello tendrá los datos del momento actual
        jugadores=jugadoresPartida;
    }//Entonces prueba si cuando obtengas al jugador tienes los datos del momento último en el que se quedó el juego (lo cual debería ser así) sino pues entonces guarda hasta de último.
    
    public Jugador[] darJugadoresEnPartida(){
        return jugadores;
    }
    
    public void recopilarDatosGeneralesPartida(int dineroPorCadaVuelta, int cdadDados, int limiteSimples, int limiteSofisticadas,int hipotecaGeneral,  int interesPOrHIpoteca, boolean hayTiempoLimite, boolean esRestringido){
        dineroPorVuelta= dineroPorCadaVuelta;
        cantidadDados= cdadDados;
        limiteEdificaciones[0]= limiteSimples;
        limiteEdificaciones[1]= limiteSofisticadas;
        valorHIpotecaGeneral= hipotecaGeneral;
        interesHIpoteca= interesPOrHIpoteca;
        tiempoLimite= hayTiempoLimite;
        modoRestringido= esRestringido;
    }
    
    public void recopilarHorasIniciales(int horas){
        horasIniciales= horas;
        horasRestantes= horasIniciales;
    }
    
    public void recopilarMinutosIniciales(int minutos){
        minutosIniciales= minutos;
        minutosRestantes=minutosIniciales;
    }   
    
    public void recopilarSegundosIniciles(int segundos){
        segundosIniciales = segundos;
        segundosRestantes=segundosIniciales;
    }//hago esto en estas 2 recopilaciones puesto que al recibir el tiempo con el cual iniciar ese es el tiempo con el cual debe trabajar el temporizador, además de esta manera se tendrá que
    //emplear de forma general solo el método de obtner el tiempo restante a menos que se haya acabado, el cual sería un caso especial
    
    public void recopilarHorasRestantes(int numeroHoras){
        horasRestantes=numeroHoras;
    }
    
    public void recopilarMinutosRestantes(int numeroMins){
        minutosRestantes=numeroMins;
    }
    
    public void recopilarSegundosRestantes(int numeroSeg){
        segundosRestantes=numeroSeg;
    }
    
    public void reestablecerTiempo(){
        horasRestantes=horasIniciales;
        minutosRestantes=minutosIniciales;
        segundosRestantes= segundosIniciales;
    }
    
    public void recopilarTipoTiempo(int tipo){//1-> temporizador... 2-> reloj
        tipoTiempo=tipo;
    }
    
    public void establecerLanzamientoDados(int estadoDados){
        estadoBotonDados=estadoDados;
    }
    
    /**
     *Este recorrido te será útil al momento de editar, pues si decinde no tocar nada, entonces de algun lugar
     * debrás sacar la info anteiror u ede caso 
     */
    public void recopilarRecorrido(ListaCircular<Casilla> listaRecorrido){
        recorrido=listaRecorrido;
    }
    
    public void recopilarRecorrdioFisico(ListaEnlazada<JPanel> listaRecorridoFisico){
        recorridoFIsico=listaRecorridoFisico;
    }
    
    public int obtenerNumeroJugadores(){
        return numeroJugadores;
    }
    
    public void recopilarTurnoActual(int turno){
        turnoActual=turno;
    }
    
    public void recopilarDatosCasillasPropLugar(int valorConstruccSImple, int valorCOnstruccSofisticada){//esto no está bien, porque cada cosntrucción vale diferente para cada lugar
        valorCtrccSimple=valorConstruccSImple;
        valorCtrccSofisticada=valorCOnstruccSofisticada;
    }
    
    
    public void recopilarDatosCasillasTrampa(String tipoDeTrampa, String breveDescripcionTrampa, int cantidaTrampa){
        tipoTrampa =tipoDeTrampa;
        breveDescripcion=breveDescripcionTrampa;
        cantidaTrampa=cantidaTrampa;
    }
    
    public void recopilarGruposLugar(String nuevoGrupo){
        pilaGruposLugar.agregarELementosCola(nuevoGrupo);
    }
    
    public void agregarGruposDeLugares(String nuevoGrupo){
        listaGruposLugar.anadirAlFinal(nuevoGrupo);
    }
    
    public void recopilarGruposServicio(String nuevoGrupo){
        pilaGruposServicio.agregarELementosCola(nuevoGrupo);
    }
    
    public void agregarGruposDeServicios(String nuevoGrupo){
        listaElementosServicio.anadirAlFinal(nuevoGrupo);
    }
    
    public void recopilarGruposEstacion(String nuevoGrupo){
        pilaGruposEstacion.agregarELementosCola(nuevoGrupo);
    }
    
    public void agregarGruposDeEstaciones(String nuevoGrupo){
        listaElementosEstacion.anadirAlFinal(nuevoGrupo);
    }
    
    public void recopilarDatosEdicion(String nombreJUego,int filas, int columnas, int numeroJUgadores, int dineroIni, int dineroPorCadaVuelta,
            int cdadDados, int limiteSimples, int limiteSofisticadas, int interesPOrHIpoteca, int horas, int minutos, int segundos ){
        nombreDelJuego=nombreJUego;        
        numFilas=filas;
        numColumnas=columnas;    
        if(numeroJugadores!=numeroJUgadores){
           numeroJugadores= numeroJUgadores;
           jugadores=null;
        }                
        dineroInicial=dineroIni;  
        dineroPorVuelta= dineroPorCadaVuelta;
        cantidadDados= cdadDados;
        limiteEdificaciones[0]= limiteSimples;
        limiteEdificaciones[1]= limiteSofisticadas;        
        interesHIpoteca= interesPOrHIpoteca;        
        recopilarHorasIniciales(horas);
        recopilarMinutosIniciales(minutos);
        recopilarSegundosIniciles(segundos);
        if(horas==0 && minutos==0 && segundos==0){
            tiempoLimite= false;//con esto del tiempo tb hubieras podido hacer, si antes había tiempo límite y ahora no, entonces pasa a ser 0, pero al pensarlo otra vez, de todos modos lo tengo que recibir porque puede que el tiempo cb asi que, mejor de una vez
        }else{
            tiempoLimite=true;
        }
        
        
    }      
    
    public void reemplazarUltimoLugarDondeSeHACreado(int indice){
        ultimoNumeroPanelAsignado=indice;
    }
    
    public void incrementarNumeroCasillasAsignadas(){
        numeroCasillasAsignadas++;
    }
    
    public void reestablecerNumeroFilas(int nuevoNumero){
        numFilas=nuevoNumero;
    }
    
    public void reestablecerNumeroColumnas(int nuevoNumero){
        numColumnas= nuevoNumero;
    }
    
    public Cola obtenerColaGuposLugares(){
        return pilaGruposLugar;
    }
    
    public ListaEnlazada obtenerListaGruposLugares(){
        return listaGruposLugar;
    }
    
    public int obtenerLimiteSimples(){
        return limiteEdificaciones[0];
    }
    
    public int obtenerLimiteSofisticadas(){
        return limiteEdificaciones[1];
    }
    
    public Cola obtenerColaGuposServicios(){
        return pilaGruposServicio;
    }
    
        
    public ListaEnlazada obtenerListaElementosServicios(){
        return listaElementosServicio;
    }
    
    public Cola obtenerColaGuposEstacion(){
        return pilaGruposEstacion;
    }
    
    public ListaEnlazada obtenerListaElementosEstaciones(){//Estos sustituirá a la pila
        return listaElementosEstacion;
    }
    
    public int obtnerInteres(){
        return interesHIpoteca;
    }
    
    public int obtenerDineroPorVuelta(){
        return dineroPorVuelta;
    }
    
    public int obtenerTurnoActual(){
        return turnoActual;
    }
    
    public int obtenerEstadoDados(){
        return estadoBotonDados;
    }
    
    public ListaCircular ObtenerRecorridoLogico(){
        return recorrido;        
    }
    
    public ListaEnlazada ObtenerRecorridoFisico(){
        return recorridoFIsico;        
    }
    
    public int obtnerHorasRestantes(){
        return horasRestantes;
    }
    
    public int obtnerMInutosRestantes(){
        return minutosRestantes;
    }
    
    public int obtnerSegundosRestantes(){
        return segundosRestantes;
    }
    
    public int obtenerHorasIniciales(){
        return horasIniciales;
    }
    
    public int obtenerMinutosIniciales(){
        return minutosIniciales;
    }
    
    public int obtenerSegundosIniciales(){
        return segundosIniciales;
    }
    
    public int obtnerTIpoTIempo(){
        return tipoTiempo;
    }
    
    public int obtnerNumeroFilas(){
        return numFilas;
    }
    
    public int obtenerNumeroColumnas(){
        return numColumnas;
    }
    
    public int obtenerDineroInicial(){
        return dineroInicial;
    }
    
    public boolean obtenerModoDelJuego(){
        return modoRestringido;
    }
    
    public int obtnerNumeroCasillasAsignadas(){
        return numeroCasillasAsignadas;
    }
    
    /**
     * Es decir el número de panel que le fue dado
     * al ser creada la cuadrícula
     * @return
     */
    public int darUltimoLugarDeCasillaCreada(){
        return ultimoNumeroPanelAsignado;
    }
}
