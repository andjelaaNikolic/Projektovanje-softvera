/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import forme.GlavnaForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import komunikacija.Komunikacija;
import koordinator.Koordinator;
import model.Prodavac;

/**
 *
 * @author Ljilja
 */
public class GlavnaFormaController {
    private final GlavnaForma gf;

    public GlavnaFormaController(GlavnaForma gf) {
        this.gf = gf;
        addActionListeners();
    }

    private void addActionListeners() {
        gf.odjavaProdavcaAddActionListener(new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent e) {
                Komunikacija.getInstance().odjaviProdavca(Koordinator.getInstance().getUlogovaniProdavac());
                System.out.println("Odjavljeni ste");
                gf.dispose();
                
            }
            
        });
        
        
    }

    public void otvoriFormu() {
        Prodavac p = koordinator.Koordinator.getInstance().getUlogovaniProdavac();
        String ulogovani = p.getIme()+" "+p.getPrezime();
        gf.getjLabel2().setText(ulogovani);
       gf.setVisible(true);
    }
    
    
    
}
