package forme;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Ljilja
 */
public class KreirajKnjiguForma extends javax.swing.JFrame {


    public KreirajKnjiguForma() {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Kreiraj knjigu");
        dizajn();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldNaslov = new javax.swing.JTextField();
        jTextFieldZanr = new javax.swing.JTextField();
        jTextFieldGodinaIzdanja = new javax.swing.JTextField();
        jTextFieldCena = new javax.swing.JTextField();
        jButtonOdustani = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButtonPromeniKnjigu = new javax.swing.JButton();
        jButtonObrisi = new javax.swing.JButton();
        jLabelID = new javax.swing.JLabel();
        jTextFieldId = new javax.swing.JTextField();
        jButtonPromeniDugme = new javax.swing.JButton();
        jButtonOdustani1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(238, 217, 183));
        setMaximumSize(new java.awt.Dimension(540, 370));
        setMinimumSize(new java.awt.Dimension(540, 370));
        setPreferredSize(new java.awt.Dimension(540, 370));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Naslov:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 110, 27));

        jLabel2.setText("Zanr:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 100, 41));

        jLabel3.setText("Godina izdanja:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 140, -1));

        jLabel4.setText("Cena:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 100, -1));
        getContentPane().add(jTextFieldNaslov, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 210, -1));
        getContentPane().add(jTextFieldZanr, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 210, -1));
        getContentPane().add(jTextFieldGodinaIzdanja, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 210, -1));
        getContentPane().add(jTextFieldCena, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, 210, -1));

        jButtonOdustani.setText("Odustani");
        getContentPane().add(jButtonOdustani, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 270, 130, 30));

        jButton1.setText("Kreiraj");
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 130, 30));

        jButtonPromeniKnjigu.setText("Sacuvaj promene");
        jButtonPromeniKnjigu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPromeniKnjiguActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonPromeniKnjigu, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, 190, 30));

        jButtonObrisi.setText("Obrisi");
        getContentPane().add(jButtonObrisi, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 130, 30));

        jLabelID.setText("Id:");
        getContentPane().add(jLabelID, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 80, 40));
        getContentPane().add(jTextFieldId, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 210, -1));

        jButtonPromeniDugme.setText("Promeni");
        getContentPane().add(jButtonPromeniDugme, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 313, 190, 30));

        jButtonOdustani1.setText("Odustani");
        getContentPane().add(jButtonOdustani1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 270, 130, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPromeniKnjiguActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPromeniKnjiguActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonPromeniKnjiguActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonObrisi;
    private javax.swing.JButton jButtonOdustani;
    private javax.swing.JButton jButtonOdustani1;
    private javax.swing.JButton jButtonPromeniDugme;
    private javax.swing.JButton jButtonPromeniKnjigu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelID;
    private javax.swing.JTextField jTextFieldCena;
    private javax.swing.JTextField jTextFieldGodinaIzdanja;
    private javax.swing.JTextField jTextFieldId;
    private javax.swing.JTextField jTextFieldNaslov;
    private javax.swing.JTextField jTextFieldZanr;
    // End of variables declaration//GEN-END:variables

    public JButton getjButton1() {
        return jButton1;
    }

    public JTextField getjTextFieldCena() {
        return jTextFieldCena;
    }

    public JTextField getjTextFieldGodinaIzdanja() {
        return jTextFieldGodinaIzdanja;
    }

    public JTextField getjTextFieldNaslov() {
        return jTextFieldNaslov;
    }

    public JTextField getjTextFieldZanr() {
        return jTextFieldZanr;
    }
    
    public void kreirajKnjiguAddActionListener(ActionListener actionListener){
        jButton1.addActionListener(actionListener);
    }
    
    public void odustaniAddActionListener(ActionListener actionListener){
        jButtonOdustani.addActionListener(actionListener);
    }
  public void obrisiAddActionListener(ActionListener actionListener){
        jButtonObrisi.addActionListener(actionListener);
    }
  
    public void promeniAddActionListener(ActionListener actionListener){
        jButtonPromeniKnjigu.addActionListener(actionListener);
    }
    
    public void odustani1AddActionListener(ActionListener actionListener){
        jButtonOdustani1.addActionListener(actionListener);
    }
    
    public void promeniDugmeAddActionListener(ActionListener actionListener){
        jButtonPromeniDugme.addActionListener(actionListener);
    }

    public JButton getjButtonObrisi() {
        return jButtonObrisi;
    }

    public JButton getjButtonOdustani() {
        return jButtonOdustani;
    }

    public JButton getjButtonPotvrdi() {
        return jButtonPromeniKnjigu;
    }

    public JLabel getjLabelID() {
        return jLabelID;
    }

    public JTextField getjTextFieldId() {
        return jTextFieldId;
    }

    public JButton getjButtonOdustani1() {
        return jButtonOdustani1;
    }

    public JButton getjButtonPromeniDugme() {
        return jButtonPromeniDugme;
    }

    public JButton getjButtonPromeniKnjigu() {
        return jButtonPromeniKnjigu;
    }

    private void dizajn() {
        // Fontovi
        Font labelFont = new Font("Times New Roman", Font.BOLD, 18);
        Font textFont = new Font("Times New Roman", Font.BOLD, 18);
        Font dugmeFont = new Font("Times New Roman", Font.BOLD, 18);

        // Pozadina forme
        getContentPane().setBackground(new java.awt.Color(245, 245, 245)); // svetlo siva
        setSize(520, 400);
        setResizable(false);
        setLocationRelativeTo(null);

        // Labele
        JLabel[] labele = { jLabel1, jLabel2, jLabel3, jLabel4, jLabelID };
        java.awt.Color labelColor = new java.awt.Color(46, 78, 31); // tamno zelena
        for (JLabel lbl : labele) {
            lbl.setFont(labelFont);
            lbl.setForeground(labelColor);
        }

        // Tekstualna polja
        JTextField[] polja = { jTextFieldCena, jTextFieldGodinaIzdanja, jTextFieldId, jTextFieldNaslov, jTextFieldZanr };
        java.awt.Color inputTextColor = new java.awt.Color(43, 43, 43); // tamno siva
        java.awt.Color inputBg = Color.WHITE;
        java.awt.Color disabledBg = new java.awt.Color(220, 220, 220); // svetlo siva

       Color inputDisabledBg = new Color(200, 230, 200);

        for (JTextField tf : polja) {
            tf.setFont(textFont);
            tf.setBorder(BorderFactory.createLineBorder(new java.awt.Color(46, 78, 31), 2));
            
           /*if(tf.isEditable()) {
                tf.setBackground(inputBg);
                tf.setForeground(inputTextColor);
*//*
            } else {
                tf.setBackground(inputDisabledBg);
                tf.setForeground(inputTextColor.brighter()); 
            }*/
             
            
        }

        // Dugmad
        JButton[] buttons = { jButton1, jButtonObrisi, jButtonOdustani, jButtonOdustani1, jButtonPromeniDugme, jButtonPromeniKnjigu };
        java.awt.Color dugmeEnabledBg = new java.awt.Color(46, 78, 31); // tamna zelena
        java.awt.Color dugmeDisabledBg = new java.awt.Color(180, 180, 180); // svetlija za disabled
        java.awt.Color dugmeEnabledFg = Color.WHITE;
        java.awt.Color dugmeDisabledFg = new java.awt.Color(80, 80, 80); // tamno siva

        for (JButton btn : buttons) {
            btn.setFont(dugmeFont);
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);

            if(btn.isEnabled()) {
                btn.setBackground(dugmeEnabledBg);
                btn.setForeground(dugmeEnabledFg);
            } else {
                btn.setBackground(dugmeDisabledBg);
                btn.setForeground(dugmeDisabledFg);
            }
    }
}

        
      
       


    
    

}
