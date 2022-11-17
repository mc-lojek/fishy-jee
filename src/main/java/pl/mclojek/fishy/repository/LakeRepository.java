package pl.mclojek.fishy.repository;

import pl.mclojek.fishy.common.datastore.DataStore;
import pl.mclojek.fishy.entity.Lake;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@RequestScoped
public class LakeRepository implements Repository<Lake, Long>{

    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Lake> find(Long id) {
        return Optional.ofNullable(em.find(Lake.class, id));
    }

    @Override
    public List<Lake> findAll() {
        return em.createQuery("select l from Lake l", Lake.class).getResultList();
    }

    @Override
    public void create(Lake entity) {
        em.persist(entity);
    }

    @Override
    public void delete(Lake entity) {
        em.remove(em.find(Lake.class, entity.getName()));
    }

    @Override
    public void update(Lake entity) {
        em.merge(entity);
    }

    @Override
    public void detach(Lake entity) {
        em.detach(entity);
    }


}
