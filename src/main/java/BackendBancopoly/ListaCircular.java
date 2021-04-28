/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackendBancopoly;

import Tarjetas.Tarjeta;
import java.io.Serializable;

/**
 *
 * @author phily
 */
public class ListaCircular<T> implements Serializable{//el inicio es "inmutable", media vez se especifique, aún en el proceso de edición, puesto que, estos listados son inmutables y por ello se crea uno nuevo cada vez que ya existiera una su versión ant establecida
    NodoDoble<T> inicio;//en este caso no agrego nodo ultimo porque no requiero ordenar por el hecho de haber dejado el proceso en el cual se va creadno la tjt junto con el recorrido y por ello según ingresen ese será el orden
    NodoDoble<T> ultimo;
    T contenido;    
    private int tamanio;    
    
    public ListaCircular(){
        inicio=null;
        establecerPseudoUltimoEnEntrar();
    }
    
     public void insertarSiguiente(T contenidoAInsertar){         
         
         if(inicio==null){
             inicio= new NodoDoble<T>(contenidoAInsertar);
             inicio.siguiente=inicio;//Aquí cierro el circulo, lo cual siempre lo tendré que hacer, al final me decidií cerrarlo desde el principio, para que la búsqueda no requiriera de un fin y así empleo anterior si fuese necesario xD                         
             inicio.anterior=inicio;
         }else{
             NodoDoble<T> nodoAuxiliar=inicio;
             
             while(nodoAuxiliar.siguiente!=inicio){//no dará problemas... por el hecho de que existen funciones especificas para comparar objetos, si es así, mejor usa el equals, así como explicaba el inge en la última clase
                 nodoAuxiliar=nodoAuxiliar.siguiente;
             }             
             nodoAuxiliar.siguiente=new NodoDoble<>(contenidoAInsertar, inicio, nodoAuxiliar);//pues se quedó en la posición anterior, debido al while                         
             inicio.anterior=nodoAuxiliar.siguiente;// pues solo esta parte es la que se destruye al add pues el nuevo ya
             //NOTA [LEELA]: esta es la lista circular y como arriba se indica esta es la parte que add la parte que se había destruido, si diera problemas solo pasa el new NodoDoble... de arriba en lugar de nodo auxiliar.siguiente
             //Aunque sería prácticamente lo mismo puesto que el nA.sig es igual a new... entonces talvez la solución sea, usar directamente el inicio en lugar de un auxiliar para realizar el proceso anterior, eso talvez evite que se estén 
             //creando casi infinitos siguientes, en el listado
             //tiene su anterior fijo, pero el siguiente no lo es mientras se sigan add, y por ello el anterior del inicio tampoco es fijo sino que será siempre el nuevo add
         }            
         
         //aquí iría el pseudo inicio si no llegara a rsfresacar las ligadura del inicio sin necesidad de estar aquí... lo cual dudo mucho que suceda porque ya tiene un enlace con el Nodo en cuestión
         tamanio++;//me será últil para el método de borrar hasta
    }
     
     public void establecerPseudoUltimoEnEntrar(){
         ultimo=inicio;//veamos si sucede lo que quiero, como yo estoy haciendo referencia al inicio entonces cada vez que se le actualicen sus respectivos sig y ant, como están ligados a él, entonces debería actualizarse el del inicio que el nodo guardó
         //sino llegara a suceder entonces deberé poner a este método dentro de insertar sigueinte, antes de tamaño, para que cada vez vaya actualizando lo ligado al inicio
     }
     
     public void sacarEnFormaPila(){
         ultimo.obtenerAnterior();//como se partirá desde el inicio[que debe ser la última tjt en salir, entonces se comenzará a sacar de forma correcta]        
     }
     
    /**
     * Revisa si la tjt sacada es una de tipo SC, ya que si es así por el hecho de ser entregada al jugador respectivo, ya no 
     * tiene nada que hacer en la pila, porque sino sería un fantasma Buuuu xD, entonces por ello deberá revisar luego de haber
     * dado la tjt y haber exe su acción determinada, por si acaso la tjt llegara a ser la primera, o solo fuera una pila de  tjt's de 
     * este tipo, por ello deberá ser llamado a parte por el hecho de que debe retornar el método que da el contenido de los nodos
     */
    public void revisarSiEsSalirCarcel(){
         Tarjeta tarjeta = (Tarjeta)ultimo.obtnerContenido();//aunque no me parece muy bien hacer eso... pero tampoco que este y el método completo para hacer girar la lista, se haga en otra clase, puesto que es un comportamiento de la lista...
         if(tarjeta.obtenerNombre().equalsIgnoreCase("SalgaCarcel")){
             //el recerramiento de la lista xD, donde se saca a la tjt de aquí y no se perderá
             //Su referencia porque ya habrá sido pasada al jugador respectivo
         }
     }
     
