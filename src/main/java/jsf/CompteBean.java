/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package jsf;

import entity.CompteBancaire;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import service.GestionnaireCompte;

/**
 *
 * @author user
 */
@Named(value = "compteBean")
@ViewScoped
public class CompteBean implements Serializable {
    private List<CompteBancaire> comptelist;
    
    @Inject
    private GestionnaireCompte comptemanager;

    public List<CompteBancaire> getComptelist() {
        if (comptelist == null) {
          comptelist = comptemanager.getAllComptes();
        }
        return comptelist;
    }
    
    
    public CompteBean() { }
    
}
