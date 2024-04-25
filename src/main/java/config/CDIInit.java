package config;

import entity.CompteBancaire;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.transaction.Transactional;
import service.GestionnaireCompte;

@ApplicationScoped
public class CDIInit {
    @Inject
    GestionnaireCompte users;
    @Transactional
    public void init(
        @Observes 
        @Initialized(ApplicationScoped.class) 
        ServletContext context) {
        users.creerCompte(new CompteBancaire("John Lennon", 150000 ));
        users.creerCompte(new CompteBancaire("Paul McCartney", 950000 ));
        users.creerCompte(new CompteBancaire("Ringo Starr", 20000 ));
        users.creerCompte(new CompteBancaire("Georges Harrisson", 100000 ));
    }
}
