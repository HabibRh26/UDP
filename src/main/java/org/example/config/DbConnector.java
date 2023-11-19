package org.example.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class DbConnector {
    Map<String, String> props;
    EntityManager em;

    public DbConnector() {
        Map<String, String> props = new HashMap<>();
        // props.put("hibernate.show_sql", "false");
        props.put("hibernate.hbm2ddl.auto", "update");
        EntityManagerFactory emf =
                new HibernatePersistenceProvider()
                        .createContainerEntityManagerFactory(new CustomPersistanceUnitInfo(), props);
        this.em = emf.createEntityManager();
        this.getEm().getTransaction().begin();
    }

    public EntityManager getEm() {
        return this.em;
    }

}
