/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsf;

import entity.CompteBancaire;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import service.GestionnaireCompte;

@Named(value = "transaction")
@ApplicationScoped
public class Transaction {
    private int montant;
    private int id;
    private int act;
    private CompteBancaire compte;
    @Inject
    private GestionnaireCompte comptemanager;

    public int getAct() {
        return act;
    }

    public void setAct(int act) {
        this.act = act;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public void setCompte(CompteBancaire compte) {
        this.compte = compte;
    }

    public GestionnaireCompte getComptemanager() {
        return comptemanager;
    }

    public void setComptemanager(GestionnaireCompte comptemanager) {
        this.comptemanager = comptemanager;
    }
    
    public String trans() {
        try{
           if(act==1) {
                comptemanager.depot(compte, montant);
                Util.addFlashInfoMessage("Depôt effectué");
            }if(act==2){
                comptemanager.retirer(compte, montant);
                Util.addFlashInfoMessage("Retrait effectué");
            }
            return "listeCompte"; 
        }catch (Exception e){
            Util.messageErreur("Une erreur s'est produite, merci de réessayer!", "Une erreur s'est produite, merci de réessayer", "form:error");
            return "affichage";
        }
        
    }
    public void loadCompte() {
        this.compte = comptemanager.getcomptebyid(id);
    }

    public Transaction() {
    }
    
    
}
