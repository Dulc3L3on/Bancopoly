/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackendBancopoly;

import java.io.Serializable;

/**
 *
 * @author phily
 */
public class ListaEnlazada<E> implements Serializable{
    private Nodo<E> primerNodo;//posee el primero objeto puesto que así se sabe de donde partir
    private Nodo<E> ultimoNodo;//obtiene el último elemento, el cual de forma directa ayuda a saber si tiene o no elementos
    private String nombreLista;//podría tener nombre,, solo debes pensar como se lo asignarás
    private int tamanioLista;
    private int tamanioFinal;//Esta var será útil para las propiedades, pues esta contiene el número de elementos totales que contiene un grupo, en este caso almacenado en una lista
    //private Casilla casillaAnadida;
    
    public ListaEnlazada(){
        crearListar();
    }
    
    public ListaEnlazada(String nombreLista){//Este será empleado para las propiedades del jugador
        crearListar();
        establecerNOmbre(nombreLista);
    }
    
    public void crearListar(){
        //si tine nombre aquí deberías indicarlo
        primerNodo=ultimoNodo=null;//porque no tienen ningun elemento
    }
    
    public void establecerNOmbre(String nombre){
        nombreLista=nombre;
    }
    
    public void establecerTamanioLimite(int limite){
        tamanioFinal=limite;
    }
    
    public void anadirAlFinal(E elementoInsertar){
        if(estaVacia()){
            insertarAlInicio(elementoInsertar);
            tamanioLista++;
        }        
        else{
             ultimoNodo=ultimoNodo.nodoSiguiente= new Nodo<E>(elementoInsertar);
             tamanioLista++;
        }
        
    }
    
    /**
     * //este era la idea original cuando pensé en la lista super enlazada... pues quería crear los nodos pero sin contenido para después irlos add conforme fueran llegando
     * @param numeroNodos
     */
 /*   public void simularArreglo(int numeroNodos){//Este será empleado para el proceso de asignaciónde propiedades, siendo la lista enlazada que emplea este método, la que viene a comportarse como un arreglo
         for (int cantidadElementos = 0; cantidadElementos < numeroNodos; cantidadElementos++) {
             anadirAlFinal(null);//así puedo revisar estos nodos y saber si debo crear o no la lista correspondiente al grupo si encuentro un null o no (de manera respectiva)
         }        
    }//aunque tb hubiera podido hacer algo así como si no encontraba un grupo igual[al recorrer el listado que tenía el tam según el número de asignados], entonces que insertara al final, de una vez la lista reciente, pero Na! me gusta más esta forma xD*/
     
     
    /*public void agregarContenidoEn(ListaEnlazada<E> listaDeInsercion, Casilla elementoAinsertar, int lugarDeInsercion){
        Nodo<E> nodoAuxiliar;
            nodoAuxiliar=listaDeInsercion.obtnerPrimerNodo();
            ListaEnlazada<Propiedad> listaGruposDeLugar;
        
        for (int indice = 0; indice < lugarDeInsercion-1; indice++) {//vamos a hacerlo de la otra forma xD, sino da pues entonces ve a jalarte la anterior
            nodoAuxiliar=nodoAuxiliar.obtenerSiguiente();            
        }//Si pues al tener al primer nodo se le resta 1 al proceso, de hallar siguientes
        
            if(nodoAuxiliar.obtenerObjectcEnCasilla()==null){
                 listaGruposDeLugar= new ListaEnlazada();
                 listaGruposDeLugar.establecerNOmbre();
            }
            
            
    }*/
     
     
     /*public void agregarCOntenidoEn(Nodo<E> nodoActual, E contenido, int lugarDeInsercion){//en realidad nodo actual es el nodo inicial el cual será enviado por la lista en cuestión
         for (int numeroIndice = 0; numeroIndice < lugarDeInsercion-1; numeroIndice++){
             nodoActual=nodoActual.obtenerSiguiente();             
         }
         
         if(nodoActual.obtenerObjectcEnCasilla()==null){
             ListaEnlazada<Casilla> subgrupoPropiedad = new ListaEnlazada();             
             nodoActual.establecerContenido(subgrupoPropiedad);
         }
         nodoActual.establecerContenido(contenido);
     }*/
    
   
    
    /**
     * Empleado al: editar una casilla que ya se encontraba definida con anterioridad, ya sea en crear o al editarla
     *                            [espaecíficamente empleado por el mapa, al menos por el momento] 
     * Encargado de dejar sin ninguna referencia hacia o del objeto en cuestión para reemplazarlo
     * por el objeto encontrado en el indice correspondiente al arreglo de objetos del panel, 
     * numero que corresponderá con el "índice" en el que se ha colocado el nodo en la lista, 
     * @param indiceObjetoELiminar
     */
    public void sustiruirEn(int indiceObjetoELiminar, E elementoSustitucion){//Es decir que la lista se crea de la misma manera en que el panel asigna los indices a sus componentes
        Nodo<E> nodoActual=primerNodo;
        int indiceActual=0;
        
        while(indiceActual<indiceObjetoELiminar || indiceObjetoELiminar==0){
            if(indiceObjetoELiminar==0){//este podría convertirse en el método insertarAlInicio
                insertarAlInicio(elementoSustitucion);
            }
            
            if(indiceActual==indiceObjetoELiminar-1 && nodoActual.obtenerSiguiente().obtenerSiguiente()==null) {//este podría volverse un método insertarAlFinal
                ultimoNodo=nodoActual.nodoSiguiente= new Nodo<E>(elementoSustitucion);
            } else if(indiceActual==indiceObjetoELiminar-1){//Esto podría convertirse en un método insertarEn... pues siempre será así
                Nodo<E> cadenaNodosPreservar;
                cadenaNodosPreservar=nodoActual.obtenerSiguiente().obtenerSiguiente();
                nodoActual.nodoSiguiente=new Nodo<E>(elementoSustitucion,cadenaNodosPreservar);//presrvo la cadena al tomarla a partir de donde quiero manternerla y asignarsela al nodo siguiente del sustituidor
            }
            
            //pregunta: en este caso no es necesario cambiar la referencia del último nodo?... yo diría que no pues es una ref al objeto no al "lugar" que opcupa el objeto en la lista... pues realmente no existe un lugar físico como tal
            indiceActual++;
        }               
    }//Este es creado puesto que en realidad al modificar la casilla, esta se reemplazará y no actualizará por la nueva, esto para evitar estar revisando que modifico y que no
    
    
    public void eliminarUltimo(){
         Nodo<E> nodoAuxiliar;
         nodoAuxiliar = primerNodo; 
         
         if(primerNodo==ultimoNodo){
             primerNodo=ultimoNodo=null;
         }else{
             while(nodoAuxiliar.obtenerSiguiente()!=ultimoNodo){//si no sale bien es porque no se está comprando de forma correcta
                nodoAuxiliar=nodoAuxiliar.obtenerSiguiente();
            }
             
             ultimoNodo=nodoAuxiliar;
             nodoAuxiliar.nodoSiguiente=null;
         }
        
        
    }
    
