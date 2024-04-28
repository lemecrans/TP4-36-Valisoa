/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entity.CompteBancaire;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import java.util.List;

@DataSourceDefinition (
    className="com.mysql.cj.jdbc.MysqlDataSource",
    name="java:app/jdbc/banque",
    serverName="localhost",
    portNumber=3306,
    user="root",              // nom et
    password="root", // mot de passe que vous avez donnés lors de la création de la base de données
    databaseName="banque",
    properties = {
      "useSSL=false",
      "allowPublicKeyRetrieval=true",
      "driverClass=com.mysql.cj.jdbc.Driver",
      "serverTimezone=UTC",
      "useJDBCCompliantTimezoneShift=true",
      "useLegacyDatetimeCode=false"
    }
)
@ApplicationScoped
public class GestionnaireCompte {
    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    @Transactional
    public void persist(CompteBancaire c) {
      em.persist(c);
    }
    public void creerCompte(CompteBancaire c) {
        persist(c);
    }
    public List<CompteBancaire> getAllComptes() {
       Query query = em.createNamedQuery("CompteBancaire.findAll");
       return query.getResultList();
    }
    public CompteBancaire getcomptebyid( int id) {
       return em.find(CompteBancaire.class, id); 
    }
    @Transactional
    public void transfert(CompteBancaire othercompte, CompteBancaire comptesource,int montant){
        try{
            othercompte.deposer(montant);
            comptesource.retirer(montant);
            em.merge(othercompte);
            em.merge(comptesource);
        }catch (Exception e){
            throw new RuntimeException("Erreur lors de la transaction : " + e.getMessage());
        }
        
    }

    public GestionnaireCompte() {
    }
}
