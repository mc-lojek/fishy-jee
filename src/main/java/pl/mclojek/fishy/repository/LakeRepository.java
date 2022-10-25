package pl.mclojek.fishy.repository;

import pl.mclojek.fishy.common.datastore.DataStore;
import pl.mclojek.fishy.entity.Lake;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Dependent
public class LakeRepository implements Repository<Lake, Long>{
    private final DataStore store;

    @Inject
    public LakeRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Lake> find(Long id) {
        return store.findLakeWithFishes(id);
    }

    @Override
    public List<Lake> findAll() {
        return store.findAllLakes();
    }

    @Override
    public void create(Lake entity) {
        store.createLake(entity);
    }

    @Override
    public void delete(Lake entity) {
        store.removeLake(entity.getId());
    }

    @Override
    public void update(Lake entity) {
        store.updateLake(entity);
    }
}
