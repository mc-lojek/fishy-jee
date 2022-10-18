package pl.mclojek.fishy.service;

import lombok.NoArgsConstructor;
import pl.mclojek.fishy.entity.User;
import pl.mclojek.fishy.repository.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@NoArgsConstructor
public class UserService {

    private UserRepository repository;

    @Inject
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public Optional<User> find(Long id) {
        return repository.find(id);
    }

    public void create(User user) {
        repository.create(user);
    }
}
