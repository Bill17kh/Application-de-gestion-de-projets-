package ma.projet.service;

import ma.projet.classes.EmployeTache;

public class EmployeTacheService extends ServiceBase<EmployeTache> {
    @Override protected Class<EmployeTache> getEntityClass() { return EmployeTache.class; }
}
