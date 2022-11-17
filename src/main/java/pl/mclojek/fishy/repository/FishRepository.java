package pl.mclojek.fishy.repository;

import pl.mclojek.fishy.common.datastore.DataStore;
import pl.mclojek.fishy.entity.Fish;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@RequestScoped
public class FishRepository implements Repository<Fish, Long> {

    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Fish> find(Long id) {
        return Optional.ofNullable(em.find(Fish.class, id));
    }

    @Override
    public List<Fish> findAll() {
        return em.createQuery("select f from Fish f", Fish.class).getResultList();
    }

    @Override
    public void create(Fish entity) {
        em.persist(entity);
    }

    @Override
    public void delete(Fish entity) {
        em.remove(em.find(Fish.class, entity.getId()));
    }

    @Override
    public void update(Fish entity) {
        em.merge(entity);
    }

    @Override
    public void detach(Fish entity) {
        em.detach(entity);
    }
}
