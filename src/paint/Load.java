/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint;

/**
 *
 * @author rayan
 */
public class Load extends javax.swing.JPanel {

    /**
     * Creates new form Load
     */
    PaintWindow myMaster;

    public Load(PaintWindow myMaster) {
        initComponents();
        this.myMaster = myMaster;
        loadFileChooser.setApproveButtonText("Load");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loadFileChooser = new javax.swing.JFileChooser();

        loadFileChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadFileChooserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(loadFileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(loadFileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void loadFileChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadFileChooserActionPerformed
        // TODO add your handling code here:

        if (evt.getActionCommand().equals("ApproveSelection")) {
            // They didn't press cancel
            myMaster.loadImage(loadFileChooser.getSelectedFile().getAbsoluteFile());
        }
        myMaster.frameLoad.setVisible(false);
    }//GEN-LAST:event_loadFileChooserActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser loadFileChooser;
    // End of variables declaration//GEN-END:variables
}
