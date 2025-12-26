/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.knjige;

import java.util.List;
import model.Knjiga;
import sistemske.operacije.OpsteSistemskeOperacije;

/**
 *
 * @author Ljilja
 */
public class SOVratiListuSviKnjiga extends OpsteSistemskeOperacije{
    
    List<Knjiga> knjige;
    Knjiga knjiga;

    @Override
    protected void preduslovi(Object param) throws Exception {
       
    }

    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
       knjige = broker.vratiSve((Knjiga)param, "");
    }

    public List<Knjiga> getKnjige() {
        return knjige;
    }
    
    
    
}
