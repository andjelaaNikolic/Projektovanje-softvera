/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import forme.KreirajRacunForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Knjiga;
import model.Kupac;
import model.Prodavac;
import model.Racun;
import model.StavkaRacuna;
import model.tabele.ModelTabeleStavkaRacuna;

/**
 *
 * @author Ljilja
 */
public class KreirajRacunController {
    
    private KreirajRacunForma krf;
    private StavkaRacuna selektovanaStavka;
    private List<StavkaRacuna> pronadjeneStavke;
    private Racun pronadjen;

    public KreirajRacunController(KreirajRacunForma krf) {
        this.krf = krf;
        addActionListeners();
    }

    private void addActionListeners() {
        
        krf.kreirajRacunAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               
                String datum=krf.getjTextFieldDatum().getText().trim();
                Kupac kupac = (Kupac) krf.getjComboBoxKupac().getSelectedItem();
                Prodavac prodavac = (Prodavac) krf.getjComboBoxProdavac().getSelectedItem();
                double ukupanIznos = Double.parseDouble(krf.getjTextFieldIznosRacuna().getText().trim());
                List<StavkaRacuna> stavke = new ArrayList<>();
                ModelTabeleStavkaRacuna mtsr = (ModelTabeleStavkaRacuna) krf.getjTableStavke().getModel();
                stavke = mtsr.getStavkeRacuna();
                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
                Date datumSredjen = new Date();
                String danS;
                String mesecS;
                String godinaS;
                int dan;
                int mesec;
                int godina;
                
                if(datum==null || datum.isEmpty()){
                    JOptionPane.showMessageDialog(krf, "Niste uneli datum.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(kupac==null){
                    JOptionPane.showMessageDialog(krf, "Niste izabrali kupca.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(prodavac==null){
                    JOptionPane.showMessageDialog(krf, "Niste izabrali prodavca.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                
                try {

                    String[] datumNiz = datum.split("\\.");
                    danS = datumNiz[0];
                    mesecS = datumNiz[1];
                    godinaS = datumNiz[2];
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(krf, "Niste uneli datum u ispravnom formatu.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    dan = Integer.parseInt(danS);
                    mesec = Integer.parseInt(mesecS);
                    godina = Integer.parseInt(godinaS);
                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(krf, "Dan, mesec i godina se mogu sastojati samo od cifara.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (dan < 1 || dan > 31 || mesec < 1 || mesec > 12) {
                    JOptionPane.showMessageDialog(krf, "Niste uneli datum u ispravnom formatu.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (mesec == 4 || mesec == 6 || mesec == 9 || mesec == 11) {
                    if (dan > 30) {
                        JOptionPane.showMessageDialog(krf, "Uneti mesec ima 30 dana.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
                if (godina % 4 == 0) {
                    if (mesec == 2) {
                        if (dan > 29) {
                            JOptionPane.showMessageDialog(krf, "Februar ima 29 dana u unetoj godini.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                    }
                } else {
                    if (mesec == 2) {
                        if (dan > 28) {
                            JOptionPane.showMessageDialog(krf, "Februar ima 28 dana u unetoj godini.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                    }

                }

                try {
                    if (!datum.trim().isEmpty()) {

                        datumSredjen = format.parse(datum);
                        
                    }
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(krf, "Niste uneli datum u ispravnom formatu.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (datumSredjen.after(new Date())) {
                    JOptionPane.showMessageDialog(krf, "Datum izdavanja racuna ne sme biti datum u buducnosti.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                if(stavke==null || stavke.isEmpty()){
                    JOptionPane.showMessageDialog(krf, "Niste uneli stavke racuna.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if(ukupanIznos<=0){
                    JOptionPane.showMessageDialog(krf, "Ukupan iznos mora biti veci od 0.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                Racun racun = new Racun(datumSredjen, ukupanIznos, prodavac, kupac);
                racun.setStavke(stavke);
                boolean uspesno = Komunikacija.getInstance().kreirajRacun(racun);

                if (uspesno==false) {
                    JOptionPane.showMessageDialog(krf, "Sistem ne može da zapamti racun!.", "Greska!", JOptionPane.WARNING_MESSAGE);
                    krf.dispose();
                } else {

                    JOptionPane.showMessageDialog(krf, "Sistem je zapamtio racun.", "Uspesno!", JOptionPane.INFORMATION_MESSAGE);
                    krf.dispose();
                }
                
                
            }
            
        });
        
        krf.dodajKnjiguAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                StavkaRacuna stavka = null;
                int kolicina=0;
                Knjiga knjiga = (Knjiga) krf.getjComboBoxKnjiga().getSelectedItem();
                if(krf.getjTextFieldKolicina().getText().trim()!=null && !krf.getjTextFieldKolicina().getText().trim().isEmpty()){
                    try{
                 kolicina = Integer.parseInt(krf.getjTextFieldKolicina().getText().trim());
                    }
                    catch(NumberFormatException en){
                       JOptionPane.showMessageDialog(krf, "Kolicina moze samo da bude broj.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return; 
                
                }
                }
                else{
                    JOptionPane.showMessageDialog(krf, "Niste uneli kolicinu.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                
                if(kolicina>20 && kolicina<1){
                    JOptionPane.showMessageDialog(krf, "Mozete izabrati najmanje 1 komad a najvise 20.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                if(knjiga==null){
                    JOptionPane.showMessageDialog(krf, "Niste izabrali knjigu.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                double cena = knjiga.getCena();
                double iznosStavke1 = cena * kolicina;
                double iznosStavke = Math.round(iznosStavke1*100.0)/100.0;
                ModelTabeleStavkaRacuna mtr = (ModelTabeleStavkaRacuna) krf.getjTableStavke().getModel();
                List<StavkaRacuna> stavke = mtr.getStavkeRacuna();
                int rB = stavke.size() + 1;
                stavka = new StavkaRacuna(rB, kolicina, knjiga, iznosStavke, null, knjiga.getCena());
                if (stavke.contains(stavka)) {

                    JOptionPane.showMessageDialog(krf, "Ova stavka je vec dodata.", "Upozorenje", JOptionPane.WARNING_MESSAGE);

                } else {
                    mtr.dodaj(stavka);
                }
                
                
                double ukupanIznos1 = 0;
                for (StavkaRacuna sr : mtr.getStavkeRacuna()) {
                    ukupanIznos1 += sr.getIznos();

                }
                
                double ukupanIznos = Math.round(ukupanIznos1 * 100.0) / 100.0;
                krf.getjTextFieldIznosRacuna().setText(String.valueOf(ukupanIznos));
                
                
                
                
            }
            
        });
        
        krf.odustaniRacunAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int odgovor = JOptionPane.showConfirmDialog(krf, "Da li ste sigurni da zelite da odustanete?", "Potvrda", JOptionPane.YES_NO_OPTION);
                if(odgovor==0){
                krf.dispose();
                }
                else{
                    return;
                }
            }
            
        });
        
        krf.promeniRacunAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int idRacuna = Integer.parseInt(krf.getjTextFieldId().getText().trim());
                Kupac kupac = (Kupac) krf.getjComboBoxKupac().getSelectedItem();
                Prodavac prodavac = (Prodavac) krf.getjComboBoxProdavac().getSelectedItem();
                String datumString = krf.getjTextFieldDatum().getText();
                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
                Date datumSredjen = new Date();
                String danS;
                String mesecS;
                String godinaS;
                int dan;
                int mesec;
                int godina;
                
                if(datumString==null || datumString.isEmpty()){
                    JOptionPane.showMessageDialog(krf, "Niste uneli datum.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                
                
                try {

                    String[] datumNiz = datumString.split("\\.");
                    danS = datumNiz[0];
                    mesecS = datumNiz[1];
                    godinaS = datumNiz[2];
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(krf, "Niste uneli datum u ispravnom formatu.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    dan = Integer.parseInt(danS);
                    mesec = Integer.parseInt(mesecS);
                    godina = Integer.parseInt(godinaS);
                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(krf, "Dan, mesec i godina se mogu sastojati samo od cifara.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (dan < 1 || dan > 31 || mesec < 1 || mesec > 12) {
                    JOptionPane.showMessageDialog(krf, "Niste uneli datum u ispravnom formatu.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (mesec == 4 || mesec == 6 || mesec == 9 || mesec == 11) {
                    if (dan > 30) {
                        JOptionPane.showMessageDialog(krf, "Uneti mesec ima 30 dana.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
                if (godina % 4 == 0) {
                    if (mesec == 2) {
                        if (dan > 29) {
                            JOptionPane.showMessageDialog(krf, "Februar ima 29 dana u unetoj godini.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                    }
                } else {
                    if (mesec == 2) {
                        if (dan > 28) {
                            JOptionPane.showMessageDialog(krf, "Februar ima 28 dana u unetoj godini.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                    }

                }

                try {
                    if (!datumString.trim().isEmpty()) {

                        datumSredjen = format.parse(datumString);
                        
                    }
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(krf, "Niste uneli datum u ispravnom formatu.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (datumSredjen.after(new Date())) {
                    JOptionPane.showMessageDialog(krf, "Datum izdavanja racuna ne sme biti datum u buducnosti.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                
                ModelTabeleStavkaRacuna mtsr = (ModelTabeleStavkaRacuna) krf.getjTableStavke().getModel();
                List<StavkaRacuna> stavke = mtsr.getStavkeRacuna();
               
                double ukupanIznos1 = 0;
                for (StavkaRacuna sr : mtsr.getStavkeRacuna()) {
                    ukupanIznos1 += sr.getIznos();

                }
                
                double ukupanIznos = Math.round(ukupanIznos1 * 100.0) / 100.0;
                krf.getjTextFieldIznosRacuna().setText(String.valueOf(ukupanIznos));
                
                java.sql.Date sqlDatum = new java.sql.Date(datumSredjen.getTime());
                
                Racun racun = new Racun(idRacuna, sqlDatum, ukupanIznos, prodavac, kupac);
                 for (StavkaRacuna sr : stavke) {
                    sr.setRacun(racun);
                }
                racun.setStavke(stavke);
                
                boolean uspesno = Komunikacija.getInstance().promeniRacun(racun);
                if(uspesno==true){
                    JOptionPane.showMessageDialog(krf, "Sistem je zapamtio racun", "Uspesno!", JOptionPane.INFORMATION_MESSAGE);
                    krf.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(krf, "Sistem ne moze da zapamti racun.", "Greska!", JOptionPane.WARNING_MESSAGE);
                    krf.dispose();
                }
                
                
                
                
            }
            
        });
        
        krf.prikaziStavkuAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                krf.getjButtonSacuvajPromeneKnjiga().setEnabled(true);
                krf.getjButtonSacuvajPromeneKnjiga().setVisible(true);
                krf.getjButtonDodajKnjigu().setEnabled(false);
                int red = krf.getjTableStavke().getSelectedRow();
                if(red!=-1){
                    ModelTabeleStavkaRacuna mtsr = (ModelTabeleStavkaRacuna) krf.getjTableStavke().getModel();
                    StavkaRacuna sr = mtsr.getStavkeRacuna().get(red);
                    Knjiga k = sr.getKnjiga();
                    int kolicina = sr.getKolicina();
                    krf.getjComboBoxKnjiga().setSelectedItem(k);
                    krf.getjTextFieldKolicina().setText(kolicina+"");
                    selektovanaStavka = sr; 
            }
                else{
                    JOptionPane.showMessageDialog(krf, "Niste izabrali stavku.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
            
        });
        
        
            krf.sacuvajPromenuStavkeRacunAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //int red = krf.getjTableStavke().getSelectedRow();
                if(selektovanaStavka!=null){
                    //Knjiga k = selektovanaStavka.getKnjiga();
                    //int kolicina = selektovanaStavka.getKolicina();
                    
                    krf.getjButtonDodajKnjigu().setEnabled(true);
                    krf.getjButtonSacuvajPromeneKnjiga().setEnabled(false);
                    Knjiga novaKnjiga = (Knjiga) krf.getjComboBoxKnjiga().getSelectedItem();
                    double cena = novaKnjiga.getCena();
                    int novaKolicina;
                    if(krf.getjTextFieldKolicina().getText().trim()!=null && !krf.getjTextFieldKolicina().getText().trim().isEmpty()){
                        try {
                          novaKolicina = Integer.parseInt(krf.getjTextFieldKolicina().getText().trim());
                        } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(krf, "Unesite ispravnu količinu (samo cifre).", "Greška", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                 else{
                    JOptionPane.showMessageDialog(krf, "Niste uneli kolicinu.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                 }
                
                if(novaKolicina>20 && novaKolicina<1){
                    JOptionPane.showMessageDialog(krf, "Mozete izabrati najmanje 1 komad a najvise 20.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                    double iznos = novaKolicina*cena;
                    selektovanaStavka.setIznos(iznos);
                    selektovanaStavka.setJedinicnaCena(cena);
                    selektovanaStavka.setKnjiga(novaKnjiga);
                    selektovanaStavka.setKolicina(novaKolicina);
                    
                   
                    
                    ((ModelTabeleStavkaRacuna) krf.getjTableStavke().getModel()).refresujPodatke();
                          
                    
            }
                else{
                    JOptionPane.showMessageDialog(krf, "Niste izabrali stavku.", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
            }
        
    });
                
            
            
        krf.promeniDugmeStavkuAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
              krf.getjButtonPromeni().setVisible(false);
                
              krf.getjButtonPromeniRacunDugme().setVisible(true);
              krf.getjButtonPromeniRacunDugme().setEnabled(true);    
              
              krf.getjButtonOdustaniRacun().setVisible(false);
              krf.getjButtonOdustaniRacun().setEnabled(false);
              
              krf.getjButtonOdustani1().setVisible(true);
              krf.getjButtonOdustani1().setEnabled(true);
                
              krf.getjButtonDodajKnjigu().setEnabled(true);
              krf.getjButtonPrikaziStavku().setEnabled(true);
              krf.getjButtonSacuvajPromeneKnjiga().setVisible(true);
              
              krf.getjButtonOdustaniRacun().setVisible(false);
              
              krf.getjTextFieldKolicina().setEnabled(true);
              krf.getjComboBoxKnjiga().setEnabled(true);
              krf.getjComboBoxKupac().setEnabled(true);
              krf.getjComboBoxProdavac().setEnabled(true);
              
              krf.getjTextFieldKolicina().setEditable(true);
              
              krf.getjTextFieldDatum().setEditable(true);
              
              krf.getjTableStavke().setEnabled(true);
                
              krf.getjButtonPromeni().setEnabled(false);
                
            }
            
            
        });
        
        krf.odustani1RacunAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               otvoriFormu(pronadjen);
            }
            
        });
        
   
        
        
        
    
    }
            

    public void otvoriFormu() {
        popuniTabelu();
        popuniComboBoxProdavac();
        popuniComboBoxKupac();
        popuniComboBoxKnjiga();
        
       
        krf.getjButtonPrikaziStavku().setVisible(false);
        krf.getjButtonKreiraj().setVisible(true);
        krf.getjButtonPromeni().setVisible(false);
        krf.getjButtonPromeniRacunDugme().setVisible(false);
        krf.getjButtonSacuvajPromeneKnjiga().setVisible(false);
        krf.getjButtonOdustaniRacun().setVisible(true);
        krf.getjButtonOdustani1().setVisible(false);
        
        krf.getjLabelId().setVisible(false);
        krf.getjTextFieldId().setVisible(false);
        krf.getjTextFieldIznosRacuna().setEditable(false);
        krf.setVisible(true);
    }

    public void otvoriFormu(Racun racun) {
        pronadjen=racun;
       
        popuniTabelu();
        popuniComboBoxProdavac();
        popuniComboBoxKupac();
        popuniComboBoxKnjiga();
        
        krf.getjComboBoxKnjiga().setEnabled(false);
        krf.getjComboBoxKupac().setEnabled(false);
        krf.getjComboBoxProdavac().setEnabled(false);
        krf.getjComboBoxKupac().setSelectedItem(racun.getKupac());
        krf.getjComboBoxProdavac().setSelectedItem(racun.getProdavac());
        
        krf.getjButtonOdustaniRacun().setVisible(true);
        krf.getjButtonOdustaniRacun().setEnabled(true);
        
        krf.getjButtonOdustani1().setVisible(false);
        krf.getjButtonOdustani1().setEnabled(false);
        
        
        krf.getjButtonDodajKnjigu().setEnabled(false);
        krf.getjButtonPromeni().setVisible(true);
        krf.getjButtonPromeni().setEnabled(true);
        krf.getjButtonPromeniRacunDugme().setVisible(false);
        krf.getjButtonPromeniRacunDugme().setEnabled(false);
        krf.getjButtonSacuvajPromeneKnjiga().setVisible(false);
        krf.getjButtonSacuvajPromeneKnjiga().setEnabled(false);
        krf.getjButtonKreiraj().setVisible(false);
        krf.getjButtonPrikaziStavku().setVisible(true);
        krf.getjButtonPrikaziStavku().setEnabled(false);
        
        
        
        krf.getjLabelId().setVisible(true);
        krf.getjTextFieldId().setVisible(true);
        krf.getjTextFieldId().setText(racun.getIdRacun()+"");
        krf.getjTextFieldId().setEditable(false);
        krf.getjTextFieldIznosRacuna().setText(racun.getUkupanIznos()+"");
        krf.getjTextFieldIznosRacuna().setEditable(false);
        krf.getjTextFieldKolicina().setEditable(false);
        
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
        String datumNovi = format.format(racun.getDatum());
        krf.getjTextFieldDatum().setText(datumNovi);
        krf.getjTextFieldDatum().setEditable(false);
        
        List<StavkaRacuna> stavkeRacuna = racun.getStavke();
        ModelTabeleStavkaRacuna mtsr = (ModelTabeleStavkaRacuna) krf.getjTableStavke().getModel();
        mtsr.setStavkeRacuna(stavkeRacuna);
        krf.getjTableStavke().setEnabled(false);
        
        krf.setVisible(true);
        
    }
    
    public void popuniTabelu(){
        ModelTabeleStavkaRacuna mtsr = new ModelTabeleStavkaRacuna(new ArrayList<>());
        krf.getjTableStavke().setModel(mtsr);
        
    }
    
    public void popuniComboBoxProdavac(){
        
        List<Prodavac> prodavci = Komunikacija.getInstance().vratiListuSvihProdavaca();
        krf.getjComboBoxProdavac().removeAllItems();
        for (Prodavac p : prodavci) {
            krf.getjComboBoxProdavac().addItem(p);
        }
        
    }
    
    public void popuniComboBoxKupac(){
        List<Kupac> kupci = Komunikacija.getInstance().vratiListuSviKupac();
        krf.getjComboBoxKupac().removeAllItems();
        for (Kupac k : kupci) {
            krf.getjComboBoxKupac().addItem(k);
        }
    }
    
    public void popuniComboBoxKnjiga(){
        List<Knjiga> knjige = Komunikacija.getInstance().vratiListuSvihKnjiga();
        krf.getjComboBoxKnjiga().removeAllItems();
        for (Knjiga k : knjige) {
            krf.getjComboBoxKnjiga().addItem(k);
        }
    }
    
    
    
}
