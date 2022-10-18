package pl.mclojek.fishy.repository;

import lombok.AllArgsConstructor;
import pl.mclojek.fishy.common.datastore.DataStore;
import pl.mclojek.fishy.entity.User;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Dependent
public class UserRepository implements Repository<User, Long> {

    private DataStore store;

    @Inject
    public UserRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<User> find(Long id) {
        return store.findUser(id);
    }

    @Override
    public List<User> findAll() {
        return store.findAllUsers();
    }

    @Override
    public void create(User entity) {
        store.createUser(entity);
    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void update(User entity) {

    }
}
