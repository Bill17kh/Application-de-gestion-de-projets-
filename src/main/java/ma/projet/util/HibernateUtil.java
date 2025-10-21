package ma.projet.util;

import ma.projet.classes.Employe;
import ma.projet.classes.EmployeTache;
import ma.projet.classes.EmployeTacheId;
import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Properties props = new Properties();
                try (InputStream in = HibernateUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
                    if (in != null) props.load(in);
                } catch (IOException e) {
                    throw new RuntimeException("Impossible de charger application.properties", e);
                }

                Configuration configuration = new Configuration();
                configuration.setProperties(props);

                configuration.addAnnotatedClass(Employe.class);
                configuration.addAnnotatedClass(Projet.class);
                configuration.addAnnotatedClass(Tache.class);
                configuration.addAnnotatedClass(EmployeTache.class);
                configuration.addAnnotatedClass(EmployeTacheId.class);

                ServiceRegistry serviceRegistry =
                        new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Erreur d'initialisation de Hibernate", e);
            }
        }
        return sessionFactory;
    }
}
