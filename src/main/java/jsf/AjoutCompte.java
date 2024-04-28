/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package jsf;

import entity.CompteBancaire;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import service.GestionnaireCompte;

@Named(value = "ajoutCompte")
@ApplicationScoped
public class AjoutCompte {

    private CompteBancaire compte;
    private String nom;
    private int solde;
    private String erreur="";
    @Inject
    private GestionnaireCompte comptemanager;

    public String getErreur() {
        return erreur;
    }

    public void setErreur(String erreur) {
        this.erreur = erreur;
    }
    

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public GestionnaireCompte getComptemanager() {
        return comptemanager;
    }

    public void setComptemanager(GestionnaireCompte comptemanager) {
        this.comptemanager = comptemanager;
    }
    
    public CompteBancaire getCompte() {
        return compte;
    }

    public void setCompte(CompteBancaire compte) {
        this.compte = compte;
    }
    
    public String action(){
        try{
            compte = new CompteBancaire(nom, solde);
            comptemanager.creerCompte(compte);
            Util.addFlashInfoMessage("Création compte effectué");
            return "listeCompte";
                    
        }catch (Exception e){
            erreur = e.getMessage();
            Util.messageErreur("Une erreur s'est produite, merci de réessayer!"+erreur, "Une erreur s'est produite, merci de réessayer"+erreur, "form:error");
            return "null";
        }
    }
    
    public AjoutCompte() {
    }
    
}
