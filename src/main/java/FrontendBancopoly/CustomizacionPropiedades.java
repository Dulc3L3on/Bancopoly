/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontendBancopoly;

import BackendBancopoly.Nodo;
import Casillas.Casilla;
import Casillas.Lugar;
import java.awt.event.KeyEvent;
import javax.swing.*;

/**
 *
 * @author phily
 */
public class CustomizacionPropiedades extends javax.swing.JDialog {
        JPanel casillaResidencia;
        Casilla casilla;//será útil para el envío de la casilla al método de customización principal
        Casilla casillaCreada;
        String tipoPropiedad;       
                
      /**
     * Creates new form CustomizacionPropiedades
     */
    public CustomizacionPropiedades(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public void recibirCasillaResidencia(JPanel casillFisica){
        casillaResidencia=casillFisica;        
    }   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JPn_propiedadesGenerales = new javax.swing.JPanel();
        lbl_titulo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtF_nuevoGrupo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cbBx_GruposExistentes = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtF_nombreLugar = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        FtxtF_pagoEstancia = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        FtxtF_pagoCompra = new javax.swing.JFormattedTextField();
        btn_aceptarServicioEstacion = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        FtxtF_pagoSimples = new javax.swing.JFormattedTextField();
        lbl_precioSImple = new javax.swing.JLabel();
        lbl_precioSofisticada = new javax.swing.JLabel();
        FtxtF_pagoSofisticadas = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(511, 483));
        setMinimumSize(new java.awt.Dimension(511, 483));
        setResizable(false);

        JPn_propiedadesGenerales.setBackground(new java.awt.Color(83, 77, 71));
        JPn_propiedadesGenerales.setMaximumSize(new java.awt.Dimension(511, 483));
        JPn_propiedadesGenerales.setMinimumSize(new java.awt.Dimension(511, 483));

        lbl_titulo.setBackground(new java.awt.Color(247, 173, 99));
        lbl_titulo.setFont(new java.awt.Font("Sawasdee", 1, 18)); // NOI18N
        lbl_titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_titulo.setText("<<LUGARES>>");
        lbl_titulo.setOpaque(true);

        jLabel3.setForeground(new java.awt.Color(230, 218, 218));
        jLabel3.setText("Nuevo grupo: ");