    /**
     *Este será el método invocado en la acción de la casilla tomaTjt para luego obtener la tjt y así exe su acción
     * respectiva [recuerda que la tjt de salga de la cárcel, en su método poseerá al jugador con su método obtner
     * tjtSC, donde se le enviará como parám la tjt misma, por medio de su método y así se hará el psae de la tjt]
     * @return
     */
    public T obtnerContenidoDelUltimo(){        
        sacarEnFormaPila();
         return ultimo.obtnerContenido();         
     }
    
    public NodoDoble obtnerInicio(){
        return inicio;
    }
    
    public void sacarElementoDelListado(){
        if(inicio.obtnerContenido()==ultimo.obtnerContenido()){
            inicio=inicio.obtnerSiguiente();//pues si no, de ahí en ad estaría apuntando a null
        }else{
             NodoDoble nodoAuxiliar = ultimo.obtnerSiguiente();
             ultimo.obtenerAnterior().reestablecerSiguiente(nodoAuxiliar);
             ultimo=nodoAuxiliar;//para que se pierda completamente la referencia de dicho nodo, al menos aquí en la lista            
        }//ahí revisas como trabaja esto, porque si recuerdas al debuggear cada uno tiene el listado de antes en su anterior, lo cual se vuelve  
        //a repetir, casi de forma indefinida, entonces si esto sucede, lo que estarás haciendo es que solamente se quite la referencia de esa
        //tjt en ese pedazo, pero todas las demás seguirán teniendo la referencia a ella
    }
     
     public void eliminarHasta(int indiceBorrado){//Este es obtenido del nombre del lbl del panel Seleccionado
         NodoDoble<T> auxiliarELiminacion= inicio; 
         
         if(indiceBorrado>tamanio/2){
             for (int anteriorBorrado = tamanio; anteriorBorrado>=indiceBorrado-1 ; anteriorBorrado--) {//si el for decrece o aumenta su contador, es arbitrario, puesto que lo único que requiero es avanzar hasta el anterior al que se borrará, para recerrar xD el círculo
                 auxiliarELiminacion=auxiliarELiminacion.anterior;
             }
             
             auxiliarELiminacion.reestablecerSiguiente(inicio);//Siempre, pues se corta hasta el primer anterior al inicio... entonces el que se salvó pararía a ser su nuevo anterior
             inicio.anterior=auxiliarELiminacion;//pues es el salvado de la eli         
         }else{
             for (int anteriorBorrado = 0; anteriorBorrado < indiceBorrado; anteriorBorrado++) {
                 auxiliarELiminacion=auxiliarELiminacion.siguiente;
             }
             
             auxiliarELiminacion.reestablecerSiguiente(inicio);
             inicio.anterior=auxiliarELiminacion;
         }
     }
     
     public void irA(int indiceAIr){//Este es por las tjtas que dicen vaya a la casilla 3 o dan el nombre, pero como las casillas del siwing tienen indice entonces al encontrar el nombre tengo el índice, con el cual ya podré referenciar aquí
         //recuerda, si la casilla esta cerca del tamaño entonces usa el método para anterior
         //y si esta cerca del inicio entonces el de siguiente
         NodoDoble<T> auxiliarLlegada= inicio; 
         
         if(indiceAIr>tamanio/2){
             for (int lugarLlegar = tamanio; lugarLlegar >indiceAIr ; lugarLlegar--) {
                 auxiliarLlegada=auxiliarLlegada.anterior;//aqui estoy 1 antes de llegar, luego de colocarse en la posición correcta deberías hacer algo, dependiendo de la casilla, no se si sea mejor quedarme aqui hasta 1 antes o llegar de una              
             }                                                              //lo cual sería hacer referncia a dicha casilla desde jugador para que se despliegue una acción y responda conforme a ella, esto haciendo aqupi referncia al método acción de la casilla que 
                                                                            //rxn como corresponda (esto al hacer siguiente.accion y así se exe el proceso 
             
         }//fin del if para cuando el valor se pasa de la mitad, lo caul ahorra tiempo
         else{
              for (int lugarLlegar = 0; lugarLlegar < indiceAIr; lugarLlegar++) {
                 auxiliarLlegada=auxiliarLlegada.siguiente;
             }
             
         
         
         }
         
     }//NO EMPLEADO POR EL MOMENTO  
    
}

//LEE LO DEL PRIMER PÁRRAFO DE ABAJO
//hay que tener una excepción (es decir condi) de que cuando el contenido del último sea = a tjtSC, el listado se recerrará
//para excluirla, puesto que ya se habrá enviado, [luego de haber obtenido la tjtj por el metodo obTjtjU de aquí]
//al ugador entonces ya no tiene que estar en el listado, esta revisión se hará antes de cada envío, y por ello la sacada 
//se hará cuando se quiera obtener la sig tjt a la de SC

//EN GENERAL SIEMPRE HABRÁN DOS MÉTODOS PARA CADA ACCIÓN QUE IBNVOLUCRE RECORRER LA LISTA, uno para siguiente (si conviene o si es el nec) y otro para atras (de = forma que antes)





