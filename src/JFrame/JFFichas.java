/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JFrame;

import javax.swing.ImageIcon;

/**
 *
 * @author Coqui
 */
public class JFFichas extends javax.swing.JFrame {
    
    
    public ImageIcon[] fichas = new ImageIcon[2];
    public ImageIcon[] fichas1 = new ImageIcon[2];
    public ImageIcon[] fichas2 = new ImageIcon[2];
    public ImageIcon[] fichas3 = new ImageIcon[2];
    public ImageIcon[] fichas4 = new ImageIcon[2];
    

    /**
     * Creates new form JFFichas
     */
    public JFFichas() {
        initComponents();
        
        fichas1[0] = new ImageIcon(getClass().getResource("/ImgProyecto/circulo.png"));
        fichas1[1] = new ImageIcon(getClass().getResource("/ImgProyecto/cruz.png"));
        
        fichas2[0] = new ImageIcon(getClass().getResource("/ImgProyecto/disco1.png"));
        fichas2[1] = new ImageIcon(getClass().getResource("/ImgProyecto/disco2.png"));
        
        fichas3[0] = new ImageIcon(getClass().getResource("/ImgProyecto/windows.png"));
        fichas3[1] = new ImageIcon(getClass().getResource("/ImgProyecto/ubunto.png"));
        
        fichas4[0] = new ImageIcon(getClass().getResource("/ImgProyecto/facebook.png"));
        fichas4[1] = new ImageIcon(getClass().getResource("/ImgProyecto/twitter.png"));

        fichas[0] = fichas1[0];
        fichas[1] = fichas1[1];
        
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
        cboFicha = new javax.swing.JComboBox();
        lblFicha1 = new javax.swing.JLabel();
        lblFicha2 = new javax.swing.JLabel();
        lblFicha1s = new javax.swing.JLabel();
        lblFicha2s = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleccionar Fichas"));

        cboFicha.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Predeterminado", "Discos", "Windows vs Ubunto", "Facebook vs Twitter" }));
        cboFicha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboFichaActionPerformed(evt);
            }
        });

        lblFicha1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblFicha2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblFicha1s.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFicha1s.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImgProyecto/circulo.png"))); // NOI18N

        lblFicha2s.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImgProyecto/cruz.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboFicha, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblFicha1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFicha1s)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblFicha2s)
                        .addGap(30, 30, 30)
                        .addComponent(lblFicha2)))
                .addGap(10, 10, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboFicha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblFicha1)
                    .addComponent(lblFicha2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFicha2s)
                    .addComponent(lblFicha1s))
                .addGap(0, 16, Short.MAX_VALUE))
        );

        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImgProyecto/Aceptar1.png"))); // NOI18N
        btnCancel.setText("Aceptar");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCancel)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancel)
                .addGap(72, 72, 72))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboFichaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboFichaActionPerformed
        int indice = cboFicha.getSelectedIndex();

        if (indice == 0) {
            fichas[0] = fichas1[0];
            fichas[1] = fichas1[1];
        } else if (indice == 1) {
            fichas[0] = fichas2[0];
            fichas[1] = fichas2[1];
        } else if (indice == 2) {
            fichas[0] = fichas3[0];
            fichas[1] = fichas3[1];
        } else if (indice == 3) {
            fichas[0] = fichas4[0];
            fichas[1] = fichas4[1];
        }

        lblFicha1s.setIcon(fichas[0]);
        lblFicha2s.setIcon(fichas[1]);
    }//GEN-LAST:event_cboFichaActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFFichas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFFichas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFFichas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFFichas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new JFFichas().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JComboBox cboFicha;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblFicha1;
    private javax.swing.JLabel lblFicha1s;
    private javax.swing.JLabel lblFicha2;
    private javax.swing.JLabel lblFicha2s;
    // End of variables declaration//GEN-END:variables
}