        txtF_nuevoGrupo.setText(" ");
        txtF_nuevoGrupo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtF_nuevoGrupoKeyPressed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(230, 218, 218));
        jLabel4.setText("Miembro del Grupo:");

        jLabel5.setForeground(new java.awt.Color(230, 218, 218));
        jLabel5.setText("Nombre del lugar :");

        txtF_nombreLugar.setText(" ");

        jLabel6.setForeground(new java.awt.Color(230, 218, 218));
        jLabel6.setText("Por estancia :");

        FtxtF_pagoEstancia.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        jLabel7.setForeground(new java.awt.Color(230, 218, 218));
        jLabel7.setText("Por compra :");

        FtxtF_pagoCompra.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        FtxtF_pagoCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FtxtF_pagoCompraActionPerformed(evt);
            }
        });

        btn_aceptarServicioEstacion.setText("ACEPTAR");
        btn_aceptarServicioEstacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aceptarServicioEstacionActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(230, 218, 218));
        jLabel1.setForeground(new java.awt.Color(230, 218, 218));
        jLabel1.setText("Pagos");

        FtxtF_pagoSimples.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        lbl_precioSImple.setBackground(new java.awt.Color(230, 218, 218));
        lbl_precioSImple.setForeground(new java.awt.Color(230, 218, 218));
        lbl_precioSImple.setText("Por construccion simple:");

        lbl_precioSofisticada.setBackground(new java.awt.Color(230, 218, 218));
        lbl_precioSofisticada.setForeground(new java.awt.Color(230, 218, 218));
        lbl_precioSofisticada.setText("Pago por construccion lujosa");

        FtxtF_pagoSofisticadas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        javax.swing.GroupLayout JPn_propiedadesGeneralesLayout = new javax.swing.GroupLayout(JPn_propiedadesGenerales);
        JPn_propiedadesGenerales.setLayout(JPn_propiedadesGeneralesLayout);
        JPn_propiedadesGeneralesLayout.setHorizontalGroup(
            JPn_propiedadesGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_titulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(JPn_propiedadesGeneralesLayout.createSequentialGroup()
                .addGroup(JPn_propiedadesGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPn_propiedadesGeneralesLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(JPn_propiedadesGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPn_propiedadesGeneralesLayout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(JPn_propiedadesGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel6)
                                    .addGroup(JPn_propiedadesGeneralesLayout.createSequentialGroup()
                                        .addComponent(lbl_precioSImple)
                                        .addGap(41, 41, 41)
                                        .addComponent(FtxtF_pagoSimples, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(JPn_propiedadesGeneralesLayout.createSequentialGroup()
                                        .addComponent(lbl_precioSofisticada)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(FtxtF_pagoSofisticadas)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPn_propiedadesGeneralesLayout.createSequentialGroup()
                                .addGroup(JPn_propiedadesGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(JPn_propiedadesGeneralesLayout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(52, 52, 52)
                                        .addComponent(txtF_nuevoGrupo))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPn_propiedadesGeneralesLayout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(34, 34, 34)
                                        .addComponent(cbBx_GruposExistentes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(JPn_propiedadesGeneralesLayout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                                        .addComponent(txtF_nombreLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPn_propiedadesGeneralesLayout.createSequentialGroup()
                                        .addGroup(JPn_propiedadesGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(JPn_propiedadesGeneralesLayout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPn_propiedadesGeneralesLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(FtxtF_pagoEstancia, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel7)
                                        .addGap(4, 4, 4)
                                        .addComponent(FtxtF_pagoCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(8, 8, 8)))
                                .addGap(67, 67, 67))))
                    .addGroup(JPn_propiedadesGeneralesLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_aceptarServicioEstacion)
                        .addGap(45, 45, 45)))
                .addContainerGap())
        );
        JPn_propiedadesGeneralesLayout.setVerticalGroup(
            JPn_propiedadesGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPn_propiedadesGeneralesLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lbl_titulo)
                .addGap(35, 35, 35)
                .addGroup(JPn_propiedadesGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtF_nuevoGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(JPn_propiedadesGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbBx_GruposExistentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(JPn_propiedadesGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtF_nombreLugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(JPn_propiedadesGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(JPn_propiedadesGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(FtxtF_pagoEstancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(FtxtF_pagoCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JPn_propiedadesGeneralesLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)))
                .addGap(18, 18, 18)
                .addGroup(JPn_propiedadesGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FtxtF_pagoSimples, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_precioSImple))
                .addGap(18, 18, 18)
                .addGroup(JPn_propiedadesGeneralesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_precioSofisticada)
                    .addComponent(FtxtF_pagoSofisticadas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(btn_aceptarServicioEstacion)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPn_propiedadesGenerales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPn_propiedadesGenerales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_aceptarServicioEstacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_aceptarServicioEstacionActionPerformed
        //recuerda que deberás hacer que el btn se active cuando todo esté lleno y que además no se pueda preionar la x, sería bonito que al presionar la x se mostrara el diálogo anterior, esto no provocaría problemas 
        //con el listado circular, ya que si cierra no se guarda nada y tampoco se crea el objt, por lo cual hasta que presione aceptar (para cualquiera de customizacion de las casillas [excepto vaya a la carcel, cárcel, que con solo aceptar
        //En el diálogo general se crea el obj y por tanto se llega a la línea donde se asigna dicho obj a la lista circular
        
         //se establecerá el número de lugares que el subgrupo posee, esto al buscar el string seleccionado del cbBx en el listado de subgrupos del registro para cuando se encuentre, entonces usar el método del nodo que hace el decido incremento
         //al numero de elemntos en nodo que en este caso vendrían a ser los lugares del subgrupo que tiene que tener para completar dicho subgrupo y así poder construir
         Nodo<String> nodoAuxiliarRegistro;
         nodoAuxiliarRegistro= SolicitudDatos.registroDatos.obtenerListaGruposLugares().obtnerPrimerNodo();
         
         for (int numeroGrupos = 0; numeroGrupos < SolicitudDatos.registroDatos.obtenerListaGruposLugares().darTamanio(); numeroGrupos++) {
            if(cbBx_GruposExistentes.getSelectedItem().equals(nodoAuxiliarRegistro.obtenerObjectcEnCasilla())){
                nodoAuxiliarRegistro.incrementarNumeroElementosNodo();
                break;
            }else{
                nodoAuxiliarRegistro=nodoAuxiliarRegistro.obtenerSiguiente();
            }
        }
        
         //mandar a llamar a la casilla, para crearla
         this.dispose();
         Casilla casillaLugar = new Lugar(casillaResidencia, SolicitudDatos.registroDatos.obtnerInteres(), Integer.parseInt(FtxtF_pagoSimples.getText().trim()),
         Integer.parseInt(FtxtF_pagoSofisticadas.getText().trim()), (String)cbBx_GruposExistentes.getSelectedItem(),  txtF_nombreLugar.getText().trim(),
         Integer.parseInt(FtxtF_pagoEstancia.getText().trim()), Integer.parseInt(FtxtF_pagoCompra.getText().trim()));                
                
         casillaCreada=casillaLugar;
         SolicitudDatos.registroDatos.incrementarNumeroPropiedadesTotales();
        
        //registroTemporal.recopilarDatosCasillasPropiedad(txtF_nuevoGrupo.getText(), txtF_nombreLugar.getText(), Integer.parseInt(FtxtF_pagoEstancia.getText()));

    }//GEN-LAST:event_btn_aceptarServicioEstacionActionPerformed

    private void FtxtF_pagoCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FtxtF_pagoCompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FtxtF_pagoCompraActionPerformed

    private void txtF_nuevoGrupoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtF_nuevoGrupoKeyPressed

        String ingresado=txtF_nuevoGrupo.getText().trim();
        boolean existe=false;
        
       if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           if(cbBx_GruposExistentes.getItemCount()==0){
                cbBx_GruposExistentes.addItem(ingresado);
            }else{
                for (int elementos = 0; elementos < cbBx_GruposExistentes.getItemCount(); elementos++) {//Si es 0 entonces no entraría
                    if(cbBx_GruposExistentes.getItemAt(elementos).equalsIgnoreCase(ingresado)){
                        existe=true;
                       //guardarListadoSegunTIpo(ingresado);//no debe ir aquí, porque está en el bloque que se exe cuando sucede lo que no queremos
                        break;
                    }//fin del if
                }              
            }
           
             if(!existe){//pues independientemente de si sea 0 o no, debe guardar el dato y agregar el ítem al cbBx
                    guardarListadosDeLugar(ingresado);
                    cbBx_GruposExistentes.addItem(ingresado);
                    cbBx_GruposExistentes.setSelectedItem(ingresado);//si sale algún error sería por esta parte... pero aquí solo se está cb el intem seleccionado, asi que no creo que vaya  a exe alguna acción puesto que eso solo lo hace con el btn y el ENTER
                    cbBx_GruposExistentes.repaint();
                    //aqupi se mandaría a llamar a registro para almacenar los grupos de cada propiedad, para que cuando seleccione un tipo de propiedad se le muestre SU listado
                    //para ello tendrás que poner identificadores[id] para saber para quien es el diálogo lo cual ayudará a mostrar el listado respect y por lo mismo asignar el ele al grupo de registro respect
                    //lo cual se habría solucionado si hubieras hecho un diálogo por cada tipo de porpiedad, como se repetían....aaa pero no son los mismos obj cada propiedad
                
                    //si no llegara a ponerse en el item ingresado recientemente haz aquí que lo haga
                }else{
                    txtF_nuevoGrupo.setText("");
                    JOptionPane.showMessageDialog(this, "Ya se encontraba en el listado", "Existente", JOptionPane.INFORMATION_MESSAGE);
                }
       }       
        
    }//GEN-LAST:event_txtF_nuevoGrupoKeyPressed

    /*public void guardarListadoSegunTIpo(String nuevoGrupo){
        switch(tipoPropiedad){
            case "Lugar":
                registro.recopilarGruposLugar(nuevoGrupo);
            break;
            
            case "Servicio básico":
                registro.recopilarGruposServicio(nuevoGrupo);
            break;
            
            case "Estación":
                registro.recopilarGruposEstacion(nuevoGrupo);
            break;
        }
    }*///con COLA
    
    public void guardarListadosDeLugar(String nuevoGrupo){      
                SolicitudDatos.registroDatos.agregarGruposDeLugares(nuevoGrupo);     
    }
    
    /**
     * 
     */
    /*public void establecerDatosDelCbBx(){//con pila
        cbBx_GruposExistentes.removeAllItems();
        
        switch(tipoPropiedad){
            case "Lugar":
                if(!registro.obtenerColaGuposLugares().estaVacia()){
                       for (int indice = 0; indice < registro.obtenerColaGuposLugares().darTamanio(); indice++) {
                           if(registro.obtenerColaGuposLugares().entregarElementos(indice)!=null){
                               cbBx_GruposExistentes.addItem((String)registro.obtenerColaGuposLugares().entregarElementos(indice));                       
                           }                           
                        }
                }//pues si lo está debería mostrar el cbBx vacío y no un null, así como al principio, de ahí en adelante para mostrar sus respectivos datos dependerá de registro
                
            break;
            
            case "Servicio básico":
                if(!registro.obtenerColaGuposLugares().estaVacia()){
                       for (int indice = 0; indice < registro.obtenerColaGuposServicios().darTamanio(); indice++) {
                           if(registro.obtenerColaGuposServicios().entregarElementos(indice)!=null){
                               cbBx_GruposExistentes.addItem((String)registro.obtenerColaGuposServicios().entregarElementos(indice));                       
                           }                           
                        }
                }
            break;
            
            case "Estación":
                if(!registro.obtenerColaGuposLugares().estaVacia()){
                        for (int indice = 0; indice < registro.obtenerColaGuposEstacion().darTamanio(); indice++) {
                            if(registro.obtenerColaGuposEstacion().entregarElementos(indice)!=null){
                                cbBx_GruposExistentes.addItem((String)registro.obtenerColaGuposEstacion().entregarElementos(indice));                       
                            }                           
                       }                              
                }   
            break;
        }
        
        cbBx_GruposExistentes.repaint();
    }*/
    
    public void establecerDatosDelCbBxConListas(){
      cbBx_GruposExistentes.removeAllItems();
      Nodo<String> nodoAuxiliar;//al final solo fue necesario 1     
        
           nodoAuxiliar=SolicitudDatos.registroDatos.obtenerListaGruposLugares().obtnerPrimerNodo();
                
           if(!SolicitudDatos.registroDatos.obtenerListaGruposLugares().estaVacia()){
                 for (int indice = 0; indice < SolicitudDatos.registroDatos.obtenerListaGruposLugares().darTamanio(); indice++) {//por el hecho de que al principio se obtuvo directamente el primer nodo, por lo cual se reduce en uno el buscar al siguiente
                    if(nodoAuxiliar.obtenerObjectcEnCasilla()!=null){
                    cbBx_GruposExistentes.addItem((String)nodoAuxiliar.obtenerObjectcEnCasilla());                       
                  }
                           
                nodoAuxiliar=nodoAuxiliar.obtenerSiguiente();
            }
        }//pues si lo está debería mostrar el cbBx vacío y no un null, así como al principio, de ahí en adelante para mostrar sus respectivos datos dependerá de registro  
        
         txtF_nuevoGrupo.setText("");
        cbBx_GruposExistentes.setSelectedIndex(cbBx_GruposExistentes.getItemCount()-1);
        cbBx_GruposExistentes.repaint();
    }
    
    public Casilla retornarCasillaLógica(){       
            return casillaCreada;//De esta forma no tengo que estar escogiendo entre que método enviar según la casilla creada 
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField FtxtF_pagoCompra;
    private javax.swing.JFormattedTextField FtxtF_pagoEstancia;
    private javax.swing.JFormattedTextField FtxtF_pagoSimples;
    private javax.swing.JFormattedTextField FtxtF_pagoSofisticadas;
    private javax.swing.JPanel JPn_propiedadesGenerales;
    private javax.swing.JButton btn_aceptarServicioEstacion;
    private javax.swing.JComboBox<String> cbBx_GruposExistentes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lbl_precioSImple;
    private javax.swing.JLabel lbl_precioSofisticada;
    private javax.swing.JLabel lbl_titulo;
    private javax.swing.JTextField txtF_nombreLugar;
    private javax.swing.JTextField txtF_nuevoGrupo;
    // End of variables declaration//GEN-END:variables
}