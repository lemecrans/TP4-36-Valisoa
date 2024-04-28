/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package jsf;

import entity.CompteBancaire;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import service.GestionnaireCompte;

/**
 *
 * @author user
 */
@Named(value = "compteBean")
@ApplicationScoped
public class CompteBean implements Serializable {
    private CompteBancaire cb;
    private List<CompteBancaire> comptelist;
    
    @Inject
    private GestionnaireCompte comptemanager;

    public CompteBancaire getCb() {
        return cb;
    }

    public void setCb(CompteBancaire cb) {
        this.cb = cb;
    }
    
    public List<CompteBancaire> getComptelist() {
        if (comptelist == null) {
          comptelist = comptemanager.getAllComptes();
        }
        return comptelist;
    }
    public String drop(CompteBancaire cbs) {
        comptemanager.drop(cbs);
        return "index";
        
    }
    
    
    public CompteBean() { }
    
}
