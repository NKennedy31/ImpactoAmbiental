/*package Repositorios;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import javax.persistence.Entity;
import java.util.List;

public abstract class Repositorio<T> implements WithGlobalEntityManager {
    private Repositorio() {}

    public void agregar(T obj) {
      entityManager().persist(obj);
    }

    public T merge(T obj) {
        return entityManager().merge(obj);
    }

    public List<T> listar() {
        String query = "FROM " + getEntityName() + " e";
        return entityManager().createQuery(query, getEntityClass())
                .getResultList();
    }

    public T buscar(long id) {
        return entityManager().find(getEntityClass(), id);
    }

    public abstract Class<T> getEntityClass();

    protected String getEntityName() {
        return getEntityClass()
                .getAnnotation(Entity.class)
                .name();
    }
}*/