/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontendBancopoly;

import java.io.Serializable;

/**
 *
 * @author phily
 */
public class tarjetaCompraPropiedad extends javax.swing.JDialog implements Serializable{
    private boolean seCompro;
    /**
     * Creates new form dialogoComun
     */
    public tarjetaCompraPropiedad(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbl_espacionombrePropiedad = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbl_espacioPrecioSencilla = new javax.swing.JLabel();
        lbl_espacioPrecioSofisticada = new javax.swing.JLabel();
        btn_comprar = new javax.swing.JButton();
        btn_dejarIr = new javax.swing.JButton();
        lbl_espacioPrecioPropiedad = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(334, 405));
        setMinimumSize(new java.awt.Dimension(334, 405));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(211, 211, 204));

        lbl_espacionombrePropiedad.setText(" ");
        lbl_espacionombrePropiedad.setOpaque(true);

        jLabel1.setFont(new java.awt.Font("Sawasdee", 1, 18)); // NOI18N
        jLabel1.setText("Precio $:");

        jLabel2.setText("-> Precio por construcción");

        jLabel3.setText("Sencilla $:");

        jLabel4.setText("Sofisticada $:");

        lbl_espacioPrecioSencilla.setText("jLabel5");

        lbl_espacioPrecioSofisticada.setText("jLabel6");

        btn_comprar.setText("Comprar");
        btn_comprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_comprarActionPerformed(evt);
            }
        });

        btn_dejarIr.setText("Dejar ir");
        btn_dejarIr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dejarIrActionPerformed(evt);
            }
        });

        lbl_espacioPrecioPropiedad.setText(" ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl_espacionombrePropiedad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(40, 40, 40)
                                .addComponent(lbl_espacioPrecioPropiedad, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(32, 32, 32)
                                .addComponent(lbl_espacioPrecioSofisticada))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbl_espacioPrecioSencilla)))))
                .addContainerGap(71, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_comprar)
                .addGap(18, 18, 18)
                .addComponent(btn_dejarIr)
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lbl_espacionombrePropiedad, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbl_espacioPrecioPropiedad, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lbl_espacioPrecioSencilla))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_espacioPrecioSofisticada)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_comprar)
                    .addComponent(btn_dejarIr))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_comprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_comprarActionPerformed
        seCompro=true;
        this.dispose();
    }//GEN-LAST:event_btn_comprarActionPerformed

    private void btn_dejarIrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dejarIrActionPerformed
        seCompro=false;
        this.dispose();
    }//GEN-LAST:event_btn_dejarIrActionPerformed

    public boolean retornarDecision(){
        return seCompro;
    }
    
    public void establecerDatosPropiedad(String nombre, int precio, int precioSimples, int precoSofisticadas){
        lbl_espacionombrePropiedad.setText(nombre);
        lbl_espacioPrecioPropiedad.setText(String.valueOf(precio));
        lbl_espacioPrecioSencilla.setText(String.valueOf(precioSimples));
        lbl_espacioPrecioSofisticada.setText(String.valueOf(precoSofisticadas));
    }
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_comprar;
    private javax.swing.JButton btn_dejarIr;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_espacioPrecioPropiedad;
    private javax.swing.JLabel lbl_espacioPrecioSencilla;
    private javax.swing.JLabel lbl_espacioPrecioSofisticada;
    private javax.swing.JLabel lbl_espacionombrePropiedad;
    // End of variables declaration//GEN-END:variables
}
