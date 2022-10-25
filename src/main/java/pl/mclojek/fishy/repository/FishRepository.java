package pl.mclojek.fishy.repository;

import pl.mclojek.fishy.common.datastore.DataStore;
import pl.mclojek.fishy.entity.Fish;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Dependent
public class FishRepository implements Repository<Fish, Long> {

    private final DataStore store;

    @Inject
    public FishRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Fish> find(Long id) {
        return store.findFish(id);
    }

    @Override
    public List<Fish> findAll() {
        return store.findAllFishes();
    }

    @Override
    public void create(Fish entity) {
        store.createFish(entity);
    }

    @Override
    public void delete(Fish entity) {
        store.removeFish(entity.getId());
    }

    @Override
    public void update(Fish entity) {
        store.updateFish(entity);
    }
}
