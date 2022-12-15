/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.pspro.vista;

import com.mycompany.pspro.dll.DAOHandler;
import com.mycompany.pspro.interfaces.IDAOHandler;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nitro
 */
public class MiVentana extends javax.swing.JFrame {
    
    private IDAOHandler dao;
    private JPanel panelActivo;
    //private PanelVentana panelVentana;
    
    /**
     * Creates new form MiVentana
     */
    public MiVentana() {
        initComponents();
        
        this.setTitle("Examen - Pablo Miguel del Castillo Barba 2ºDAM");
        this.dao = DAOHandler.getInstance();
        
        //this.panelVentana = new PanelVentana();
        //panelActivo = panelVentana;
        this.getContentPane().add(panelActivo);
        panelActivo.setSize(this.getSize());
        panelActivo.setVisible(true);
        panelActivo.updateUI();
        
    }

    public IDAOHandler getDao() {
        return dao;
    }

    public void setDao(IDAOHandler dao) {
        this.dao = dao;
    }
    
    public void cambiarPanel(JPanel panel) {

        panelActivo.setVisible(false);
        this.getContentPane().remove(panelActivo);
        panelActivo = panel;
        this.getContentPane().add(panelActivo);
        panelActivo.setSize(this.getSize());
        panelActivo.setVisible(true);
        panelActivo.updateUI();
        
    }
    
    public void cargarTabla(JTable tablaVista) {
        ArrayList<Object> listaObjetos = dao.getObjects();

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Marca");

        model.addColumn("Modelo");

        model.addColumn("Matricula");

        Object[] registroLeido = new Object[model.getColumnCount()];

        for (Object obj : listaObjetos) {

            //registroLeido[0] = obj.getCampo1();

            //registroLeido[1] = obj.getCampo2();

            //registroLeido[2] = obj.getCampo3();

            model.addRow(registroLeido);

        }

        tablaVista.setModel(model);
    }
    
    private void cargarLista(JPanel panel, JList listaVista) {
        ArrayList<Object> listaObjetos = dao.getObjects();

        DefaultListModel modelo = new DefaultListModel();

        for (int i = 0; i < listaObjetos.size(); i++) {
            //modelo.addElement(listaObjetos.get(i).getCampo1());
        }
        
        listaVista.setModel(modelo);

        panel.updateUI();
    }
    
    private void cargarComboBox(JPanel panel, JComboBox comboBoxVista) {
        ArrayList<Object> listaObjetos = dao.getObjects();

        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel();

        for (int i = 0; i < listaObjetos.size(); i++) {
            //modelo.addElement(listaObjetos.get(i).getCampo1());
        }
        
        comboBoxVista.setModel(modelo);

        panel.updateUI();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 831, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 492, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MiVentana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MiVentana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MiVentana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MiVentana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MiVentana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
