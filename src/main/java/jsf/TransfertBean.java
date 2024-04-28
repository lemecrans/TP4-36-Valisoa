/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package jsf;

import entity.CompteBancaire;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.io.Serializable;
import service.GestionnaireCompte;

/**
 *
 * @author user
 */
@Named(value = "transfertBean")
@ApplicationScoped
public class TransfertBean implements Serializable {

    private String response;
    private int idcomptes;
    private int idcompted;
    private int montant;
    private CompteBancaire comptesource;
    private CompteBancaire othercompte;
    @Inject
    private GestionnaireCompte comptemanager;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getIdcomptes() {
        return idcomptes;
    }

    public void setIdcomptes(int idcomptes) {
        this.idcomptes = idcomptes;
    }

    public int getIdcompted() {
        return idcompted;
    }

    public void setIdcompted(int idcompted) {
        this.idcompted = idcompted;
    }

    public CompteBancaire getComptesource() {
        return comptesource;
    }

    public void setComptesource(CompteBancaire comptesource) {
        this.comptesource = comptesource;
    }
    
    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public CompteBancaire getOthercompte() {
        return othercompte;
    }

    public void setOthercompte(CompteBancaire othercompte) {
        this.othercompte = othercompte;
    }
    
    public String transfereo() {
        boolean erreur = false;
        try{
            othercompte = comptemanager.getcomptebyid(idcompted);
            comptesource = comptemanager.getcomptebyid(idcomptes);
            if (othercompte == null  ) {
                Util.messageErreur("Aucun compte avec cet id !"+idcompted, "Aucun compte avec cet id !", "form:idcompted");
                erreur = true;
            }
            if (comptesource == null  ) {
                Util.messageErreur("Aucun compte avec cet id !"+idcomptes, "Aucun compte avec cet id !", "form:idcomptes");
                erreur = true;
            }
            if (erreur) { // en cas d'erreur, rester sur la même page
              return null;
            }
            comptemanager.transfert(othercompte, comptesource, montant);
            response="Transaction Done";
            Util.addFlashInfoMessage("Transfert correctement effectué");
            montant=0;
            idcompted =0;
            idcomptes =0;
            comptesource=null;
            return "listeCompte";
            //return "affichage";
                    
        }catch (Exception e){
            response="Transaction Error";
            Util.messageErreur("Une erreur s'est produite, merci de réessayer!", "Une erreur s'est produite, merci de réessayer", "form:error");
            throw new RuntimeException("Erreur lors de la transaction : " + e.getMessage());
        }
    }
    public TransfertBean() {
    }   
}