    public E retornarUltimoELemento(){
        return ultimoNodo.obtenerObjectcEnCasilla();
    }
    
    public void insertarAlInicio(E elementoInsertar){
        primerNodo=ultimoNodo= new Nodo<E>(elementoInsertar);
    }        
    
    //public void insertarNuevaColumna(int numeroColumnaAnterior){
        
        
    //}//es que no se puede llamar así... pues estos métodos deben ser generales

    /**
     * Ya no fue empleado porque al momento de mandar el contenido, 
     * en este caso la casilla física, se hubiera mandado a todos los nuevos nodos
     * la misma referencia y por lo tanto cuando uno de ello recibiera cambio
     * lo demás también lo hubieran hecho
     * 
     * @param valor
     * @param desde
     * @param lista
     */
    
    public void insertarACada(int valor, int desde, ListaEnlazada<E> lista){//recuerda los números se recibirán según el usuario los ve
        Nodo<E> nodoAuxiliar=lista.primerNodo;
        int tamanioListadoOriginal=lista.darTamanio();         
        
        for (int numeroNodo = 1; numeroNodo < desde; numeroNodo++) {            
                nodoAuxiliar=nodoAuxiliar.nodoSiguiente;            
        }
        
        while(desde<=tamanioListadoOriginal){//para hacerlo más general se hará que el while se detenga si el valor el mayor al número al tamaño original
            //inserto sigueitne
            
            //me posiciono en el siguiente
            
            //le sumo a "desde" 1 hasta llegar a sumarle el número == valor y al sumar también avanzo al siguiente nodo, eso si siempre debo revisar que no me he pasado del límite
            //ya sea corroborando hasta que número llegaré al sumarle al valor de desde el "valor" y si no se pasa del tamaño original entonces procedo a avanzar...
            
        }//debe ser <= por el hehco de que iremos con los números según los ve el usuario [es decir iniciando desde 1]
        
        
    }
    
     public void nuevoNodoDespuesDe(Nodo<E> nodoAnteriorInsercion, E contenido){
        Nodo<E> nodoAuxiliar = nodoAnteriorInsercion.nodoSiguiente;
        Nodo<E> nodoNuevo;
        
        nodoNuevo= new Nodo<>(contenido, nodoAuxiliar);
        nodoAnteriorInsercion.nodoSiguiente=nodoNuevo;
    }
    
    /**
     * Este es el método que se encarga de recibir el nodo desde donde se debe comenzar
     * a avanzar y luego se ubica en el nodo donde se quiere la inserción para luego add
     * [bien hubiera podido ser recursiva esta función, para que en lugar del return se llamase
     * a sí misma y parase cuando el nodo inicial fuera null, pero lo que sucede es que al principio no
     * se recorre la misma cantiada que después de haber insertado el primer nuevo nodo, por eso no 
     * lo hice así...]
     * 
     * @param nodoInicial
     * @param hasta
     * @param contenido
     * @return
     */
    public Nodo<E> avanzarHastaEInsertar(Nodo<E> nodoInicial, int hasta, E contenido){//será el mérodo empleado para añadir una nueva casilla al incrementar columnas
        for (int paso = 0; paso < hasta; paso++) {
            nodoInicial=nodoInicial.nodoSiguiente;//lego hasta el nodo en el que debe insertarse un nuevo siguiente
        }
        
        nuevoNodoDespuesDe(nodoInicial,contenido);//agrego e nuevo contenido
        nodoInicial=nodoInicial.nodoSiguiente;//me ubico en el nodo recién creado para devolverlo y así se pueda trabajar con él
        
        return nodoInicial;        
    }    
    
    
    public boolean estaVacia(){
        return primerNodo==null;
    }
    
    public Nodo<E> obtnerPrimerNodo (){
        return primerNodo;
    }
    
    public Nodo<E> obtenerUltimoNodo(){
        return ultimoNodo;
    }   
    
    public int darTamanio(){
        return tamanioLista;
    }
    
    public int darTamanioLimite(){// "límite" para el caso de los grupos de las proiedades
        return tamanioFinal;
    }
    
    public String obtenerNombre(){
        return nombreLista;
    }
    
    
    //faltan los métodos correspondientes a la edición, como insertar la columna o fila (columna requiere de la última casilla que se encuentre en ella para add a parti de dicha casilla, de forma análoga para las filas
}
