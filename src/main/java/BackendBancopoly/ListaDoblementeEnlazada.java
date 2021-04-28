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
public class ListaDoblementeEnlazada<T> implements Serializable{
    private NodoDoble<T> primero;
    private NodoDoble<T> ultimo;
    T contenido;
    int tamanio;         
    
    public ListaDoblementeEnlazada(){
        crearLista();
    }
    
    public void crearLista(){
        primero=ultimo=null;
    }
    
    /**
     *  Este método es solo para hacer crecer a la lista conforme vayan llegando cosas 
     *  sin considerar nada más
     * @param contenido
     */
    public void anadirAlFinal(T contenido){//de forma predefinida añadir es hacia la derecha
        if(estaVacia()){//pues solo tiene un dije no tiene a nadie más a su alrededor entonces no debo definir siguiente ni ultimo
            primero = new NodoDoble(contenido);
            ultimo=primero;
        }else{
            NodoDoble<T> nodoAuxiliar=ultimo;//pues el último es quien contiene los enlaces acumulados y como no apunta a último
            //como tal sino a lo que estaba apuntando último, entonces este sería el único que haría referencia a lo que en último se referenciaba
            ultimo = new NodoDoble(contenido, null, nodoAuxiliar);
            nodoAuxiliar.siguiente=ultimo;            
        }
        
        tamanio++;
        
    }
    
    public void anadirAlInicio(T contenido){
        if(estaVacia()){//pues solo tiene un dije no tiene a nadie más a su alrededor entonces no debo definir siguiente ni ultimo
            primero = new NodoDoble(contenido);
            ultimo=primero;
        }else{
            NodoDoble<T> nodoAuxiliar=primero;//pues el último es quien contiene los enlaces acumulados y como no apunta a último
            //como tal sino a lo que estaba apuntando último, entonces este sería el único que haría referencia a lo que en último se referenciaba
            primero = new NodoDoble(contenido, nodoAuxiliar, null);
            nodoAuxiliar.anterior=primero;            
        }        
        
        tamanio++;
    }
    
    public void anadirDespuesDe(T elementoAAnadir, NodoDoble<T> nodoReferencia){
        if(nodoReferencia.siguiente==null){
            anadirAlFinal(elementoAAnadir);
        }else{
            NodoDoble<T> nuevoNodo;
            NodoDoble<T> auxiliar;                       
            auxiliar=nodoReferencia.siguiente;
                           
            nuevoNodo = new NodoDoble(elementoAAnadir, auxiliar, nodoReferencia);//quiere decir que estoy insertando luego del nodo al que es < su valor, por la primera condi de ser >=                           
            nodoReferencia.reestablecerSiguiente(nuevoNodo);//debería funcionar correctamente en la parte de arriba porque de todos modos está haciendo referencia a la misma instancia entonces si dicha instancia cb en algp tb lo hará ña referencia a ella
            auxiliar.reestablecerAnterior(nuevoNodo);                          
        
            incrementarTamanio();   
        }        
             
        //recuerda que no necesitas devolver nada por el hecho de que estás modificando directamente a la referencia que recibiste por ello cuando salgas de aquí ahora 
        //a lo que originalmente apuntaba aquel que te dio la ref ahora apunta a ese lugar pero con las modificaciones hechas aquí        
    }
    
    public void anadirAntesDe(T elementoAAnadir, NodoDoble<T> nodoReferencia){
        if(nodoReferencia.anterior==null){
            anadirAlInicio(elementoAAnadir);
        }else{
            NodoDoble<T> auxiliar;                       
            auxiliar=nodoReferencia.anterior;//realamente esto no era necesario pero para que fuera más claro... (pues solo debías colocar nodoAuxiliar.anterior solo que antes de modif al nodoauxi su ant porque sino ya no iba a salir bien)
                           
            NodoDoble<T> nuevoNodo = new NodoDoble(elementoAAnadir, nodoReferencia, auxiliar);
                           
            auxiliar.reestablecerSiguiente(nuevoNodo);
            nodoReferencia.reestablecerAnterior(nuevoNodo);   
                           
            incrementarTamanio();
        }                
    }
    
    public ListaDoblementeEnlazada<T> convertirALista(T arreglo[]){
        ListaDoblementeEnlazada listado = new ListaDoblementeEnlazada();
        
        for(int numeroElemento = 0; numeroElemento < arreglo.length; numeroElemento++) {
            listado.anadirAlFinal(arreglo[numeroElemento]);
        }
        
        return listado;
              
    }//si funciona xD ahora ya sabes que puedes involucrar a un objeto en una operación de su misma clase
    
    public T darElementoEn(int numeroNodo){//comienza desde 0, entonces al ser 0 retornará el inicio de una vez
        NodoDoble<T> nodoAuxiliar= primero;
        
        for (int nodoBuscado = 0; nodoBuscado < numeroNodo; nodoBuscado++) {
            nodoAuxiliar=nodoAuxiliar.siguiente;
        }
        
        return nodoAuxiliar.contenido;
    }
    
    /**
     * Este método recibe el 1er númerod el nodo, que corresponde al que tendrá a un nuevo siguiente
     * y el 2do número de nodo quien será el que tendrá un nuevo anterior
     * 
     *
     * @param listadoOriginal
     * @param desde
     * @param hasta
     */
    public void eliminarPorIntervalo(ListaDoblementeEnlazada<T> listadoOriginal, int desde, int hasta){
        //aquí se navegará con un for hasta el número antes que corresponde al 1er nodo y luego a partir del siguiente de ahí se guardará en 
        //un nodo auxiliar para que al llegar al número antes del nodo indicado se guarde y así se hagan las nuevas asignaciones al listado originarl
        //que se quedó en el primer parámetro y se devolverá el nuevo listado...
        NodoDoble<T> nodoAuxiliar;//se queda un nodo antes desde el cual se desea borrar
        NodoDoble<T> nodoAuxiliar2;//se queda en el nodo hasta el que se desea borrar
        nodoAuxiliar = listadoOriginal.primero;      
        
        for (int numeroNodo = 1; numeroNodo < desde-1; numeroNodo++) {//estos métodos trabajan tomando en cuenta que reciben los datos corrctos, es decir que tú afuera debes encargarte de que esto suceda
            nodoAuxiliar = nodoAuxiliar.siguiente;            
        }
            nodoAuxiliar2= nodoAuxiliar;
            
        for (int numeroNodo = 0; numeroNodo < (hasta-desde)+1; numeroNodo++) {//pues se quedó justo donde debe partir y se debe unir la lista nueva con el siguiente del nodo hasta el cual se quiere eliminar
            nodoAuxiliar2=nodoAuxiliar2.siguiente;
        }
        
        
        nodoAuxiliar.siguiente=nodoAuxiliar2.siguiente;
        nodoAuxiliar2.anterior=nodoAuxiliar;    
    }//y así cuando se use nuevamente el listado ya se tendrá hecha la modificación pues se están manipulando los datos que al principio este tenía
    
  
    
    
    public boolean estaVacia(){//pues con que el primero esté nulo el último igual...
        return primero==null;
    }
    
    public NodoDoble<T> obtnerInicio(){
        return primero;
    }
    
    public void incrementarTamanio(){
        tamanio++;
    }
    
    public int darTamanio(){
        return tamanio;
    }
    
}
